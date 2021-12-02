package com.example.booksforgram.model.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginBindingModel {
    @NotEmpty
    @Size(min = 6,max = 20,message = "Потребителското име е невалидно")
    private String username;
    @Size(min = 4,max = 20,message = "Паролата е невалидна")
    private String password;

    public LoginBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
