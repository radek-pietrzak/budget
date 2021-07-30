package com.home.budget.mappers.main;

import com.home.budget.entities.main.Expense;
import com.home.budget.responses.ExpenseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ExpenseMapper {
    public abstract ExpenseResponse mapExpenseToEntity(final Expense expense);
}
