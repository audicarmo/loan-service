package loanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import loanservice.entity.request.LoanSimulationRequest;
import loanservice.entity.response.LoanSimulationResponse;
import loanservice.service.LoanSimulationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/simulations")
@Tag(name = "Credit Simulator", description = "Endpoints for credit simulation")
class LoanSimulationController {
    private final LoanSimulationService loanSimulationService;

    LoanSimulationController(LoanSimulationService loanSimulationService) {
        this.loanSimulationService = loanSimulationService;
    }

    @PostMapping("/simulation")
    @Operation(summary = "Simulate Loan", description = "Simulates a loan based on the parameters provided.")
    public ResponseEntity<LoanSimulationResponse> simulateLoan(
            @RequestBody LoanSimulationRequest request) {
        LoanSimulationResponse response = loanSimulationService.simulateLoan(request);
        return ResponseEntity.ok(response);
    }

}
