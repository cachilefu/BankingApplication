package com.revature.project1;

import java.util.Date;

public class CustomerTransfer {
	
	

	int transferId;
	
	private double amount;
	
	private int originAccountNumber ;
	
	private int destinationAccountNumber;

	public CustomerTransfer(int transferId, double amount, int originAccountNumber, int destinationAccountNumber) {
		super();
		this.transferId = transferId;
		this.amount = amount;
		this.originAccountNumber = originAccountNumber;
		this.destinationAccountNumber = destinationAccountNumber;
		
		
	}

	public int getTransferId() {
		return transferId;
	}

	public double getAmount() {
		return amount;
	}

	public int getOriginAccountNumber() {
		return originAccountNumber;
	}

	public int getDestinationAccountNumber() {
		return destinationAccountNumber;
	}

	@Override
	public String toString() {
		return "CustomerTransfer [transferId=" + transferId + ", amount=" + amount + ", originAccountNumber="
				+ originAccountNumber + ", destinationAccountNumber=" + destinationAccountNumber + "]";
	}


	

}
	

