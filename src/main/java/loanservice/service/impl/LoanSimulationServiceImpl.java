package loanservice.service.impl;

import loanservice.entity.request.LoanSimulationRequest;
import loanservice.entity.response.LoanSimulationResponse;
import loanservice.service.LoanSimulationService;
import loanservice.util.TaxCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Service
class LoanSimulationServiceImpl implements LoanSimulationService {
    private final TaxCalculator taxCalculator;

    LoanSimulationServiceImpl(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    @Override
    public LoanSimulationResponse simulateLoan(LoanSimulationRequest request) {
        int age = calculateAge(request.getDateOfBirthClient());
        BigDecimal annualInterestRate = taxCalculator.calculateInterestRate(age);
        BigDecimal interestRateMonthly = annualInterestRate.divide(BigDecimal.valueOf(12), RoundingMode.HALF_EVEN);

        BigDecimal loanAmount = request.getLoanAmount();
        int paymentPeriodMonths = request.getPaymentPeriodMonths();

        BigDecimal pmt = calcularPMT(loanAmount, interestRateMonthly, paymentPeriodMonths);
        BigDecimal amountPaidTotal = pmt.multiply(BigDecimal.valueOf(paymentPeriodMonths));
        BigDecimal interestPaidTotal = amountPaidTotal.subtract(loanAmount);

        LoanSimulationResponse response = new LoanSimulationResponse();
        response.setInstallmentsMonthly(pmt);
        response.setAmountPaidTotal(amountPaidTotal);
        response.setInterestPaidTotal(interestPaidTotal);

        return response;
    }

    private int calculateAge(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    private BigDecimal calcularPMT(BigDecimal loanAmount, BigDecimal interestRateMonthly, int paymentPeriodMonths) {
        BigDecimal base = interestRateMonthly.add(BigDecimal.ONE).pow(paymentPeriodMonths);
        return loanAmount.multiply(interestRateMonthly).multiply(base)
                .divide(base.subtract(BigDecimal.ONE), RoundingMode.HALF_EVEN);
    }

}
