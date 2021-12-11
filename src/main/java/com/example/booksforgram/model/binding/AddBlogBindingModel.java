package com.example.booksforgram.model.binding;

import com.example.booksforgram.model.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddBlogBindingModel {
    @NotEmpty(message = "Моля,въведете заглавие")
    private String title;
    @NotEmpty(message = "Моля,въведете описане")
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
