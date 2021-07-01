package com.home.budget.mappers;

import com.home.budget.entities.Income;
import org.springframework.stereotype.Service;

@Service
public class IncomeMapperImpl extends IncomeMapper {
    @Override
    public Income mapIncomeToEntity(Income income) {
        return income;
    }
}
