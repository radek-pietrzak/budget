package com.home.budget.modifications.categories;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@ApiModel(description = "SavingCategory modification")
public class SavingCategoryModification {

    @ApiModelProperty(value = "SavingCategory id", required = true, example = "5")
    @Size(max = 18)
    private String id;

    @ApiModelProperty(value = "Category name", required = true, example = "Some name")
    @Size(max = 64)
    @NotBlank
    private String categoryName;
}
