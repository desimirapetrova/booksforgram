package com.example.booksforgram.model.binding;

import com.example.booksforgram.model.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToOne;

public class AddBlogBindingModel {
    private String title;
    private String  description;

    private MultipartFile picture;

    public AddBlogBindingModel() {
    }

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
