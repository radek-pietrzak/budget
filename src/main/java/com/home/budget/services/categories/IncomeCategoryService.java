package com.home.budget.services.categories;

import com.home.budget.entities.categories.IncomeCategory;
import com.home.budget.mappers.categories.implementations.IncomeCategoryMapperImpl;
import com.home.budget.modifications.categories.IncomeCategoryModification;
import com.home.budget.repositories.categories.IncomeCategoryRepository;
import com.home.budget.requests.get.categories.GetIncomeCategoryRequest;
import com.home.budget.requests.postput.categories.PostPutIncomeCategoryRequest;
import com.home.budget.responses.categories.GetIncomeCategoryResponse;
import com.home.budget.sort.MainSort;
import com.home.budget.specifications.builders.categories.IncomeCategorySpecificationBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class IncomeCategoryService {

    private final IncomeCategoryRepository incomeCategoryRepository;
    private final IncomeCategoryMapperImpl incomeCategoryMapper;

    @Transactional
    public void editIncomeCategory(PostPutIncomeCategoryRequest request) {

        if (request.getIncomeCategory().getId() == null) {
            request.getIncomeCategory().setId("0");
        }

        IncomeCategoryModification incomeCategory = request.getIncomeCategory();

        IncomeCategory incomeCategoryBuild = IncomeCategory.builder()
                .id(Long.valueOf(incomeCategory.getId()))
                .categoryName(incomeCategory.getCategoryName())
                .build();

        incomeCategoryRepository.save(incomeCategoryBuild);
    }

    public GetIncomeCategoryResponse getIncomeCategories(GetIncomeCategoryRequest request) {
        final Sort orders = new MainSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Specification<IncomeCategory> specifications = new IncomeCategorySpecificationBuilder(request.getSearchSpecCriteria()).build();
        final Page<IncomeCategory> incomeCategoryPage = incomeCategoryRepository.findAll(specifications, pageRequest);

        return GetIncomeCategoryResponse.builder()
                .incomeCategories(incomeCategoryPage.map(incomeCategoryMapper::mapIncomeCategoryToEntity).toList())
                .totalPages(incomeCategoryPage.getTotalPages())
                .hasNextPage(incomeCategoryPage.hasNext())
                .build();
    }

}
