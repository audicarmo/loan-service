package loanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import loanservice.entity.request.LoanSimulationRequest;
import loanservice.entity.response.LoanSimulationResponse;
import loanservice.producer.MessagePublisher;
import loanservice.service.LoanSimulationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/simulations")
@Tag(name = "Credit Simulator", description = "Endpoints for credit simulation")
class LoanSimulationController {
    private final LoanSimulationService loanSimulationService;
    private final MessagePublisher messagePublisher;

    LoanSimulationController(LoanSimulationService loanSimulationService, MessagePublisher messagePublisher) {
        this.loanSimulationService = loanSimulationService;
        this.messagePublisher = messagePublisher;
    }

    @PostMapping("/simulation")
    @Operation(summary = "Simulate Loan", description = "Simulates a loan based on the parameters provided.")
    public ResponseEntity<LoanSimulationResponse> simulateLoan(
            @RequestBody LoanSimulationRequest request) {
        LoanSimulationResponse response = loanSimulationService.simulateLoan(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/simulations")
    @Operation(summary = "Simulate Multiple Loans", description = "Simulates multiple loans based on the parameters provided.")
    public ResponseEntity<List<LoanSimulationResponse>> simulateMultipleLoans(
            @RequestBody List<LoanSimulationRequest> requests) {
        List<LoanSimulationResponse> responses = loanSimulationService.simulateLoans(requests);
        return ResponseEntity.ok(responses);
    }
}
