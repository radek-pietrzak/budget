package com.home.budget.connectors.main;

import com.home.budget.connectors.API;
import com.home.budget.entities.main.Income;
import com.home.budget.requests.get.main.GetIncomeRequest;
import com.home.budget.requests.postput.main.PostPutIncomeRequest;
import com.home.budget.responses.main.GetIncomeResponse;
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
