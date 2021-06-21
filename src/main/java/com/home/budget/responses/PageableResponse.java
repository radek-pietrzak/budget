package com.home.budget.responses;

import com.home.budget.requests.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public abstract class PageableResponse {
    @NotNull
    private Page page;
    @NotNull
    private Boolean hasNextPage;
    @NotNull
    private Integer totalPages;

}