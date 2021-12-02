package com.example.booksforgram.init;

import com.example.booksforgram.service.BookService;
import com.example.booksforgram.service.CategoryService;
import com.example.booksforgram.service.ConditionService;
import com.example.booksforgram.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {
    private final CategoryService categoryService;
    private final ConditionService conditionService;
    private final UserService userService;
    private final BookService bookService;

    public DatabaseInit(CategoryService categoryService, ConditionService conditionService, UserService userService, BookService bookService) {
        this.categoryService = categoryService;
        this.conditionService = conditionService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategories();
        conditionService.initConditions();
        userService.initializeUsersAndRoles();
        bookService.initBooks();

    }
}

