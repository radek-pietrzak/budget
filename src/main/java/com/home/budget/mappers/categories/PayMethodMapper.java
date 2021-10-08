package com.home.budget.mappers.categories;

import com.home.budget.entities.categories.PayMethod;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PayMethodMapper {
    public abstract PayMethod mapPayMethodToEntity(final PayMethod payMethod);
}
