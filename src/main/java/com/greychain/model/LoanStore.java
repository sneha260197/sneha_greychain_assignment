package com.greychain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan_store")
public class LoanStore implements Serializable {

	@Id
	@Column(name = "loan_id")
	private String loanID;
	@Column(name = "customer_id")
	private String customerID;
	@Column(name = "lender_id")
	private String lenderID;
	private double amount;
	@Column(name = "remaining_amount")
	private double remainingAmount;
	@Column(name = "payment_date")
	private String paymentDate;
	@Column(name = "interest_per_day")
	private double interestPerDay;
	@Column(name = "due_date")
	private String dueDate;
	@Column(name = "penalty_per_day")
	private double penaltyPerDay;
	private boolean cancel;

	public LoanStore(String loanID, String customerID, String lenderID, double amount, double remainingAmount,
			String paymentDate, double interestPerDay, String dueDate, double penaltyPerDay, boolean cancel) throws Exception {
		if (paymentDate.compareTo(dueDate) > 0) {
			throw new Exception("Payment date can't be greater than the Due Date");
		}

		this.loanID = loanID;
		this.customerID = customerID;
		this.lenderID = lenderID;
		this.amount = amount;
		this.remainingAmount = remainingAmount;
		this.paymentDate = paymentDate;
		this.interestPerDay = interestPerDay;
		this.dueDate = dueDate;
		this.penaltyPerDay = penaltyPerDay;
		this.cancel = false;
	}

	public LoanStore() {
		
	}

	public String getLoanID() {
		return loanID;
	}

	public void setLoanID(String loanID) {
		this.loanID = loanID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getLenderID() {
		return lenderID;
	}

	public void setLenderID(String lenderID) {
		this.lenderID = lenderID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getInterestPerDay() {
		return interestPerDay;
	}

	public void setInterestPerDay(double interestPerDay) {
		this.interestPerDay = interestPerDay;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public double getPenaltyPerDay() {
		return penaltyPerDay;
	}

	public void setPenaltyPerDay(double penaltyPerDay) {
		this.penaltyPerDay = penaltyPerDay;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}


}
