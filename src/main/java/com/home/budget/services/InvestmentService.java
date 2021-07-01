package com.home.budget.services;

import com.home.budget.entities.Investment;
import com.home.budget.entities.InvestmentCategory;
import com.home.budget.mappers.InvestmentMapperImpl;
import com.home.budget.modifications.InvestmentModification;
import com.home.budget.repositories.InvestmentCategoryRepository;
import com.home.budget.repositories.InvestmentRepository;
import com.home.budget.requests.GetInvestmentRequest;
import com.home.budget.requests.PostPutInvestmentRequest;
import com.home.budget.responses.GetInvestmentResponse;
import com.home.budget.sort.MainSort;
import com.home.budget.specifications.InvestmentSpecificationBuilder;
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
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final InvestmentCategoryRepository investmentCategoryRepository;
    private final InvestmentMapperImpl investmentMapper;

    @Transactional
    public void editInvestment(PostPutInvestmentRequest request) {

        if (request.getInvestment().getId() == null) {
            request.getInvestment().setId("0");
        }

        InvestmentModification investment = request.getInvestment();

        String categoryName = investment.getCategoryName();

        saveInvestmentCategoryIfNew(categoryName);

        InvestmentCategory investmentCategoryFromRepo = investmentCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getCategoryName().equals(categoryName))
                .findFirst()
                .orElseThrow();

        Investment investmentBuild = Investment.builder()
                .id(Long.valueOf(investment.getId()))
                .user(investment.getUser())
                .amount(BigDecimal.valueOf(Long.parseLong(investment.getAmount())))
                .currency(investment.getCurrency())
                .description(investment.getDescription())
                .checkDate(LocalDate.parse(investment.getCheckDate()))
                .investmentCategory(investmentCategoryFromRepo)
                .build();

        investmentRepository.save(investmentBuild);
    }

    public GetInvestmentResponse getInvestments(GetInvestmentRequest request) {
        final Sort orders = new MainSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Specification<Investment> specifications = new InvestmentSpecificationBuilder(request.getSearchSpecCriteria()).build();
        final Page<Investment> investmentPage = investmentRepository.findAll(specifications, pageRequest);

        return GetInvestmentResponse.builder()
                .investments(investmentPage.map(investmentMapper::mapInvestmentToEntity).toList())
                .totalPages(investmentPage.getTotalPages())
                .hasNextPage(investmentPage.hasNext())
                .build();
    }

    private void saveInvestmentCategoryIfNew(String categoryName) {
        InvestmentCategory investmentCategory = investmentCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getCategoryName().equals(categoryName))
                .findFirst()
                .orElse(null);

        if (investmentCategory == null) {
            investmentCategory = new InvestmentCategory(categoryName);
            investmentCategoryRepository.save(investmentCategory);
        }
    }
}
