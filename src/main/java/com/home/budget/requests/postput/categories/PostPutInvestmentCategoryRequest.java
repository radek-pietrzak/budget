package com.home.budget.requests.postput.categories;

import com.home.budget.modifications.categories.InvestmentCategoryModification;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PostPutInvestmentCategoryRequest {

    @ApiModelProperty(value = "Object of investment category modification", required = true)
    @NotNull
    @Valid
    private InvestmentCategoryModification investmentCategory;
}
