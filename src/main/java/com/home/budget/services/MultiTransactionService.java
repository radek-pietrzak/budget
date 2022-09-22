package com.home.budget.services;

import com.home.budget.mappers.main.implemenations.TransactionMapperImpl;
import com.home.budget.modifications.TransactionModification;
import com.home.budget.repositories.ContractorRepository;
import com.home.budget.repositories.CurrencyRepository;
import com.home.budget.repositories.TransactionCategoryRepository;
import com.home.budget.repositories.TransactionRepository;
import com.home.budget.repositories.categories.PayMethodRepository;
import com.home.budget.requests.postput.PostMultiTransactionRequest;
import com.home.budget.requests.postput.PutMultiTransactionRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MultiTransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionCategoryRepository transactionCategoryRepository;
    private final PayMethodRepository payMethodRepository;
    private final ContractorRepository contractorRepository;
    private final CurrencyRepository currencyRepository;
    private final TransactionMapperImpl transactionMapper;

    public ResponseEntity<List<TransactionModification>> checkTransactions(PostMultiTransactionRequest request) {
        List<TransactionModification> transactions = new ArrayList<>();

        List<List<String>> table = new ArrayList<>();
        List<String> line = new ArrayList<>();
        StringBuilder cell = new StringBuilder();

        if (request != null && request.getMultiTransaction() != null) {
            CharacterIterator it = new StringCharacterIterator(request.getMultiTransaction());
            while (it.current() != CharacterIterator.DONE) {

                if ("^".equals(String.valueOf(it.current()))) {
                    line.add(cell.toString());
                    cell = new StringBuilder();

                } else if ("&".equals(String.valueOf(it.current()))) {
                    line.add(cell.toString());
                    table.add(line);
                    cell = new StringBuilder();
                    line = new ArrayList<>();

                } else {
                    cell.append(it.current());
                }

                it.next();
            }

            line.add(cell.toString());
            table.add(line);
        }

        table.forEach(l -> {
            TransactionModification transaction = TransactionModification.builder()
                    .type("EXPENSE")
                    .contractor(l.get(0))
                    .amount(l.get(3))
                    .currency(l.get(4))
                    .description(l.get(2))
                    .payDate(l.get(5))
                    .payMethod(l.get(6))
                    .category(l.get(1))
                    .build();

            transactions.add(transaction);
        });

        return ResponseEntity.ok(transactions);
    }

    @Transactional
    public void saveTransactions(PutMultiTransactionRequest request) {

    }
}
