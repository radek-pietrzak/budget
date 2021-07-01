package com.home.budget.connectors;

import com.home.budget.entities.ExpenseCategory;
import com.home.budget.requests.GetExpenseCategoryRequest;
import com.home.budget.requests.PostPutExpenseCategoryRequest;
import com.home.budget.responses.GetExpenseCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface ExpenseCategoryApi {

    @PutMapping(path = API.EXPENSE_CATEGORIES)
    ResponseEntity<HttpStatus> editExpenseCategory(@Valid @RequestBody PostPutExpenseCategoryRequest request);

    @PostMapping(path = API.EXPENSE_CATEGORIES)
    ResponseEntity<GetExpenseCategoryResponse> getExpenseCategories(@Valid @RequestBody GetExpenseCategoryRequest request);

    @GetMapping(path = API.EXPENSE_CATEGORIES_ID)
    ResponseEntity<Optional<ExpenseCategory>> getExpenseCategory(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.EXPENSE_CATEGORIES_ID)
    ResponseEntity<HttpStatus> deleteExpenseCategory(@NotNull @PathVariable String id);

}
