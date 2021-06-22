package com.home.budget.specifications;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchSpecCriteria {

    @ApiModelProperty("Name of field")
    @Size(max = 64)
    private String key;

    @ApiModelProperty("Operation")
    private SpecificationType operation;

    @ApiModelProperty(value = "String value", example = "funny")
    @Size(max = 256)
    private String value;


//    @ApiModelProperty(value = "Join table alias", example = "child")
//    private String alias;
}
