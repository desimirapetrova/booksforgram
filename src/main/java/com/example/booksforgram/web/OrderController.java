package com.example.booksforgram.web;

import com.example.booksforgram.model.binding.OrderBindingModel;
import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.service.OrderServiceModel;
import com.example.booksforgram.repository.BookRepository;
import com.example.booksforgram.repository.OrderRepository;
import com.example.booksforgram.service.OrderService;
import com.example.booksforgram.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    public OrderController(UserService userService, OrderService orderService, ModelMapper modelMapper, BookRepository bookRepository, OrderRepository orderRepository) {
        this.userService = userService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public String newSeller(Principal user, Model model) {
        User userFromDB = userService.findByUsername(user.getName());

        model.addAttribute("orders", userFromDB.getBookList());
        model.addAttribute("allOrders",orderService.findAll());
        return "orders";
    }

    @PostMapping("/orders")
    public String makeOrder(@Valid OrderBindingModel orderBindingModel, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Principal user,Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderBindingModel", orderBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderBindingModel", bindingResult);

            return "redirect:orders";
        }
        User userFromDB = userService.findByUsername(user.getName());
        orderService.makeOrder(modelMapper
                .map(orderBindingModel, OrderServiceModel.class), user.getName());

//        List<Long>books=userFromDB.getBookList().stream().map(book -> book.getId()).collect(Collectors.toList());
//        bookRepository.findAllById(books).forEach(book -> book.setQuantity(0));
        userFromDB.getBookList().clear();
        userService.save(userFromDB);
//        return "redirect:/details/" + bookServiceModel.getId();
        return "redirect:/profile";
    }
    @ModelAttribute
    public OrderBindingModel orderBindingModel(){
        return  new OrderBindingModel();
    }

}
