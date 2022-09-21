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
        transactionResponse.setContractor(transaction.getContractor().getEmail());
        transactionResponse.setAmount(transaction.getAmount().toString());
        transactionResponse.setCurrency(transaction.getCurrency().getAbbreviation());
        transactionResponse.setDescription(transaction.getDescription());
        transactionResponse.setPayDate(transaction.getPayDate());
        transactionResponse.setPayMethod(transaction.getPayMethod().getPayMethodName());
        transactionResponse.setTransactionCategory(transaction.getCategory().getName());
        transactionResponse.setCreatedUser(transaction.getCreatedUser());
        transactionResponse.setUpdatedUser(transaction.getUpdatedUser());
        transactionResponse.setCreatedDate(transaction.getCreatedDate());
        transactionResponse.setUpdatedDate(transaction.getUpdatedDate());
        return transactionResponse;
    }
}
