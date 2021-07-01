package com.home.budget.mappers;

import com.home.budget.entities.Saving;
import org.springframework.stereotype.Service;

@Service
public class SavingMapperImpl extends SavingMapper {
    @Override
    public Saving mapSavingToEntity(Saving saving) {
        return saving;
    }
}
