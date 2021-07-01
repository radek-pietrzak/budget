package com.home.budget.mappers.categories.implementations;

import com.home.budget.entities.categories.PayMethod;
import com.home.budget.mappers.categories.PayMethodMapper;
import org.springframework.stereotype.Service;

@Service
public class PayMethodMapperImpl extends PayMethodMapper {
    @Override
    public PayMethod mapPayMethodToEntity(PayMethod savingCategory) {
        return savingCategory;
    }
}
