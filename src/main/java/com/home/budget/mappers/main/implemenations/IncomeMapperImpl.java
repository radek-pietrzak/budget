package com.home.budget.mappers.main.implemenations;

import com.home.budget.entities.main.Income;
import com.home.budget.mappers.main.IncomeMapper;
import org.springframework.stereotype.Service;

@Service
public class IncomeMapperImpl extends IncomeMapper {
    @Override
    public Income mapIncomeToEntity(Income income) {
        return income;
    }
}
