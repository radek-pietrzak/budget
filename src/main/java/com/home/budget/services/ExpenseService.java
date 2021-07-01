package com.home.budget.services;

import com.home.budget.entities.Expense;
import com.home.budget.entities.ExpenseCategory;
import com.home.budget.entities.PayMethod;
import com.home.budget.mappers.ExpenseMapperImpl;
import com.home.budget.modifications.ExpenseModification;
import com.home.budget.repositories.ExpenseCategoryRepository;
import com.home.budget.repositories.ExpenseRepository;
import com.home.budget.repositories.PayMethodRepository;
import com.home.budget.requests.GetExpenseRequest;
import com.home.budget.requests.PostPutExpenseRequest;
import com.home.budget.responses.GetExpenseResponse;
import com.home.budget.sort.MainSort;
import com.home.budget.specifications.ExpenseSpecificationBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final PayMethodRepository payMethodRepository;
    private final ExpenseMapperImpl expenseMapper;

    @Transactional
    public void editExpense(PostPutExpenseRequest request) {

        if (request.getExpense().getId() == null) {
            request.getExpense().setId("0");
        }

        ExpenseModification expense = request.getExpense();

        String payMethodName = expense.getPayMethodName();

        savePayMethodIfNew(payMethodName);

        PayMethod payMethodFromRepo = payMethodRepository.findAll()
                .stream()
                .filter(name -> name.getPayMethodName().equals(payMethodName))
                .findFirst()
                .orElseThrow();

        String categoryName = expense.getCategoryName();

        saveExpenseCategoryIfNew(categoryName);

        ExpenseCategory expenseCategoryFromRepo = expenseCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getCategoryName().equals(categoryName))
                .findFirst()
                .orElseThrow();

        Expense expenseBuild = Expense.builder()
                .id(Long.valueOf(expense.getId()))
                .user(expense.getUser())
                .amount(BigDecimal.valueOf(Long.parseLong(expense.getAmount())))
                .currency(expense.getCurrency())
                .description(expense.getDescription())
                .payDate(LocalDate.parse(expense.getPayDate()))
                .payMethod(payMethodFromRepo)
                .expenseCategory(expenseCategoryFromRepo)
                .build();

        expenseRepository.save(expenseBuild);
    }

    public GetExpenseResponse getExpenses(GetExpenseRequest request) {
        final Sort orders = new MainSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Specification<Expense> specifications = new ExpenseSpecificationBuilder(request.getSearchSpecCriteria()).build();
        final Page<Expense> expensePage = expenseRepository.findAll(specifications, pageRequest);

        return GetExpenseResponse.builder()
                .expenses(expensePage.map(expenseMapper::mapExpenseToEntity).toList())
                .totalPages(expensePage.getTotalPages())
                .hasNextPage(expensePage.hasNext())
                .build();
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

    private void saveExpenseCategoryIfNew(String categoryName) {
        ExpenseCategory expenseCategory = expenseCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getCategoryName().equals(categoryName))
                .findFirst()
                .orElse(null);

        if (expenseCategory == null) {
            expenseCategory = new ExpenseCategory(categoryName);
            expenseCategoryRepository.save(expenseCategory);
        }
    }
}
