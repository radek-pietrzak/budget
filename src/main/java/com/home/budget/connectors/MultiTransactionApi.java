package com.home.budget.connectors;

import com.home.budget.requests.postput.PostMultiTransactionRequest;
import com.home.budget.requests.postput.PutMultiTransactionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
public interface MultiTransactionApi {

    @PostMapping(path = API.MULTI_TRANSACTIONS)
    ResponseEntity<HttpStatus> checkTransactions(@Valid @RequestBody PostMultiTransactionRequest request);

    @PutMapping(path = API.MULTI_TRANSACTIONS)
    ResponseEntity<HttpStatus> saveTransactions(@Valid @RequestBody PutMultiTransactionRequest request);

}
