package com.home.budget.controllers.categories;

import com.home.budget.connectors.categories.SavingCategoryApi;
import com.home.budget.entities.categories.SavingCategory;
import com.home.budget.repositories.categories.SavingCategoryRepository;
import com.home.budget.requests.get.categories.GetSavingCategoryRequest;
import com.home.budget.requests.postput.categories.PostPutSavingCategoryRequest;
import com.home.budget.responses.categories.GetSavingCategoryResponse;
import com.home.budget.services.categories.SavingCategoryService;
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
@Api(tags = "SavingCategory Controller")
public class SavingCategoryController implements SavingCategoryApi {

    private final SavingCategoryRepository savingCategoryRepository;
    private final SavingCategoryService savingCategoryService;

    @Override
    public ResponseEntity<HttpStatus> editSavingCategory(PostPutSavingCategoryRequest request) {
        savingCategoryService.editSavingCategory(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<GetSavingCategoryResponse> getSavingCategories(GetSavingCategoryRequest request) {
        GetSavingCategoryResponse response = savingCategoryService.getSavingCategories(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Optional<SavingCategory>> getSavingCategory(String id) {
        Optional<SavingCategory> savingCategory = savingCategoryRepository.findById(Long.valueOf(id));
        return ResponseEntity.ok(savingCategory);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteSavingCategory(String id) {
        if (savingCategoryRepository.existsById(Long.valueOf(id))) {
            savingCategoryRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
