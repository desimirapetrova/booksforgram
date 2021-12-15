package com.example.booksforgram.model.binding;

import com.example.booksforgram.model.entity.Role;
import com.example.booksforgram.model.entity.enums.GenderEnum;
import com.example.booksforgram.model.validator.UniqueEmail;
import com.example.booksforgram.model.validator.UniqueUserName;

import javax.validation.constraints.*;
import java.util.Set;

public class EditUserBindingModel {
    private String username;
    @Size(min = 3, max = 20, message = "Името трябва да съдържа повече от 2 символа.")
    private String first_name;
    @NotEmpty(message = "Моля,попълнете това поле.")
    @Size(min = 3, max = 20, message = "Фамилията трябва да съдържа повече от 2 символа.")
    private String last_name;
    @Min(value = 12, message = "Минималната възраст е 12 години.")
    private int age;


    public EditUserBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}