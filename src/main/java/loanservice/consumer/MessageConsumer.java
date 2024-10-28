package loanservice.consumer;

import loanservice.entity.request.LoanSimulationRequest;

public interface MessageConsumer {
    void consumeLoanSimulationRequest(LoanSimulationRequest request);
}
