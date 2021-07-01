package com.home.budget.controllers;

import com.home.budget.connectors.ExpenseCategoryApi;
import com.home.budget.entities.ExpenseCategory;
import com.home.budget.repositories.ExpenseCategoryRepository;
import com.home.budget.requests.GetExpenseCategoryRequest;
import com.home.budget.requests.PostPutExpenseCategoryRequest;
import com.home.budget.responses.GetExpenseCategoryResponse;
import com.home.budget.services.ExpenseCategoryService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping
@AllArgsConstructor
@Api(tags = "ExpenseCategory Controller")
public class ExpenseCategoryController implements ExpenseCategoryApi {

    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseCategoryService expenseCategoryService;

    @Override
    public ResponseEntity<HttpStatus> editExpenseCategory(PostPutExpenseCategoryRequest request) {
        expenseCategoryService.editExpenseCategory(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<GetExpenseCategoryResponse> getExpenseCategories(GetExpenseCategoryRequest request) {
        GetExpenseCategoryResponse response = expenseCategoryService.getExpenseCategories(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Optional<ExpenseCategory>> getExpenseCategory(String id) {
        Optional<ExpenseCategory> expenseCategory = expenseCategoryRepository.findById(Long.valueOf(id));
        return ResponseEntity.ok(expenseCategory);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteExpenseCategory(String id) {
        if (expenseCategoryRepository.existsById(Long.valueOf(id))) {
            expenseCategoryRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
