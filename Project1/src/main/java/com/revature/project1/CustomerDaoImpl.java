package com.revature.project1;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDaoImpl {

	private final Customer customer;
	private boolean logout = false;

	public CustomerDaoImpl(Customer customer) {
		super();
		this.customer = customer;
		System.out.println("Welcome " + customer.toString() + "!");
	}

	public void displayCustomerOptions(Scanner sc) {
		while (!logout) {
			System.out.println("What would you like to do?");
			System.out.println("1. View My Accounts");
			System.out.println("2. Make a Deposit");
			System.out.println("3. Make a Withdrawl");
			System.out.println("4. Tranfer Money");
			System.out.println("5. View Pending Transfers");
			System.out.println("6. Approve Pending Transfers");
			System.out.println("7. Reject Pending Transfers");
			System.out.println("8. Create a New Account");
			System.out.println("9. Log Out");

			int decision = 0;
			while (decision == 0) {

				System.out.print("Enter Option Number:");
				if (sc.hasNextInt()) {
					decision = sc.nextInt();
				} else
					// if the input is not an integer, it will not be assigned to decision, but
					// the input will still be used.
					sc.next();
				System.out.println();

			}
			if (decision == 1) {
				viewAccounts(sc);
			
			} else if (decision == 2) {
				getAccount(sc).makeDeposit(sc);
			
			} else if (decision == 3) {
				getAccount(sc).makeWithdrawal(sc);
			
			} else if (decision == 4) {
				getAccount(sc).makeTransfer(sc);

			} else if (decision == 5) {
				viewPendingTransfers(sc);
			}
			else if (decision == 6) {
				approvePendingTransfers(sc);
			}
			else if (decision == 7) {
				rejectPendingTransfers(sc);
			}

			else if (decision == 8) {
				makeAccount(sc);
			}

			else if (decision == 9) {

				logout = true;

			} else {
				System.out.println("Please enter a valid number.");
			}
		}

	}

	private void viewPendingTransfers(Scanner sc) {

		ArrayList<CustomerTransfer> pendingTransfers = DBConnection.checkPendingTransfers(customer);
		for (CustomerTransfer transfer : pendingTransfers)
			System.out.println(transfer.toString());
	
	}
		
	
	
	private void approvePendingTransfers(Scanner sc) {
		
		System.out.println("Enter the account number that the transfer is from.");
		int originAccountNumber = sc.nextInt();
		
		System.out.println("Enter the account number that the transfer is going to.");
		int destinationAccountNumber = sc.nextInt();
		
		System.out.println("Enter the amount that is being transfered?");
		double amount = sc.nextDouble();
		
		System.out.println("Enter the transfer id ");
		int transferId = sc.nextInt();
		
		DBConnection.approveTransfer(originAccountNumber, destinationAccountNumber, amount, transferId);
			
	System.out.println("The transfer has been approved.");
}
				
		private void rejectPendingTransfers(Scanner sc) {
		System.out.println("Enter the Transfer Id.");
		int transferId = sc.nextInt();
		
		DBConnection.rejectTransfer(transferId);
			System.out.println("The transfer has been rejected.");
			
		}
	
	
	private void makeAccount(Scanner sc) {
		System.out.println("How much money would you like to start with?");
		double balance = sc.nextDouble();
		if (balance <= 0) {
			System.out.println("Your inital amount is too low.");
			return;
		}
		System.out.println("Is this a Checking Account or a Savings Account?");
		String accountType = sc.next();

		DBConnection.accountApplication(balance, accountType, customer);
		
		System.out.println("Your account application has been submitted.");
	}

	private CustomerAccountDaoImpl getAccount(Scanner sc) {
		System.out.println("Enter your account number.");
		int accountNumber = sc.nextInt();
		CustomerAccount account = DBConnection.findByAccountByNumber(accountNumber, customer);
		return new CustomerAccountDaoImpl(account, customer);
	}

	private void viewAccounts(Scanner sc) {
		ArrayList<CustomerAccount> accounts = DBConnection.findAccountsById(customer);

		for (CustomerAccount account : accounts)
			System.out.println(accounts.toString());

	}

}
