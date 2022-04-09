package com.saassignment.osgi.billingserviceimpl;

import java.util.List;


import com.saassignment.osgi.billingservice.BillingService;
import com.saassignment.osgi.datastore.DataStore;
import com.saassignment.osgi.model.Surgery ;



public class BillingServiceImpl implements BillingService {
	private List<Surgery> surgicalList = DataStore.surgicalList;//Item list details in the supermarket 
	private double bill;//to store the bill value
	private String[][] billdetails = new String[1000][6]; //To store the purchased item's details ,Assumption:only 1000 different items are allowed for an order
	private int count = -1; //to store the item count [starts from 0]

	

	@Override
	public List<Surgery> displayList() {
		return DataStore.surgicalList;
		
	}
	public int generateBill(int id,int insuarance,int pckg,int day) {
		

		boolean valid = false;
		Surgery currentItem = null;
		for (Surgery tempItem : surgicalList) {
			if(id == tempItem.getSurgeryId()) {
				currentItem = tempItem;
				valid = true;
				count++;
				break;
			}
		}
		if(valid) {
			
		this.bill = this.bill + (((currentItem.getFinalCharge()) - insuarance)+(pckg*day) +2500); 
	
		
		billdetails[count][0]= Integer.toString(id);
		billdetails[count][1]= currentItem.getSurgeryType();
		
		billdetails[count][2]= Integer.toString(insuarance);
		billdetails[count][3]= Integer.toString(pckg);
		billdetails[count][4]= Integer.toString(day);
		billdetails[count][5]= Double.toString(((currentItem.getFinalCharge()) - insuarance)+(pckg*day) +2500);
		
	
		return 1;
		}
		else {
			return -1;
		}
	}
	public double displayFinalBillAmount() {
		double finalBill = this.bill;
		newBill();
		
		return finalBill;
		
	}
	public String[][] dispalybillDetails(){
		
		return billdetails;
	}
	public void newBill() {//To reset all relevant fields to default values 
		this.bill = 0;
		this.count=-1;
		
	}

}
