package com.revature.project1;

import java.util.ArrayList;

public class Employee {
	private int empid;
	
	private String firstName;
	
	private String lastName;

	
	public Employee(int empid, String firstName, String lastName) {
		super();
		this.empid = empid;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getEmpid() {
		return empid;
	}

	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "Employee [Employee ID: " + empid + ", First Name: " + firstName + ", Last Name: " + lastName + "]";
	}

	
	
	
	
	
	
	

}
