package com.example.booksforgram.service.impl;

import com.example.booksforgram.model.entity.Category;
import com.example.booksforgram.model.entity.enums.CategoryEnum;
import com.example.booksforgram.repository.CategoryRepository;
import com.example.booksforgram.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if(categoryRepository.count()!=0){
            return;
        }
        Arrays.stream(CategoryEnum.values()).forEach(categoryEnum -> {
            Category category=new Category();
            category.setName(categoryEnum);
            categoryRepository.save(category);
        });
    }

    @Override
    public Category findByCategoryEnum(CategoryEnum category) {
        return categoryRepository.findByName(category).orElse(null);
    }
}
