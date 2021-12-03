package com.example.booksforgram.web;

import com.example.booksforgram.service.BookService;
import com.example.booksforgram.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class SearchController {

    private final BookService bookService;
    private final UserService userService;

    public SearchController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }
    @GetMapping("/search")
    public String Search(@RequestParam(value = "searchTerm", required = false)
                                       String pSearchTerm, Model model, Principal principal) {

        model.addAttribute("searchResult", bookService.findAllByNameOrAuthor(pSearchTerm));
        model.addAttribute("user",userService.findByUsername(principal.getName()));
        return "search";
    }
}