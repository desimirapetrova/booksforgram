package com.example.booksforgram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogsController {
    @GetMapping("/blogs")
    public String getBlgos(){
        return "blogs";
    }
}
