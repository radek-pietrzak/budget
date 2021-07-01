package com.home.budget.mappers.main.implemenations;

import com.home.budget.entities.main.Investment;
import com.home.budget.mappers.main.InvestmentMapper;
import org.springframework.stereotype.Service;

@Service
public class InvestmentMapperImpl extends InvestmentMapper {
    @Override
    public Investment mapInvestmentToEntity(Investment investment) {
        return investment;
    }
}
