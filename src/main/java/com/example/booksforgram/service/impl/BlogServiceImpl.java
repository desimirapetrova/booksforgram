package com.example.booksforgram.service.impl;

import com.example.booksforgram.model.entity.Blog;
import com.example.booksforgram.model.entity.Picture;
import com.example.booksforgram.model.service.AddBlogServiceModel;
import com.example.booksforgram.repository.BlogRepository;
import com.example.booksforgram.repository.PictureRepository;
import com.example.booksforgram.repository.UserRepository;
import com.example.booksforgram.service.BlogService;
import com.example.booksforgram.service.CloudinaryImage;
import com.example.booksforgram.service.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final BlogRepository blogRepository;
    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;
    public BlogServiceImpl(ModelMapper modelMapper, CloudinaryService cloudinaryService, BlogRepository blogRepository, PictureRepository pictureRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.blogRepository = blogRepository;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long add(AddBlogServiceModel addBlogServiceModel, String author) throws IOException {
        MultipartFile img = addBlogServiceModel.getPicture();

        Blog blog = modelMapper
                .map(addBlogServiceModel, Blog.class);
                blog.setPicture(getPictureEntity(img));
                blog.setAuthor(userRepository.findByUsername(author).get());
        blog =blogRepository.save(blog);

        return blog.getId();
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll().stream()
                .map(blog -> modelMapper.map(blog, Blog.class))
                .collect(Collectors.toList());
    }

    @Override
    public String getShortDescrip() {
        return blogRepository.getShortDiscript();
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    private Picture getPictureEntity(MultipartFile img) throws IOException {
            final CloudinaryImage uploaded = cloudinaryService.upload(img);
            Picture picture=new Picture();
            picture.setUrl(uploaded.getUrl());
            picture.setPublicId(uploaded.getPublicId());
            return pictureRepository.save(picture);
    }
}
