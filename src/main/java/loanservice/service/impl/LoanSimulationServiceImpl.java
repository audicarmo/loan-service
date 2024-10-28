package loanservice.service.impl;

import loanservice.entity.request.LoanSimulationRequest;
import loanservice.entity.response.LoanSimulationResponse;
import loanservice.service.LoanSimulationService;
import loanservice.utils.LoanCalculator;
import loanservice.utils.TaxCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Service
public class LoanSimulationServiceImpl implements LoanSimulationService {
    private static final BigDecimal MONTHS_IN_YEAR = BigDecimal.valueOf(12);
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    private final TaxCalculator taxCalculator;
    private final LoanCalculator loanCalculator;

    public LoanSimulationServiceImpl(TaxCalculator taxCalculator, LoanCalculator loanCalculator) {
        this.taxCalculator = taxCalculator;
        this.loanCalculator = loanCalculator;
    }

    @Override
    public LoanSimulationResponse simulateLoan(LoanSimulationRequest request) {
        validateRequest(request);

        int age = calculateAge(request.getDateOfBirthClient());
        BigDecimal annualInterestRate = taxCalculator.calculateInterestRate(age);

        BigDecimal interestRateMonthly = annualInterestRate.divide(MONTHS_IN_YEAR, 6, ROUNDING_MODE);

        BigDecimal loanAmount = request.getLoanAmount();
        int paymentPeriodMonths = request.getPaymentPeriodMonths();

        BigDecimal monthlyInstallment = loanCalculator.calculateMonthlyPayment(loanAmount, interestRateMonthly, paymentPeriodMonths);
        BigDecimal amountPaidTotal = monthlyInstallment.multiply(BigDecimal.valueOf(paymentPeriodMonths));
        BigDecimal interestPaidTotal = amountPaidTotal.subtract(loanAmount).max(BigDecimal.ZERO);

        monthlyInstallment = monthlyInstallment.setScale(2, ROUNDING_MODE);
        amountPaidTotal = amountPaidTotal.setScale(2, ROUNDING_MODE);
        interestPaidTotal = interestPaidTotal.setScale(2, ROUNDING_MODE);

        return createResponse(monthlyInstallment, amountPaidTotal, interestPaidTotal);
    }

    private void validateRequest(LoanSimulationRequest request) {
        if (request == null || request.getLoanAmount() == null || request.getLoanAmount().compareTo(BigDecimal.ZERO) <= 0 ||
                request.getPaymentPeriodMonths() <= 0 || request.getDateOfBirthClient() == null) {
            throw new IllegalArgumentException("Invalid loan simulation request");
        }
    }

    private int calculateAge(LocalDate dateBirth) {
        return Period.between(dateBirth, LocalDate.now()).getYears();
    }

    private LoanSimulationResponse createResponse(BigDecimal monthlyInstallment, BigDecimal totalAmountPaid, BigDecimal totalInterestPaid) {
        LoanSimulationResponse response = new LoanSimulationResponse();
        response.setInstallmentsMonthly(monthlyInstallment);
        response.setAmountPaidTotal(totalAmountPaid);
        response.setInterestPaidTotal(totalInterestPaid);
        return response;
    }
}
