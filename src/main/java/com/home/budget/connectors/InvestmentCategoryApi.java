package com.home.budget.connectors;

import com.home.budget.entities.InvestmentCategory;
import com.home.budget.requests.GetInvestmentCategoryRequest;
import com.home.budget.requests.PostPutInvestmentCategoryRequest;
import com.home.budget.responses.GetInvestmentCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface InvestmentCategoryApi {

    @PutMapping(path = API.INVESTMENT_CATEGORIES)
    ResponseEntity<HttpStatus> editInvestmentCategory(@Valid @RequestBody PostPutInvestmentCategoryRequest request);

    @PostMapping(path = API.INVESTMENT_CATEGORIES)
    ResponseEntity<GetInvestmentCategoryResponse> getInvestmentCategories(@Valid @RequestBody GetInvestmentCategoryRequest request);

    @GetMapping(path = API.INVESTMENT_CATEGORIES_ID)
    ResponseEntity<Optional<InvestmentCategory>> getInvestmentCategory(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.INVESTMENT_CATEGORIES_ID)
    ResponseEntity<HttpStatus> deleteInvestmentCategory(@NotNull @PathVariable String id);

}
