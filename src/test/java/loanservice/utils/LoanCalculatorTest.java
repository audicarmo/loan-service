package loanservice.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanCalculatorTest {

    private final LoanCalculator loanCalculator = new LoanCalculator();

    @Test
    public void testCalculateMonthlyPaymentWithZeroInterestRate() {
        BigDecimal loanAmount = new BigDecimal("10000");
        BigDecimal monthlyInterestRate = BigDecimal.ZERO;
        int paymentPeriodMonths = 12;

        BigDecimal expectedPayment = loanAmount.divide(BigDecimal.valueOf(paymentPeriodMonths), RoundingMode.HALF_EVEN);
        BigDecimal actualPayment = loanCalculator.calculateMonthlyPayment(loanAmount, monthlyInterestRate, paymentPeriodMonths);

        assertEquals(expectedPayment, actualPayment, "Payment should be evenly divided when interest rate is zero");
    }

    @Test
    public void testCalculateMonthlyPaymentWithNonZeroInterestRate() {
        BigDecimal loanAmount = new BigDecimal("10000");
        BigDecimal monthlyInterestRate = new BigDecimal("0.01"); // 1% monthly interest
        int paymentPeriodMonths = 12;

        BigDecimal base = monthlyInterestRate.add(BigDecimal.ONE).pow(paymentPeriodMonths);
        BigDecimal expectedPayment = loanAmount.multiply(monthlyInterestRate).multiply(base)
                .divide(base.subtract(BigDecimal.ONE), RoundingMode.HALF_EVEN);

        BigDecimal actualPayment = loanCalculator.calculateMonthlyPayment(loanAmount, monthlyInterestRate, paymentPeriodMonths);

        assertEquals(expectedPayment, actualPayment, "Payment should match calculated PMT formula result");
    }

    @Test
    public void testCalculateMonthlyPaymentWithShortTermLoan() {
        BigDecimal loanAmount = new BigDecimal("5000");
        BigDecimal monthlyInterestRate = new BigDecimal("0.05"); // 5% monthly interest
        int paymentPeriodMonths = 6;

        BigDecimal base = monthlyInterestRate.add(BigDecimal.ONE).pow(paymentPeriodMonths);
        BigDecimal expectedPayment = loanAmount.multiply(monthlyInterestRate).multiply(base)
                .divide(base.subtract(BigDecimal.ONE), RoundingMode.HALF_EVEN);

        BigDecimal actualPayment = loanCalculator.calculateMonthlyPayment(loanAmount, monthlyInterestRate, paymentPeriodMonths);

        assertEquals(expectedPayment, actualPayment, "Payment should be accurate for a short-term loan with interest");
    }

    @Test
    public void testCalculateMonthlyPaymentWithHighInterestRate() {
        BigDecimal loanAmount = new BigDecimal("1000");
        BigDecimal monthlyInterestRate = new BigDecimal("0.2"); // 20% monthly interest
        int paymentPeriodMonths = 3;

        BigDecimal base = monthlyInterestRate.add(BigDecimal.ONE).pow(paymentPeriodMonths);
        BigDecimal expectedPayment = loanAmount.multiply(monthlyInterestRate).multiply(base)
                .divide(base.subtract(BigDecimal.ONE), RoundingMode.HALF_EVEN);

        BigDecimal actualPayment = loanCalculator.calculateMonthlyPayment(loanAmount, monthlyInterestRate, paymentPeriodMonths);

        assertEquals(expectedPayment, actualPayment, "Payment should handle high interest rates correctly");
    }
}
