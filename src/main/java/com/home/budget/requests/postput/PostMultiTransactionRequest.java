package com.home.budget.requests.postput;

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
public class PostMultiTransactionRequest {

    @ApiModelProperty(value = "Object of multi-transaction", required = true)
    @NotNull
    @Valid
    private String multiTransaction;
}
