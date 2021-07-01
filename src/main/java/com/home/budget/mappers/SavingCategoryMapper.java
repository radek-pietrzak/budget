package com.home.budget.mappers;

import com.home.budget.entities.SavingCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SavingCategoryMapper {
    public abstract SavingCategory mapSavingCategoryToEntity(final SavingCategory savingCategory);
}
