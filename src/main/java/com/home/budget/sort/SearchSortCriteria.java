package com.home.budget.sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchSortCriteria {

    @Size(max = 64)
    private String key;
    private SortType operation;
}
