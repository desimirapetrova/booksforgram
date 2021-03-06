package com.example.booksforgram.model.service;

import com.example.booksforgram.model.entity.Category;
import com.example.booksforgram.model.entity.Condition;
import com.example.booksforgram.model.entity.enums.CategoryEnum;
import com.example.booksforgram.model.entity.enums.ConditionEnum;

public class BookUpdateServiceModel {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private ConditionEnum condition;
    private CategoryEnum category;
    private String author;
    private String imageUrl;


    public BookUpdateServiceModel() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
