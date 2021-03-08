package com.revature.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDaoImpl {

	private final Employee employee;
	private boolean logout = false;

	public EmployeeDaoImpl(Employee employee) {
		super();
		this.employee = employee;
		System.out.println("Welcome " + employee.toString() + "!");

	}

	public void displayEmployeeOptions(Scanner sc) {
		while (!logout) {
			System.out.println("What would you like to do?");
			System.out.println("1. View Customer Accounts");
			System.out.println("2. Check Pending Accounts");
			System.out.println("3. Approve Pending Accounts ");
			System.out.println("4. Reject Pending Accounts ");
			System.out.println("5. View Transactions");
			System.out.println("6. Logout");

			int decision = 0;
			while (decision == 0) {

				System.out.print("Enter Option Number:");
				if (sc.hasNextInt()) {
					decision = sc.nextInt();
				} else

					sc.next();
				System.out.println();

			}
			if (decision == 1) {
				viewCustomerAccounts(sc);

			} else if (decision == 2) {
				checkPendingAccounts(sc);
			
			} else if (decision ==3) {
				approvePendingAccounts(sc);
			}
			
			else if (decision ==4) {
				rejectPendingAccounts(sc);
			}
			else if (decision ==5) {
				viewTransactionLog(sc);
			}
			
			else if (decision == 6) {

				logout = true;

			} else {
				System.out.println("Please enter a valid number.");
			}

		}
	}

	private void viewTransactionLog(Scanner sc) {
		ArrayList<LogTransaction> log = DBConnection.viewTransactionLogs();
		
		for (LogTransaction logs : log)
			System.out.println(logs.toString());
	}

	private void rejectPendingAccounts(Scanner sc) {
		System.out.println("Enter the ID number for the account you would like to reject.");
		int transferId = sc.nextInt();
		DBConnection.rejectAccountApplication(transferId);
		
	}

	private void approvePendingAccounts(Scanner sc) {
		System.out.println("Enter the ID for the account you would like to approve.");
		int pendingAccountId = sc.nextInt();
		
		DBConnection.approveAccountApplication(pendingAccountId);
		
		System.out.println("The account has been approved.");
		
	}

	private void checkPendingAccounts(Scanner sc) {
		ArrayList<PendingAccounts> pendingAccounts = DBConnection.checkPendingAccounts();

		for (PendingAccounts pAccount : pendingAccounts)
			System.out.println(pAccount.toString());


	}

	private void viewCustomerAccounts(Scanner sc) {
		System.out.println("Enter the Customer Id for the account you would like to view.");
		int customerId = sc.nextInt();

		Customer customer = DBConnection.findCustomerById(customerId);
		System.out.println(customer.toString());
		ArrayList<CustomerAccount> accounts = DBConnection.findCustomerAccountsById(customerId);

		for (CustomerAccount account : accounts)
			System.out.println(account.toString());
		
		

	}

}