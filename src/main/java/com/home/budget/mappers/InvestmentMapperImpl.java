package com.home.budget.mappers;

import com.home.budget.entities.Investment;
import org.springframework.stereotype.Service;

@Service
public class InvestmentMapperImpl extends InvestmentMapper {
    @Override
    public Investment mapInvestmentToEntity(Investment investment) {
        return investment;
    }
}
