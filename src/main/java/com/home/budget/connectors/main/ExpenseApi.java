package com.home.budget.connectors.main;

import com.home.budget.connectors.API;
import com.home.budget.entities.main.Expense;
import com.home.budget.requests.get.main.GetExpenseRequest;
import com.home.budget.requests.postput.main.PostPutExpenseRequest;
import com.home.budget.responses.ExpenseResponse;
import com.home.budget.responses.main.GetExpenseResponse;
import org.keycloak.authorization.client.util.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
public interface ExpenseApi {

    @PutMapping(path = API.EXPENSES)
    ResponseEntity<HttpStatus> editExpense(@Valid @RequestBody PostPutExpenseRequest request);

    @PostMapping(path = API.EXPENSES)
    ResponseEntity<GetExpenseResponse> getExpenses(@Valid @RequestBody GetExpenseRequest request);

    @GetMapping(path = API.EXPENSES_ID)
    ResponseEntity<ExpenseResponse> getExpense(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.EXPENSES_ID)
    ResponseEntity<HttpStatus> deleteExpense(@NotNull @PathVariable String id);

    @GetMapping(path = API.EXPENSES)
    ResponseEntity<List<Expense>> getExpenses();

}
