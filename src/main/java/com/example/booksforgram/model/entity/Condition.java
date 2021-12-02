package com.example.booksforgram.model.entity;

import com.example.booksforgram.model.entity.enums.ConditionEnum;
import org.hibernate.validator.constraints.Currency;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConditionEnum name;

    public Condition() {
    }

    public ConditionEnum getName() {
        return name;
    }

    public void setName(ConditionEnum name) {
        this.name = name;
    }
}
