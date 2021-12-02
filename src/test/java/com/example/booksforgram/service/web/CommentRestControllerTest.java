package com.example.booksforgram.service.web;

import com.example.booksforgram.model.binding.BookUpdateBindingModel;
import com.example.booksforgram.model.binding.NewCommentBindingModel;
import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.Comment;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.entity.enums.GenderEnum;
import com.example.booksforgram.repository.BookRepository;
import com.example.booksforgram.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

@WithMockUser("desimira1")
@SpringBootTest
@AutoConfigureMockMvc
class CommentRestControllerTest {

    private static final String COMMENT_1 = "Чудесна книга";
    private static final String COMMENT_2 = "Препоръчвам";

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
        testUser.setPassword("11111");
        testUser.setUsername("desimira3");
        testUser.setEmail("desi3@abv.bg");
        testUser.setFirst_name("Desimira");
        testUser.setLast_name("Petrova");
        testUser.setGender(GenderEnum.FEMALE);

//        testUser = this.userRepository.save(testUser);
    }

//    @AfterEach
//    void tearDown() {
//        this.bookRepository.deleteAll();
//        this.userRepository.deleteAll();
//    }

    @Test
    void testGetComments() throws Exception {
        var book = initComments(initBook());

        mockMvc.perform(get("/api/" + book.getId() + "/comments")).
                andExpect(status().isOk());
//                andExpect(jsonPath("$", hasSize(2))).
//                andExpect(jsonPath("$.[0].message", is(COMMENT_1))).
//                andExpect(jsonPath("$.[1].message", is(COMMENT_2)));
    }

    @Test
    void testCreateComments() throws Exception {
        NewCommentBindingModel testComment = new NewCommentBindingModel().
                setMessage(COMMENT_1);

        var emptyBook = initBook();

        mockMvc.perform(
                post("/api/" +emptyBook .getId() + "/comments")
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
        testBook.setName("Testing route");
        testBook.setDescription("anjfjnef");

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

        book.setComments(List.of(comment1, comment2));

        return bookRepository.save(book);
    }
}