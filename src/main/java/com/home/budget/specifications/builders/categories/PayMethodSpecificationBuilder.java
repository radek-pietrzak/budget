package com.home.budget.specifications.builders.categories;

import com.home.budget.entities.categories.PayMethod;
import com.home.budget.specifications.categories.PayMethodSpecification;
import com.home.budget.specifications.SearchSpecCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PayMethodSpecificationBuilder {

    private final List<SearchSpecCriteria> criteria;

    public Specification<PayMethod> build() {
        if (CollectionUtils.isEmpty(criteria)) {
            return null;
        }

        final List<PayMethodSpecification> specifications = criteria.stream()
                .map(PayMethodSpecification::new)
                .collect(Collectors.toList());

        Specification<PayMethod> result = Specification.where(null);
        for (PayMethodSpecification specification : specifications) {
            result = result.and(specification);
        }

        return result;
    }


}
