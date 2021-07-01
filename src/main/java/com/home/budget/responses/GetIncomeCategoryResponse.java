package com.home.budget.responses;

import com.home.budget.entities.IncomeCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Income categories")
public class GetIncomeCategoryResponse extends PageableResponse{

    @ApiModelProperty
    private List<IncomeCategory> incomeCategories;
}
