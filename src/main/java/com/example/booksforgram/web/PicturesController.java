package com.example.booksforgram.web;

import com.example.booksforgram.model.binding.AddBookBindingModel;
import com.example.booksforgram.model.binding.PictureBindingModel;
import com.example.booksforgram.model.entity.Picture;
import com.example.booksforgram.repository.PictureRepository;
import com.example.booksforgram.service.CloudinaryImage;
import com.example.booksforgram.service.CloudinaryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PicturesController {
    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;

    public PicturesController(CloudinaryService cloudinaryService,
                              PictureRepository pictureRepository) {
        // Do not use repos directly in the controller
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
    }

//    @GetMapping("/add")
//    public String addPicture() {
//        return "add";
//    }

//    @PostMapping("/pictures/add")
//    public String addPicture(PictureBindingModel bindingModel) throws IOException {
//
//        var picture = createPictureEntity(bindingModel.getPicture(),
//                bindingModel.getTitle());
//
//        pictureRepository.save(picture);
//
//        return "redirect:/";
//    }
//
//    private Picture createPictureEntity(MultipartFile file, String title) throws IOException {
//        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);
//        Picture picture=new Picture();
//        picture.setPublicId(uploaded.getPublicId());
//        picture.setTitle(title);
//        picture.setUrl(uploaded.getUrl());
//       return picture;
//    }

}
