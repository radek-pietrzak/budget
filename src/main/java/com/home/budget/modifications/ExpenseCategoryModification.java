package com.home.budget.modifications;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@ApiModel(description = "ExpenseCategory modification")
public class ExpenseCategoryModification {

    @ApiModelProperty(value = "ExpenseCategory id", required = true, example = "5")
    @Size(max = 18)
    private String id;

    @ApiModelProperty(value = "Category name", required = true, example = "Some name")
    @Size(max = 64)
    @NotBlank
    private String categoryName;
}
