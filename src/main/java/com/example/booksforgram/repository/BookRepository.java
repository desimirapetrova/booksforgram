package com.example.booksforgram.repository;

import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.view.BookViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Book findByName(String pSearchTerm);

    List<Book> findByOwner(User user);

    @Query("select b from  Book b where b.name=?1 or b.author=?1")
    List<Book> findAllByNameOrAuthor(String pSearchTerm);

    @Query("select b from  Book b where b.name=?1 or b.author=?1 or b.category.name=?1 or b.condition.name=?1 or b.price=?1 or b.owner.username=?1")
    List<Book> searchBook(String pSearchTerm);



    @Query("select  b from Book  b where b.category.id=?1")
    List<Book> findByCategory(Long id);

    List<Book> findAllByOwner_Id(Long id);
}
