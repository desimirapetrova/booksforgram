package com.example.booksforgram.web;

import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.repository.UserRepository;
import com.example.booksforgram.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {

    private final UserService userService;
    private final UserRepository userRepository;

    public CartController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/shoppingCart")
    public String getShoppingCart(Principal userSession, Model model) {

        User userFromDb=userService.findByUsername(userSession.getName());
        model.addAttribute("cardItems",userFromDb.getBookList());
//        model.addAttribute("total",userFromDb.getBookList().stream()
//        .map(book -> book.getPrice()).reduce(Integer::sum).orElse(null));

//        userFromDb.getBookList().forEach(book ->);
        return "/shoppingCart";
    }
    @PostMapping("/addToCart")
    public String addToCart(Model model,
                            @RequestParam("add") Book book,Principal userSession) {


        User user=userService.findByUsername(userSession.getName());

            user.getBookList().add(book);
        user.getWishBookList().removeIf(book1 -> book1.getId() == book.getId());

        userService.save(user);

            return "redirect:shoppingCart";

    }
    @GetMapping("/remove")
    public String getremove(){
        return "/shoppingCart";
    }
    @PostMapping("/remove")
    public String removeFromCart(
            @RequestParam(value = "bookId") Book book,
            Principal userSession) {
        User user = userService.findByUsername(userSession.getName());

        if(book!=null) {
            user.getBookList().removeIf(book1 -> book1.getId() == book.getId());
        }
        userService.save(user);

        return "redirect:/shoppingCart";
    }
}
