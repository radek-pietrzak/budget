package com.home.budget.mappers;

import com.home.budget.entities.IncomeCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class IncomeCategoryMapper {
    public abstract IncomeCategory mapIncomeCategoryToEntity(final IncomeCategory incomeCategory);
}
