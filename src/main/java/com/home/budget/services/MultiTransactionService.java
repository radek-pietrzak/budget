package com.home.budget.services;

import com.home.budget.mappers.main.implemenations.TransactionMapperImpl;
import com.home.budget.repositories.ContractorRepository;
import com.home.budget.repositories.CurrencyRepository;
import com.home.budget.repositories.TransactionCategoryRepository;
import com.home.budget.repositories.TransactionRepository;
import com.home.budget.repositories.categories.PayMethodRepository;
import com.home.budget.requests.postput.PostPutTransactionRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class MultiTransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionCategoryRepository transactionCategoryRepository;
    private final PayMethodRepository payMethodRepository;
    private final ContractorRepository contractorRepository;
    private final CurrencyRepository currencyRepository;
    private final TransactionMapperImpl transactionMapper;

    @Transactional
    public void checkTransactions(PostPutTransactionRequest request) {

    }

    @Transactional
    public void saveTransactions(PostPutTransactionRequest request) {

    }
}
