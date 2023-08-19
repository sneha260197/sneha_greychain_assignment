package com.greychain.dto;

import java.util.Objects;

public class LoanStoreDto {
	
	private String loanID;
	private String customerID;
	private String lenderID;
	private double amount;
	private double remainingAmount;
	private String paymentDate;
	private double interestPerDay;
	private String dueDate;
	private double penaltyPerDay;
	private boolean cancel;
	
	public LoanStoreDto() {
		super();
	}

	public LoanStoreDto(String loanID, String customerID, String lenderID, double amount, double remainingAmount,
			String paymentDate, double interestPerDay, String dueDate, double penaltyPerDay, boolean cancel) {
		this.loanID = loanID;
		this.customerID = customerID;
		this.lenderID = lenderID;
		this.amount = amount;
		this.remainingAmount = remainingAmount;
		this.paymentDate = paymentDate;
		this.interestPerDay = interestPerDay;
		this.dueDate = dueDate;
		this.penaltyPerDay = penaltyPerDay;
		this.cancel = cancel;
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

	@Override
	public int hashCode() {
		return Objects.hash(amount, cancel, customerID, dueDate, interestPerDay, lenderID, loanID, paymentDate,
				penaltyPerDay, remainingAmount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanStoreDto other = (LoanStoreDto) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && cancel == other.cancel
				&& Objects.equals(customerID, other.customerID) && Objects.equals(dueDate, other.dueDate)
				&& Double.doubleToLongBits(interestPerDay) == Double.doubleToLongBits(other.interestPerDay)
				&& Objects.equals(lenderID, other.lenderID) && Objects.equals(loanID, other.loanID)
				&& Objects.equals(paymentDate, other.paymentDate)
				&& Double.doubleToLongBits(penaltyPerDay) == Double.doubleToLongBits(other.penaltyPerDay)
				&& Double.doubleToLongBits(remainingAmount) == Double.doubleToLongBits(other.remainingAmount);
	}
	

}
