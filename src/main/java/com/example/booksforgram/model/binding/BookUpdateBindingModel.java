package com.example.booksforgram.model.binding;

import com.example.booksforgram.model.entity.Category;
import com.example.booksforgram.model.entity.Condition;
import com.example.booksforgram.model.entity.enums.CategoryEnum;
import com.example.booksforgram.model.entity.enums.ConditionEnum;
import com.example.booksforgram.model.service.BookUpdateServiceModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigInteger;

public class BookUpdateBindingModel {
    private Long id;
    @Size(min = 1,message = "Въведете име на книгата")
    private String name;
    @NotEmpty(message = "Моля,добавете описание")
    @Size(min = 10,message = "Минимален брой символи 10")
    private String description;
    @NotNull(message = "Въведете цена")
    @Positive(message = "Въведете валидна цена")
    private Integer price;
    @NotNull(message = "Моля,изберете състояние")
    private ConditionEnum condition;
    @NotNull(message = "Моля,изберете категория")
    private CategoryEnum category;
    @Size(min = 3,message = "Името трябва да съдържа поне 3 символа.")
    @NotEmpty(message = "Въведете име автора")
    private String author;

    private String imageUrl;



    public BookUpdateBindingModel() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ConditionEnum getCondition() {
        return condition;
    }

    public void setCondition(ConditionEnum condition) {
        this.condition = condition;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
