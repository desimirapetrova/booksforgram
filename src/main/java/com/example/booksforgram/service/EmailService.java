package com.example.booksforgram.service;

import com.example.booksforgram.model.entity.Email;
import com.example.booksforgram.model.service.EmailServiceModel;

import java.util.List;

public interface EmailService {

    void saveEmails(EmailServiceModel emailServiceModel,String username);

    List<Email> findAll();
}
