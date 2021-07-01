package com.home.budget.mappers;

import com.home.budget.entities.InvestmentCategory;
import org.springframework.stereotype.Service;

@Service
public class InvestmentCategoryMapperImpl extends InvestmentCategoryMapper {
    @Override
    public InvestmentCategory mapInvestmentCategoryToEntity(InvestmentCategory investmentCategory) {
        return investmentCategory;
    }
}
