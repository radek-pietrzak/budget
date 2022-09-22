package com.home.budget.requests.postput;

import com.home.budget.modifications.TransactionModification;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PutMultiTransactionRequest {

    @ApiModelProperty(value = "List of transactions", required = true)
    @NotNull
    @Valid
    private List<TransactionModification> transactions;
}
