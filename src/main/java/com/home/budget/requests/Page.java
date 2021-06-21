package com.home.budget.requests;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    @ApiModelProperty(value = "Page size", example = "10")
    @NotNull
    private Integer size;
    @ApiModelProperty(value = "Page number", example = "0")
    @NotNull
    private Integer number;
}
