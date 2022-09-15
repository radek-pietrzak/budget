package com.home.budget.mappers.main;

import com.home.budget.entities.Transaction;
import com.home.budget.responses.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TransactionMapper {
    public abstract TransactionResponse mapTransactionToEntity(final Transaction transaction);
}
