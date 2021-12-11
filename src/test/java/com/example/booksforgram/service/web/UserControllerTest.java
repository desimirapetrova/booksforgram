package com.example.booksforgram.service.web;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


import com.example.booksforgram.model.entity.Role;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.entity.enums.GenderEnum;
import com.example.booksforgram.model.entity.enums.UserRoleEnum;
import com.example.booksforgram.repository.UserRepository;
import com.example.booksforgram.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.Constants;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


    @SpringBootTest
    @AutoConfigureMockMvc
    class UserControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private UserRepository userRepository;

        @AfterEach
        void tearDown() {
            userRepository.deleteAll();
        }

        @Test
        void testOpenRegisterForm() throws Exception {
            mockMvc.
                    perform(get("/register"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("register"));
        }

        private static final String TEST_USER_EMAIL = "pesho@example.com";
        private static final String TEST_USERNAME = "petartest";
        private static final int TEST_USER_AGE = 22;

        @Test
        void testRegisterUser() throws Exception {

            mockMvc.perform(post("/register").
                    param("username",TEST_USERNAME).
                    param("first_name","Pesho").
                    param("last_name","Petrov").
                    param("email",TEST_USER_EMAIL).
                    param("age",String.valueOf(TEST_USER_AGE)).
                    param("password","12345").
                    param("confirmPassword","12345").
                    param("gender",GenderEnum.female.name()).
                    with(csrf()).
                    contentType(MediaType.APPLICATION_FORM_URLENCODED)
            ).
                    andExpect(status().is3xxRedirection());

            Assertions.assertEquals(1, userRepository.count());
//
            Optional<User> newlyCreatedUserOpt = userRepository.findByUsername(TEST_USERNAME);
//
            Assertions.assertTrue(newlyCreatedUserOpt.isPresent());

            User newlyCreatedUser = newlyCreatedUserOpt.get();

            Assertions.assertEquals(TEST_USER_AGE, newlyCreatedUser.getAge());
            Assertions.assertEquals(TEST_USER_EMAIL,newlyCreatedUser.getEmail());
            Assertions.assertEquals(TEST_USERNAME,newlyCreatedUser.getUsername());
            Assertions.assertEquals(GenderEnum.female.name(),newlyCreatedUser.getGender().name());
            Assertions.assertEquals("Pesho",newlyCreatedUser.getFirst_name());
            Assertions.assertEquals("Petrov",newlyCreatedUser.getLast_name());


        }

    }