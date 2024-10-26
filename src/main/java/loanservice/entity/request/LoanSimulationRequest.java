package loanservice.entity.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanSimulationRequest {

    private BigDecimal loanAmount;
    private LocalDate dateOfBirthClient;
    private int paymentPeriodMonths;

    // Getters e Setters
    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public LocalDate getDateOfBirthClient() {
        return dateOfBirthClient;
    }

    public void setDateOfBirthClient(LocalDate dateOfBirthClient) {
        this.dateOfBirthClient = dateOfBirthClient;
    }

    public int getPaymentPeriodMonths() {
        return paymentPeriodMonths;
    }

    public void setPaymentPeriodMonths(int paymentPeriodMonths) {
        this.paymentPeriodMonths = paymentPeriodMonths;
    }

}
