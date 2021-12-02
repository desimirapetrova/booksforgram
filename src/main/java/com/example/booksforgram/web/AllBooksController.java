package com.example.booksforgram.web;

import com.example.booksforgram.model.binding.DetailsBindingModel;
import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.service.BookService;
import com.example.booksforgram.service.CommentService;
import com.example.booksforgram.service.UserService;
import com.example.booksforgram.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class AllBooksController {
    private final BookService bookService;
    private final UserService userService;
    private final CommentService commentService;
    public AllBooksController(BookService bookService, UserService userService, CommentService commentService) {
        this.bookService = bookService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/featured")
    public String allBooks(Model model,Principal principal) {

        if(principal==null){
            List<Book> books = bookService.findAll();
            model.addAttribute("books",books);
            model.addAttribute("user",principal);
            return "featured";
        }
        List<Book> books = bookService.findAll();

        model.addAttribute("user",userService.findByUsername(principal.getName()));
        model.addAttribute("books",books);
        return "featured";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id,Model model,Principal principal){
        //itemService.showDetails(id);
        Book book=bookService.findById(id);
        if(book==null){
            throw new ObjectNotFoundException("Not found",id);
        }

        model.addAttribute("comments",commentService.findAll());
        model.addAttribute("book",bookService.findById(id));
        model.addAttribute("user",userService.findByUsername(principal.getName()));
        return "details";
    }
    @PostMapping("/details/{id}")
    public String detail(@PathVariable Long id,@Valid DetailsBindingModel detailsBindingModel) {
        bookService.showDetails(id);
        return "details";
    }
//    @GetMapping("/categories")
//    public String categories(@PathVariable String name,Model model){
//        //itemService.showDetails(id);
//        List<Book>bookList=bookService.findByCategoryName(name);
//        model.addAttribute("bookCategory",bookList);
//        return "categories";
//    }
//    @PostMapping("/categories/{name}")
//    public String categories(@PathVariable String name,@Valid DetailsBindingModel detailsBindingModel) {
//        bookService.showCategories(name);
//        return "categories";
//    }
    @RequestMapping("/categories")
    public String searchByCategory(
            @RequestParam("id") Long id,
            Model model, Principal principal
    ) {

        List<Book> bookList = bookService.findByCategory(id);
        model.addAttribute("bookList", bookList);
        model.addAttribute("idss",id.longValue());

        return "categories";
    }
    @GetMapping("/mybooks")
    public String getMyBooks(Model model, Principal principal){
        User owner=userService.findByUsername(principal.getName());
        List<Book> mybooks = bookService.findAllByOwner(owner);
        model.addAttribute( "myBooks",mybooks);
        return "/mybooks";
    }


}
