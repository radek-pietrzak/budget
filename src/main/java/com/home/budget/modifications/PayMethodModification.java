package com.home.budget.modifications;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@ApiModel(description = "PayMethod modification")
public class PayMethodModification {

    @ApiModelProperty(value = "PayMethod id", required = true, example = "5")
    @Size(max = 18)
    private String id;

    @ApiModelProperty(value = "Pay method", required = true, example = "Some name")
    @Size(max = 64)
    @NotBlank
    private String payMethodName;
}
