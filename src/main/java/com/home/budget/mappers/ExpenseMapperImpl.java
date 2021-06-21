package com.home.budget.mappers;

import com.home.budget.entities.Expense;
import org.springframework.stereotype.Service;

@Service
public class ExpenseMapperImpl extends ExpenseMapper {
    @Override
    public Expense mapExpenseToEntity(Expense expense) {
        return expense;
    }
}
