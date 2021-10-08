package com.home.budget.connectors.categories;

import com.home.budget.connectors.API;
import com.home.budget.entities.categories.PayMethod;
import com.home.budget.requests.get.categories.GetPayMethodRequest;
import com.home.budget.requests.postput.categories.PostPutPayMethodRequest;
import com.home.budget.responses.categories.GetPayMethodResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface PayMethodApi {

    @PutMapping(path = API.PAY_METHODS)
    ResponseEntity<HttpStatus> editPayMethod(@Valid @RequestBody PostPutPayMethodRequest request);

    @PostMapping(path = API.PAY_METHODS)
    ResponseEntity<GetPayMethodResponse> getSavingCategories(@Valid @RequestBody GetPayMethodRequest request);

    @GetMapping(path = API.PAY_METHODS_ID)
    ResponseEntity<Optional<PayMethod>> getPayMethod(@NotNull @PathVariable String id);

    @DeleteMapping(path = API.PAY_METHODS_ID)
    ResponseEntity<HttpStatus> deletePayMethod(@NotNull @PathVariable String id);

}
