package com.home.budget.controllers.main;

import com.home.budget.connectors.main.ExpenseApi;
import com.home.budget.entities.main.Expense;
import com.home.budget.repositories.main.ExpenseRepository;
import com.home.budget.requests.get.main.GetExpenseRequest;
import com.home.budget.requests.postput.main.PostPutExpenseRequest;
import com.home.budget.responses.ExpenseResponse;
import com.home.budget.responses.main.GetExpenseResponse;
import com.home.budget.services.main.ExpenseService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<ExpenseResponse> getExpense(String id) {
        ExpenseResponse expenseResponse = expenseService.getExpenseResponse(id);
        return ResponseEntity.ok(expenseResponse);
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

    @Override
    public ResponseEntity<List<Expense>> getExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return ResponseEntity.ok(expenses);
    }


}
