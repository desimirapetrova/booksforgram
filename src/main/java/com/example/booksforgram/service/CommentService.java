package com.example.booksforgram.service;

import com.example.booksforgram.model.entity.Comment;
import com.example.booksforgram.model.service.CommentServiceModel;
import com.example.booksforgram.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {
    List<CommentViewModel> getComments(Long bookId);

    CommentViewModel createComment(CommentServiceModel serviceModel);

    List<Comment> findAll();
}
