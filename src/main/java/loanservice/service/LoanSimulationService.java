package loanservice.service;

import loanservice.entity.request.LoanSimulationRequest;
import loanservice.entity.response.LoanSimulationResponse;

public interface LoanSimulationService {

    LoanSimulationResponse simulateLoan(LoanSimulationRequest request);

}
