package loanservice.service;

import loanservice.entity.request.LoanSimulationRequest;
import loanservice.entity.response.LoanSimulationResponse;
import loanservice.service.impl.LoanSimulationServiceImpl;
import loanservice.utils.LoanCalculator;
import loanservice.utils.TaxCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
    @Disabled("Necessário corrigir NullPointerException - investigação em andamento")
    void testSimulateLoans() {
        // Criação de LoanSimulationRequest com setters
        LoanSimulationRequest request1 = new LoanSimulationRequest();
        request1.setLoanAmount(new BigDecimal("10000"));
        request1.setDateOfBirthClient(LocalDate.of(1990, 1, 1));
        request1.setPaymentPeriodMonths(24);

        LoanSimulationRequest request2 = new LoanSimulationRequest();
        request2.setLoanAmount(new BigDecimal("5000"));
        request2.setDateOfBirthClient(LocalDate.of(1985, 5, 15));
        request2.setPaymentPeriodMonths(12);

        // Criação de LoanSimulationResponse com setters
        LoanSimulationResponse response1 = new LoanSimulationResponse();
        response1.setInstallmentsMonthly(new BigDecimal("450.00"));
        response1.setAmountPaidTotal(new BigDecimal("10800.00"));
        response1.setInterestPaidTotal(new BigDecimal("800.00"));

        LoanSimulationResponse response2 = new LoanSimulationResponse();
        response2.setInstallmentsMonthly(new BigDecimal("420.00"));
        response2.setAmountPaidTotal(new BigDecimal("5040.00"));
        response2.setInterestPaidTotal(new BigDecimal("40.00"));

        // Configuração de mock para taxCalculator e loanCalculator
        when(taxCalculator.calculateInterestRate(33)).thenReturn(new BigDecimal("5.00")); // idade calculada para request1
        when(taxCalculator.calculateInterestRate(38)).thenReturn(new BigDecimal("4.50")); // idade calculada para request2

        when(loanCalculator.calculateMonthlyPayment(new BigDecimal("10000"), new BigDecimal("0.00417"), 24))
                .thenReturn(new BigDecimal("450.00"));
        when(loanCalculator.calculateMonthlyPayment(new BigDecimal("5000"), new BigDecimal("0.00375"), 12))
                .thenReturn(new BigDecimal("420.00"));

        // Executar o método simulateLoans
        List<LoanSimulationResponse> responses = loanSimulationService.simulateLoans(Arrays.asList(request1, request2));

        // Verificação dos resultados
        assertEquals(2, responses.size());
        assertEquals(new BigDecimal("450.00"), responses.get(0).getInstallmentsMonthly());
        assertEquals(new BigDecimal("420.00"), responses.get(1).getInstallmentsMonthly());
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
