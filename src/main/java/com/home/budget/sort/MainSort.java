package com.home.budget.sort;

import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class MainSort {
    private static final String DEFAULT_SORT_COLUMN = "id";
    private final List<SearchSortCriteria> searchSortCriteria;

    public Sort orders() {
        Sort defaultSort = Sort.by(Sort.Direction.DESC, DEFAULT_SORT_COLUMN);
        Sort.Order defaultOrder = new Sort.Order(Sort.Direction.DESC, DEFAULT_SORT_COLUMN, Sort.NullHandling.NULLS_FIRST);

        if (CollectionUtils.isEmpty(searchSortCriteria)) {
            return defaultSort;
        }
        final List<Sort.Order> orders = searchSortCriteria.stream()
                .map(item -> sortByKey(item.getOperation(), item.getKey()))
                .collect(toList());

        orders.add(defaultOrder);

        return Sort.by(orders);
    }

    private Sort.Order sortByKey(final SortType operation, final String key) {
        if (SortType.ASC.equals(operation)) {
            return new Sort.Order(Sort.Direction.ASC, key, Sort.NullHandling.NULLS_LAST);
        } else {
            return new Sort.Order(Sort.Direction.DESC, key, Sort.NullHandling.NULLS_FIRST);
        }
    }
}
