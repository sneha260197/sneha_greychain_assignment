package com.greychain.service;

import java.util.List;

import com.greychain.dto.AggregateAmountByCustomer;
import com.greychain.dto.AggregateAmountByInterest;
import com.greychain.dto.AggregateAmountByLender;
import com.greychain.dto.LoanStoreDto;
import com.greychain.model.LoanStore;

public interface LoanStoreServiceInterface {
	
	public List<LoanStoreDto> getAllLoans();
	
	public LoanStoreDto addLoan(LoanStore loan) throws Exception;
	
	public List<AggregateAmountByLender> aggregateByLender();
	
	public List<AggregateAmountByInterest> aggregateByInterest();
	
	public List<AggregateAmountByCustomer> aggregateByCustomerID();

}
