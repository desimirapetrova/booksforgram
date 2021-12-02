package com.example.booksforgram.service.impl;

import com.example.booksforgram.model.entity.Category;
import com.example.booksforgram.model.entity.Condition;
import com.example.booksforgram.model.entity.enums.CategoryEnum;
import com.example.booksforgram.model.entity.enums.ConditionEnum;
import com.example.booksforgram.repository.ConditionRepository;
import com.example.booksforgram.service.ConditionService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ConditionServiceImpl implements ConditionService {
    private final ConditionRepository conditionRepository;

    public ConditionServiceImpl(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public Condition findByConditionEnum(ConditionEnum condition) {
        return conditionRepository.findByName(condition).orElse(null);

    }

    @Override
    public void initConditions() {
        if(conditionRepository.count()!=0){
            return;
        }
        Arrays.stream(ConditionEnum.values()).forEach(conditionEnum -> {
            Condition condition=new Condition();
            condition.setName(conditionEnum);
            conditionRepository.save(condition);
        });
    }
}
