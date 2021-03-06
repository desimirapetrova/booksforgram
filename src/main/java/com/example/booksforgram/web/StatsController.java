package com.example.booksforgram.web;


import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.entity.enums.UserRoleEnum;
import com.example.booksforgram.service.StatsService;
import com.example.booksforgram.service.UserService;
import com.example.booksforgram.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class StatsController {

    private final StatsService statsService;
    private final UserService userService;

    public StatsController(StatsService statsService, UserService userService) {
        this.statsService = statsService;
        this.userService = userService;
    }

    @GetMapping("/statistics")
    public ModelAndView statistics(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stats", statsService.getStats());
        modelAndView.setViewName("statistics");
        User currentUser =userService.findByUsername(principal.getName());
        currentUser.getRoles().stream().forEach(u->{
            if(u.getRole().equals(UserRoleEnum.USER)) {
                throw new ObjectNotFoundException("not found");

            }
        });
        return modelAndView;
    }

}