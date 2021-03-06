package com.home.budget.specifications.builders.categories;

import com.home.budget.entities.categories.InvestmentCategory;
import com.home.budget.specifications.categories.InvestmentCategorySpecification;
import com.home.budget.specifications.SearchSpecCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class InvestmentCategorySpecificationBuilder {

    private final List<SearchSpecCriteria> criteria;

    public Specification<InvestmentCategory> build() {
        if (CollectionUtils.isEmpty(criteria)) {
            return null;
        }

        final List<InvestmentCategorySpecification> specifications = criteria.stream()
                .map(InvestmentCategorySpecification::new)
                .collect(Collectors.toList());

        Specification<InvestmentCategory> result = Specification.where(null);
        for (InvestmentCategorySpecification specification : specifications) {
            result = result.and(specification);
        }

        return result;
    }


}
