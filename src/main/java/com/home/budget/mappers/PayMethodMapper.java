package com.home.budget.mappers;

import com.home.budget.entities.PayMethod;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PayMethodMapper {
    public abstract PayMethod mapPayMethodToEntity(final PayMethod payMethod);
}
