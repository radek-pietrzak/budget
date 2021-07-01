package com.home.budget.connectors;

import com.home.budget.entities.IncomeCategory;
import com.home.budget.requests.GetIncomeCategoryRequest;
import com.home.budget.requests.PostPutIncomeCategoryRequest;
import com.home.budget.responses.GetIncomeCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface IncomeCategoryApi {

    @PutMapping(path = API.INCOME_CATEGORIES)
    ResponseEntity<HttpStatus> editIncomeCategory(@Valid @RequestBody PostPutIncomeCategoryRequest request);

    @PostMapping(path = API.INCOME_CATEGORIES)
    ResponseEntity<GetIncomeCategoryResponse> getIncomeCategories(@Valid @RequestBody GetIncomeCategoryRequest request);

    @GetMapping(path = API.INCOME_CATEGORIES_ID)
    ResponseEntity<Optional<IncomeCategory>> getIncomeCategory(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.INCOME_CATEGORIES_ID)
    ResponseEntity<HttpStatus> deleteIncomeCategory(@NotNull @PathVariable String id);

}
