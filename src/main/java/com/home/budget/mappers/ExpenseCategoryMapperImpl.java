package com.home.budget.mappers;

import com.home.budget.entities.ExpenseCategory;
import org.springframework.stereotype.Service;

@Service
public class ExpenseCategoryMapperImpl extends ExpenseCategoryMapper {
    @Override
    public ExpenseCategory mapExpenseCategoryToEntity(ExpenseCategory expenseCategory) {
        return expenseCategory;
    }
}
