package com.home.budget.specifications.builders;

import com.home.budget.entities.Transaction;
import com.home.budget.specifications.SearchSpecCriteria;
import com.home.budget.specifications.TransactionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TransactionSpecificationBuilder {

    private final List<SearchSpecCriteria> criteria;

    public Specification<Transaction> build() {
        if (CollectionUtils.isEmpty(criteria)) {
            return null;
        }

        final List<TransactionSpecification> specifications = criteria.stream()
                .map(TransactionSpecification::new)
                .collect(Collectors.toList());

        Specification<Transaction> result = Specification.where(null);
        for (TransactionSpecification specification : specifications) {
            result = result.and(specification);
        }

        return result;
    }


}
