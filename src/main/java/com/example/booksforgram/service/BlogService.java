package com.example.booksforgram.service;

import com.example.booksforgram.model.entity.Blog;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.service.AddBlogServiceModel;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    Long add(AddBlogServiceModel addBlogServiceModel, String author) throws IOException;

    List<Blog> findAll();

    String getShortDescrip();

    Blog findById(Long id);
}
