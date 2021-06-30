package com.home.budget.connectors;

import com.home.budget.entities.Investment;
import com.home.budget.requests.GetInvestmentRequest;
import com.home.budget.requests.PostPutInvestmentRequest;
import com.home.budget.responses.GetInvestmentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface InvestmentApi {

    @PutMapping(path = API.INVESTMENTS)
    ResponseEntity<HttpStatus> editInvestment(@Valid @RequestBody PostPutInvestmentRequest request);

    @PostMapping(path = API.INVESTMENTS)
    ResponseEntity<GetInvestmentResponse> getInvestments(@Valid @RequestBody GetInvestmentRequest request);

    @GetMapping(path = API.INVESTMENTS_ID)
    ResponseEntity<Optional<Investment>> getInvestment(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.INVESTMENTS_ID)
    ResponseEntity<HttpStatus> deleteInvestment(@NotNull @PathVariable String id);

}
