package com.example.booksforgram.service.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeController {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser("desimira")
    void testGetHomeWithLoginUser() throws Exception {
        mockMvc.
                perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    void testGetHomeWithAnonymous() throws Exception {
        mockMvc.
                perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
