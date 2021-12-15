package com.example.booksforgram.service;

import com.example.booksforgram.model.entity.Role;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.service.UserEditServiceModel;
import com.example.booksforgram.model.service.UserServiceModel;
import org.springframework.data.jpa.repository.Query;

import java.security.Principal;
import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);
    void loginUser(Long id, String username);


    UserServiceModel findByUsernameAndPassword(String username, String password);
    User findById(Long id);

    void initializeUsersAndRoles();

    User findByUsername(String username);

    User save(User user);

    void registerAndLoginUser(UserServiceModel userServiceModel);

//    void activate(Principal principal);
//
//    void deactivate(Principal principal);

    boolean isUserNameFree(String userName);

    boolean isEmailFree(String email);

    void update(UserEditServiceModel serviceModel);

   List<User> findAll();

    void makeAdmin(Long id);

}
