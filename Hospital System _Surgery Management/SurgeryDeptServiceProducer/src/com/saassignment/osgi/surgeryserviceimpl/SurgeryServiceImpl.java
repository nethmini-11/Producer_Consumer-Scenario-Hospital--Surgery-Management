package com.saassignment.osgi.surgeryserviceimpl;

import java.util.List;




import java.util.Scanner;

import com.saassignment.osgi.datastore.ApplicationDataStore;
import com.saassignment.osgi.datastore.DataStore;
import com.saassignment.osgi.surgeryservice.SurgeryService;
import com.saassignment.osgi.model.SurgeryApplication;
import com.saassignment.osgi.model.Surgery;


public class SurgeryServiceImpl implements SurgeryService {

	@Override
	public synchronized int  addSurgeryDetails(String patientID,String patientName,String surType,String contactNumber) {

		SurgeryApplication newSurgery = new SurgeryApplication(ApplicationDataStore.surgicalList.size() + 1, patientID,patientName, surType,contactNumber);
		ApplicationDataStore.surgicalList.add(newSurgery);

	return 1;
		
	}

	@Override
	public  synchronized int updateSurgeryDetails(String  updatePatientID,String updatePatientName,String updateSurType,String updateContactNumber) {
		SurgeryApplication itemToBeUpdated = null;
		boolean invalid = true;
		int count = -1;
		for (SurgeryApplication tempItem : ApplicationDataStore.surgicalList) {
			count++;
			if (tempItem.getPatientID().equalsIgnoreCase(updatePatientID)) {

				itemToBeUpdated = tempItem;
				invalid = false;
				break;

			}
		

		}
		if (!invalid) {

			

			itemToBeUpdated.setPatientID(updatePatientID);
			itemToBeUpdated.setPatientName(updatePatientName);
			itemToBeUpdated.setSurType(updateSurType);
			itemToBeUpdated.setContactNumber(updateContactNumber);
			//itemToBeUpdated.calculateFinalcharge();

			ApplicationDataStore.surgicalList.set(count, itemToBeUpdated);
			return 1;

		} else {
			return -1;
		}
		
		
	}

	@Override
	public  synchronized int removeSurgeryDetails(String patientID) {

		boolean invalid = true;
		int count = -1;
		for (SurgeryApplication tempItem :ApplicationDataStore.surgicalList) {
			count++;
			if (tempItem.getPatientID().equalsIgnoreCase(patientID)) {

			
				invalid = false;
				break;

			}

		}
		if (!invalid) {

			ApplicationDataStore.surgicalList.remove(count);
			return 1;

		} else {
			return -1;
		}
	}

	@Override
	public int searchSurgeryDetails(String patientID) {
		boolean valid = false;
	
		for (SurgeryApplication tempItem : ApplicationDataStore.surgicalList) {
		
			if (tempItem.getPatientID().equalsIgnoreCase(patientID)) {

				
				valid = true;
				break;

			}
			
			
			

		}
		if(valid) {
			return 1;
			
		}
		else {
			return -1;
		}
		
	}
	
	

	@Override
	public List<SurgeryApplication> listSurgicalDetails() {

		return ApplicationDataStore.surgicalList;
	}

	
	@Override
	public List<Surgery> displayList() {
		return DataStore.surgicalList;
		
	}

}
