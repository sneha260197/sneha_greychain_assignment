package com.greychain.serviceTest;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.greychain.dto.AggregateAmountByCustomer;
import com.greychain.dto.AggregateAmountByInterest;
import com.greychain.dto.AggregateAmountByLender;
import com.greychain.dto.LoanStoreDto;
import com.greychain.model.LoanStore;
import com.greychain.repository.LoanStoreRepoInterface;
import com.greychain.service.LoanStoreServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LoanStoreServiceImplTest {
	
	@Mock
    private LoanStoreRepoInterface loanStoreRepo;

    @InjectMocks
    private LoanStoreServiceImpl loanStoreService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetAllLoans() {
        List<LoanStore> loanStoreList = new ArrayList<>();
        try {
			loanStoreList.add(new LoanStore("L1", "C1", "LEN1", 10000, 10000, "2023-06-05", 1, "2023-07-05", 0.0001, false));
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
			loanStoreList.add(new LoanStore("L2", "C1", "LEN1", 20000, 5000, "2023-06-01", 0.0001, "2023-08-05", 0.0001, false));
		} catch (Exception e) {
			e.printStackTrace();
		}
        when(loanStoreRepo.findAll()).thenReturn(loanStoreList);

        List<LoanStoreDto> expectedDtos = loanStoreList.stream()
                .map(loan -> loanStoreService.convertToDTO(loan))
                .collect(Collectors.toList());

        List<LoanStoreDto> actualDtos = loanStoreService.getAllLoans();

        assertEquals(expectedDtos, actualDtos);
    }
    
    @Test
    public void testAddLoan() throws Exception {
        LoanStore loan = new LoanStore();
        loan.setLoanID("123");
        loan.setDueDate("2023-08-31");
        loan.setPaymentDate("2023-08-25");

        when(loanStoreRepo.save(any(LoanStore.class))).thenReturn(loan);

        assertDoesNotThrow(() -> loanStoreService.addLoan(loan));

        verify(loanStoreRepo).save(any(LoanStore.class));
    }

    @Test
    public void testCheckDueDate() {
        LoanStore loan = new LoanStore();
        loan.setLoanID("456");
        loan.setDueDate("2023-08-20");
        loan.setPaymentDate("2023-08-25");

        assertThrows(Exception.class, () -> loanStoreService.addLoan(loan));
    }

    @Test
    public void testCheckOverDueLoanNotOverdue() {
        String loanId = "789";
        String dueDate = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        loanStoreService.checkOverDueLoan(loanId, dueDate);
    }

    @Test
    public void testCheckOverDueLoanOverdue() {
        String loanId = "789";
        String dueDate = LocalDate.now().minusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        loanStoreService.checkOverDueLoan(loanId, dueDate);
    }
    
    @Test
    public void testAggregateByLender() {
        List<Object[]> lenderAggregates = new ArrayList<>();
        lenderAggregates.add(new Object[]{"LEN1", 50000.0});
        lenderAggregates.add(new Object[]{"LEN2", 30000.0});

        when(loanStoreRepo.aggregateAmountByLender()).thenReturn(lenderAggregates);

        List<AggregateAmountByLender> result = loanStoreService.aggregateByLender();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("LEN1", result.get(0).getLenderID());
        assertEquals(50000.0, result.get(0).getAggregateAmount());

        assertEquals("LEN2", result.get(1).getLenderID());
        assertEquals(30000.0, result.get(1).getAggregateAmount());
    }
    
    @Test
    public void testAggregateByInterest() {
        List<Object[]> interestAggregates = new ArrayList<>();
        interestAggregates.add(new Object[]{5.0, 1000.0});
        interestAggregates.add(new Object[]{6.5, 1500.0});

        when(loanStoreRepo.aggregateInterestByInterest()).thenReturn(interestAggregates);

        List<AggregateAmountByInterest> result = loanStoreService.aggregateByInterest();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(5.0, result.get(0).getInterestPerDay());
        assertEquals(1000.0, result.get(0).getAggregateAmount());

        assertEquals(6.5, result.get(1).getInterestPerDay());
        assertEquals(1500.0, result.get(1).getAggregateAmount());
    }
    
    @Test
    public void testAggregateByCustomerID() {
        List<Object[]> customerAggregates = new ArrayList<>();
        customerAggregates.add(new Object[]{"customer1", 1000.0});
        customerAggregates.add(new Object[]{"customer2", 1500.0});

        when(loanStoreRepo.aggregateAmountByCustomer()).thenReturn(customerAggregates);

        List<AggregateAmountByCustomer> result = loanStoreService.aggregateByCustomerID();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("customer1", result.get(0).getCustomerID());
        assertEquals(1000.0, result.get(0).getAggregateAmount());

        assertEquals("customer2", result.get(1).getCustomerID());
        assertEquals(1500.0, result.get(1).getAggregateAmount());
    }
}
