package com.home.budget.services.main;

import com.home.budget.entities.main.Income;
import com.home.budget.entities.categories.IncomeCategory;
import com.home.budget.mappers.main.implemenations.IncomeMapperImpl;
import com.home.budget.modifications.main.IncomeModification;
import com.home.budget.repositories.categories.IncomeCategoryRepository;
import com.home.budget.repositories.main.IncomeRepository;
import com.home.budget.requests.get.main.GetIncomeRequest;
import com.home.budget.requests.postput.main.PostPutIncomeRequest;
import com.home.budget.responses.main.GetIncomeResponse;
import com.home.budget.sort.MainSort;
import com.home.budget.specifications.builders.main.IncomeSpecificationBuilder;
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
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final IncomeCategoryRepository incomeCategoryRepository;
    private final IncomeMapperImpl incomeMapper;

    @Transactional
    public void editIncome(PostPutIncomeRequest request) {

        if (request.getIncome().getId() == null) {
            request.getIncome().setId("0");
        }

        IncomeModification income = request.getIncome();

        String categoryName = income.getCategoryName();

        saveIncomeCategoryIfNew(categoryName);

        IncomeCategory incomeCategoryFromRepo = incomeCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getCategoryName().equals(categoryName))
                .findFirst()
                .orElseThrow();

        Income incomeBuild = Income.builder()
                .id(Long.valueOf(income.getId()))
                .user(income.getUser())
                .amount(BigDecimal.valueOf(Long.parseLong(income.getAmount())))
                .currency(income.getCurrency())
                .description(income.getDescription())
                .payDate(LocalDate.parse(income.getPayDate()))
                .incomeCategory(incomeCategoryFromRepo)
                .build();

        incomeRepository.save(incomeBuild);
    }

    public GetIncomeResponse getIncomes(GetIncomeRequest request) {
        final Sort orders = new MainSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Specification<Income> specifications = new IncomeSpecificationBuilder(request.getSearchSpecCriteria()).build();
        final Page<Income> incomePage = incomeRepository.findAll(specifications, pageRequest);

        return GetIncomeResponse.builder()
                .incomes(incomePage.map(incomeMapper::mapIncomeToEntity).toList())
                .totalPages(incomePage.getTotalPages())
                .hasNextPage(incomePage.hasNext())
                .build();
    }

    private void saveIncomeCategoryIfNew(String categoryName) {
        IncomeCategory incomeCategory = incomeCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getCategoryName().equals(categoryName))
                .findFirst()
                .orElse(null);

        if (incomeCategory == null) {
            incomeCategory = new IncomeCategory(categoryName);
            incomeCategoryRepository.save(incomeCategory);
        }
    }
}
