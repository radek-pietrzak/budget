package com.home.budget.services.categories;

import com.home.budget.entities.categories.SavingCategory;
import com.home.budget.mappers.categories.implementations.SavingCategoryMapperImpl;
import com.home.budget.modifications.categories.SavingCategoryModification;
import com.home.budget.repositories.categories.SavingCategoryRepository;
import com.home.budget.requests.get.categories.GetSavingCategoryRequest;
import com.home.budget.requests.postput.categories.PostPutSavingCategoryRequest;
import com.home.budget.responses.categories.GetSavingCategoryResponse;
import com.home.budget.sort.MainSort;
import com.home.budget.specifications.builders.categories.SavingCategorySpecificationBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class SavingCategoryService {

    private final SavingCategoryRepository savingCategoryRepository;
    private final SavingCategoryMapperImpl savingCategoryMapper;

    @Transactional
    public void editSavingCategory(PostPutSavingCategoryRequest request) {

        if (request.getSavingCategory().getId() == null) {
            request.getSavingCategory().setId("0");
        }

        SavingCategoryModification savingCategory = request.getSavingCategory();

        SavingCategory savingCategoryBuild = SavingCategory.builder()
                .id(Long.valueOf(savingCategory.getId()))
                .categoryName(savingCategory.getCategoryName())
                .build();

        savingCategoryRepository.save(savingCategoryBuild);
    }

    public GetSavingCategoryResponse getSavingCategories(GetSavingCategoryRequest request) {
        final Sort orders = new MainSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Specification<SavingCategory> specifications = new SavingCategorySpecificationBuilder(request.getSearchSpecCriteria()).build();
        final Page<SavingCategory> savingCategoryPage = savingCategoryRepository.findAll(specifications, pageRequest);

        return GetSavingCategoryResponse.builder()
                .savingCategories(savingCategoryPage.map(savingCategoryMapper::mapSavingCategoryToEntity).toList())
                .totalPages(savingCategoryPage.getTotalPages())
                .hasNextPage(savingCategoryPage.hasNext())
                .build();
    }

}
