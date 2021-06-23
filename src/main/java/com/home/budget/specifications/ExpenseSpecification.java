package com.home.budget.specifications;

import com.home.budget.entities.Expense;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import java.util.Locale;

import static com.home.budget.specifications.SpecificationType.*;

@RequiredArgsConstructor
public class ExpenseSpecification implements Specification<Expense> {

    private final SearchSpecCriteria criteria;

    @Override
    public Predicate toPredicate(
            final @NonNull Root<Expense> root,
            final @NonNull CriteriaQuery<?> query,
            final @NonNull CriteriaBuilder criteriaBuilder
    ) {

        if (EQUAL.equals(criteria.getOperation())) {
            final Path<Object> objectPath = root.get((criteria.getKey()));
            return criteriaBuilder.equal(objectPath, criteria.getValue());

        } else if (CONTAINS.equals(criteria.getOperation())) {

            final Predicate predicateById = criteriaBuilder
                    .like(root.get("id").as(String.class), "%" + criteria.getValue() + "%");

            final Predicate predicateByUser = criteriaBuilder
                    .like(criteriaBuilder.lower(root.get("user")),
                            "%" + criteria.getValue().toLowerCase(Locale.ROOT) + "%");

            final Predicate predicateByDescription = criteriaBuilder
                    .like(criteriaBuilder.lower(root.get("description")),
                            "%" + criteria.getValue().toLowerCase(Locale.ROOT) + "%");


            return criteriaBuilder.or(predicateById, predicateByUser, predicateByDescription);
        }
        return null;

    }

}
