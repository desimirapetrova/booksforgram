package com.example.booksforgram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogsController {
    @GetMapping("/blogs")
    public String getBlogs(){
        return "blogs";
    }
    @GetMapping("/blog")
    public String getBlog(){
            return "blog";
        }
    }
