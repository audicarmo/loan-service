package loanservice.producer;

import loanservice.entity.request.LoanSimulationRequest;

import java.util.List;

public interface MessagePublisher {
    void publishLoanSimulationRequest(LoanSimulationRequest request);
    void publishLoanSimulationRequests(List<LoanSimulationRequest> requests);
}
