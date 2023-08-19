package com.greychain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greychain.dto.AggregateAmountByCustomer;
import com.greychain.dto.AggregateAmountByInterest;
import com.greychain.dto.AggregateAmountByLender;
import com.greychain.dto.LoanStoreDto;
import com.greychain.model.LoanStore;
import com.greychain.service.LoanStoreServiceImpl;

@RestController
@RequestMapping("/loan-store")
public class LoanStoreController {

	@Autowired
	private LoanStoreServiceImpl loanStoreService;
	
	// API to get loan details
	@GetMapping("/loans")
	public List<LoanStoreDto> getAllLoans() {
		return loanStoreService.getAllLoans();
	}

	// API to add loan along with validations 
	@PostMapping("/loans")
	public LoanStoreDto addLoan(@RequestBody LoanStore loan) throws Exception {
		return loanStoreService.addLoan(loan);
	}

	// API to get aggregate amount by lender
	@GetMapping("/aggregateByLender")
	public ResponseEntity<List<AggregateAmountByLender>> aggregateByLender() {
		List<AggregateAmountByLender> lenderGroups = loanStoreService.aggregateByLender();
		return ResponseEntity.ok(lenderGroups);
	}

	// API to get aggregate amount by interest
	@GetMapping("/aggregateByInterest")
	public ResponseEntity<List<AggregateAmountByInterest>> aggregateByInterest() {
		List<AggregateAmountByInterest> interestGroups = loanStoreService.aggregateByInterest();
		return ResponseEntity.ok(interestGroups);
	}

	// API to get aggregate amount by customer
	@GetMapping("/aggregateByCustomerID")
	public ResponseEntity<List<AggregateAmountByCustomer>> aggregateByCustomerID() {
		List<AggregateAmountByCustomer> customerIdGroups = loanStoreService.aggregateByCustomerID();
		return ResponseEntity.ok(customerIdGroups);
	}

}
