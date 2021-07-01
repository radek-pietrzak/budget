package com.home.budget.controllers;

import com.home.budget.connectors.IncomeCategoryApi;
import com.home.budget.entities.IncomeCategory;
import com.home.budget.repositories.IncomeCategoryRepository;
import com.home.budget.requests.GetIncomeCategoryRequest;
import com.home.budget.requests.PostPutIncomeCategoryRequest;
import com.home.budget.responses.GetIncomeCategoryResponse;
import com.home.budget.services.IncomeCategoryService;
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
@Api(tags = "IncomeCategory Controller")
public class IncomeCategoryController implements IncomeCategoryApi {

    private final IncomeCategoryRepository incomeCategoryRepository;
    private final IncomeCategoryService incomeCategoryService;

    @Override
    public ResponseEntity<HttpStatus> editIncomeCategory(PostPutIncomeCategoryRequest request) {
        incomeCategoryService.editIncomeCategory(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<GetIncomeCategoryResponse> getIncomeCategories(GetIncomeCategoryRequest request) {
        GetIncomeCategoryResponse response = incomeCategoryService.getIncomeCategories(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Optional<IncomeCategory>> getIncomeCategory(String id) {
        Optional<IncomeCategory> incomeCategory = incomeCategoryRepository.findById(Long.valueOf(id));
        return ResponseEntity.ok(incomeCategory);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteIncomeCategory(String id) {
        if (incomeCategoryRepository.existsById(Long.valueOf(id))) {
            incomeCategoryRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
