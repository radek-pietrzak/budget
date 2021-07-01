package com.home.budget.connectors;

import com.home.budget.entities.Expense;
import com.home.budget.entities.Income;
import com.home.budget.requests.GetExpenseRequest;
import com.home.budget.requests.GetIncomeRequest;
import com.home.budget.requests.PostPutExpenseRequest;
import com.home.budget.requests.PostPutIncomeRequest;
import com.home.budget.responses.GetExpenseResponse;
import com.home.budget.responses.GetIncomeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface IncomeApi {

    @PutMapping(path = API.INCOMES)
    ResponseEntity<HttpStatus> editIncome(@Valid @RequestBody PostPutIncomeRequest request);

    @PostMapping(path = API.INCOMES)
    ResponseEntity<GetIncomeResponse> getIncomes(@Valid @RequestBody GetIncomeRequest request);

    @GetMapping(path = API.INCOMES_ID)
    ResponseEntity<Optional<Income>> getIncome(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.INCOMES_ID)
    ResponseEntity<HttpStatus> deleteIncome(@NotNull @PathVariable String id);

}
