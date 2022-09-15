package com.home.budget.requests.get;

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
@ApiModel(description = "Get transactions")
public class GetTransactionRequest extends PageableRequest {
    private String requestedDate;
    private List<SearchSpecCriteria> searchSpecCriteria;
    private List<SearchSortCriteria> searchSortCriteria;
}

