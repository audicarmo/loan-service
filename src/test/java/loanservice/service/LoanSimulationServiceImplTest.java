package loanservice.service;

import loanservice.entity.request.LoanSimulationRequest;
import loanservice.entity.response.LoanSimulationResponse;
import loanservice.service.impl.LoanSimulationServiceImpl;
import loanservice.utils.LoanCalculator;
import loanservice.utils.TaxCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanSimulationServiceImplTest {

    @Mock
    private TaxCalculator taxCalculator;

    @Mock
    private LoanCalculator loanCalculator;

    @InjectMocks
    private LoanSimulationServiceImpl loanSimulationService;

    private LoanSimulationRequest request;

    @BeforeEach
    public void setup() {
        request = new LoanSimulationRequest();
        request.setLoanAmount(BigDecimal.valueOf(10000));
        request.setDateOfBirthClient(LocalDate.of(1995, 6, 15));
        request.setPaymentPeriodMonths(24);
    }

    @Test
    public void testSimulateLoan_ValidRequest() {
        when(taxCalculator.calculateInterestRate(anyInt())).thenReturn(BigDecimal.valueOf(0.03));
        when(loanCalculator.calculateMonthlyPayment(any(BigDecimal.class), any(BigDecimal.class), anyInt()))
                .thenReturn(BigDecimal.valueOf(429.81));

        LoanSimulationResponse response = loanSimulationService.simulateLoan(request);

        assertNotNull(response);
        assertEquals(BigDecimal.valueOf(429.81), response.getInstallmentsMonthly());
        assertEquals(BigDecimal.valueOf(10315.44), response.getAmountPaidTotal());
        assertEquals(BigDecimal.valueOf(315.44), response.getInterestPaidTotal());
    }

    @Test
    public void testSimulateLoan_InvalidRequest_NullLoanAmount() {
        request.setLoanAmount(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            loanSimulationService.simulateLoan(request);
        });
        assertEquals("Invalid loan simulation request", exception.getMessage());
    }

    @Test
    public void testSimulateLoan_InvalidRequest_NegativeLoanAmount() {
        request.setLoanAmount(BigDecimal.valueOf(-10000));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            loanSimulationService.simulateLoan(request);
        });
        assertEquals("Invalid loan simulation request", exception.getMessage());
    }

    @Test
    public void testSimulateLoan_ZeroInterestRate() {
        lenient().when(taxCalculator.calculateInterestRate(anyInt())).thenReturn(BigDecimal.ZERO);
        lenient().when(loanCalculator.calculateMonthlyPayment(any(BigDecimal.class), any(BigDecimal.class), anyInt()))
                .thenReturn(BigDecimal.valueOf(416.67));

        LoanSimulationResponse response = loanSimulationService.simulateLoan(request);

        assertNotNull(response);
        assertEquals(BigDecimal.valueOf(416.67), response.getInstallmentsMonthly());
        assertEquals(BigDecimal.valueOf(10000.08), response.getAmountPaidTotal());
        assertEquals(BigDecimal.valueOf(0.08), response.getInterestPaidTotal());
    }

}
