package com.revature.project1;

import java.util.ArrayList;

public class CustomerAccount {
	
	private String accountType;
	
	private int accountNumber;
	
	private int customerId;
	
	private double balance;
	
	//int previousTransaction;
	
	//boolean approval = false;

	
	

	public CustomerAccount(String accountType, int accountNumber, int customerId, double balance) {
		super();
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.balance = balance;
	}


	public String getAccountType() {
		return accountType;
	}


	public int getAccountNumber() {
		return accountNumber;
	}




	public int getCustomerId() {
		return customerId;
	}



	public double getBalance() {
		return balance;
	}



	void deposit(int amount) {
		
		if(amount > 0) {
			balance = balance + amount;
			
		}

	}
		void withdraw(int amount) {
			if(amount > 0) {
				balance = balance - amount;
			}
		 else {
			System.out.println("Please enter an amoint greater than 0.");
		}
		
		
			
	}


		@Override
		public String toString() {
			return "CustomerAccount [accountType=" + accountType + ", accountNumber=" + accountNumber + ", customerId="
					+ customerId + ", balance=" + balance + "]";
		}

	
	
	
}

