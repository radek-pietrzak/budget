package com.home.budget.requests;

import com.home.budget.modifications.ExpenseModification;
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
public class PostPutExpenseRequest {

    @ApiModelProperty(value = "Object of expense modification", required = true)
    @NotNull
    @Valid
    private ExpenseModification expense;
}
