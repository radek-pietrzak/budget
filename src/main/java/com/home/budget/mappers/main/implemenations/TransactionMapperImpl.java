package com.home.budget.mappers.main.implemenations;

import com.home.budget.entities.Transaction;
import com.home.budget.mappers.main.TransactionMapper;
import com.home.budget.responses.TransactionResponse;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapperImpl extends TransactionMapper {
    @Override
    public TransactionResponse mapTransactionToEntity(Transaction transaction) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(transaction.getId().toString());
        transactionResponse.setUser(transaction.getUser());
        transactionResponse.setAmount(transaction.getAmount().toString());
        transactionResponse.setCurrency(transaction.getCurrency());
        transactionResponse.setDescription(transaction.getDescription());
        transactionResponse.setPayDate(transaction.getPayDate());
        transactionResponse.setPayMethod(transaction.getPayMethod().getPayMethodName());
        transactionResponse.setTransactionCategory(transaction.getTransactionCategory().getCategoryName());
        transactionResponse.setCreatedUser(transaction.getCreatedUser());
        transactionResponse.setUpdatedUser(transaction.getUpdatedUser());
        transactionResponse.setCreatedDate(transaction.getCreatedDate());
        transactionResponse.setUpdatedDate(transaction.getUpdatedDate());
        return transactionResponse;
    }
}
