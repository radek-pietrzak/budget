package com.home.budget.sort;

import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class MainSort {
    private static final String DEFAULT_SORT_COLUMN = "payDate";
    private final List<SearchSortCriteria> searchSortCriteria;

    public org.springframework.data.domain.Sort orders() {
        if (CollectionUtils.isEmpty(searchSortCriteria)) {
            return org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC, DEFAULT_SORT_COLUMN);
        }
        final List<org.springframework.data.domain.Sort.Order> orders = searchSortCriteria.stream()
                .map(item -> sortByKey(item.getOperation(), item.getKey()))
                .collect(toList());

        return org.springframework.data.domain.Sort.by(orders);
    }

    private org.springframework.data.domain.Sort.Order sortByKey(final SortType operation, final String sort) {
        if (SortType.ASC.equals(operation)) {
            return new org.springframework.data.domain.Sort.Order(org.springframework.data.domain.Sort.Direction.ASC, sort, org.springframework.data.domain.Sort.NullHandling.NULLS_LAST);
        } else {
            return new org.springframework.data.domain.Sort.Order(org.springframework.data.domain.Sort.Direction.DESC, sort, org.springframework.data.domain.Sort.NullHandling.NULLS_FIRST);
        }
    }
}
