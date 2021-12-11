package com.example.booksforgram.web;

import com.example.booksforgram.model.binding.AddBookBindingModel;
import com.example.booksforgram.model.binding.BookUpdateBindingModel;
import com.example.booksforgram.model.binding.LoginBindingModel;
import com.example.booksforgram.model.binding.PictureBindingModel;
import com.example.booksforgram.model.entity.Picture;
import com.example.booksforgram.model.service.BookServiceModel;
import com.example.booksforgram.model.service.BookUpdateServiceModel;
import com.example.booksforgram.model.view.BookViewModel;
import com.example.booksforgram.repository.PictureRepository;
import com.example.booksforgram.service.BookService;
import com.example.booksforgram.service.CloudinaryImage;
import com.example.booksforgram.service.CloudinaryService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
//@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final ModelMapper modelMapper;
    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;

    public BookController(BookService bookService, ModelMapper modelMapper, PictureRepository pictureRepository, CloudinaryService cloudinaryService) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/add-book")
    public String addBook(){
        return "add-book";
    }
    @PostMapping("/add-book")
    public String addConfirm(@Valid AddBookBindingModel addBookBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Principal user) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addBookBindingModel", addBookBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addBookBindingModel", bindingResult);

            return "redirect:add-book";
        }

//        var picture = createPictureEntity(bindingModel.getPicture(),
//                bindingModel.getTitle());
//
//        pictureRepository.save(picture);

        BookServiceModel bookServiceModel=bookService.addBook(modelMapper
                .map(addBookBindingModel, BookServiceModel.class), user.getName());

    return "redirect:/details/" + bookServiceModel.getId() ;

    }
    @PreAuthorize("isOwner(#id)")
    @GetMapping("/books/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model,
                            Principal principal) {

        BookViewModel bookViewModel = bookService.findById2(id, principal.getName());
        BookUpdateBindingModel bookModel = modelMapper.map(
                bookViewModel,
                BookUpdateBindingModel.class
        );
//        model.addAttribute("engines", EngineEnum.values());
//        model.addAttribute("transmissions", TransmissionEnum.values());
        model.addAttribute("bookModel", bookModel);

        return "update";
    }
    @PatchMapping("/books/{id}/edit")
    public String editOffer(
            @PathVariable Long id,
            @Valid BookUpdateBindingModel bookModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws ObjectNotFoundException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerModel", bookModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookModel", bindingResult);

            return "redirect:/books/" + id + "/edit";
        }

        BookUpdateServiceModel serviceModel = modelMapper.map(bookModel,
                BookUpdateServiceModel.class);
        serviceModel.setId(id);

        bookService.updateBook(serviceModel);

        return "redirect:/details/" + id ;
    }
    @PreAuthorize("isOwner(#id)")
    //@PreAuthorize("@offerServiceImpl.isOwner(#principal.name, #id)")
    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id,
                              Principal principal) {

        // Most naive approach - check explicitly if the current user is an
        //owner and throw exception if this is not the case.
//        if (!offerService.isOwner(principal.getName(), id)) {
//            throw new RuntimeException();
//        }
        bookService.deleteBook(id);

        return "redirect:/mybooks";
    }

    @ModelAttribute
    public AddBookBindingModel addBookBindingModel(){
        return new AddBookBindingModel();
    }


}
