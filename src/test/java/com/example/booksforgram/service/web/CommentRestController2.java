package com.example.booksforgram.service.web;

import com.example.booksforgram.model.binding.NewCommentBindingModel;
import com.example.booksforgram.model.entity.*;
import com.example.booksforgram.model.entity.enums.CategoryEnum;
import com.example.booksforgram.model.entity.enums.ConditionEnum;
import com.example.booksforgram.model.entity.enums.GenderEnum;
import com.example.booksforgram.repository.BookRepository;
import com.example.booksforgram.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.time.LocalDateTime;
import java.util.List;

@WithMockUser("desimira")
@SpringBootTest
@AutoConfigureMockMvc
class CommentRestController2 {

    private static final String COMMENT_1 = "Cooool!";
    private static final String COMMENT_2 = "Чудесна книга!!!!!";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setPassword("password");
        testUser.setUsername("desimira2");
        testUser.setEmail("desi@example.com");
        testUser.setFirst_name("desi");
        testUser.setLast_name("petrova");
        testUser.setAge(22);
        testUser.setGender(GenderEnum.female);
        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testGetComments() throws Exception {
        var route = initComments(initBook());

        mockMvc.perform(get("/api/" + route.getId() + "/comments")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2))).
                andExpect(jsonPath("$.[0].message", is(COMMENT_1))).
                andExpect(jsonPath("$.[1].message", is(COMMENT_2)));
    }

    @Test
    void testCreateComments() throws Exception {
        NewCommentBindingModel testComment = new NewCommentBindingModel().
                setMessage(COMMENT_1);

        Book emptyBook;
        emptyBook = initBook();

        mockMvc.perform(
                post("/api/" + emptyBook.getId() + "/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testComment))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/" + emptyBook.getId() + "/comments/\\d")))
                .andExpect(jsonPath("$.message").value(is(COMMENT_1)));

    }

    private Book initBook() {
        Book testBook = new Book();
        testBook.setName("Testing book");
        testBook.setPrice(29);
        testBook.setDescription("fesfefefefefesfesfes");
        testBook.setAuthor("dmwkfmkwkawfma");
        testBook.setImageUrl("dfefesfes");

        return bookRepository.save(testBook);
    }

    private Book initComments(Book book) {

        Comment comment1 = new Comment();
        comment1.setCreated(LocalDateTime.now());
        comment1.setAuthor(testUser);
        comment1.setTextContent(COMMENT_1);
        comment1.setApproved(true);
        comment1.setBook(book);

        Comment comment2 = new Comment();
        comment2.setCreated(LocalDateTime.now());
        comment2.setAuthor(testUser);
        comment2.setTextContent(COMMENT_2);
        comment2.setApproved(true);
        comment2.setBook(book);

        book.setComments(List.of(comment1,comment2));

        return bookRepository.save(book);
    }
}
