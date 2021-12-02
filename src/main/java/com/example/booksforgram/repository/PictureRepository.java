package com.example.booksforgram.repository;

import com.example.booksforgram.model.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Long> {
    void deleteAllByPublicId(String publicId);
}
