package com.home.budget.controllers;

import com.home.budget.connectors.ExpenseApi;
import com.home.budget.entities.Expense;
import com.home.budget.repositories.ExpenseRepository;
import com.home.budget.requests.GetExpenseRequest;
import com.home.budget.requests.PostPutExpenseRequest;
import com.home.budget.responses.GetExpenseResponse;
import com.home.budget.services.ExpenseService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
@AllArgsConstructor
@Api(tags = "Expense Controller")
public class ExpenseController implements ExpenseApi {

    private final ExpenseRepository expenseRepository;
    private final ExpenseService expenseService;

    @Override
    public ResponseEntity<HttpStatus> editExpense(PostPutExpenseRequest request) {
        expenseService.editExpense(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<GetExpenseResponse> getExpenses(GetExpenseRequest request) {
        GetExpenseResponse response = expenseService.getExpenses(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Optional<Expense>> getExpense(String id) {
        Optional<Expense> expense = expenseRepository.findById(Long.valueOf(id));
        return ResponseEntity.ok(expense);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteExpense(String id) {
        if (expenseRepository.existsById(Long.valueOf(id))) {
            expenseRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
