package com.example.booksforgram.web;

import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.entity.enums.UserRoleEnum;
import com.example.booksforgram.service.UserService;
import com.example.booksforgram.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UsersRoleController {
    private final UserService userService;

    public UsersRoleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String allUsers(Model model, Principal principal){
        model.addAttribute("users",userService.findAll());
        User currentUser =userService.findByUsername(principal.getName());
        currentUser.getRoles().stream().forEach(u->{
            if(u.getRole().equals(UserRoleEnum.USER)) {
                throw new ObjectNotFoundException("not found");

            }
        });
        return "users";
    }
    @PatchMapping("/users/{id}")
    public String makeUsersAdmin(Model model,@PathVariable Long id){
        userService.makeAdmin(id);
        return "redirect:/users";
    }
}
