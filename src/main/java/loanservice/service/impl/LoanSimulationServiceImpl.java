package loanservice.service.impl;

import loanservice.entity.request.LoanSimulationRequest;
import loanservice.entity.response.LoanSimulationResponse;
import loanservice.service.LoanSimulationService;
import loanservice.util.TaxCalculator;
import org.springframework.stereotype.Service;

@Service
class LoanSimulationServiceImpl implements LoanSimulationService {
    private final TaxCalculator taxCalculator;

    // É realmente necessário incluir construtor para service nessa versão do java?
    LoanSimulationServiceImpl(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public LoanSimulationResponse simulateLoan(LoanSimulationRequest request) {
        return null;
    }
}
