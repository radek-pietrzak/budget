package com.home.budget.specifications;

import com.home.budget.entities.SavingCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SavingCategorySpecificationBuilder {

    private final List<SearchSpecCriteria> criteria;

    public Specification<SavingCategory> build() {
        if (CollectionUtils.isEmpty(criteria)) {
            return null;
        }

        final List<SavingCategorySpecification> specifications = criteria.stream()
                .map(SavingCategorySpecification::new)
                .collect(Collectors.toList());

        Specification<SavingCategory> result = Specification.where(null);
        for (SavingCategorySpecification specification : specifications) {
            result = result.and(specification);
        }

        return result;
    }


}
