package com.saassignment.osgi.billingservice;

import java.util.List;

import com.saassignment.osgi.model.Surgery;
//Service methods which will provide to the cashier consumers
public interface BillingService {
	public List<Surgery> displayList();//return the item list with item details
	public int generateBill(int id,int qty,int pckg,int day);//calculates the on going bill continuously
	public double displayFinalBillAmount();//displays the final bill amount
	public String[][]dispalybillDetails();//returns the purchased item list wit details
}
