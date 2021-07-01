package com.home.budget.mappers;

import com.home.budget.entities.SavingCategory;
import org.springframework.stereotype.Service;

@Service
public class SavingCategoryMapperImpl extends SavingCategoryMapper {
    @Override
    public SavingCategory mapSavingCategoryToEntity(SavingCategory savingCategory) {
        return savingCategory;
    }
}
