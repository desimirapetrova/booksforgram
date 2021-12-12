package com.example.booksforgram.web;

import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class WishController {

    private final UserService userService;

    public WishController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/wishlist")
    public String getWishList(Principal userSession, Model model) {

        User userFromDb=userService.findByUsername(userSession.getName());
        model.addAttribute("wishItems",userFromDb.getWishBookList());

        return "wishlist";
    }
    @PostMapping("/addToWishList")
    public String addToWishList(Model model,
                            @RequestParam("add") Book book, Principal userSession) {


        User user=userService.findByUsername(userSession.getName());

        user.getWishBookList().add(book);
        userService.save(user);

        return "redirect:featured";
    }
    @GetMapping("/removeFromWish")
    public String getremove(){
        return "/wishlist";
    }
    @PostMapping("/removeFromWish")
    public String removeFromWishList(
            @RequestParam(value = "bookId") Book book,
            Principal userSession) {
        User user = userService.findByUsername(userSession.getName());

        if(book!=null) {
            user.getWishBookList().removeIf(book1 -> book1.getId() == book.getId());
        }
        userService.save(user);

        return "redirect:/wishlist";
    }
    @PostMapping("/moveToWishList")
    public String move(Model model,
                  @RequestParam("bookId") Book book, Principal userSession) {


        User user = userService.findByUsername(userSession.getName());

        user.getWishBookList().add(book);
        user.getBookList().removeIf(book1 -> book1.getId() == book.getId());
        userService.save(user);

        return "redirect:shoppingCart";
    }
}
