package loanservice.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TaxCalculator {

    public BigDecimal calculateInterestRate(int idade) {
        if (idade <= 25) {
            return BigDecimal.valueOf(0.05);
        } else if (idade <= 40) {
            return BigDecimal.valueOf(0.03);
        } else if (idade <= 60) {
            return BigDecimal.valueOf(0.02);
        } else {
            return BigDecimal.valueOf(0.04);
        }
    }

}
