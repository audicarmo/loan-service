package loanservice.controller;

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
class LoanSimulationController {
    private final LoanSimulationService loanSimulationService;

    LoanSimulationController(LoanSimulationService loanSimulationService) {
        this.loanSimulationService = loanSimulationService;
    }

    @PostMapping("/simulation")
    public ResponseEntity<LoanSimulationResponse> simulateLoan(
            @RequestBody LoanSimulationRequest request) {
        // Invoca o serviço e retorna a resposta
        return null;
    }
}
