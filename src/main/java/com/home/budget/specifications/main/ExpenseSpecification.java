package com.home.budget.specifications.main;

import com.home.budget.entities.categories.ExpenseCategory;
import com.home.budget.entities.main.Expense;
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
public class ExpenseSpecification implements Specification<Expense> {

    private final SearchSpecCriteria criteria;

    @Override
    public Predicate toPredicate(
            final @NonNull Root<Expense> root,
            final @NonNull CriteriaQuery<?> query,
            final @NonNull CriteriaBuilder criteriaBuilder
    ) {

        Join<Expense, ExpenseCategory> expCatJoinRoot = root.join("expenseCategory", JoinType.LEFT);
        Join<Expense, ExpenseCategory> payMethJoinRoot = root.join("payMethod", JoinType.LEFT);

        if (EQUAL.equals(criteria.getOperation())) {
            final Path<Object> objectPath = root.get((criteria.getKey()));
            return criteriaBuilder.equal(objectPath, criteria.getContent());

        } else if (CONTAINS.equals(criteria.getOperation()) && null != criteria.getContent()) {

            final Predicate predicateByUser = criteriaBuilder
                    .like(criteriaBuilder.lower(root.get("user")),
                            "%" + criteria.getContent().toLowerCase(Locale.ROOT) + "%");

            final Predicate predicateByCurrency = criteriaBuilder
                    .like(criteriaBuilder.lower(root.get("currency")),
                            "%" + criteria.getContent().toLowerCase(Locale.ROOT) + "%");


            final Predicate predicateByDescription = criteriaBuilder
                    .like(criteriaBuilder.lower(root.get("description")),
                            "%" + criteria.getContent().toLowerCase(Locale.ROOT) + "%");

            final Predicate predicateByCategory = criteriaBuilder
                    .like(criteriaBuilder.lower(expCatJoinRoot.get("categoryName")),
                            "%" + criteria.getContent().toLowerCase(Locale.ROOT) + "%");

            final Predicate predicateByPayMethod = criteriaBuilder
                    .like(criteriaBuilder.lower(payMethJoinRoot.get("payMethodName")),
                            "%" + criteria.getContent().toLowerCase(Locale.ROOT) + "%");


            return criteriaBuilder.or(predicateByUser,
                    predicateByCurrency,
                    predicateByDescription,
                    predicateByCategory,
                    predicateByPayMethod);

        } else if (GREATER.equals(criteria.getOperation()) && "payDate".equals(criteria.getKey()) && null != criteria.getContent()) {
            final LocalDate date = LocalDate.parse(criteria.getContent(), DateTimeFormatter.ISO_DATE);

            return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), date);

        } else if (LESS.equals(criteria.getOperation()) && "payDate".equals(criteria.getKey()) && null != criteria.getContent()) {
            final LocalDate date = LocalDate.parse(criteria.getContent(), DateTimeFormatter.ISO_DATE);

            return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), date);

        } else if (GREATER.equals(criteria.getOperation()) && "amount".equals(criteria.getKey()) && null != criteria.getContent()) {
            final Double amount = Double.valueOf(criteria.getContent());

            return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), amount);

        } else if (LESS.equals(criteria.getOperation()) && "amount".equals(criteria.getKey()) && null != criteria.getContent()) {
            final Double amount = Double.valueOf(criteria.getContent());

            return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), amount);
        }

        return null;

    }

}
