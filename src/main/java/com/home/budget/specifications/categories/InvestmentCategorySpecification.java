package com.home.budget.specifications.categories;

import com.home.budget.entities.categories.InvestmentCategory;
import com.home.budget.specifications.SearchSpecCriteria;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Locale;

import static com.home.budget.specifications.SpecificationType.CONTAINS;
import static com.home.budget.specifications.SpecificationType.EQUAL;

@RequiredArgsConstructor
public class InvestmentCategorySpecification implements Specification<InvestmentCategory> {

    private final SearchSpecCriteria criteria;

    @Override
    public Predicate toPredicate(
            final @NonNull Root<InvestmentCategory> root,
            final @NonNull CriteriaQuery<?> query,
            final @NonNull CriteriaBuilder criteriaBuilder
    ) {

        if (EQUAL.equals(criteria.getOperation())) {
            final Path<Object> objectPath = root.get((criteria.getKey()));
            return criteriaBuilder.equal(objectPath, criteria.getValue());

        } else if (CONTAINS.equals(criteria.getOperation())) {

            final Predicate predicateById = criteriaBuilder
                    .like(root.get("id").as(String.class), "%" + criteria.getValue() + "%");

            final Predicate predicateByCategoryName = criteriaBuilder
                    .like(criteriaBuilder.lower(root.get("categoryName")),
                            "%" + criteria.getValue().toLowerCase(Locale.ROOT) + "%");


            return criteriaBuilder.or(predicateById, predicateByCategoryName);

        }

        return null;

    }

}
