package com.example.booksforgram.repository;

import com.example.booksforgram.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("select c from Comment c order by c.created desc ")
    List<Comment>findAllOrderedByCreated();
}
