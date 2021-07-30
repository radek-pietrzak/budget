package com.home.budget.services.main;

import com.home.budget.entities.main.Expense;
import com.home.budget.entities.categories.ExpenseCategory;
import com.home.budget.entities.categories.PayMethod;
import com.home.budget.mappers.main.implemenations.ExpenseMapperImpl;
import com.home.budget.modifications.main.ExpenseModification;
import com.home.budget.repositories.categories.ExpenseCategoryRepository;
import com.home.budget.repositories.main.ExpenseRepository;
import com.home.budget.repositories.categories.PayMethodRepository;
import com.home.budget.requests.get.main.GetExpenseRequest;
import com.home.budget.requests.postput.main.PostPutExpenseRequest;
import com.home.budget.responses.ExpenseResponse;
import com.home.budget.responses.main.GetExpenseResponse;
import com.home.budget.sort.MainSort;
import com.home.budget.specifications.builders.main.ExpenseSpecificationBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

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

        String payMethodName = expense.getPayMethod();

        savePayMethodIfNew(payMethodName);

        PayMethod payMethodFromRepo = payMethodRepository.findAll()
                .stream()
                .filter(name -> name.getPayMethodName().equals(payMethodName))
                .findFirst()
                .orElseThrow();

        String categoryName = expense.getExpenseCategory();

        saveExpenseCategoryIfNew(categoryName);

        ExpenseCategory expenseCategoryFromRepo = expenseCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getCategoryName().equals(categoryName))
                .findFirst()
                .orElseThrow();

        if (expense.getAmount().contains(",")) {
            String amount = expense.getAmount().replace(',', '.');
            expense.setAmount(amount);
        }

        Expense expenseBuild = Expense.builder()
                .id(Long.valueOf(expense.getId()))
                .user(expense.getUser())
                .amount(BigDecimal.valueOf(Float.parseFloat(expense.getAmount())))
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

    public ExpenseResponse getExpenseResponse(String id) {
        Optional<Expense> expenseOpt = expenseRepository.findById(Long.valueOf(id));
        Expense expense = expenseOpt.orElseThrow();

        ExpenseResponse expenseResponse = expenseMapper.mapExpenseToEntity(expense);

        return expenseResponse;
    }
}
