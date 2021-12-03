package com.example.booksforgram.service;

import com.example.booksforgram.model.binding.PictureBindingModel;
import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.Picture;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.service.BookServiceModel;
import com.example.booksforgram.model.service.BookUpdateServiceModel;
import com.example.booksforgram.model.view.BookViewModel;
import javassist.tools.rmi.ObjectNotFoundException;

import java.io.IOException;
import java.util.List;

public interface BookService {
    BookServiceModel addBook(BookServiceModel bookServiceModel, String owner) throws IOException;

    List<Book> findAll();

    void showDetails(Long id);

    Book findById(Long id);

    Book getBookById(Long id);


    Book getProductById(Long id);

    Book findByName(String pSearchTerm);

    void initBooks();

    List<Book> findAllByOwner(User owner);

    List<Book> findAllByNameOrAuthor(String pSearchTerm);

    List<Book> findByCategory(Long id);

    BookViewModel findById2(Long id, String userIdentifier);

    void updateBook(BookUpdateServiceModel serviceModel) throws ObjectNotFoundException;

    void deleteBook(Long id);

    List<Book> findBookById(Long id);
}
