package com.home.budget.connectors;

import com.home.budget.requests.GetExpenseRequest;
import com.home.budget.requests.PostPutExpenseRequest;
import com.home.budget.responses.GetExpenseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface ExpenseApi {

    @RequestMapping(path = API.EXPENSES,
            method = {RequestMethod.POST, RequestMethod.PUT},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HttpStatus> editExpense(@RequestBody PostPutExpenseRequest request);

    @GetMapping(path = API.EXPENSES)
    GetExpenseResponse getExpenses(@Valid @RequestBody GetExpenseRequest request);

    @GetMapping(path = API.EXPENSES_ID)
    ResponseEntity<?> getExpense(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.EXPENSES_ID)
    ResponseEntity<?> deleteExpense(@NotNull @PathVariable String id);

}
