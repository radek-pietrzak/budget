package com.home.budget.modifications;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(description = "Transaction modification")
public class TransactionModification {

    @ApiModelProperty(value = "Transaction id", example = "5")
    @Size(max = 18)
    private String id;

    @ApiModelProperty(value = "Type", required = true, example = "EXPENSE")
    @Size(max = 32)
    @NotBlank
    private String type;

    @ApiModelProperty(value = "Contractor", required = true, example = "Radek")
    @Size(max = 32)
    @NotBlank
    private String contractor;

    @ApiModelProperty(value = "Amount", required = true, example = "50")
    @Size(max = 32)
    @NotBlank
    private String amount;

    @ApiModelProperty(value = "Currency", required = true, example = "PLN")
    @Size(min = 3, max = 3)
    @NotBlank
    private String currency;

    @ApiModelProperty(value = "Description", required = true, example = "Some food")
    @Size(max = 64)
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "Pay date", required = true, example = "2021-06-21", dataType = "java.lang.String")
    @NotBlank
    private String payDate;

    @ApiModelProperty(value = "Pay Method", required = true, example = "Some method")
    @Size(max = 32)
    @NotBlank
    private String payMethod;

    @ApiModelProperty(value = "Transaction Category", required = true, example = "Some category")
    @Size(max = 64)
    @NotBlank
    private String category;
}
