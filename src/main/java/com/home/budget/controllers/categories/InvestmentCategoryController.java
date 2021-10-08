package com.home.budget.controllers.categories;

import com.home.budget.connectors.categories.InvestmentCategoryApi;
import com.home.budget.entities.categories.InvestmentCategory;
import com.home.budget.repositories.categories.InvestmentCategoryRepository;
import com.home.budget.requests.get.categories.GetInvestmentCategoryRequest;
import com.home.budget.requests.postput.categories.PostPutInvestmentCategoryRequest;
import com.home.budget.responses.categories.GetInvestmentCategoryResponse;
import com.home.budget.services.categories.InvestmentCategoryService;
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
@Api(tags = "InvestmentCategory Controller")
public class InvestmentCategoryController implements InvestmentCategoryApi {

    private final InvestmentCategoryRepository investmentCategoryRepository;
    private final InvestmentCategoryService investmentCategoryService;

    @Override
    public ResponseEntity<HttpStatus> editInvestmentCategory(PostPutInvestmentCategoryRequest request) {
        investmentCategoryService.editInvestmentCategory(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<GetInvestmentCategoryResponse> getInvestmentCategories(GetInvestmentCategoryRequest request) {
        GetInvestmentCategoryResponse response = investmentCategoryService.getInvestmentCategories(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Optional<InvestmentCategory>> getInvestmentCategory(String id) {
        Optional<InvestmentCategory> investmentCategory = investmentCategoryRepository.findById(Long.valueOf(id));
        return ResponseEntity.ok(investmentCategory);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteInvestmentCategory(String id) {
        if (investmentCategoryRepository.existsById(Long.valueOf(id))) {
            investmentCategoryRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
