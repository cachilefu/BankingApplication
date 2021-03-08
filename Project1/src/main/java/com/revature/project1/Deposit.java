package com.revature.project1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Deposit
 */
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		
		int accountNumber = Integer.parseInt(request.getParameter("depositAccount"));
		double amount = Double.parseDouble(request.getParameter("depositAmount")); 
		
		if (amount > 0) {
		DBConnection.depositAmount(amount, (Customer) request.getAttribute("user"), accountNumber);
		//CustomerAccount depositBalance = DBConnection.findByAccountByNumber(accountNumber, (Customer) request.getAttribute("user"));
		
		out.println(amount + " has been deposited to your account.");
		//out.println("Your Balance is now: " + depositBalance.getBalance());
	
		
		} else {
			
			out.println("Please enter an amount higher than 0.");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
