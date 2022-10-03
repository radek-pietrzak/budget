package com.home.budget.controllers;

import com.home.budget.connectors.MultiTransactionApi;
import com.home.budget.modifications.TransactionModification;
import com.home.budget.requests.postput.PostMultiTransactionRequest;
import com.home.budget.requests.postput.PutMultiTransactionRequest;
import com.home.budget.services.MultiTransactionService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
@AllArgsConstructor
@Api(tags = "Multi Transaction Controller")
public class MultiTransactionController implements MultiTransactionApi {

    private final MultiTransactionService service;

    @Override
    public ResponseEntity<List<TransactionModification>> checkTransactions(PostMultiTransactionRequest request) {
        return service.checkTransactions(request);
    }

    @Override
    public ResponseEntity<HttpStatus> saveTransactions(PutMultiTransactionRequest request) {
        return service.saveTransactions(request);
    }
}
