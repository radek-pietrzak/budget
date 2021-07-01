package com.home.budget.mappers;

import com.home.budget.entities.ExpenseCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ExpenseCategoryMapper {
    public abstract ExpenseCategory mapExpenseCategoryToEntity(final ExpenseCategory expenseCategory);
}
