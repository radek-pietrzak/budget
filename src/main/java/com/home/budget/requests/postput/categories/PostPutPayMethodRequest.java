package com.home.budget.requests.postput.categories;

import com.home.budget.modifications.categories.PayMethodModification;
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
public class PostPutPayMethodRequest {

    @ApiModelProperty(value = "Object of pay method modification", required = true)
    @NotNull
    @Valid
    private PayMethodModification payMethod;
}
