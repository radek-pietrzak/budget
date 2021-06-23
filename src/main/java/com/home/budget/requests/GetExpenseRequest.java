package com.home.budget.requests;

import com.home.budget.sort.SearchSortCriteria;
import com.home.budget.spec.SearchSpecCriteria;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ApiModel(description = "Get expenses")
public class GetExpenseRequest extends PageableRequest {
    private List<SearchSpecCriteria> searchSpecCriteria;
    private List<SearchSortCriteria> searchSortCriteria;
}

