package com.home.budget.mappers.categories.implementations;

import com.home.budget.entities.categories.IncomeCategory;
import com.home.budget.mappers.categories.IncomeCategoryMapper;
import org.springframework.stereotype.Service;

@Service
public class IncomeCategoryMapperImpl extends IncomeCategoryMapper {
    @Override
    public IncomeCategory mapIncomeCategoryToEntity(IncomeCategory incomeCategory) {
        return incomeCategory;
    }
}
