package com.example.booksforgram.repository;

import com.example.booksforgram.model.entity.Condition;
import com.example.booksforgram.model.entity.enums.ConditionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConditionRepository extends JpaRepository<Condition,Long> {
    Optional<Condition> findByName(ConditionEnum condition);
}
