package com.home.budget.sort;

import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class ExpenseSort {
    private static final String DEFAULT_SORT_COLUMN = "payDate";
    private final List<SearchSortCriteria> searchSortCriteria;

    public Sort orders() {
        if (CollectionUtils.isEmpty(searchSortCriteria)) {
            return Sort.by(Sort.Direction.ASC, DEFAULT_SORT_COLUMN);
        }
        final List<Sort.Order> orders = searchSortCriteria.stream()
                .map(item -> sortByKey(item.getOperation(), item.getKey()))
                .collect(toList());

        return Sort.by(orders);
    }

    private Sort.Order sortByKey(final SortType operation, final String sort) {
        if (SortType.ASC.equals(operation)) {
            return new Sort.Order(Sort.Direction.ASC, sort, Sort.NullHandling.NULLS_LAST);
        } else {
            return new Sort.Order(Sort.Direction.DESC, sort, Sort.NullHandling.NULLS_FIRST);
        }
    }
}
