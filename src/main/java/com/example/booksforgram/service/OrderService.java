package com.example.booksforgram.service;

import com.example.booksforgram.model.entity.Order;
import com.example.booksforgram.model.service.OrderServiceModel;

import java.util.List;

public interface OrderService {
    OrderServiceModel makeOrder(OrderServiceModel orderServiceModel, String buyer);

    List<Order> findByBuyer(String name);

    List<Order> findAll();

}
