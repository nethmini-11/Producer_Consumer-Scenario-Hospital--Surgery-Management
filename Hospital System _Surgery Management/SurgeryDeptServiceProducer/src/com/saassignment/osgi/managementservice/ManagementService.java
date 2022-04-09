package com.saassignment.osgi.managementservice;

import java.util.List;

import com.saassignment.osgi.model.Surgery;
//Provide the services which manager consumers need
public interface ManagementService {
	
	public  int  addSurgicalDetails(String surgeryType,String surgeonName,double surgeryFee,double surgeryDiscount);//Adds the new items to the item list
	public   int  updateSurgicalDetails(String updateSurgeryType,String updateSurgeonName,double updateSurgeryFee,double updateSurgeryDiscount);//Updates the item details
	public   int removeSurgicalDetails(String surgeryType);//Removes the items from the list
	public   int searchSurgicalDetails(String surgeryType);//Searches and item by name
	public   List<Surgery> listSurgicalDetails();//Returns the item list
	
}
