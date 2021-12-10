package com.example.booksforgram.service.web;

import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.entity.enums.GenderEnum;
import com.example.booksforgram.repository.BlogRepository;
import com.example.booksforgram.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@SpringBootTest
@AutoConfigureMockMvc
public class BlogController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser("desimira")
    void getBlogs() throws Exception {
        mockMvc.
                perform(get("/blogs"))
                .andExpect(status().isOk())
                .andExpect(view().name("blogs"));
    }
    @Test
    @WithMockUser("desimira")
    void getBlog() throws Exception {
        mockMvc.
                perform(get("/myblog"))
                .andExpect(status().isOk())
                .andExpect(view().name("myblog"));
    }
    @Test
    @WithMockUser("desimira")
    void getaddBlog() throws Exception {
        mockMvc.
                perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/add"));
    }
    @Autowired
    private BlogRepository blogRepository;

    @AfterEach
    void tearDown() {
        blogRepository.deleteAll();
    }

    @Test
    void testAddBlogError() throws Exception {

        mockMvc.perform(post("/add").
                param("title", "blogtest").
                param("description", "desccccccc").
                contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).
                andExpect(status().is4xxClientError());

        Assertions.assertEquals(0, blogRepository.count());
//
    }

    }
