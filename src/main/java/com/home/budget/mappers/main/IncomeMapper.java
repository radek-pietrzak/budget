package com.home.budget.mappers.main;

import com.home.budget.entities.main.Income;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class IncomeMapper {
    public abstract Income mapIncomeToEntity(final Income income);
}
