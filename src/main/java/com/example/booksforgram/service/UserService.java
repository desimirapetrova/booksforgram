package com.example.booksforgram.service;

import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.service.UserServiceModel;
import org.springframework.data.jpa.repository.Query;

import java.security.Principal;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);
    void loginUser(Long id, String username);


    UserServiceModel findByUsernameAndPassword(String username, String password);
    User findById(Long id);

    void initializeUsersAndRoles();

    User findByUsername(String username);

    User save(User user);

    void registerAndLoginUser(UserServiceModel userServiceModel);

    void activate(Principal principal);

    void deactivate(Principal principal);

}
