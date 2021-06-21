package com.home.budget.controllers;

import com.home.budget.connectors.ExpenseApi;
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
import com.home.budget.sort.ExpenseSort;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping
@AllArgsConstructor
@Api(tags = "Expense Controller")
public class ExpenseController implements ExpenseApi {

    private final ExpenseRepository expenseRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final PayMethodRepository payMethodRepository;
    private final ExpenseMapperImpl expenseMapper;

    @Transactional
    @Override
    public ResponseEntity<HttpStatus> editExpense(@Valid PostPutExpenseRequest request) {
        ExpenseModification expense = request.getExpense();

        String payMethodName = expense.getPayMethodName();

        PayMethod payMethod = payMethodRepository.findAll()
                .stream()
                .filter(method -> method.getPayMethodName().equals(payMethodName))
                .findFirst()
                .orElse(null);

        if (payMethod == null) {
            payMethod = new PayMethod(payMethodName);
            payMethodRepository.save(payMethod);
        }

        PayMethod payMethodFromRepo = payMethodRepository.findAll()
                .stream()
                .filter(name -> name.getPayMethodName().equals(payMethodName))
                .findFirst()
                .orElseThrow();

        String categoryName = expense.getCategoryName();

        ExpenseCategory expenseCategory = expenseCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getCategoryName().equals(categoryName))
                .findFirst()
                .orElse(null);

        if (expenseCategory == null) {
            expenseCategory = new ExpenseCategory(categoryName);
            expenseCategoryRepository.save(expenseCategory);
        }

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

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Override
    public GetExpenseResponse getExpenses(GetExpenseRequest request) {
        final Sort orders = new ExpenseSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Page<Expense> expensePage = expenseRepository.findAll(pageRequest);
        return GetExpenseResponse.builder()
                .expenses(expensePage.map(expenseMapper::mapExpenseToEntity).toList())
                .totalPages(expensePage.getTotalPages())
                .hasNextPage(expensePage.hasNext())
                .build();
    }

    @Override
    public ResponseEntity<?> getExpense(@PathVariable String id) {
        Optional<Expense> expense = expenseRepository.findById(Long.valueOf(id));
        return ResponseEntity.ok(expense);
    }

    @Override
    public ResponseEntity<?> deleteExpense(@PathVariable String id) {
        expenseRepository.deleteById(Long.valueOf(id));
        return ResponseEntity.accepted().build();
    }


}
