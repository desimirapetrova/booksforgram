package com.example.booksforgram.repository;

import com.example.booksforgram.model.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
    @Query("select b.description from Blog b")
    String getShortDiscript();
}
