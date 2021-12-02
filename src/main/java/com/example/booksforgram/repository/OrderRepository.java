package com.example.booksforgram.repository;

import com.example.booksforgram.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByBuyer_Username(String name);

    List<Order> findAllByBookList_(String name);

    List<Order> findAllByBookListContaining(String name);

}
