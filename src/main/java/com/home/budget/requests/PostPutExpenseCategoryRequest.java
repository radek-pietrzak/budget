package com.home.budget.requests;

import com.home.budget.modifications.ExpenseCategoryModification;
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
public class PostPutExpenseCategoryRequest {

    @ApiModelProperty(value = "Object of expense category modification", required = true)
    @NotNull
    @Valid
    private ExpenseCategoryModification expenseCategory;
}
