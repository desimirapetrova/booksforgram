package com.example.booksforgram.model.service;

import org.springframework.web.multipart.MultipartFile;

public class AddBlogServiceModel {
    private String title;
    private String  description;

    private MultipartFile picture;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }
}
