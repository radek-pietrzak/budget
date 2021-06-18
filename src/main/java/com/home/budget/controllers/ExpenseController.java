package com.home.budget.controllers;

import com.home.budget.entities.Expense;
import com.home.budget.repositories.ExpenseRepository;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@AllArgsConstructor
@Api
public class ExpenseController {

    private final ExpenseRepository expenseRepository;

    @PostMapping(path = API.EXPENSES)
    public ResponseEntity<?> addExpense(@RequestBody Expense expense) {
        expenseRepository.save(expense);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = API.EXPENSES)
    public ResponseEntity<List<Expense>> getExpenses() {
        return ResponseEntity.ok(expenseRepository.findAll());
    }

    @GetMapping(path = API.EXPENSES_ID)
    public ResponseEntity<?> getExpense(@PathVariable String id) {
        Optional<Expense> expense = expenseRepository.findById(Long.valueOf(id));
        return ResponseEntity.ok(expense);
    }

    @PutMapping(path = API.EXPENSES)
    public ResponseEntity<?> updateExpense(@RequestBody Expense expense) {
        expenseRepository.saveAndFlush(expense);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping (path = API.EXPENSES_ID)
    public ResponseEntity<?> deleteExpense(@PathVariable String id) {
        expenseRepository.deleteById(Long.valueOf(id));
        return ResponseEntity.accepted().build();
    }


}
