package com.home.budget.mappers.categories;

import com.home.budget.entities.categories.SavingCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SavingCategoryMapper {
    public abstract SavingCategory mapSavingCategoryToEntity(final SavingCategory savingCategory);
}
