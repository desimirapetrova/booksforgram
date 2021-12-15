package com.example.booksforgram.web;

import com.example.booksforgram.model.binding.BookUpdateBindingModel;
import com.example.booksforgram.model.binding.EditUserBindingModel;
import com.example.booksforgram.model.binding.LoginBindingModel;
import com.example.booksforgram.model.binding.RegisterBindingModel;
import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.Order;
import com.example.booksforgram.model.entity.Role;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.entity.enums.UserRoleEnum;
import com.example.booksforgram.model.service.BookUpdateServiceModel;
import com.example.booksforgram.model.service.UserEditServiceModel;
import com.example.booksforgram.model.service.UserServiceModel;
import com.example.booksforgram.service.BookService;
import com.example.booksforgram.service.EmailService;
import com.example.booksforgram.service.OrderService;
import com.example.booksforgram.service.UserService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.apache.tomcat.util.http.parser.Authorization;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller

public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BookService bookService;
    private final OrderService orderService;
    public UserController(UserService userService, ModelMapper modelMapper, BookService bookService, OrderService orderService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
        this.orderService = orderService;
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/register")
    public String registerConfirm(@Valid RegisterBindingModel registerBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !registerBindingModel.getPassword()
                .equals(registerBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("registerBindingModel", registerBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerBindingModel", bindingResult);

            return "redirect:register";
        }

//        userService.registerUser(modelMapper
//                .map(registerBindingModel, UserServiceModel.class));

        userService.registerAndLoginUser(modelMapper
                .map(registerBindingModel, UserServiceModel.class));

        return "redirect:/";

    }

    @GetMapping("/login")
    public String login(Model model,Principal principal) {
        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }
//        userService.activate(principal);
        return "login";
    }
    @PostMapping("/login-error")
    public String failedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                    String userName,
            RedirectAttributes attributes
    ) {

        attributes.addFlashAttribute("bad_credentials", true);
        attributes.addFlashAttribute("username", userName);

        return "redirect:/login";
    }

//    @PostMapping("/login")
//    public String loginConfirm(@Valid LoginBindingModel loginBindingModel, BindingResult bindingResult,
//                               RedirectAttributes redirectAttributes,Principal principal) {
//
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("loginBindingModel", loginBindingModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginBindingModel", bindingResult);
//
//            return "redirect:login";
//        }
//
//        UserServiceModel userServiceModel = userService
//                .findByUsernameAndPassword(loginBindingModel.getUsername(), loginBindingModel.getPassword());
//
//
//        if (userServiceModel == null) {
//            redirectAttributes.addFlashAttribute("loginBindingModel", loginBindingModel);
//            redirectAttributes.addFlashAttribute("isFound", false);
//            return "redirect:login";
//        }
//
//        return "redirect:/";
//    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession,Principal principal) {
        httpSession.invalidate();
//        userService.deactivate(principal);
        return "redirect:/";
    }
    @ModelAttribute
    public RegisterBindingModel registerBindingModel(){
        return new RegisterBindingModel();
    }
    @ModelAttribute
    public LoginBindingModel loginBindingModel(){
        return new LoginBindingModel();
    }

    @GetMapping("/profile")
    public String profile( Model model,Principal principal){
        User userName=userService.findByUsername(principal.getName());
        model.addAttribute("user",userService.findByUsername(principal.getName()));

        List<Book> books = bookService.findAllByOwner(userName);
        List<Order> orders=orderService.findAll();
       model.addAttribute("allmyBooks",books);
        model.addAttribute("myorders",orderService.findByBuyer(principal.getName()));
        model.addAttribute("allOrders", orderService.findAll());
        model.addAttribute("myorderedbooks",orders.stream().map(order -> order.getBookList()).collect(Collectors.toList()));
        return "profile";
    }
    @GetMapping("/profileUser/{id}")
    public String profileId( @PathVariable Long id,Model model,Principal principal){
        User userName=userService.findById(id);
        model.addAttribute("user",userName);
        model.addAttribute("books",bookService.findBookById(id));

        return "profileUser";
    }
    @GetMapping("/profile/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model,
                            Principal principal) {
        model.addAttribute("user", userService.findById(id));
        User user=userService.findById(id);
        User currentUser=userService.findByUsername(principal.getName());

//        if(!user.getUsername().equals(principal.getName())
//        ){
//            throw new com.example.booksforgram.web.exception.ObjectNotFoundException("Not found",id);
//        }
        if(!user.getUsername().equals(principal.getName())){
        currentUser.getRoles().stream().forEach(u-> {
            if(u.getRole().equals(UserRoleEnum.USER)) {
                throw new com.example.booksforgram.web.exception.ObjectNotFoundException("Not found");
            }
        }
        );
        }
        return "updateProfile";
    }
    @PatchMapping("/profile/{id}/edit")
    public String editUser(
            @PathVariable Long id,
            @Valid EditUserBindingModel editUserBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Principal principal) throws ObjectNotFoundException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("editUserBindingModel", editUserBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editUserBindingModel", bindingResult);

            return "redirect:/profile/" + id + "/edit";
        }
        UserEditServiceModel serviceModel=modelMapper.map(editUserBindingModel,
                UserEditServiceModel.class);

        serviceModel.setId(id);
        userService.update(serviceModel);

        return "redirect:/profile";
    }

}
