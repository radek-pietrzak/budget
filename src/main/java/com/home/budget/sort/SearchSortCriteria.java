package com.home.budget.sort;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(example = "id")
    private String key;
    @ApiModelProperty(example = "ASC")
    private SortType operation;
}
