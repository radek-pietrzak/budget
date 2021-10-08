package com.home.budget.mappers.categories;

import com.home.budget.entities.categories.InvestmentCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class InvestmentCategoryMapper {
    public abstract InvestmentCategory mapInvestmentCategoryToEntity(final InvestmentCategory investmentCategory);
}
