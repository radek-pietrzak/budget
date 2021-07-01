package com.home.budget.mappers;

import com.home.budget.entities.InvestmentCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class InvestmentCategoryMapper {
    public abstract InvestmentCategory mapInvestmentCategoryToEntity(final InvestmentCategory investmentCategory);
}
