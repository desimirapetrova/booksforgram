package com.example.booksforgram.web;

import com.example.booksforgram.model.entity.PromoBook;
import com.example.booksforgram.service.PromoBookService;
import com.example.booksforgram.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PromoBookController {
    private final PromoBookService promoBookService;
    private final UserService userService;

    public PromoBookController(PromoBookService promoBookService, UserService userService) {
        this.promoBookService = promoBookService;
        this.userService = userService;
    }

    @GetMapping("/promo")
    public String allBooks(Model model, Principal principal) {

        if(principal==null){
            List<PromoBook> books = promoBookService.findAll();
            model.addAttribute("books",books);
            model.addAttribute("user",principal);
            return "featured";
        }
        List<PromoBook> books = promoBookService.findAll();

        model.addAttribute("user",userService.findByUsername(principal.getName()));
        model.addAttribute("books",books);
        return "promo";
    }
}
