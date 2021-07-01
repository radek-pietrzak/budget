package com.home.budget.connectors;

import com.home.budget.entities.Saving;
import com.home.budget.requests.GetSavingRequest;
import com.home.budget.requests.PostPutSavingRequest;
import com.home.budget.responses.GetSavingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface SavingApi {

    @PutMapping(path = API.SAVINGS)
    ResponseEntity<HttpStatus> editSaving(@Valid @RequestBody PostPutSavingRequest request);

    @PostMapping(path = API.SAVINGS)
    ResponseEntity<GetSavingResponse> getSavings(@Valid @RequestBody GetSavingRequest request);

    @GetMapping(path = API.SAVINGS_ID)
    ResponseEntity<Optional<Saving>> getSaving(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.SAVINGS_ID)
    ResponseEntity<HttpStatus> deleteSaving(@NotNull @PathVariable String id);

}
