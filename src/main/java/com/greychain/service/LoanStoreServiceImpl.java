package com.greychain.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greychain.dto.AggregateAmountByCustomer;
import com.greychain.dto.AggregateAmountByInterest;
import com.greychain.dto.AggregateAmountByLender;
import com.greychain.dto.LoanStoreDto;
import com.greychain.model.LoanStore;
import com.greychain.repository.LoanStoreRepoInterface;

@Service
public class LoanStoreServiceImpl implements LoanStoreServiceInterface {
	
	@Autowired
	private LoanStoreRepoInterface loanStoreRepo;

	// Method to get loan details
	@Override
	public List<LoanStoreDto> getAllLoans() {
		List<LoanStore> loans = loanStoreRepo.findAll();
        return loans.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	// Method to convert into LoanStoreDto
	public LoanStoreDto convertToDTO(LoanStore loan) {
		LoanStoreDto dto = new LoanStoreDto();
		dto.setLoanID(loan.getLoanID());
		dto.setCustomerID(loan.getCustomerID());
		dto.setLenderID(loan.getLenderID());
		dto.setAmount(loan.getAmount());
		dto.setRemainingAmount(loan.getRemainingAmount());
		dto.setPaymentDate(loan.getPaymentDate());
		dto.setInterestPerDay(loan.getInterestPerDay());
		dto.setDueDate(loan.getDueDate());
		dto.setPenaltyPerDay(loan.getPenaltyPerDay());
		dto.setCancel(loan.isCancel());
        return dto;
    }

	// Method to add loan along with validations
	@Override
	public LoanStoreDto addLoan(LoanStore loan) throws Exception {
		// To check whether Payment Date is greater than Due Date, if so will throw error
		checkDueDate(loan.getLoanID(), loan.getDueDate(), loan.getPaymentDate());
		
		// To add loan to loan store table in database
		try {
			LoanStore newLoan = new LoanStore();
			newLoan.setLoanID(loan.getLoanID());
			newLoan.setCustomerID(loan.getCustomerID());
			newLoan.setLenderID(loan.getLenderID());
			newLoan.setAmount(loan.getAmount());
			newLoan.setRemainingAmount(loan.getRemainingAmount());
			newLoan.setPaymentDate(loan.getPaymentDate());
			newLoan.setInterestPerDay(loan.getInterestPerDay());
			newLoan.setDueDate(loan.getDueDate());
			newLoan.setPenaltyPerDay(loan.getPenaltyPerDay());
			newLoan.setCancel(false);
			LoanStore savedLoan = loanStoreRepo.save(newLoan);
			LoanStoreDto savedLoanDto = convertToDTO(savedLoan);
			
			// To check Loan Overdue
			checkOverDueLoan(loan.getLoanID(), loan.getDueDate());
			return savedLoanDto;
		} catch (Exception e) {
			System.out.println("Exception occured while adding loan");
			throw e;
		}
	}

	// Method to check whether Payment Date is greater than Due Date, if so will throw error
	public void checkDueDate(String loanID, String dueDate, String paymentDate) throws Exception {
		if (dueDate.compareTo(paymentDate) < 0) {
			System.out.println("Alert: Loan with ID " + loanID + " has crossed the due date.");
			throw new Exception("Loan with ID " + loanID + " has crossed the due date.");
		}
	}
	
	// Method to check Loan Overdue
	public void checkOverDueLoan(String loanId, String dueDate) {
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (currentDate.compareTo(dueDate) < 0) {
			System.out.println("Alert: Loan " + loanId + " is overdue");
		}
	}
	
	// Method to get aggregate amount by lender
	@Override
	public List<AggregateAmountByLender> aggregateByLender() {
		List<Object[]> lenderAggregates = loanStoreRepo.aggregateAmountByLender();
		List<AggregateAmountByLender> lenderAggregateDtos = lenderAggregates.stream()
				.map(this::convertToLenderDto).collect(Collectors.toList());

		return lenderAggregateDtos;
	}
	
	// Method to convert into AggregateAmountByLender DTO
	public AggregateAmountByLender convertToLenderDto(Object[] data) {
		AggregateAmountByLender dto = new AggregateAmountByLender((String) data[0], (Double) data[1]);
        return dto;
	}

	// Method to get aggregate amount by interest
	@Override
	public List<AggregateAmountByInterest> aggregateByInterest() {
		List<Object[]> interestAggregates = loanStoreRepo.aggregateInterestByInterest();
		List<AggregateAmountByInterest> interestAggregateDtos = interestAggregates.stream()
				.map(this::convertToInterestDto).collect(Collectors.toList());

		return interestAggregateDtos;
	}
	
	// Method to convert into AggregateAmountByInterest DTO
	public AggregateAmountByInterest convertToInterestDto(Object[] data) {
		AggregateAmountByInterest dto = new AggregateAmountByInterest((Double) data[0], (Double) data[1]);
        return dto;
	}

	// Method to get aggregate amount by customer
	@Override
	public List<AggregateAmountByCustomer> aggregateByCustomerID() {
		List<Object[]> customerAggregates = loanStoreRepo.aggregateAmountByCustomer();
		List<AggregateAmountByCustomer> customerAggregateDtos = customerAggregates.stream()
				.map(this::convertToCustomerDto).collect(Collectors.toList());

		return customerAggregateDtos;
	}

	// Method to convert into AggregateAmountByCustomer DTO
	public AggregateAmountByCustomer convertToCustomerDto(Object[] data) {
		AggregateAmountByCustomer dto = new AggregateAmountByCustomer((String) data[0], (Double) data[1]);
        return dto;
	}

}
