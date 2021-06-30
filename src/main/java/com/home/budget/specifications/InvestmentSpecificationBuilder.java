package com.home.budget.specifications;

import com.home.budget.entities.Investment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class InvestmentSpecificationBuilder {

    private final List<SearchSpecCriteria> criteria;

    public Specification<Investment> build() {
        if (CollectionUtils.isEmpty(criteria)) {
            return null;
        }

        final List<InvestmentSpecification> specifications = criteria.stream()
                .map(InvestmentSpecification::new)
                .collect(Collectors.toList());

        Specification<Investment> result = Specification.where(null);
        for (InvestmentSpecification specification : specifications) {
            result = result.and(specification);
        }

        return result;
    }


}
