package com.greychain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greychain.model.LoanStore;

@Repository
public interface LoanStoreRepoInterface extends JpaRepository<LoanStore, String> {

	// Query to aggregate amount by lender
	@Query("SELECT ls.lenderID, SUM(ls.remainingAmount + ls.interestPerDay + ls.penaltyPerDay) "
			+ "FROM LoanStore ls GROUP BY ls.lenderID")
	List<Object[]> aggregateAmountByLender();

	// Query to aggregate amount by interest
	@Query("SELECT ls.interestPerDay, SUM(ls.remainingAmount + ls.interestPerDay + ls.penaltyPerDay) " 
			+ "FROM LoanStore ls GROUP BY ls.interestPerDay")
	List<Object[]> aggregateInterestByInterest();

	// Query to aggregate amount by customer
	@Query("SELECT ls.customerID, SUM(ls.remainingAmount + ls.interestPerDay + ls.penaltyPerDay) "
			+ "FROM LoanStore ls GROUP BY ls.customerID")
	List<Object[]> aggregateAmountByCustomer();
}
