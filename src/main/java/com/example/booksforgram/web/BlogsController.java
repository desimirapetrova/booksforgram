package com.example.booksforgram.web;

import com.example.booksforgram.model.binding.AddBlogBindingModel;
import com.example.booksforgram.model.service.AddBlogServiceModel;
import com.example.booksforgram.service.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
public class BlogsController {
    private final BlogService blogService;
    private final ModelMapper modelMapper;

    public BlogsController(BlogService blogService, ModelMapper modelMapper) {
        this.blogService = blogService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/blogs")
    public String getBlogs(Model model){
        model.addAttribute("blogs",blogService.findAll());

        return "blogs";

    }
    @GetMapping("/myblog")
    public String getmyBlog(){
            return "myblog";
        }
    @GetMapping("/blog/{id}")
    public String getBlog(@PathVariable Long id ,Model model){
        model.addAttribute("blogs",blogService.findById(id));
        return "blog";
    }

    @GetMapping("/add")
    public String add(Model model) {
//        model.addAttribute("languages", LanguageEnum.values());
//        model.addAttribute("categories", CategoryEnum.values());
//        model.addAttribute("publishingHouses", publishingHouseService.findAllPublishingHouseNames());
        model.addAttribute("blogs",blogService.findAll());
        return "/add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AddBlogBindingModel addBlogBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal principal) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addBlogBindingModel", addBlogBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addBlogBindingModel", bindingResult);

            return "redirect:/books/add";
        }

        AddBlogServiceModel addBlogServiceModel = modelMapper
                .map(addBlogBindingModel, AddBlogServiceModel.class);

//        bookAddServiceModel.setCreator(principal.getUsername());

        Long bookId = blogService.add(addBlogServiceModel,principal.getName());

//        redirectAttributes.addFlashAttribute("addedSuccessfully", true);
        return "redirect:/blogs";
    }
    @ModelAttribute
    public AddBlogBindingModel addBlogBindingModel(){
        return  new AddBlogBindingModel();
    }
    }

