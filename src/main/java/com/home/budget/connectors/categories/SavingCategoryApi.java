package com.home.budget.connectors.categories;

import com.home.budget.connectors.API;
import com.home.budget.entities.categories.SavingCategory;
import com.home.budget.requests.get.categories.GetSavingCategoryRequest;
import com.home.budget.requests.postput.categories.PostPutSavingCategoryRequest;
import com.home.budget.responses.categories.GetSavingCategoryResponse;
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
