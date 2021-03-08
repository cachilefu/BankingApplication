package com.revature.project1;

public class LogTransaction {
	
	
	int transactionId;
	int origin_account_number;
	int destination_account_number;
	double amount;
	public LogTransaction(int transactionId, int origin_account_number, int destination_account_number, double amount) {
		super();
		this.transactionId = transactionId;
		this.origin_account_number = origin_account_number;
		this.destination_account_number = destination_account_number;
		this.amount = amount;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public int getOrigin_account_number() {
		return origin_account_number;
	}
	public int getDestination_account_number() {
		return destination_account_number;
	}
	public double getAmount() {
		return amount;
	}
	@Override
	public String toString() {
		return "Log Transaction [Transaction Id: " + transactionId + ", Origin Account Number: " + origin_account_number
				+ ", Destination Account Number: " + destination_account_number + ", Amount: " + amount + "]";
	}
	
	
	
	
	
	
	
	

}
