package com.example.booksforgram.repository;

import com.example.booksforgram.model.entity.Category;
import com.example.booksforgram.model.entity.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(CategoryEnum category);
}
