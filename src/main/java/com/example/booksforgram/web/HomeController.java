package com.example.booksforgram.web;

import com.example.booksforgram.model.binding.EmailBindingModel;
import com.example.booksforgram.model.entity.Email;
import com.example.booksforgram.model.service.EmailServiceModel;
import com.example.booksforgram.repository.EmailRepository;
import com.example.booksforgram.service.EmailService;
import com.example.booksforgram.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    private final UserService userService;
    private final EmailService emailService;
    private final ModelMapper modelMapper;
    private final EmailRepository emailRepository;

    public HomeController(UserService userService, EmailService emailService, ModelMapper modelMapper, EmailRepository emailRepository) {
        this.userService = userService;
        this.emailService = emailService;
        this.modelMapper = modelMapper;
        this.emailRepository = emailRepository;
    }

    @GetMapping("/")
    public String home(Principal principal){

        if (principal==null) {
            return "index";
        }
        return "home";
    }
    @PostMapping("/newsletter")
    public String newSeller(Principal principal, @Valid EmailBindingModel emailBindingModel, Model model){
        emailService.saveEmails(modelMapper
                .map(emailBindingModel, EmailServiceModel.class),principal.getName());
        model.addAttribute("allEmails",emailService.findAll());
        List<Email> emails=emailRepository.findByEmail(modelMapper
                .map(emailBindingModel, EmailServiceModel.class).getEmail());
        if (!emails.isEmpty()) {
            model.addAttribute("usernameError", "Вече съществува");
            return "home";
        }
        return "home" ;
        }

        @ModelAttribute EmailBindingModel emailBindingModel(){
        return new EmailBindingModel();
        }
}
