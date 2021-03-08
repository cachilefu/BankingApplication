package com.revature.project1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApproveTransferAmount
 */
public class ApproveTransferAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveTransferAmount() {
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
		
		int originAccountNumber = Integer.parseInt(request.getParameter("approveOriginAccount"));
		double amount = Double.parseDouble(request.getParameter("approveAmount")); 
		int destinationAccountNumber = Integer.parseInt(request.getParameter("approveDestinationAccount"));
		int transferId = Integer.parseInt(request.getParameter("approveTransferId"));
		
		DBConnection.approveTransfer(originAccountNumber, destinationAccountNumber, amount, transferId);
		
		out.println("The transfer has been approved. " + amount + " has been added your account.");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
