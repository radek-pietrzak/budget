package com.home.budget.mappers.main.implemenations;

import com.home.budget.entities.main.Expense;
import com.home.budget.mappers.main.ExpenseMapper;
import com.home.budget.responses.ExpenseResponse;
import org.springframework.stereotype.Service;

@Service
public class ExpenseMapperImpl extends ExpenseMapper {
    @Override
    public ExpenseResponse mapExpenseToEntity(Expense expense) {
        ExpenseResponse expenseResponse = new ExpenseResponse();
        expenseResponse.setId(expense.getId().toString());
        expenseResponse.setUser(expense.getUser());
        expenseResponse.setAmount(expense.getAmount().toString());
        expenseResponse.setCurrency(expense.getCurrency());
        expenseResponse.setDescription(expense.getDescription());
        expenseResponse.setPayDate(expense.getPayDate());
        expenseResponse.setPayMethod(expense.getPayMethod().getPayMethodName());
        expenseResponse.setExpenseCategory(expense.getExpenseCategory().getCategoryName());
        expenseResponse.setCreatedUser(expense.getCreatedUser());
        expenseResponse.setUpdatedUser(expense.getUpdatedUser());
        expenseResponse.setCreatedDate(expense.getCreatedDate());
        expenseResponse.setUpdatedDate(expense.getUpdatedDate());
        return expenseResponse;
    }
}
