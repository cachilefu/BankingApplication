package com.revature.project1;

import java.util.Scanner;

public class CustomerAccountDaoImpl {

	private CustomerAccount account;
	private Customer customer;

	public CustomerAccountDaoImpl(CustomerAccount account, Customer customer) {
		this.account = account;
		this.customer = customer;

	}

	public void makeWithdrawal(Scanner sc) {
		int accountNumber = sc.nextInt();
		System.out.println("How much would you like to withdraw?");
		double amount = sc.nextDouble();

		if (amount > account.getBalance()) {
			System.out.println("You do not have enough funds to withdraw your desired amount.");
			return;
		} else if (amount <= 0) {

			System.out.println("You cannnot withdraw this amount. Please enter a valid amount.");
			return;

		}
		DBConnection.withdrawAmount(amount, customer, accountNumber);
		System.out.println("You have withdrawn " + amount + "from your account.");

	}

	public void makeTransfer(Scanner sc) {
		System.out.println("How much would you like to transfer?");
		double amount = sc.nextDouble();
		System.out.println("Which account would you like to transfer to?");
		int destinationAccount = sc.nextInt();
		DBConnection.transferAmount(amount, destinationAccount, destinationAccount);
		
		System.out.println("You have posted a trasfer for $" + amount + " to account " + destinationAccount);
	}

	public void makeDeposit(Scanner sc) {
		System.out.println("Which Account Would You Like To Deposit To?");
		int accountNumber = sc.nextInt();
		System.out.println("How much would you like to deposit?");
		double amount = sc.nextDouble();
		if (amount <= 0) {
			System.out.println("You cannot deposit a negative amount. Please try again.");
			return;
		}
		DBConnection.depositAmount(amount, customer, accountNumber);
		System.out.println("You have deposited " + amount + "to your account.");

	}

}
