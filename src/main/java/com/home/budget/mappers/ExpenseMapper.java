package com.home.budget.mappers;

import com.home.budget.entities.Expense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ExpenseMapper {
    public abstract Expense mapExpenseToEntity(final Expense expense);
}
