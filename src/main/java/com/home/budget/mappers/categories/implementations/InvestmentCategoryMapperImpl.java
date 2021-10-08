package com.home.budget.mappers.categories.implementations;

import com.home.budget.entities.categories.InvestmentCategory;
import com.home.budget.mappers.categories.InvestmentCategoryMapper;
import org.springframework.stereotype.Service;

@Service
public class InvestmentCategoryMapperImpl extends InvestmentCategoryMapper {
    @Override
    public InvestmentCategory mapInvestmentCategoryToEntity(InvestmentCategory investmentCategory) {
        return investmentCategory;
    }
}
