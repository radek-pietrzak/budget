package com.home.budget.connectors.categories;

import com.home.budget.connectors.API;
import com.home.budget.entities.categories.ExpenseCategory;
import com.home.budget.entities.main.Expense;
import com.home.budget.requests.get.categories.GetExpenseCategoryRequest;
import com.home.budget.requests.postput.categories.PostPutExpenseCategoryRequest;
import com.home.budget.responses.categories.GetExpenseCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
public interface ExpenseCategoryApi {

    @PutMapping(path = API.EXPENSE_CATEGORIES)
    ResponseEntity<HttpStatus> editExpenseCategory(@Valid @RequestBody PostPutExpenseCategoryRequest request);

    @PostMapping(path = API.EXPENSE_CATEGORIES)
    ResponseEntity<GetExpenseCategoryResponse> getExpenseCategories(@Valid @RequestBody GetExpenseCategoryRequest request);

    @GetMapping(path = API.EXPENSE_CATEGORIES_ID)
    ResponseEntity<Optional<ExpenseCategory>> getExpenseCategory(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.EXPENSE_CATEGORIES_ID)
    ResponseEntity<HttpStatus> deleteExpenseCategory(@NotNull @PathVariable String id);

    @GetMapping(path = API.EXPENSE_CATEGORIES)
    ResponseEntity<List<ExpenseCategory>> getExpenseCategories();

}
