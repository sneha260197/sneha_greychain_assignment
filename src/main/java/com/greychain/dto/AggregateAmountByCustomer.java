package com.greychain.dto;

public class AggregateAmountByCustomer {

	private String customerID;
	private Double aggregateAmount;
	
	public AggregateAmountByCustomer(String customerID, Double aggregateAmount) {
		super();
		this.customerID = customerID;
		this.aggregateAmount = aggregateAmount;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public Double getAggregateAmount() {
		return aggregateAmount;
	}
	public void setAggregateAmount(Double aggregateAmount) {
		this.aggregateAmount = aggregateAmount;
	}
	
}
