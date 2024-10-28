package loanservice.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TaxCalculator {

    public BigDecimal calculateInterestRate(int age) {
        if (age <= 25) {
            return BigDecimal.valueOf(0.05);
        } else if (age <= 40) {
            return BigDecimal.valueOf(0.03);
        } else if (age <= 60) {
            return BigDecimal.valueOf(0.02);
        } else {
            return BigDecimal.valueOf(0.04);
        }
    }

}
