package com.example.booksforgram.service.impl;

import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.Email;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.service.EmailServiceModel;
import com.example.booksforgram.model.view.BookViewModel;
import com.example.booksforgram.repository.EmailRepository;
import com.example.booksforgram.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    private final ModelMapper modelMapper;

    public EmailServiceImpl(EmailRepository emailRepository, ModelMapper modelMapper) {
        this.emailRepository = emailRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public void saveEmails(EmailServiceModel emailServiceModel,String username) {
        Email email = new Email();
            List<Email> emails=emailRepository.findByEmail(emailServiceModel.getEmail());
            if(emails.isEmpty()) {
                email.setEmail(emailServiceModel.getEmail());
                email.setUsername(username);
            emailRepository.save(email);
        }else {
//           TODO
        }
    }

    @Override
    public List<Email> findAll() {
        return emailRepository.findAll().stream()
                .map(email -> modelMapper.map(email, Email.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isEmailFree(String email) {

        return emailRepository.findByEmailIgnoreCase(email).isEmpty();
    }
}
