package com.greychain.dto;

public class AggregateAmountByLender {
	
	private String lenderID;
	private Double aggregateAmount;
	
	public AggregateAmountByLender(String lenderID, Double aggregateAmount) {
		super();
		this.lenderID = lenderID;
		this.aggregateAmount = aggregateAmount;
	}
	
	public String getLenderID() {
		return lenderID;
	}
	public void setLenderID(String lenderID) {
		this.lenderID = lenderID;
	}
	public Double getAggregateAmount() {
		return aggregateAmount;
	}
	public void setAggregateAmount(Double aggregateAmount) {
		this.aggregateAmount = aggregateAmount;
	}

	
}
