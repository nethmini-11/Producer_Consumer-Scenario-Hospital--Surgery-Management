package com.saassignment.osgi.managementserviceimpl;

import java.util.List;
   



import java.util.Scanner;

import com.saassignment.osgi.datastore.DataStore;
import com.saassignment.osgi.managementservice.ManagementService;
import com.saassignment.osgi.model.Surgery;


public class SurgeryDeptServiceImpl implements ManagementService {

	@Override
	public synchronized int  addSurgicalDetails(String surgeryType,String surgeonName,double surgeryFee,double surgeryDiscount) {

		Surgery newSurgery = new Surgery(DataStore.surgicalList.size() + 1, surgeryType,surgeonName, surgeryFee,surgeryDiscount);
		DataStore.surgicalList.add(newSurgery);

	return 1;
		
	}

	@Override
	public  synchronized int updateSurgicalDetails(String updateSurgeryType,String updateSurgeonName,double updateSurgeryFee,double updateSurgeryDiscount) {
		Surgery itemToBeUpdated = null;
		boolean invalid = true;
		int count = -1;
		for (Surgery tempItem : DataStore.surgicalList) {
			count++;
			if (tempItem.getSurgeryType().equalsIgnoreCase(updateSurgeryType)) {

				itemToBeUpdated = tempItem;
				invalid = false;
				break;

			}
		

		}
		if (!invalid) {

			

			itemToBeUpdated.setSurgeryType(updateSurgeryType);
			itemToBeUpdated.setSurgeonName(updateSurgeonName);
			itemToBeUpdated.setSurgeryPrice(updateSurgeryFee);
			itemToBeUpdated.setSurgerydiscount(updateSurgeryDiscount);
			itemToBeUpdated.calculateFinalcharge();

			DataStore.surgicalList.set(count, itemToBeUpdated);
			return 1;

		} else {
			return -1;
		}
		
		
	}

	@Override
	public  synchronized int removeSurgicalDetails(String surgeryType) {

		boolean invalid = true;
		int count = -1;
		for (Surgery tempItem :DataStore.surgicalList) {
			count++;
			if (tempItem.getSurgeryType().equalsIgnoreCase(surgeryType)) {

			
				invalid = false;
				break;

			}

		}
		if (!invalid) {

			DataStore.surgicalList.remove(count);
			return 1;

		} else {
			return -1;
		}
	}

	@Override
	public int searchSurgicalDetails(String surgeryType) {
		boolean valid = false;
	
		for (Surgery tempItem : DataStore.surgicalList) {
		
			if (tempItem.getSurgeryType().equalsIgnoreCase(surgeryType)) {

				
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
	public List<Surgery> listSurgicalDetails() {

		return DataStore.surgicalList;
	}

	


}
