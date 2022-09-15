package com.home.budget.connectors;

import com.home.budget.entities.Transaction;
import com.home.budget.requests.get.GetTransactionRequest;
import com.home.budget.requests.postput.PostPutTransactionRequest;
import com.home.budget.responses.TransactionResponse;
import com.home.budget.responses.GetTransactionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
public interface TransactionApi {

    @PutMapping(path = API.TRANSACTIONS)
    ResponseEntity<HttpStatus> editTransaction(@Valid @RequestBody PostPutTransactionRequest request);

    @PostMapping(path = API.TRANSACTIONS)
    ResponseEntity<GetTransactionResponse> getTransactions(@Valid @RequestBody GetTransactionRequest request);

    @GetMapping(path = API.TRANSACTIONS_ID)
    ResponseEntity<TransactionResponse> getTransaction(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.TRANSACTIONS_ID)
    ResponseEntity<HttpStatus> deleteTransaction(@NotNull @PathVariable String id);

    @GetMapping(path = API.TRANSACTIONS)
    ResponseEntity<List<Transaction>> getTransactions();

}
