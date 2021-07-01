package com.home.budget.requests.get.categories;

import com.home.budget.requests.PageableRequest;
import com.home.budget.sort.SearchSortCriteria;
import com.home.budget.specifications.SearchSpecCriteria;
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
@ApiModel(description = "Get income categories")
public class GetIncomeCategoryRequest extends PageableRequest {
    private List<SearchSpecCriteria> searchSpecCriteria;
    private List<SearchSortCriteria> searchSortCriteria;
}

