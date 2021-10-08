package com.home.budget.responses.main;

import com.home.budget.entities.main.Saving;
import com.home.budget.responses.PageableResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Savings")
public class GetSavingResponse extends PageableResponse {

    @ApiModelProperty
    private List<Saving> savings;
}
