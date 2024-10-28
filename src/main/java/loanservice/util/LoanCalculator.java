package loanservice.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class LoanCalculator {

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    public BigDecimal calculateMonthlyPayment(BigDecimal loanAmount, BigDecimal monthlyInterestRate, int paymentPeriodMonths) {
        if (monthlyInterestRate.compareTo(BigDecimal.ZERO) == 0) {
            return loanAmount.divide(BigDecimal.valueOf(paymentPeriodMonths), ROUNDING_MODE);
        }

        // Fórmula PMT: A = P * (r * (1 + r)^n) / ((1 + r)^n - 1)
        BigDecimal base = monthlyInterestRate.add(BigDecimal.ONE).pow(paymentPeriodMonths);
        return loanAmount.multiply(monthlyInterestRate).multiply(base)
                .divide(base.subtract(BigDecimal.ONE), ROUNDING_MODE);
    }
}

