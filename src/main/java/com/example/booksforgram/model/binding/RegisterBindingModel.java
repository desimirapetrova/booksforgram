package com.example.booksforgram.model.binding;

import com.example.booksforgram.model.entity.enums.GenderEnum;
import com.example.booksforgram.model.validator.UniqueEmail;
import com.example.booksforgram.model.validator.UniqueUserName;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;

public class RegisterBindingModel {
    @NotEmpty(message = "Моля,попълнете това поле.")
    @UniqueUserName
    @Size(min = 6,max = 20,message = "Потребителското име трябва да съдържа повече от 5 символа.")
    private String username;
    @NotEmpty(message = "Моля,попълнете това поле.")
    @Size(min = 3,max = 20,message = "Името трябва да съдържа повече от 2 символа.")
    private String first_name;
    @NotEmpty(message = "Моля,попълнете това поле." )
    @Size(min = 3,max = 20,message = "Фамилията трябва да съдържа повече от 2 символа.")
    private String last_name;
    @UniqueEmail
    @Email(message = "Емейл адресът трябва да бъде валиден.")
    private String email;
    @Min(value = 12,message = "Минималната възраст е 12 години.")
    private int age;
    @Size(min = 4,max = 20,message = "Паролата трябва да бъде повече от 3 символа.")
    private String password;
    @Size(min = 4,max = 20,message = "Паролата трябва да бъде повече от 3 символа.")
    private String confirmPassword;

    @NotNull(message = "Моля изберете пол.")
    private GenderEnum gender;

    public RegisterBindingModel() {
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
