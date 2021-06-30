package com.home.budget.specifications;

import com.home.budget.entities.Income;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class IncomeSpecificationBuilder {

    private final List<SearchSpecCriteria> criteria;

    public Specification<Income> build() {
        if (CollectionUtils.isEmpty(criteria)) {
            return null;
        }

        final List<IncomeSpecification> specifications = criteria.stream()
                .map(IncomeSpecification::new)
                .collect(Collectors.toList());

        Specification<Income> result = Specification.where(null);
        for (IncomeSpecification specification : specifications) {
            result = result.and(specification);
        }

        return result;
    }


}
