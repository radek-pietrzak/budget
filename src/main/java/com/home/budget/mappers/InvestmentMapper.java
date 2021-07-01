package com.home.budget.mappers;

import com.home.budget.entities.Investment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class InvestmentMapper {
    public abstract Investment mapInvestmentToEntity(final Investment investment);
}
