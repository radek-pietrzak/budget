package com.home.budget.specifications.main;

import com.home.budget.entities.main.Income;
import com.home.budget.specifications.SearchSpecCriteria;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.home.budget.specifications.SpecificationType.*;

@RequiredArgsConstructor
public class IncomeSpecification implements Specification<Income> {

    private final SearchSpecCriteria criteria;

    @Override
    public Predicate toPredicate(
            final @NonNull Root<Income> root,
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

        } else if (GREATER.equals(criteria.getOperation())) {
            final LocalDate date = LocalDate.parse(criteria.getValue(), DateTimeFormatter.ISO_DATE);

            return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), date);

        } else if (LESS.equals(criteria.getOperation())) {
            final LocalDate date = LocalDate.parse(criteria.getValue(), DateTimeFormatter.ISO_DATE);

            return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), date);
        }

        return null;

    }

}
