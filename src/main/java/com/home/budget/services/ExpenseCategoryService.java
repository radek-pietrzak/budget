package com.home.budget.services;

import com.home.budget.entities.ExpenseCategory;
import com.home.budget.mappers.ExpenseCategoryMapperImpl;
import com.home.budget.modifications.ExpenseCategoryModification;
import com.home.budget.repositories.ExpenseCategoryRepository;
import com.home.budget.requests.GetExpenseCategoryRequest;
import com.home.budget.requests.PostPutExpenseCategoryRequest;
import com.home.budget.responses.GetExpenseCategoryResponse;
import com.home.budget.sort.MainSort;
import com.home.budget.specifications.ExpenseCategorySpecificationBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ExpenseCategoryService {

    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseCategoryMapperImpl expenseCategoryMapper;

    @Transactional
    public void editExpenseCategory(PostPutExpenseCategoryRequest request) {

        if (request.getExpenseCategory().getId() == null) {
            request.getExpenseCategory().setId("0");
        }

        ExpenseCategoryModification expenseCategory = request.getExpenseCategory();

        ExpenseCategory expenseCategoryBuild = ExpenseCategory.builder()
                .id(Long.valueOf(expenseCategory.getId()))
                .categoryName(expenseCategory.getCategoryName())
                .build();

        expenseCategoryRepository.save(expenseCategoryBuild);
    }

    public GetExpenseCategoryResponse getExpenseCategories(GetExpenseCategoryRequest request) {
        final Sort orders = new MainSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Specification<ExpenseCategory> specifications = new ExpenseCategorySpecificationBuilder(request.getSearchSpecCriteria()).build();
        final Page<ExpenseCategory> expenseCategoryPage = expenseCategoryRepository.findAll(specifications, pageRequest);

        return GetExpenseCategoryResponse.builder()
                .expenseCategories(expenseCategoryPage.map(expenseCategoryMapper::mapExpenseCategoryToEntity).toList())
                .totalPages(expenseCategoryPage.getTotalPages())
                .hasNextPage(expenseCategoryPage.hasNext())
                .build();
    }

}
