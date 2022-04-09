package com.saassignment.osgi.surgeryservice;

import java.util.List;



import com.saassignment.osgi.model.SurgeryApplication;
import com.saassignment.osgi.model.Surgery;
//Provide the services which manager consumers need
public interface SurgeryService {
	
	public  int  addSurgeryDetails(String patientID,String patientName,String surType,String contactNumber);//Adds the new items to the item list
	public   int  updateSurgeryDetails(String updatePatientID,String updatePatientName,String updateSurType,String updateContactNumber);//Updates the item details
	public   int removeSurgeryDetails(String patientID);//Removes the items from the list
	public   int searchSurgeryDetails(String patientID);//Searches and item by name
	public   List<SurgeryApplication> listSurgicalDetails();//Returns the item list
	public List<Surgery> displayList();
	
}
