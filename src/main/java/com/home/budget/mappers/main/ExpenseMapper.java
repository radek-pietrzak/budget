package com.home.budget.mappers.main;

import com.home.budget.entities.main.Expense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ExpenseMapper {
    public abstract Expense mapExpenseToEntity(final Expense expense);
}
