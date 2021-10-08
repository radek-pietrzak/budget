package com.home.budget.specifications.builders.main;

import com.home.budget.entities.main.Saving;
import com.home.budget.specifications.main.SavingSpecification;
import com.home.budget.specifications.SearchSpecCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SavingSpecificationBuilder {

    private final List<SearchSpecCriteria> criteria;

    public Specification<Saving> build() {
        if (CollectionUtils.isEmpty(criteria)) {
            return null;
        }

        final List<SavingSpecification> specifications = criteria.stream()
                .map(SavingSpecification::new)
                .collect(Collectors.toList());

        Specification<Saving> result = Specification.where(null);
        for (SavingSpecification specification : specifications) {
            result = result.and(specification);
        }

        return result;
    }


}
