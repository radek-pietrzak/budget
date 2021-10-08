package com.home.budget.specifications;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchSpecCriteria {

    @ApiModelProperty(value = "Name of field", example = "description")
    @Size(max = 64)
    private String key;

    @ApiModelProperty(value = "Operation", example = "CONTAINS")
    private SpecificationType operation;

    @ApiModelProperty(value = "description", example = "Some")
    @Size(max = 256)
    private String content;

}
