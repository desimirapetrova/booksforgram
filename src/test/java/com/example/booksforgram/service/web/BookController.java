package com.example.booksforgram.service.web;

import com.example.booksforgram.model.entity.*;
import com.example.booksforgram.model.entity.enums.CategoryEnum;
import com.example.booksforgram.model.entity.enums.ConditionEnum;
import com.example.booksforgram.model.service.BookServiceModel;
import com.example.booksforgram.repository.BookRepository;
import com.example.booksforgram.repository.CategoryRepository;
import com.example.booksforgram.repository.ConditionRepository;
import com.example.booksforgram.repository.UserRepository;
import com.example.booksforgram.service.BookService;
import com.example.booksforgram.service.CategoryService;
import com.example.booksforgram.service.EmailService;
import com.example.booksforgram.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("desimira")
public class BookController {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    ModelMapper mapper;
    @Mock
    private BookService bookService;
    @Mock
    private EmailService emailService;

    private Book testBook;
    private List<Email> emails;

    private List<Book>books;
//    private static final String CATEGORY_NAME = "Мистерии";
//    private static final String CONDITION_NAME = "ЗАПАЗЕНО";
    private static final String DESCRIPTION = "\"Търсете и ще намерите.\"\n" +
            "\n" +
            "Тези думи отекват в съзнанието на видния харвардски професор по религиозна символика Робърт Лангдън, когато се свестява в болница, без спомен къде се намира и как се е озовал там. Не може да обясни и произхода на страховития предмет, открит сред вещите му.\n" +
            "\n" +
            "Заплаха за живота ще запрати Лангдън и младата лекарка Сиена Брукс на главоломна гонитба из Флоренция. Единствено познаването на тайни проходи и древни загадки, спотайващи се зад историческата фасада, може да ги спаси от неизвестните им преследвачи.\n" +
            "\n" +
            "Водени само от няколко стиха от мрачния шедьовър на Данте, Лангдън и Сиена трябва да разгадаят серия шифри, скрити дълбоко в някои от най-прочутите творби на Ренесанса – скулптури, картини, сгради, – за да открият отговора на загадка, която може би ще им помогне да спасят света от ужасяваща заплаха...\n" +
            "\n" +
            "Разиграващ се на невероятен фон, вдъхновен от една от най-зловещите литературни класики в историята, „Ад” е най-завладяващият и предизвикващ размисъл роман на Дан Браун досега - задъхана надпревара с времето, която ще ви грабне още от първата страница и няма да ви остави до последната.\n";
    private Category category=new Category();
    private Condition condition=new Condition();
    private User owner=new User();

    @Autowired
    private BookRepository bookRepository;
    @BeforeEach
    public void init(){

//        testBook=new Book();
//                testBook.setName(TEST_NAME);
//                testBook.setDescription(DESCRIPTION);
//                category.setName(CategoryEnum.Роман);
//                testBook.setCategory(category);
//                condition.setName(ConditionEnum.ИЗПОЛЗВАНО);
//                testBook.setCondition(condition);
//                testBook.setPrice(4);
//                testBook.setImageUrl("11455345431");
//                testBook.setAuthor(TEST_AUTHOR);
//        bookRepository= Mockito.mock(BookRepository.class);
//        bookRepository.save(testBook);
//        this.books.add(testBook);

    }
    @AfterEach
    void tearDown()  throws  Exception{
//       this.bookRepository.delete(testBook);
        bookRepository.deleteAll();
    }

    @Test
    void testShowAllBooks() throws Exception {
        mockMvc.
                perform(get("/featured"))
                .andExpect(status().isOk())
                .andExpect(view().name("featured"));
    }
    @Test
    void testGetMyBooks() throws Exception {
        mockMvc.
                perform(get("/mybooks"))
                .andExpect(status().isOk())
                .andExpect(view().name("/mybooks"));
    }

//        @Test
//    void testGetShoppingCart() throws Exception {
//        mockMvc.
//                perform(get("/shoppingCart"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/shoppingCart"));
//    }
//    @Test
//    void testGetWishlist() throws Exception {
//        mockMvc.
//                perform(get("/wishlist"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("wishlist"));
//    }
    @Test
    void testGetSearch() throws Exception {
        mockMvc.
                perform(get("/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"));
    }
//    @Test
//    void testGetOrders() throws Exception {
//        mockMvc.
//                perform(get("/orders"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("orders"));
//    }
//@Test
//    void testEditBookGEt() throws Exception {
//        mockMvc.
//                perform(get("/books/1/edit"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("update"));
//    }
@Test
    void testFindByID() throws Exception {
        Mockito.when(bookService.findById(4L)).thenReturn(this.testBook);
    }@Test
    void testFindByEmail() throws Exception {
        Mockito.when(emailService.findAll()).thenReturn(emails);
    }
@Test
    void testFindByCategory() throws Exception {
        Mockito.when(bookService.findByCategory(4L)).thenReturn(books);
    }
    @Test
    void testFindBySearch2() throws Exception {
        Mockito.when(bookService.findAllByNameOrAuthor(TEST_AUTHOR)).thenReturn(books);
    }
    @Test
    void testFindAll() throws Exception {
        Mockito.when(bookService.findAll()).thenReturn(books);
    }
    @Test
    void testSearch() throws Exception {
        Mockito.when(bookService.findAllByNameOrAuthor(String.valueOf(1))).thenReturn(books);
    }

    @Test
    void testAddBook() throws Exception {
        mockMvc.
                perform(get("/add-book"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-book"));
    }


    private static final String TEST_NAME = "Аaaagygygy";
    private static final String TEST_AUTHOR = "Дан Браун";
    private static final int TEST_QUANTITY = 1;

//    @Test
//    void testAddBookPost() throws Exception {
//
//        Book newlyCreatedBook=bookRepository.findByName(TEST_NAME);
//
//        Assertions.assertEquals(TEST_NAME, newlyCreatedBook.getName());
//        Assertions.assertEquals(TEST_AUTHOR,newlyCreatedBook.getAuthor());
//        Assertions.assertEquals(TEST_QUANTITY, newlyCreatedBook.getQuantity());
//    }


    @Test
    void testAddBookError() throws Exception {
//        category=CategoryEnum.Роман.name();
//        condition.setId(1L);
        category.setName(CategoryEnum.Роман);
        condition.setName(ConditionEnum.използвано);
        mockMvc.perform(post("/add-book").
                param("name",TEST_NAME).
                param("author",TEST_AUTHOR).
                param("price", String.valueOf(2)).
                param("condition", String.valueOf(condition)).
                param("description",DESCRIPTION).
                param("category", String.valueOf(category)).
                param("image_url","11444484844481").
                with(csrf()).
                contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(0, bookRepository.count());

//        Optional<Book> newBook = Optional.ofNullable((bookRepository.findByName(TEST_NAME)));
////
//        Assertions.assertTrue(newBook.isPresent());
////
//        Book newCreatedBook = newBook.get();
//
//        assertEquals(TEST_NAME, newCreatedBook.getName());
//        assertEquals(TEST_AUTHOR, newCreatedBook.getAuthor());
//        assertEquals(DESCRIPTION, newCreatedBook.getDescription());
//        assertEquals(CATEGORY_NAME, newCreatedBook.getCategory().getName());
//        assertEquals(CONDITION_NAME, newCreatedBook.getCondition().getName());
//        assertEquals("111", newCreatedBook.getImageUrl());
//        assertEquals("desimira", newCreatedBook.getOwner());
//        assertEquals(String.valueOf(2), newCreatedBook.getPrice());
    }

}
