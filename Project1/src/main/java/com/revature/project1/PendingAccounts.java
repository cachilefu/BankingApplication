package com.revature.project1;

public class PendingAccounts {
	
	private int pendingAccountId;
	private double balance;
	private String AccountType;
	private int customerId;
	
	
	public PendingAccounts(int pendingAccountId, double balance, String accountType, int customerId) {
		super();
		this.pendingAccountId = pendingAccountId;
		this.balance = balance;
		AccountType = accountType;
		this.customerId = customerId;
	}


	public int getPendingAccountId() {
		return pendingAccountId;
	}


	public double getBalance() {
		return balance;
	}


	public String getAccountType() {
		return AccountType;
	}


	public int getCustomerId() {
		return customerId;
	}


	@Override
	public String toString() {
		return "PendingAccounts [Pending AccountId: " + pendingAccountId + ", Balance: " + balance + ", Account Type: "
				+ AccountType + ", Customer ID: " + customerId + "]";
	}
	

}
