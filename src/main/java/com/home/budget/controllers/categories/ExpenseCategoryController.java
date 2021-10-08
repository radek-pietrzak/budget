package com.home.budget.controllers.categories;

import com.home.budget.connectors.categories.ExpenseCategoryApi;
import com.home.budget.entities.categories.ExpenseCategory;
import com.home.budget.repositories.categories.ExpenseCategoryRepository;
import com.home.budget.requests.get.categories.GetExpenseCategoryRequest;
import com.home.budget.requests.postput.categories.PostPutExpenseCategoryRequest;
import com.home.budget.responses.categories.GetExpenseCategoryResponse;
import com.home.budget.services.categories.ExpenseCategoryService;
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
