package com.home.budget.mappers.main.implemenations;

import com.home.budget.entities.main.Saving;
import com.home.budget.mappers.main.SavingMapper;
import org.springframework.stereotype.Service;

@Service
public class SavingMapperImpl extends SavingMapper {
    @Override
    public Saving mapSavingToEntity(Saving saving) {
        return saving;
    }
}
