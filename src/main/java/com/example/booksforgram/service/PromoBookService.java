package com.example.booksforgram.service;

import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.PromoBook;

import java.util.List;

public interface PromoBookService {
    List<PromoBook> findAll();

    void initPromoBooks();

    void deletePromoBooks();

    void show();
}
