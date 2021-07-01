package com.home.budget.controllers.main;

import com.home.budget.connectors.main.InvestmentApi;
import com.home.budget.entities.main.Investment;
import com.home.budget.repositories.main.InvestmentRepository;
import com.home.budget.requests.get.main.GetInvestmentRequest;
import com.home.budget.requests.postput.main.PostPutInvestmentRequest;
import com.home.budget.responses.main.GetInvestmentResponse;
import com.home.budget.services.main.InvestmentService;
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
@Api(tags = "Investment Controller")
public class InvestmentController implements InvestmentApi {

    private final InvestmentRepository investmentRepository;
    private final InvestmentService investmentService;

    @Override
    public ResponseEntity<HttpStatus> editInvestment(PostPutInvestmentRequest request) {
        investmentService.editInvestment(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<GetInvestmentResponse> getInvestments(GetInvestmentRequest request) {
        GetInvestmentResponse response = investmentService.getInvestments(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Optional<Investment>> getInvestment(String id) {
        Optional<Investment> investment = investmentRepository.findById(Long.valueOf(id));
        return ResponseEntity.ok(investment);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteInvestment(String id) {
        if (investmentRepository.existsById(Long.valueOf(id))) {
            investmentRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
