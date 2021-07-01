package com.home.budget.requests;

import com.home.budget.modifications.IncomeCategoryModification;
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
public class PostPutIncomeCategoryRequest {

    @ApiModelProperty(value = "Object of income category modification", required = true)
    @NotNull
    @Valid
    private IncomeCategoryModification incomeCategory;
}
