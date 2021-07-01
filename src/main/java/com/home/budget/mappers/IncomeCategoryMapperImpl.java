package com.home.budget.mappers;

import com.home.budget.entities.IncomeCategory;
import org.springframework.stereotype.Service;

@Service
public class IncomeCategoryMapperImpl extends IncomeCategoryMapper {
    @Override
    public IncomeCategory mapIncomeCategoryToEntity(IncomeCategory incomeCategory) {
        return incomeCategory;
    }
}
