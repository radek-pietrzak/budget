package com.home.budget.mappers.categories;

import com.home.budget.entities.categories.IncomeCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class IncomeCategoryMapper {
    public abstract IncomeCategory mapIncomeCategoryToEntity(final IncomeCategory incomeCategory);
}
