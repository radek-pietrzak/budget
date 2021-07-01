package com.home.budget.specifications;

import com.home.budget.entities.ExpenseCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ExpenseCategorySpecificationBuilder {

    private final List<SearchSpecCriteria> criteria;

    public Specification<ExpenseCategory> build() {
        if (CollectionUtils.isEmpty(criteria)) {
            return null;
        }

        final List<ExpenseCategorySpecification> specifications = criteria.stream()
                .map(ExpenseCategorySpecification::new)
                .collect(Collectors.toList());

        Specification<ExpenseCategory> result = Specification.where(null);
        for (ExpenseCategorySpecification specification : specifications) {
            result = result.and(specification);
        }

        return result;
    }


}
