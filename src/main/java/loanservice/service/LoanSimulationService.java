package loanservice.service;

import loanservice.entity.request.LoanSimulationRequest;
import loanservice.entity.response.LoanSimulationResponse;

import java.util.List;

public interface LoanSimulationService {

    LoanSimulationResponse simulateLoan(LoanSimulationRequest request);

    List<LoanSimulationResponse> simulateLoans(List<LoanSimulationRequest> requests);
}
