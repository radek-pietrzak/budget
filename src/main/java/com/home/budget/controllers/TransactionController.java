package com.home.budget.controllers;

import com.home.budget.connectors.TransactionApi;
import com.home.budget.entities.Transaction;
import com.home.budget.repositories.TransactionRepository;
import com.home.budget.requests.get.GetTransactionRequest;
import com.home.budget.requests.postput.PostPutTransactionRequest;
import com.home.budget.responses.TransactionResponse;
import com.home.budget.responses.GetTransactionResponse;
import com.home.budget.services.TransactionService;
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
@Api(tags = "Transaction Controller")
public class TransactionController implements TransactionApi {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @Override
    public ResponseEntity<HttpStatus> editTransaction(PostPutTransactionRequest request) {
        transactionService.editTransaction(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<GetTransactionResponse> getTransactions(GetTransactionRequest request) {
        GetTransactionResponse response = transactionService.getTransactions(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<TransactionResponse> getTransaction(String id) {
        TransactionResponse transactionResponse = transactionService.getTransactionResponse(id);
        return ResponseEntity.ok(transactionResponse);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteTransaction(String id) {
        if (transactionRepository.existsById(Long.valueOf(id))) {
            transactionRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<Transaction>> getTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return ResponseEntity.ok(transactions);
    }


}
