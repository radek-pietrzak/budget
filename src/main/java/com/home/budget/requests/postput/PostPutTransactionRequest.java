package com.home.budget.requests.postput;

import com.home.budget.modifications.TransactionModification;
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
public class PostPutTransactionRequest {

    @ApiModelProperty(value = "Object of transaction modification", required = true)
    @NotNull
    @Valid
    private TransactionModification transaction;
}
