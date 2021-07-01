package com.home.budget.specifications;

import com.home.budget.entities.IncomeCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class IncomeCategorySpecificationBuilder {

    private final List<SearchSpecCriteria> criteria;

    public Specification<IncomeCategory> build() {
        if (CollectionUtils.isEmpty(criteria)) {
            return null;
        }

        final List<IncomeCategorySpecification> specifications = criteria.stream()
                .map(IncomeCategorySpecification::new)
                .collect(Collectors.toList());

        Specification<IncomeCategory> result = Specification.where(null);
        for (IncomeCategorySpecification specification : specifications) {
            result = result.and(specification);
        }

        return result;
    }


}
