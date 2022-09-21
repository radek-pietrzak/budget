package com.home.budget.services;

import com.home.budget.entities.TransactionCategory;
import com.home.budget.entities.categories.PayMethod;
import com.home.budget.entities.Transaction;
import com.home.budget.mappers.main.implemenations.TransactionMapperImpl;
import com.home.budget.modifications.TransactionModification;
import com.home.budget.repositories.ContractorRepository;
import com.home.budget.repositories.CurrencyRepository;
import com.home.budget.repositories.TransactionCategoryRepository;
import com.home.budget.repositories.categories.PayMethodRepository;
import com.home.budget.repositories.TransactionRepository;
import com.home.budget.requests.get.GetTransactionRequest;
import com.home.budget.requests.postput.PostPutTransactionRequest;
import com.home.budget.responses.TransactionResponse;
import com.home.budget.responses.GetTransactionResponse;
import com.home.budget.sort.MainSort;
import com.home.budget.specifications.SearchSpecCriteria;
import com.home.budget.specifications.SpecificationType;
import com.home.budget.specifications.builders.TransactionSpecificationBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionCategoryRepository transactionCategoryRepository;
    private final PayMethodRepository payMethodRepository;
    private final ContractorRepository contractorRepository;
    private final CurrencyRepository currencyRepository;
    private final TransactionMapperImpl transactionMapper;

    @Transactional
    public void editTransaction(PostPutTransactionRequest request) {

        if (request.getTransaction().getId() == null) {
            request.getTransaction().setId("0");
        }

        TransactionModification transaction = request.getTransaction();

        savePayMethodIfNew(transaction.getPayMethod());
        saveTransactionCategoryIfNew(transaction.getTransactionCategory());

        if (transaction.getAmount().contains(",")) {
            String amount = transaction.getAmount().replace(',', '.');
            transaction.setAmount(amount);
        }

        Transaction transactionBuild = Transaction.builder()
                .id(Long.valueOf(transaction.getId()))
                .contractor(contractorRepository.findFirstByFirstName(transaction.getContractor()))
                .amount(BigDecimal.valueOf(Float.parseFloat(transaction.getAmount())))
                .currency(currencyRepository.findFirstByAbbreviation(transaction.getCurrency()))
                .description(transaction.getDescription())
                .payDate(Date.from(LocalDate.parse(transaction.getPayDate()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .payMethod(payMethodRepository.findFirstByPayMethodName(transaction.getPayMethod()))
                .category(transactionCategoryRepository.findFirstByName(transaction.getTransactionCategory()))
                .build();

        transactionRepository.save(transactionBuild);
    }

    public GetTransactionResponse getTransactions(GetTransactionRequest request) {
        String currentDate = LocalDate.now().toString();

        if (null == request.getRequestedDate()) {
            request.setRequestedDate(getDateWithLastDayOfMonth(currentDate));
        }

        addMonthSpecCriteria(request, currentDate);

        final Sort orders = new MainSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Specification<Transaction> specifications = new TransactionSpecificationBuilder(request.getSearchSpecCriteria()).build();
        final Page<Transaction> transactionPage = transactionRepository.findAll(specifications, pageRequest);

        return GetTransactionResponse.builder()
                .transactions(transactionPage.map(transactionMapper::mapTransactionToEntity).toList())
                .totalPages(transactionPage.getTotalPages())
                .hasNextPage(transactionPage.hasNext())
                .currentDate(currentDate)
                .requestedDate(getDateWithLastDayOfMonth(request.getRequestedDate()))
                .build();
    }

    private void addMonthSpecCriteria(GetTransactionRequest request, String date) {
        if (null == request.getRequestedDate()) {
            request.setRequestedDate(date);
        }

        SearchSpecCriteria monthBeginCriteria = new SearchSpecCriteria("payDate", SpecificationType.GREATER, getDateWithFirstDayOfMonth(request.getRequestedDate()));
        SearchSpecCriteria monthEndCriteria = new SearchSpecCriteria("payDate", SpecificationType.LESS, getDateWithLastDayOfMonth(request.getRequestedDate()));
        if (null == request.getSearchSpecCriteria()) {
            List<SearchSpecCriteria> criteriaList = new ArrayList<>();
            request.setSearchSpecCriteria(criteriaList);
        }
        request.getSearchSpecCriteria().add(monthBeginCriteria);
        request.getSearchSpecCriteria().add(monthEndCriteria);
    }


    private String getDateWithLastDayOfMonth(String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        int month = parsedDate.getMonthValue();
        while (month == parsedDate.getMonthValue()) {
            parsedDate = parsedDate.plusDays(1);
        }
        parsedDate = parsedDate.minusDays(1);

        return parsedDate.toString();
    }

    private String getDateWithFirstDayOfMonth(String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        int month = parsedDate.getMonthValue();
        while (month == parsedDate.getMonthValue()) {
            parsedDate = parsedDate.minusDays(1);
        }
        parsedDate = parsedDate.plusDays(1);

        return parsedDate.toString();
    }

    private void savePayMethodIfNew(String payMethodName) {
        PayMethod payMethod = payMethodRepository.findAll()
                .stream()
                .filter(method -> method.getPayMethodName().equals(payMethodName))
                .findFirst()
                .orElse(null);

        if (payMethod == null) {
            payMethod = new PayMethod(payMethodName);
            payMethodRepository.save(payMethod);
        }
    }

    private void saveTransactionCategoryIfNew(String categoryName) {
        TransactionCategory transactionCategory = transactionCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getName().equals(categoryName))
                .findFirst()
                .orElse(null);

        if (transactionCategory == null) {
            transactionCategory = new TransactionCategory(categoryName);
            transactionCategoryRepository.save(transactionCategory);
        }
    }

    public TransactionResponse getTransactionResponse(String id) {
        Optional<Transaction> transactionOpt = transactionRepository.findById(Long.valueOf(id));
        Transaction transaction = transactionOpt.orElseThrow();

        return transactionMapper.mapTransactionToEntity(transaction);
    }
}
