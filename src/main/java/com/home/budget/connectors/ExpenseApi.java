package com.home.budget.connectors;

import com.home.budget.entities.Expense;
import com.home.budget.requests.GetExpenseRequest;
import com.home.budget.responses.GetExpenseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ExpenseApi {

    @RequestMapping(path = API.EXPENSES, method = {RequestMethod.POST, RequestMethod.PUT})
    ResponseEntity<HttpStatus> editExpense(@RequestBody Expense expense);

    @GetMapping(path = API.EXPENSES)
    GetExpenseResponse getExpenses(GetExpenseRequest request);

    @GetMapping(path = API.EXPENSES_ID)
    ResponseEntity<?> getExpense(@PathVariable String id);

    @DeleteMapping(path = API.EXPENSES_ID)
    ResponseEntity<?> deleteExpense(@PathVariable String id);

}
