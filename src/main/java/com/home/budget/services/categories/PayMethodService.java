package com.home.budget.services.categories;

import com.home.budget.entities.categories.PayMethod;
import com.home.budget.mappers.categories.implementations.PayMethodMapperImpl;
import com.home.budget.modifications.categories.PayMethodModification;
import com.home.budget.repositories.categories.PayMethodRepository;
import com.home.budget.requests.get.categories.GetPayMethodRequest;
import com.home.budget.requests.postput.categories.PostPutPayMethodRequest;
import com.home.budget.responses.categories.GetPayMethodResponse;
import com.home.budget.sort.MainSort;
import com.home.budget.specifications.builders.categories.PayMethodSpecificationBuilder;
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
