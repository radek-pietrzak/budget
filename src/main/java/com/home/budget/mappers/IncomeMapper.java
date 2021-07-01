package com.home.budget.mappers;

import com.home.budget.entities.Income;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class IncomeMapper {
    public abstract Income mapIncomeToEntity(final Income income);
}
