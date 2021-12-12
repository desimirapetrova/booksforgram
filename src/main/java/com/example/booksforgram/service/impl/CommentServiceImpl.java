package com.example.booksforgram.service.impl;

import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.Comment;
import com.example.booksforgram.model.service.CommentServiceModel;
import com.example.booksforgram.model.view.CommentViewModel;
import com.example.booksforgram.repository.BookRepository;
import com.example.booksforgram.repository.CommentRepository;
import com.example.booksforgram.repository.UserRepository;
import com.example.booksforgram.service.CommentService;
import com.example.booksforgram.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long bookId) {
        var bookOpt = bookRepository.
                findById(bookId);

//        if (routeOpt.isEmpty()) {
//            throw new ObjectNotFoundException("Route with id " + routeId + " was not found!");
//        }

        return bookOpt.get().getComments(). stream().
                map(this::mapAsComment).
                collect(Collectors.toList());
    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {
        Objects.requireNonNull(commentServiceModel.getCreator());

        var book = bookRepository.
                findById(commentServiceModel.getBookId()).
                orElseThrow(() -> new ObjectNotFoundException("Book with id " + commentServiceModel.getBookId() + " not found!",commentServiceModel.getBookId()));

        var author = userRepository.
                findByUsername(commentServiceModel.getCreator()).
                orElseThrow(() -> new ObjectNotFoundException("User with eamil " + commentServiceModel.getCreator() + " not found!",commentServiceModel.getBookId()));

        Comment newComment = new Comment();
        newComment.setApproved(false);
        newComment.setTextContent(commentServiceModel.getMessage());
        newComment.setCreated(LocalDateTime.now());
        newComment.setBook(book);
        newComment.setAuthor(author);

        Comment savedComment = commentRepository.save(newComment);

        return mapAsComment(savedComment);
    }

    @Override
    public List<Comment> findAll() {

        return commentRepository.findAll().stream()
                .map(comment -> modelMapper.map(comment, Comment.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findAllOrderByTime() {

        return commentRepository.findAllOrderedByCreated().stream()
                .map(comment -> modelMapper.map(comment, Comment.class))
                .collect(Collectors.toList());
    }

    private CommentViewModel mapAsComment(Comment commentEntity) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.
                setCommentId(commentEntity.getId()).
                setCanApprove(true).
                setCanDelete(true).
                setCreated(commentEntity.getCreated()).
                setMessage(commentEntity.getTextContent()).
                setUser(commentEntity.getAuthor().getUsername());

        return commentViewModel;
    }
}
