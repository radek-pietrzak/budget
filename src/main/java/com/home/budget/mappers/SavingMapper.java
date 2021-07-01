package com.home.budget.mappers;

import com.home.budget.entities.Saving;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SavingMapper {
    public abstract Saving mapSavingToEntity(final Saving saving);
}
