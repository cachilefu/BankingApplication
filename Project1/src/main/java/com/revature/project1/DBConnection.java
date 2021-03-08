package com.revature.project1;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {

	private static Connection conn;

	// creating a connection if we do not have one
	private static void connect() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			return;
		}
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres", "root1234");
	}

	public static boolean usernameIsInvalid(String username) {
		try {
			return usernameExists(username) == false;

		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}

	}

	public static boolean usernameIsTaken(String username) {
		try {
			return usernameExists(username);
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}

	}

	// returns true if the username is valid
	private static boolean usernameExists(String username) throws SQLException {

		connect();
		String sql = "SELECT * FROM customers WHERE username = ?";
		PreparedStatement smt = conn.prepareStatement(sql);
		// first value is which question mark it is from left to right
		// the second is what the variable is.
		smt.setString(1, username);
		// This gets a result set back when we execute a query.
		ResultSet rs = smt.executeQuery();
		// rs.next() return true if there is another result.
		return rs.next();

	}

	public static boolean employeeUsernameIsInvalid(String username) {

		try {
			connect();
			String sql = "SELECT * FROM employees WHERE username = ?";
			PreparedStatement smt = conn.prepareStatement(sql);
			// first value is which question mark it is from left to right
			// the second is what the variable is.
			smt.setString(1, username);
			// This gets a result set back when we execute a query.
			ResultSet rs = smt.executeQuery();
			// rs.next() return true if there is another result.

			boolean usernameExists = rs.next();
			// if usernameExists is true, then the method should return false.
			return usernameExists == false;

		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}

	}

	// returns true if the username is valid
	public static Customer getCustomer(String username, String password) {
		// defining customer variable
		Customer customer = null;
		try {
			connect();
			String sql = "SELECT * FROM customers WHERE username = ? AND password = ?";
			PreparedStatement smt = conn.prepareStatement(sql);
			// first value is which question mark it is from left to right
			// the second is what the variable is.
			// The username and password in the database are Strings.
			smt.setString(1, username);
			smt.setString(2, password);
			// This gets a result set back when we execute a query.
			ResultSet rs = smt.executeQuery();
			// rs.next() return true if there is another result.
			if (rs.next()) {
				int customerId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				// We are creating a customer data access object. We can get the data from
				// referencing this object.
				customer = new Customer(customerId, firstName, lastName);
			}

		}
		// If the exception is caught. It is handled and the program continues. A null
		// customer object is returned.

		catch (SQLException e) {
			e.printStackTrace();

		}
		return customer;
	}

	public static Employee getEmployee(String username, String password) {
		// defining customer variable
		Employee employee = null;
		try {
			connect();
			String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";
			PreparedStatement smt = conn.prepareStatement(sql);
			// first value is which question mark it is from left to right
			// the second is what the variable is.
			// The username and password in the database are Strings.
			smt.setString(1, username);
			smt.setString(2, password);
			// This gets a result set back when we execute a query.
			ResultSet rs = smt.executeQuery();
			// rs.next() return true if there is another result.
			// rs is the results on the statement.
			if (rs.next()) {
				int empId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				// We are creating a customer data access object. We can get the data from
				// referencing this object.
				employee = new Employee(empId, firstName, lastName);
			}

		}

		// If the exception is caught. It is handled and the program continues. A null
		// customer object is returned.
		catch (SQLException e) {
			e.printStackTrace();

		}
		return employee;
	}

	public static Customer createNewCustomer(String firstName, String lastName, String username, String password) {

		Customer customer = null;
		try {
			connect();

			String sql = "INSERT into customers (first_name, last_name, username, password) VALUES(?,?,?,?)";
			PreparedStatement smt = conn.prepareStatement(sql);

			smt.setString(1, firstName);
			smt.setString(2, lastName);
			smt.setString(3, username);
			smt.setString(4, password);

			smt.executeUpdate();

			customer = getCustomer(username, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	public static CustomerAccount findByAccountByNumber(int accountNumber, Customer customer) {

		CustomerAccount account = null;
		try {
			connect();
			String sql = "SELECT * FROM customer_accounts WHERE account_number = ? AND  customer_id =?";
			PreparedStatement smt = conn.prepareStatement(sql);

			smt.setInt(1, accountNumber);
			smt.setInt(2, customer.getCustomerId());

			// This gets a result set back when we execute a query.
			ResultSet rs = smt.executeQuery();
			// rs.next() return true if there is another result.
			if (rs.next()) {
				accountNumber = rs.getInt(1);
				int customerId = rs.getInt(2);
				String accountType = rs.getString(3);
				double balance = rs.getDouble(4);
				// We are creating a customer data access object. We can get the data from
				// referencing this object.

				account = new CustomerAccount(accountType, accountNumber, customerId, balance);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	public static ArrayList<CustomerAccount> findAccountsById(Customer customer) {

		ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
		try {
			connect();
			String sql = "SELECT * FROM customer_accounts WHERE customer_id =?";
			PreparedStatement smt = conn.prepareStatement(sql);

			smt.setInt(1, customer.getCustomerId());

			// This gets a result set back when we execute a query.
			ResultSet rs = smt.executeQuery();
			// rs.next() return true if there is another result.
			while (rs.next()) {
				int accountNumber = rs.getInt(1);
				int customerId = rs.getInt(2);
				String accountType = rs.getString(3);
				double balance = rs.getDouble(4);
				// We are creating a customer data access object. We can get the data from
				// referencing this object.

				CustomerAccount customerAccount = new CustomerAccount(accountType, accountNumber, customerId, balance);
				accounts.add(customerAccount);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public static void depositAmount(double amount, Customer customer, int accountNumber) {
		try {
			connect();
			String sql = " UPDATE customer_accounts SET balance = balance + ? WHERE account_number =?";

			PreparedStatement smt = conn.prepareStatement(sql);

			smt.setDouble(1, amount);
			smt.setInt(2, accountNumber);

			smt.executeUpdate();
			
			logTransaction(0, accountNumber, amount);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void withdrawAmount(double amount, Customer customer, int accountNumber) {
		try {
			connect();

			String sql = "UPDATE customer_accounts SET balance = balance - ? WHERE account_number =?";

			PreparedStatement smt = conn.prepareStatement(sql);

			smt.setDouble(1, amount);
			smt.setInt(2, accountNumber);

			smt.executeUpdate();
			
			logTransaction(accountNumber, 0, amount);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void transferAmount(double amount, int originAccount, int destinationAccount) {
		try {
			connect();
			String sql = "INSERT into transfers (amount, origin_account_number, destination_account_number) VALUES(?,?,?)";

			PreparedStatement smt = conn.prepareStatement(sql);

			smt.setDouble(1, amount);
			smt.setInt(2, originAccount);
			smt.setInt(3, destinationAccount);

			smt.executeUpdate();
			
			
		
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void accountApplication(double balance, String accountType, Customer customer) {
		try {
			connect();
			String sql = "INSERT into pending_accounts (balance, account_type, customer_id) VALUES(?,?,?)";
			PreparedStatement smt = conn.prepareStatement(sql);

			smt.setDouble(1, balance);
			smt.setString(2, accountType);
			smt.setInt(3, customer.getCustomerId());

			smt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<CustomerTransfer> checkPendingTransfers(Customer customer) {
		ArrayList<CustomerTransfer> pendingTransfers = new ArrayList<CustomerTransfer>();
		try {
			connect();
			String sql = "SELECT * from transfers WHERE destination_account_number = ?";

			PreparedStatement smt = conn.prepareStatement(sql);

			ArrayList<CustomerAccount> accounts = findAccountsById(customer);

			for (CustomerAccount account : accounts) {

				smt.setInt(1, account.getAccountNumber());

				ResultSet rs = smt.executeQuery();

				while (rs.next()) {
					int transferId = rs.getInt(1);
					double amount = rs.getDouble(2);
					int originAccountNumber = rs.getInt(3);
					int destinationAccountNumber = rs.getInt(4);

					CustomerTransfer transfer = new CustomerTransfer(transferId, amount, originAccountNumber,
							destinationAccountNumber);
					pendingTransfers.add(transfer);

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pendingTransfers;

	}

	public static void approveTransfer(int originAccountNumber, int destinationAccountNumber, double amount,
			int transferId) {
		try {
			connect();
			CallableStatement cstmt = conn.prepareCall("CALL transfers_procedure(?,?,?,?)");
			cstmt.setInt(1, originAccountNumber);
			cstmt.setInt(2, destinationAccountNumber);
			cstmt.setDouble(3, amount);
			cstmt.setInt(4, transferId);
			cstmt.execute();
			
			logTransaction(originAccountNumber, destinationAccountNumber, amount);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rejectTransfer(int transferId) {

		try {
			connect();
			String sql = "DELETE from transfers WHERE transfer_id = ?";

			PreparedStatement smt = conn.prepareStatement(sql);
			smt.setInt(1, transferId);
			smt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Customer findCustomerById(int customerId) {
		Customer customer = null;
		try {
			connect();
			String sql = "SELECT * from customers WHERE customer_id = ?";

			PreparedStatement smt = conn.prepareStatement(sql);
			smt.setInt(1, customerId);

			ResultSet rs = smt.executeQuery();

			if (rs.next()) {

				String firstName = rs.getString(2);
				String lastName = rs.getString(3);

				customer = new Customer(customerId, firstName, lastName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	public static ArrayList<CustomerAccount> findCustomerAccountsById(int customerId) {
		ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
		try {
			connect();
			String sql = "SELECT * from customer_accounts WHERE customer_id = ?";

			PreparedStatement smt = conn.prepareStatement(sql);
			smt.setInt(1, customerId);

			ResultSet rs = smt.executeQuery();

			while (rs.next()) {
				int accountNumber = rs.getInt(1);
				String accountType = rs.getString(3);
				double balance = rs.getDouble(4);

				CustomerAccount customerAccount = new CustomerAccount(accountType, accountNumber, customerId, balance);
				accounts.add(customerAccount);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public static void approveAccountApplication(int pendingAccountId) {
		try {
			connect();
			CallableStatement cstmt = conn.prepareCall("CALL approve_accounts(?)");
			cstmt.setInt(1, pendingAccountId);
			cstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rejectAccountApplication(int transferId) {

		try {
			connect();
			String sql = "DELETE from pending_accounts WHERE pending_account_id = ?";

			PreparedStatement smt = conn.prepareStatement(sql);
			smt.setInt(1, transferId);
			smt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<PendingAccounts> checkPendingAccounts() {
		ArrayList<PendingAccounts> pendingAccounts = new ArrayList<PendingAccounts>();
		try {
			connect();
			String sql = "SELECT * from pending_accounts";

			PreparedStatement smt = conn.prepareStatement(sql);

			ResultSet rs = smt.executeQuery();

			while (rs.next()) {
				int pendingAccountId = rs.getInt(1);
				double balance = rs.getDouble(2);
				String accountType = rs.getString(3);
				int customerId = rs.getInt(4);

				PendingAccounts pAccount = new PendingAccounts(pendingAccountId, balance, accountType, customerId);
				pendingAccounts.add(pAccount);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pendingAccounts;
	}


	private static void logTransaction(int origin_account, int destination_account, double amount) throws SQLException
	{
	    connect();
	    String sql = "INSERT INTO transactions_log (origin_account_number, destination_account_number, amount) VALUES (?, ?, ?)";
	    PreparedStatement smt = conn.prepareStatement(sql);
	    smt.setInt(1, origin_account);
	    smt.setInt(2, destination_account);
	    smt.setDouble(3, amount);
	    smt.executeUpdate();
	    
	}
	
	public static ArrayList<LogTransaction> viewTransactionLogs() {
		
		ArrayList<LogTransaction> log = new ArrayList<LogTransaction>();
		try {
			connect();
			String sql = "SELECT * from transactions_log";

			PreparedStatement smt = conn.prepareStatement(sql);

			ResultSet rs = smt.executeQuery();

			while (rs.next()) {
				int  transactionId = rs.getInt(1);
				int origin_account_number = rs.getInt(2);
				int destination_account_number= rs.getInt(3);
				double amount = rs.getDouble(4);

				LogTransaction logs = new LogTransaction(transactionId, origin_account_number, destination_account_number, amount);
				log.add(logs);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return log;
	}
	}

