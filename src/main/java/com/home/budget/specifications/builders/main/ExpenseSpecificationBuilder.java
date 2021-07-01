package com.home.budget.specifications.builders.main;

import com.home.budget.entities.main.Expense;
import com.home.budget.specifications.main.ExpenseSpecification;
import com.home.budget.specifications.SearchSpecCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ExpenseSpecificationBuilder {

    private final List<SearchSpecCriteria> criteria;

    public Specification<Expense> build() {
        if (CollectionUtils.isEmpty(criteria)) {
            return null;
        }

        final List<ExpenseSpecification> specifications = criteria.stream()
                .map(ExpenseSpecification::new)
                .collect(Collectors.toList());

        Specification<Expense> result = Specification.where(null);
        for (ExpenseSpecification specification : specifications) {
            result = result.and(specification);
        }

        return result;
    }


}
