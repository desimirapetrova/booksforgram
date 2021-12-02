package com.example.booksforgram.service;

import com.example.booksforgram.model.entity.Condition;
import com.example.booksforgram.model.entity.enums.ConditionEnum;

public interface ConditionService {
    Condition findByConditionEnum(ConditionEnum condition);

    void initConditions();
}
