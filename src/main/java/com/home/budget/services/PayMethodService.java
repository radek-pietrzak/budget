package com.home.budget.services;

import com.home.budget.entities.PayMethod;
import com.home.budget.mappers.PayMethodMapperImpl;
import com.home.budget.modifications.PayMethodModification;
import com.home.budget.repositories.PayMethodRepository;
import com.home.budget.requests.GetPayMethodRequest;
import com.home.budget.requests.PostPutPayMethodRequest;
import com.home.budget.responses.GetPayMethodResponse;
import com.home.budget.sort.MainSort;
import com.home.budget.specifications.PayMethodSpecificationBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class PayMethodService {

    private final PayMethodRepository payMethodRepository;
    private final PayMethodMapperImpl payMethodMapper;

    @Transactional
    public void editPayMethod(PostPutPayMethodRequest request) {

        if (request.getPayMethod().getId() == null) {
            request.getPayMethod().setId("0");
        }

        PayMethodModification payMethod = request.getPayMethod();

        PayMethod payMethodBuild = PayMethod.builder()
                .id(Long.valueOf(payMethod.getId()))
                .payMethodName(payMethod.getPayMethodName())
                .build();

        payMethodRepository.save(payMethodBuild);
    }

    public GetPayMethodResponse getSavingCategories(GetPayMethodRequest request) {
        final Sort orders = new MainSort(request.getSearchSortCriteria()).orders();
        final PageRequest pageRequest = PageRequest.of(request.getPage().getNumber(), request.getPage().getSize(), orders);
        final Specification<PayMethod> specifications = new PayMethodSpecificationBuilder(request.getSearchSpecCriteria()).build();
        final Page<PayMethod> payMethodPage = payMethodRepository.findAll(specifications, pageRequest);

        return GetPayMethodResponse.builder()
                .payMethods(payMethodPage.map(payMethodMapper::mapPayMethodToEntity).toList())
                .totalPages(payMethodPage.getTotalPages())
                .hasNextPage(payMethodPage.hasNext())
                .build();
    }

}
