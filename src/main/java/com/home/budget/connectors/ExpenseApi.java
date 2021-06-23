package com.home.budget.connectors;

import com.home.budget.entities.Expense;
import com.home.budget.requests.GetExpenseRequest;
import com.home.budget.requests.PostPutExpenseRequest;
import com.home.budget.responses.GetExpenseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface ExpenseApi {

    @PutMapping(path = API.EXPENSES)
    ResponseEntity<HttpStatus> editExpense(@Valid @RequestBody PostPutExpenseRequest request);

    @PostMapping(path = API.EXPENSES)
    ResponseEntity<GetExpenseResponse> getExpenses(@Valid @RequestBody GetExpenseRequest request);

    @GetMapping(path = API.EXPENSES_ID)
    ResponseEntity<Optional<Expense>> getExpense(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.EXPENSES_ID)
    ResponseEntity<HttpStatus> deleteExpense(@NotNull @PathVariable String id);

}
