package com.home.budget.mappers.categories.implementations;

import com.home.budget.entities.categories.SavingCategory;
import com.home.budget.mappers.categories.SavingCategoryMapper;
import org.springframework.stereotype.Service;

@Service
public class SavingCategoryMapperImpl extends SavingCategoryMapper {
    @Override
    public SavingCategory mapSavingCategoryToEntity(SavingCategory savingCategory) {
        return savingCategory;
    }
}
