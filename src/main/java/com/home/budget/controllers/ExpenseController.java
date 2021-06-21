package com.home.budget.controllers;

import com.home.budget.connectors.API;
import com.home.budget.entities.Expense;
import com.home.budget.entities.ExpenseCategory;
import com.home.budget.entities.PayMethod;
import com.home.budget.mappers.ExpenseMapperImpl;
import com.home.budget.repositories.ExpenseCategoryRepository;
import com.home.budget.repositories.ExpenseRepository;
import com.home.budget.repositories.PayMethodRepository;
import com.home.budget.requests.GetExpenseRequest;
import com.home.budget.responses.GetExpenseResponse;
import com.home.budget.sort.ExpenseSort;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping
@AllArgsConstructor
@Api
public class ExpenseController {

    private final ExpenseRepository expenseRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final PayMethodRepository payMethodRepository;
    private final ExpenseMapperImpl expenseMapper;

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
    public GetExpenseResponse getExpenses(GetExpenseRequest request) {
        final Sort orders = new ExpenseSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Page<Expense> expensePage = expenseRepository.findAll(pageRequest);
        return GetExpenseResponse.builder()
                .expenses(expensePage.map(expenseMapper::mapExpenseToEntity).toList())
                .totalPages(expensePage.getTotalPages())
                .hasNextPage(expensePage.hasNext())
                .build();
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
