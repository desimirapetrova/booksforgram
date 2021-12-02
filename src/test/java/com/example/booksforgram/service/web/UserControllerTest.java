package com.example.booksforgram.service.web;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.entity.enums.GenderEnum;
import com.example.booksforgram.repository.UserRepository;
import com.example.booksforgram.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
    private User user;
    @InjectMocks
    UserServiceImpl userService;
    private User USER_ENTITY_MODEL;

//    @AfterEach
//    void tearDown()  throws  Exception{
//    userRepository.deleteAll();
//    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc.
                perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    } @Test
    void testLoginForm() throws Exception {
        mockMvc.
                perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
    @Test
    @WithMockUser("desimira")
    void testOpenLogout() throws Exception {
        mockMvc.
                perform(get("/logout"))
                .andExpect(status().isOk())
                .andExpect(view().name("/"));
    }
 @Test
 @WithMockUser("desimira")
 void testGetProfile() throws Exception {
        mockMvc.
                perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"));

    }@Test
    @WithMockUser("desimira")

    void testGetMyProfile() throws Exception {
        mockMvc.
                perform(get("/profileUser/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("profileUser"));
    }

    private static final String TEST_USER_EMAIL = "desi@abv.bg";
    private static final String TEST_USERNAME = "desimira";
    private static final int TEST_USER_AGE = 22;

    @Test
    void testRegisterUser() throws Exception {
        mockMvc.perform(post("/register").
                param("username",TEST_USERNAME).
                param("first_name","Desimira").
                param("last_name","Petrova").
                param("email",TEST_USER_EMAIL).
                param("age",String.valueOf(TEST_USER_AGE)).
                param("password","11111").
                param("confirmPassword","11111").
                with(csrf()).
                contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).
                andExpect(status().is3xxRedirection());

//        Assertions.assertEquals(1, userRepository.count());

        Optional<User> newlyCreatedUserOpt = userRepository.findByUsername(TEST_USERNAME);

        Assertions.assertTrue(newlyCreatedUserOpt.isPresent());

        User newlyCreatedUser = newlyCreatedUserOpt.get();

        assertEquals(TEST_USER_AGE, newlyCreatedUser.getAge());
        assertEquals(TEST_USERNAME, newlyCreatedUser.getUsername());
        assertEquals(TEST_USER_EMAIL, newlyCreatedUser.getEmail());
        assertEquals("Desimira", newlyCreatedUser.getFirst_name());
        assertEquals("Petrova", newlyCreatedUser.getLast_name());

    }




}