package com.home.budget.controllers;

import com.home.budget.entities.Expense;
import com.home.budget.entities.ExpenseCategory;
import com.home.budget.entities.PayMethod;
import com.home.budget.repositories.ExpenseCategoryRepository;
import com.home.budget.repositories.ExpenseRepository;
import com.home.budget.repositories.PayMethodRepository;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@AllArgsConstructor
@Api
public class ExpenseController {

    private final ExpenseRepository expenseRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final PayMethodRepository payMethodRepository;

    @Transactional
    @RequestMapping(path = API.EXPENSES, method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> addExpense(@RequestBody Expense expense) {
        String payMethodName = expense.getPayMethod().getPayMethodName();

        PayMethod payMethod = payMethodRepository.findAll()
                .stream()
                .filter(name -> name.getPayMethodName().equals(payMethodName))
                .findFirst()
                .orElse(null);

        if (payMethod == null) {
            payMethod = new PayMethod(payMethodName);
            payMethodRepository.save(payMethod);
        }

        PayMethod payMethodFromRepo = payMethodRepository.findAll()
                .stream()
                .filter(name -> name.getPayMethodName().equals(payMethodName))
                .findFirst()
                .orElseThrow();

        String categoryName = expense.getExpenseCategory().getCategoryName();

        ExpenseCategory expenseCategory = expenseCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getCategoryName().equals(categoryName))
                .findFirst()
                .orElse(null);

        if (expenseCategory == null) {
            expenseCategory = new ExpenseCategory(categoryName);
            expenseCategoryRepository.save(expenseCategory);
        }

        ExpenseCategory expenseCategoryFromRepo = expenseCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getCategoryName().equals(categoryName))
                .findFirst()
                .orElseThrow();

        expense.setPayMethod(payMethodFromRepo);
        expense.setExpenseCategory(expenseCategoryFromRepo);
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

    @DeleteMapping(path = API.EXPENSES_ID)
    public ResponseEntity<?> deleteExpense(@PathVariable String id) {
        expenseRepository.deleteById(Long.valueOf(id));
        return ResponseEntity.accepted().build();
    }


}
