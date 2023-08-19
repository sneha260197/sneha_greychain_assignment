package com.greychain.dto;

public class AggregateAmountByInterest {

	private double interestPerDay;
	private double aggregateAmount;
	
	public AggregateAmountByInterest(double interestPerDay, Double aggregateAmount) {
		super();
		this.interestPerDay = interestPerDay;
		this.aggregateAmount = aggregateAmount;
	}
	
	public double getInterestPerDay() {
		return interestPerDay;
	}
	public void setInterestPerDay(double interestPerDay) {
		this.interestPerDay = interestPerDay;
	}
	public double getAggregateAmount() {
		return aggregateAmount;
	}
	public void setAggregateAmount(double aggregateAmount) {
		this.aggregateAmount = aggregateAmount;
	}
	
	
}
