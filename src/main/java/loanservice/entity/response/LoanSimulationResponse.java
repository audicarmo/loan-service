package loanservice.entity.response;

import java.math.BigDecimal;

public class LoanSimulationResponse {

    private BigDecimal amountPaidTotal;
    private BigDecimal installmentsMonthly;
    private BigDecimal interestPaidTotal;

    // Getters e Setters
    public BigDecimal getAmountPaidTotal() {
        return amountPaidTotal;
    }

    public void setAmountPaidTotal(BigDecimal amountPaidTotal) {
        this.amountPaidTotal = amountPaidTotal;
    }

    public BigDecimal getInstallmentsMonthly() {
        return installmentsMonthly;
    }

    public void setInstallmentsMonthly(BigDecimal installmentsMonthly) {
        this.installmentsMonthly = installmentsMonthly;
    }

    public BigDecimal getInterestPaidTotal() {
        return interestPaidTotal;
    }

    public void setInterestPaidTotal(BigDecimal interestPaidTotal) {
        this.interestPaidTotal = interestPaidTotal;
    }

}
