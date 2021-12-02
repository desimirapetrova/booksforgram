package com.example.booksforgram.web;

import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.service.BookService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    private final BookService bookService;

    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/search")
    public String Search(@RequestParam(value = "searchTerm", required = false)
                                       String pSearchTerm, Model model) {

        model.addAttribute("searchResult", bookService.findAllByNameOrAuthor(pSearchTerm));

        return "search";
    }
}