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
import com.home.budget.sort.SearchSortCriteria;
import com.home.budget.specifications.SearchSpecCriteria;
import com.home.budget.specifications.SpecificationType;
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
import java.util.ArrayList;
import java.util.List;
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
        if (null == request) {
            request = new GetExpenseRequest();
        }
        if (null == request.getPage() || null == request.getPage().getNumber()) {
            com.home.budget.requests.Page page = new com.home.budget.requests.Page(10, 0);
            request.setPage(page);
        }

        if (null == request.getSearchSortCriteria()) {
            List<SearchSortCriteria> sortCriteriaList = new ArrayList<>();
            request.setSearchSortCriteria(sortCriteriaList);
        }

        String currentDate = LocalDate.now().toString();

        if (null == request.getRequestedDate()) {
            request.setRequestedDate(getDateWithLastDayOfMonth(currentDate));
        }

        addMonthSpecCriteria(request, currentDate);

        final Sort orders = new MainSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Specification<Expense> specifications = new ExpenseSpecificationBuilder(request.getSearchSpecCriteria()).build();
        final Page<Expense> expensePage = expenseRepository.findAll(specifications, pageRequest);

        final com.home.budget.requests.Page page = new com.home.budget.requests.Page();
        page.setNumber(pageRequest.getPageNumber());
        page.setSize(pageRequest.getPageSize());


        return GetExpenseResponse.builder()
                .expenses(expensePage.map(expenseMapper::mapExpenseToEntity).toList())
                .totalPages(expensePage.getTotalPages())
                .hasNextPage(expensePage.hasNext())
                .currentDate(currentDate)
                .requestedDate(getDateWithLastDayOfMonth(request.getRequestedDate()))
                .page(page)
                .build();
    }

    private void addMonthSpecCriteria(GetExpenseRequest request, String date) {
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

        return expenseMapper.mapExpenseToEntity(expense);
    }
}
