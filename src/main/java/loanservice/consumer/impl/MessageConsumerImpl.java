package loanservice.consumer.impl;

import loanservice.consumer.MessageConsumer;
import loanservice.entity.request.LoanSimulationRequest;
import loanservice.entity.response.LoanSimulationResponse;
import loanservice.service.LoanSimulationService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageConsumerImpl implements MessageConsumer {

    private final LoanSimulationService loanSimulationService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public MessageConsumerImpl(LoanSimulationService loanSimulationService) {
        this.loanSimulationService = loanSimulationService;
    }

    @Override
    public void consumeLoanSimulationRequest(LoanSimulationRequest request) {
        executorService.submit(() -> {
            LoanSimulationResponse response = loanSimulationService.simulateLoan(request);
            System.out.println("Processed simulation: " + response);
        });
    }
}
