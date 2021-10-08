package com.home.budget.mappers.main.implemenations;

import com.home.budget.entities.main.Expense;
import com.home.budget.mappers.main.ExpenseMapper;
import org.springframework.stereotype.Service;

@Service
public class ExpenseMapperImpl extends ExpenseMapper {
    @Override
    public Expense mapExpenseToEntity(Expense expense) {
        return expense;
    }
}
