package com.example.booksforgram.web;

import com.example.booksforgram.model.binding.NewCommentBindingModel;
import com.example.booksforgram.model.entity.Comment;
import com.example.booksforgram.model.service.CommentServiceModel;
import com.example.booksforgram.model.validation.ApiError;
import com.example.booksforgram.model.view.CommentViewModel;
import com.example.booksforgram.repository.CommentRepository;
import com.example.booksforgram.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;

    public CommentRestController(CommentService commentService,
                                 ModelMapper modelMapper, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/api/{bookId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(
            @PathVariable Long bookId,
            Principal principal, Model model
            ) {
        return ResponseEntity.ok(
                commentService.getComments(bookId));

    }

    @PostMapping("/api/{bookId}/comments")
    public ResponseEntity<CommentViewModel> newComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long bookId,
            @RequestBody @Valid NewCommentBindingModel newCommentBindingModel

    ) {
        CommentServiceModel serviceModel =
                modelMapper.map(newCommentBindingModel, CommentServiceModel.class);
        serviceModel.setBookId(bookId);
        serviceModel.setCreator(principal.getUsername());
//        serviceModel.setMessage(newCommentBindingModel.getMessage());

        CommentViewModel newComment =
                commentService.createComment(serviceModel);

        URI locationOfNewComment =
                URI.create(String.format("/api/%s/comments/%s", bookId, newComment.getCommentId()));

        return ResponseEntity.
                created(locationOfNewComment).
                body(newComment);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }

}