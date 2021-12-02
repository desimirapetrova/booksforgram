package com.example.booksforgram.service;

import com.example.booksforgram.model.entity.Category;
import com.example.booksforgram.model.entity.enums.CategoryEnum;

public interface CategoryService {
    void initCategories();

    Category findByCategoryEnum(CategoryEnum category);
}
