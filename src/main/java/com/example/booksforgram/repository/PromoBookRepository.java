package com.example.booksforgram.repository;

import com.example.booksforgram.model.entity.PromoBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoBookRepository extends JpaRepository<PromoBook,Long> {
}
