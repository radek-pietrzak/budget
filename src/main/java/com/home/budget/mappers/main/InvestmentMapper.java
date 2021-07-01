package com.home.budget.mappers.main;

import com.home.budget.entities.main.Investment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class InvestmentMapper {
    public abstract Investment mapInvestmentToEntity(final Investment investment);
}
