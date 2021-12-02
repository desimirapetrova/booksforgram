package com.example.booksforgram.model.view;

import com.example.booksforgram.model.entity.Category;
import com.example.booksforgram.model.entity.Condition;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.entity.enums.CategoryEnum;
import com.example.booksforgram.model.entity.enums.ConditionEnum;

import java.math.BigInteger;

public class BookViewModel {
    private Long id;
    private String name;
    private CategoryEnum category;
    private ConditionEnum condition;
    private Integer price;
    private String author;
    private String description;
    private String imageUrl;
    private User owner;
    private Integer quantity;



    public BookViewModel() {
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public ConditionEnum getCondition() {
        return condition;
    }

    public void setCondition(ConditionEnum condition) {
        this.condition = condition;
    }

    public Integer getPrice() {
        return price;
    }
}
