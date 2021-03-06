package com.home.budget.mappers.main;

import com.home.budget.entities.main.Saving;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SavingMapper {
    public abstract Saving mapSavingToEntity(final Saving saving);
}
