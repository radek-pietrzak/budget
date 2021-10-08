package com.home.budget.specifications.categories;

import com.home.budget.entities.categories.ExpenseCategory;
import com.home.budget.specifications.SearchSpecCriteria;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Locale;

import static com.home.budget.specifications.SpecificationType.*;

@RequiredArgsConstructor
public class ExpenseCategorySpecification implements Specification<ExpenseCategory> {

    private final SearchSpecCriteria criteria;

    @Override
    public Predicate toPredicate(
            final @NonNull Root<ExpenseCategory> root,
            final @NonNull CriteriaQuery<?> query,
            final @NonNull CriteriaBuilder criteriaBuilder
    ) {

        if (EQUAL.equals(criteria.getOperation())) {
            final Path<Object> objectPath = root.get((criteria.getKey()));
            return criteriaBuilder.equal(objectPath, criteria.getContent());

        } else if (CONTAINS.equals(criteria.getOperation())) {

            final Predicate predicateById = criteriaBuilder
                    .like(root.get("id").as(String.class), "%" + criteria.getContent() + "%");

            final Predicate predicateByCategoryName = criteriaBuilder
                    .like(criteriaBuilder.lower(root.get("categoryName")),
                            "%" + criteria.getContent().toLowerCase(Locale.ROOT) + "%");


            return criteriaBuilder.or(predicateById, predicateByCategoryName);

        }

        return null;

    }

}
