package com.home.budget.services;

import com.home.budget.entities.InvestmentCategory;
import com.home.budget.mappers.InvestmentCategoryMapperImpl;
import com.home.budget.modifications.InvestmentCategoryModification;
import com.home.budget.repositories.InvestmentCategoryRepository;
import com.home.budget.requests.GetInvestmentCategoryRequest;
import com.home.budget.requests.PostPutInvestmentCategoryRequest;
import com.home.budget.responses.GetInvestmentCategoryResponse;
import com.home.budget.sort.MainSort;
import com.home.budget.specifications.InvestmentCategorySpecificationBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class InvestmentCategoryService {

    private final InvestmentCategoryRepository investmentCategoryRepository;
    private final InvestmentCategoryMapperImpl investmentCategoryMapper;

    @Transactional
    public void editInvestmentCategory(PostPutInvestmentCategoryRequest request) {

        if (request.getInvestmentCategory().getId() == null) {
            request.getInvestmentCategory().setId("0");
        }

        InvestmentCategoryModification investmentCategory = request.getInvestmentCategory();

        InvestmentCategory investmentCategoryBuild = InvestmentCategory.builder()
                .id(Long.valueOf(investmentCategory.getId()))
                .categoryName(investmentCategory.getCategoryName())
                .build();

        investmentCategoryRepository.save(investmentCategoryBuild);
    }

    public GetInvestmentCategoryResponse getInvestmentCategories(GetInvestmentCategoryRequest request) {
        final Sort orders = new MainSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Specification<InvestmentCategory> specifications = new InvestmentCategorySpecificationBuilder(request.getSearchSpecCriteria()).build();
        final Page<InvestmentCategory> investmentCategoryPage = investmentCategoryRepository.findAll(specifications, pageRequest);

        return GetInvestmentCategoryResponse.builder()
                .investmentCategories(investmentCategoryPage.map(investmentCategoryMapper::mapInvestmentCategoryToEntity).toList())
                .totalPages(investmentCategoryPage.getTotalPages())
                .hasNextPage(investmentCategoryPage.hasNext())
                .build();
    }

}
