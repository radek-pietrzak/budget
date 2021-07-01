package com.home.budget.services;

import com.home.budget.entities.Saving;
import com.home.budget.entities.SavingCategory;
import com.home.budget.mappers.SavingMapperImpl;
import com.home.budget.modifications.SavingModification;
import com.home.budget.repositories.SavingCategoryRepository;
import com.home.budget.repositories.SavingRepository;
import com.home.budget.requests.GetSavingRequest;
import com.home.budget.requests.PostPutSavingRequest;
import com.home.budget.responses.GetSavingResponse;
import com.home.budget.sort.MainSort;
import com.home.budget.specifications.SavingSpecificationBuilder;
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
public class SavingService {

    private final SavingRepository savingRepository;
    private final SavingCategoryRepository savingCategoryRepository;
    private final SavingMapperImpl savingMapper;

    @Transactional
    public void editSaving(PostPutSavingRequest request) {

        if (request.getSaving().getId() == null) {
            request.getSaving().setId("0");
        }

        SavingModification saving = request.getSaving();

        String categoryName = saving.getCategoryName();

        saveSavingCategoryIfNew(categoryName);

        SavingCategory savingCategoryFromRepo = savingCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getCategoryName().equals(categoryName))
                .findFirst()
                .orElseThrow();

        Saving savingBuild = Saving.builder()
                .id(Long.valueOf(saving.getId()))
                .user(saving.getUser())
                .amount(BigDecimal.valueOf(Long.parseLong(saving.getAmount())))
                .currency(saving.getCurrency())
                .description(saving.getDescription())
                .checkDate(LocalDate.parse(saving.getCheckDate()))
                .savingCategory(savingCategoryFromRepo)
                .build();

        savingRepository.save(savingBuild);
    }

    public GetSavingResponse getSavings(GetSavingRequest request) {
        final Sort orders = new MainSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Specification<Saving> specifications = new SavingSpecificationBuilder(request.getSearchSpecCriteria()).build();
        final Page<Saving> savingPage = savingRepository.findAll(specifications, pageRequest);

        return GetSavingResponse.builder()
                .savings(savingPage.map(savingMapper::mapSavingToEntity).toList())
                .totalPages(savingPage.getTotalPages())
                .hasNextPage(savingPage.hasNext())
                .build();
    }

    private void saveSavingCategoryIfNew(String categoryName) {
        SavingCategory savingCategory = savingCategoryRepository.findAll()
                .stream()
                .filter(name -> name.getCategoryName().equals(categoryName))
                .findFirst()
                .orElse(null);

        if (savingCategory == null) {
            savingCategory = new SavingCategory(categoryName);
            savingCategoryRepository.save(savingCategory);
        }
    }
}
