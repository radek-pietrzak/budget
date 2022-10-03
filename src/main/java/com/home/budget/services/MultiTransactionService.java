package com.home.budget.services;

import com.home.budget.entities.*;
import com.home.budget.entities.Currency;
import com.home.budget.entities.categories.PayMethod;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

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
    public ResponseEntity<HttpStatus> saveTransactions(PutMultiTransactionRequest request) {

        Set<String> contractorNames = new HashSet<>();
        Set<String> payMethodNames = new HashSet<>();
        Set<String> currencyAbbreviations = new HashSet<>();
        Set<String> transactionCategoryNames = new HashSet<>();
        List<Transaction> transactions = new ArrayList<>();

        var existingContractors = contractorRepository.findAllByFirstNameIn(contractorNames);
        var existingPayMethods = payMethodRepository.findAllByPayMethodNameIn(payMethodNames);
        var existingCurrencies = currencyRepository.findAllByAbbreviationIn(currencyAbbreviations);
        var existingTransactionCategories = transactionCategoryRepository.findAllByNameIn(transactionCategoryNames);

        for (TransactionModification transaction : request.getTransactions()) {
            if (transaction.getId() == null) {
                transaction.setId("0");
            }

            contractorNames.add(transaction.getContractor());
            payMethodNames.add(transaction.getPayMethod());
            currencyAbbreviations.add(transaction.getCurrency());
            transactionCategoryNames.add(transaction.getCategory());

            if (transaction.getAmount().contains(",")) {
                String amount = transaction.getAmount().replace(',', '.');
                transaction.setAmount(amount);
            }



            var allContractors = mergeAndGetContractors(contractorNames, existingContractors);
            var allPayMethods = mergeAndGetPayMethods(payMethodNames, existingPayMethods);
            var allCurrencies = mergeAndGetCurrencies(currencyAbbreviations, existingCurrencies);
            var allTransactionCategories = mergeAndGetTransactionCategories(transactionCategoryNames, existingTransactionCategories);

            transactions.add(Transaction.builder()
                    .id(Long.valueOf(transaction.getId()))
                    .type(TransactionType.getType(transaction.getType()))
                    .contractor(contractorRepository.findFirstByFirstName(transaction.getContractor()))
                    .amount(BigDecimal.valueOf(Float.parseFloat(transaction.getAmount())))
                    .currency(currencyRepository.findFirstByAbbreviation(transaction.getCurrency()))
                    .description(transaction.getDescription())
                    .payDate(Date.from(LocalDate.parse(transaction.getPayDate()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                    .payMethod(payMethodRepository.findFirstByPayMethodName(transaction.getPayMethod()))
                    .category(transactionCategoryRepository.findFirstByName(transaction.getCategory()))
                    .build());
        }



        transactionRepository.saveAllAndFlush(transactions);

        return ResponseEntity.ok().build();
    }

    private List<Contractor> mergeAndGetContractors(Set<String> contractors, List<Contractor> existingContractors) {

        var existingContractorNames = existingContractors
                .stream()
                .map(Contractor::getFirstName)
                .collect(Collectors.toList());

        List<Contractor> contractorsToSave = new ArrayList<>();

        for (String contractor : contractors) {
            if (!existingContractorNames.contains(contractor)) {
                var contractorToSave = Contractor.builder()
                        .id(0L)
                        .firstName(contractor)
                        .build();

                contractorsToSave.add(contractorToSave);
                existingContractors.add(contractorToSave);
            }
        }

        contractorRepository.saveAllAndFlush(contractorsToSave);

        return existingContractors;
    }

    private List<PayMethod> mergeAndGetPayMethods(Set<String> payMethodNames, List<PayMethod> existingPayMethods) {

        var existingPayMethodNames = existingPayMethods
                .stream()
                .map(PayMethod::getPayMethodName)
                .collect(Collectors.toList());

        List<PayMethod> payMethodsToSave = new ArrayList<>();

        for (String payMethodName : payMethodNames) {
            if (!existingPayMethodNames.contains(payMethodName)) {
                var payMethodToSave = PayMethod.builder()
                        .id(0L)
                        .payMethodName(payMethodName)
                        .build();

                payMethodsToSave.add(payMethodToSave);
                existingPayMethods.add(payMethodToSave);
            }
        }

        payMethodRepository.saveAllAndFlush(payMethodsToSave);

        return existingPayMethods;
    }

    private List<Currency> mergeAndGetCurrencies(Set<String> currencies, List<Currency> existingCurrencies) {

        var existingCurrencyNames = existingCurrencies
                .stream()
                .map(Currency::getAbbreviation)
                .collect(Collectors.toList());

        List<Currency> currenciesToSave = new ArrayList<>();

        for (String currency : currencies) {
            if (!existingCurrencyNames.contains(currency)) {
                var currencyToSave = Currency.builder()
                        .id(0L)
                        .abbreviation(currency)
                        .build();

                currenciesToSave.add(currencyToSave);
                existingCurrencies.add(currencyToSave);
            }
        }

        currencyRepository.saveAllAndFlush(currenciesToSave);

        return existingCurrencies;
    }

    private List<TransactionCategory> mergeAndGetTransactionCategories(Set<String> transactionCategoryNames, List<TransactionCategory> transactionCategories) {

        var existingTransactionCategoryNames = transactionCategories
                .stream()
                .map(TransactionCategory::getName)
                .collect(Collectors.toList());

        List<TransactionCategory> transactionCategoriesToSave = new ArrayList<>();

        for (String transactionCategory : transactionCategories) {
            if (!existingTransactionCategories.contains(transactionCategory)) {
                transactionCategoriesToSave.add(TransactionCategory.builder()
                        .id(0L)
                        .name(transactionCategory)
                        .build());
            }
        }

        transactionCategoryRepository.saveAllAndFlush(transactionCategoriesToSave);
    }
}
