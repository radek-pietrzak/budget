package com.home.budget.requests;

import com.home.budget.modifications.IncomeModification;
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
public class PostPutIncomeRequest {

    @ApiModelProperty(value = "Object of income modification", required = true)
    @NotNull
    @Valid
    private IncomeModification income;
}
