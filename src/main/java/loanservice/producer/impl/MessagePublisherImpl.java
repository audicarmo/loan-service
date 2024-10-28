package loanservice.producer.impl;

import loanservice.entity.request.LoanSimulationRequest;
import loanservice.producer.MessagePublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagePublisherImpl implements MessagePublisher {

    @Override
    public void publishLoanSimulationRequest(LoanSimulationRequest request) {
        System.out.println("Sending a loan simulation to the queue: " + request);
    }

    @Override
    public void publishLoanSimulationRequests(List<LoanSimulationRequest> requests) {
        requests.forEach(this::publishLoanSimulationRequest);
    }
}
