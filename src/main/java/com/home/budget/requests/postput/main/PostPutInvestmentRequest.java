package com.home.budget.requests.postput.main;

import com.home.budget.modifications.main.InvestmentModification;
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
public class PostPutInvestmentRequest {

    @ApiModelProperty(value = "Object of investment modification", required = true)
    @NotNull
    @Valid
    private InvestmentModification investment;
}
