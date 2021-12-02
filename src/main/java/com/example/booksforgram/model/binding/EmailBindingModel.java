package com.example.booksforgram.model.binding;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

public class EmailBindingModel {
    @Email(message = "въведения имейл адрес е навалиден")
//    @UniqueElements(message = "вече съществува")
    private String email;
    private String username;

    public EmailBindingModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
