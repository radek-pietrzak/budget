package com.home.budget.requests.postput.main;

import com.home.budget.modifications.main.SavingModification;
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
public class PostPutSavingRequest {

    @ApiModelProperty(value = "Object of saving modification", required = true)
    @NotNull
    @Valid
    private SavingModification saving;
}
