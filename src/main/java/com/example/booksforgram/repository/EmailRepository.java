package com.example.booksforgram.repository;

import com.example.booksforgram.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email,Long> {
    List<Email> findByEmail(String email);

    Optional<Email> findByEmailIgnoreCase(String email);
}
