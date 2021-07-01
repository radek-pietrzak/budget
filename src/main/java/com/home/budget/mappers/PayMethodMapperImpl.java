package com.home.budget.mappers;

import com.home.budget.entities.PayMethod;
import org.springframework.stereotype.Service;

@Service
public class PayMethodMapperImpl extends PayMethodMapper {
    @Override
    public PayMethod mapPayMethodToEntity(PayMethod savingCategory) {
        return savingCategory;
    }
}
