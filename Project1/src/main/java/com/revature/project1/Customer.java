package com.revature.project1;

import java.util.ArrayList;

public class Customer {
	
	final private int customerId;
	
	final private String firstName;
	
	final private String lastName;
	

	public Customer(int customerId, String firstName, String lastName) 
	{
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;	
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}
	@Override
	public String toString() 
	{
		//returns "Customer [customerId=0, firstName=Sara, lastName=Samuel]";
		return "Customer [Customer Id: " + customerId + ", First Name: " + firstName + ", Last Name: " + lastName + "]";
	}


	




}

