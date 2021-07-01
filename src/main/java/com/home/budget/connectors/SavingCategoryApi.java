package com.home.budget.connectors;

import com.home.budget.entities.SavingCategory;
import com.home.budget.requests.GetSavingCategoryRequest;
import com.home.budget.requests.PostPutSavingCategoryRequest;
import com.home.budget.responses.GetSavingCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface SavingCategoryApi {

    @PutMapping(path = API.SAVING_CATEGORIES)
    ResponseEntity<HttpStatus> editSavingCategory(@Valid @RequestBody PostPutSavingCategoryRequest request);

    @PostMapping(path = API.SAVING_CATEGORIES)
    ResponseEntity<GetSavingCategoryResponse> getSavingCategories(@Valid @RequestBody GetSavingCategoryRequest request);

    @GetMapping(path = API.SAVING_CATEGORIES_ID)
    ResponseEntity<Optional<SavingCategory>> getSavingCategory(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.SAVING_CATEGORIES_ID)
    ResponseEntity<HttpStatus> deleteSavingCategory(@NotNull @PathVariable String id);

}
