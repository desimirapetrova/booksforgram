package com.example.booksforgram.service.impl;

import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.Order;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.service.OrderServiceModel;
import com.example.booksforgram.repository.BookRepository;
import com.example.booksforgram.repository.OrderRepository;
import com.example.booksforgram.repository.UserRepository;
import com.example.booksforgram.service.OrderService;
import com.example.booksforgram.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final UserService userService;
    private final BookRepository bookRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserRepository userRepository, UserService userService, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.userService = userService;
        this.bookRepository = bookRepository;
    }


    @Override
    public OrderServiceModel makeOrder(OrderServiceModel orderServiceModel, String buyer) {
        User userFromDB = userService.findByUsername(buyer);
        List<User> owner=userFromDB.getBookList().stream().map(book -> book.getOwner()).collect(Collectors.toList());
        Order order=modelMapper.map(orderServiceModel,Order.class);
        order.setBuyer(userRepository.findByUsername(buyer).orElse(null));
        order.setOwner(owner);
        order.setDate(LocalDate.now());
        order.setBookList(userFromDB.getBookList());
        Order savedOrder=orderRepository.save(order);
       List<Book>books= bookRepository.findAllById(order.getBookList().stream().map(book -> book.getId()).collect(Collectors.toList()));
       books.forEach(book->{
           book.setQuantity(0);
           bookRepository.save(book);
       });

//        order.setBookList();
//        order.getBookList().forEach(book -> bookRepository.findByName(book.getName()).setQuantity(0));
        return modelMapper.map(savedOrder, OrderServiceModel.class);
    }

    @Override
    public List<Order> findByBuyer(String name) {

        return orderRepository.findAllByBuyer_Username(name).stream()
                .map(order -> modelMapper.map(order, Order.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll().stream().map(order -> modelMapper.map(order,Order.class))
                .collect(Collectors.toList());
    }




}
