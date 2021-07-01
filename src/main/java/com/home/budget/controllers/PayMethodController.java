package com.home.budget.controllers;

import com.home.budget.connectors.PayMethodApi;
import com.home.budget.entities.PayMethod;
import com.home.budget.repositories.PayMethodRepository;
import com.home.budget.requests.GetPayMethodRequest;
import com.home.budget.requests.PostPutPayMethodRequest;
import com.home.budget.responses.GetPayMethodResponse;
import com.home.budget.services.PayMethodService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping
@AllArgsConstructor
@Api(tags = "PayMethod Controller")
public class PayMethodController implements PayMethodApi {

    private final PayMethodRepository payMethodRepository;
    private final PayMethodService payMethodService;

    @Override
    public ResponseEntity<HttpStatus> editPayMethod(PostPutPayMethodRequest request) {
        payMethodService.editPayMethod(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<GetPayMethodResponse> getSavingCategories(GetPayMethodRequest request) {
        GetPayMethodResponse response = payMethodService.getSavingCategories(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Optional<PayMethod>> getPayMethod(String id) {
        Optional<PayMethod> payMethod = payMethodRepository.findById(Long.valueOf(id));
        return ResponseEntity.ok(payMethod);
    }

    @Override
    public ResponseEntity<HttpStatus> deletePayMethod(String id) {
        if (payMethodRepository.existsById(Long.valueOf(id))) {
            payMethodRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
