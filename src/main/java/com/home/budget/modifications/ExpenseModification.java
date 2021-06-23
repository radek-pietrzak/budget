package com.home.budget.modifications;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@ApiModel(description = "Expense modification")
public class ExpenseModification {

    @ApiModelProperty(value = "Expense id", required = true, example = "5")
    private Long id;
    @ApiModelProperty(value = "User", required = true, example = "Radek")
    private String user;
    @ApiModelProperty(value = "Amount", required = true, example = "50")
    private BigDecimal amount;
    @ApiModelProperty(value = "Currency", required = true, example = "PLN")
    private String currency;
    @ApiModelProperty(value = "Description", required = true, example = "Some food")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "Pay date", required = true, example = "2021-06-21", dataType = "java.lang.String")
    private LocalDate payDate;
    @ApiModelProperty(value = "Pay Method", required = true, example = "Some method")
    private String payMethodName;
    @ApiModelProperty(value = "Expense Category", required = true, example = "Some category")
    private String categoryName;
}
