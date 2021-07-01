package com.home.budget.controllers.main;

import com.home.budget.connectors.main.SavingApi;
import com.home.budget.entities.main.Saving;
import com.home.budget.repositories.main.SavingRepository;
import com.home.budget.requests.get.main.GetSavingRequest;
import com.home.budget.requests.postput.main.PostPutSavingRequest;
import com.home.budget.responses.main.GetSavingResponse;
import com.home.budget.services.main.SavingService;
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
@Api(tags = "Saving Controller")
public class SavingController implements SavingApi {

    private final SavingRepository savingRepository;
    private final SavingService savingService;

    @Override
    public ResponseEntity<HttpStatus> editSaving(PostPutSavingRequest request) {
        savingService.editSaving(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<GetSavingResponse> getSavings(GetSavingRequest request) {
        GetSavingResponse response = savingService.getSavings(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Optional<Saving>> getSaving(String id) {
        Optional<Saving> saving = savingRepository.findById(Long.valueOf(id));
        return ResponseEntity.ok(saving);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteSaving(String id) {
        if (savingRepository.existsById(Long.valueOf(id))) {
            savingRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
