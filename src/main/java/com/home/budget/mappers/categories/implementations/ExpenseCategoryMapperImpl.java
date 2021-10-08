package com.home.budget.mappers.categories.implementations;

import com.home.budget.entities.categories.ExpenseCategory;
import com.home.budget.mappers.categories.ExpenseCategoryMapper;
import org.springframework.stereotype.Service;

@Service
public class ExpenseCategoryMapperImpl extends ExpenseCategoryMapper {
    @Override
    public ExpenseCategory mapExpenseCategoryToEntity(ExpenseCategory expenseCategory) {
        return expenseCategory;
    }
}
