package com.home.budget.mappers.categories;

import com.home.budget.entities.categories.ExpenseCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ExpenseCategoryMapper {
    public abstract ExpenseCategory mapExpenseCategoryToEntity(final ExpenseCategory expenseCategory);
}
