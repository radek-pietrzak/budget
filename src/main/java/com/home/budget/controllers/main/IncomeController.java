package com.home.budget.controllers.main;

import com.home.budget.connectors.main.IncomeApi;
import com.home.budget.entities.main.Income;
import com.home.budget.repositories.main.IncomeRepository;

import com.home.budget.requests.get.main.GetIncomeRequest;
import com.home.budget.requests.postput.main.PostPutIncomeRequest;
import com.home.budget.responses.main.GetIncomeResponse;
import com.home.budget.services.main.IncomeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping
@AllArgsConstructor
@Api(tags = "Income Controller")
public class IncomeController implements IncomeApi {

    private final IncomeRepository incomeRepository;
    private final IncomeService incomeService;

    @Override
    public ResponseEntity<HttpStatus> editIncome(PostPutIncomeRequest request) {
        incomeService.editIncome(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<GetIncomeResponse> getIncomes(GetIncomeRequest request) {
        GetIncomeResponse response = incomeService.getIncomes(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Optional<Income>> getIncome(String id) {
        Optional<Income> income = incomeRepository.findById(Long.valueOf(id));
        return ResponseEntity.ok(income);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteIncome(String id) {
        if (incomeRepository.existsById(Long.valueOf(id))) {
            incomeRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
