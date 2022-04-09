package com.saassignment.osgi.surgeryconsumer;
import java.util.List;




import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.saassignment.osgi.datastore.ApplicationDataStore;
import com.saassignment.osgi.surgeryservice.SurgeryService;
import com.saassignment.osgi.model.SurgeryApplication;
import com.saassignment.osgi.model.Surgery;


public class Activator implements BundleActivator {
	ServiceReference surgeryServiceReference;
	Scanner input = new Scanner(System.in);

	boolean exit = false;

	@Override
	public void start(BundleContext context) throws Exception {//Start life cycle method
		System.out.println("				|++++++++++  Hospital Surgery Consumer Started  ++++++++++|");
		System.out.println("");
		surgeryServiceReference = context.getServiceReference(SurgeryService.class.getName());
		@SuppressWarnings("unchecked")
		SurgeryService surgeryService = (SurgeryService) context.getService(surgeryServiceReference);		//Instance of surgeryconsumer
		
		

		do {
		int selection = 7;
		do {
		System.out.println("________________________________________________  Apply for a surgery  ________________________________________________");
		System.out.println("");
		System.out.println("						Select an option to continue ...");
		System.out.println("");
		System.out.println("						1.Apply for new surgery");
		System.out.println("						2.Update your surgery details");
		System.out.println("						3.Delete applied surgeries");
		System.out.println("						4.List applied surgeries");
		System.out.println("						5.Surgery Details List");
		System.out.println("						6.Search applied surgeries");
		System.out.println("						7.Exit");
		
		System.out.println("");
		System.out.println("Enter a Number to continue : " );
		 selection = input.nextInt();
		
		 input.nextLine();
		 if(selection == 7) {
			 exit = true;
		 }
		 
		 if(selection !=1 && selection !=2 && selection !=3 && selection !=4 && selection !=5 && selection!=6 && selection!=7) {
			 System.out.println("Please enter a valid selection");
		 }
		}while(selection !=1 && selection !=2 && selection !=3 && selection !=4 && selection !=5 && selection!=6 && selection!=7);
		
	
        String  backToHome = null;
		if (selection == 1) { //Apply for surgeries
			do {
			System.out.println("Enter Patient ID : ");
			String patientID = input.nextLine();
			
			System.out.println("Enter Patient's Name: ");
			String patientName = input.nextLine();

			System.out.println("Enter the type of surgery: ");
			String surType = input.nextLine();

			System.out.println("Enter valid contact number: ");
			String contactNumber = input.nextLine();
			input.nextLine();
			int result =surgeryService.addSurgeryDetails(patientID,patientName,surType , contactNumber);//Consumes the SurgeryService addSurgeryDetails()
			 String msg = (result ==1) ? "Successfully applied for the surgery!!" :"please enter a valid number";
			   System.out.println(msg);
			System.out.println("Press 0 to go back home or Press any other key to continue");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));

		}else if (selection == 2) {//Update applied surgery list
			do {
				
				System.out.println("Patient ID: ");
				String updatePatientID = input.nextLine();
				
				System.out.println("Patient Name: ");
				String updatePatientName = input.nextLine();

				System.out.println("Surgery Type: ");
				String updateSurType = input.nextLine();

				System.out.println("Valid Contact Number: ");
				String updateContactNumber = input.nextLine();
				input.nextLine();
				
			int result =surgeryService.updateSurgeryDetails(updatePatientID,updatePatientName,updateSurType,updateContactNumber);//Consumes the SurgeryService updateSurgeryDetails()
			  String msg = (result ==1) ? "Updated Successfully !" :"please enter a valid name";
			   System.out.println(msg);
           System.out.println("Press 0 to go back home or Press any other key to continue");
			
			backToHome=input.nextLine();
			
			}while(!(backToHome.equals("0")));
			
		}
		else if (selection == 3) {//remove applied surgeries from the list
			do {
			System.out.println("Enter the Patient ID :");

			String patientID = input.nextLine();
			int result =surgeryService.removeSurgeryDetails(patientID);//Consumes the SurgeryService removeSurgeryDetails()
			   String msg = (result ==1) ? "Successfully removed from the list !!!" :"Please check the surgery Type and try again !!";
			   System.out.println(msg);
              System.out.println("Press 0 to go back home or Press any other key to continue");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));


		}
		else if(selection == 4) {//Handles displaying all items in the list
			do {
				List<SurgeryApplication> surgeryList =surgeryService.listSurgicalDetails();//Consumes the SurgeryService listSurgicalDetails()
				System.out.println("");
				System.out.println("							+ ____________________________________ Surgery Detail List  ____________________________________ +");
				System.out.println("");
				System.out.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.println("ID	"+"\t" +"Patient ID	"+"\t"+"Patient Name	"+"\t" + "Surgery Type	"+"\t" + "Contact Number	"+"\t");
				System.out.println("------------------------------------------------------------------------------------------------------------------------");
			for(SurgeryApplication tempItem: surgeryList ) {
				System.out.println(tempItem.getApplicationId()+"\t\t"+tempItem.getPatientID()+"\t\t\t"+tempItem.getPatientName()+"\t\t"+tempItem.getSurType()+"\t\t\t"+tempItem.getContactNumber()+"\t\t"+"\n");
				
				
				
				
			}
			System.out.println(" _____________________________________________________________________________________________________________________________________________________________________________________");
            System.out.println("Press 0 to go back home or Press any other key to continue");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));

		}
		
		else if (selection == 5) {//View Surgery list
			do {
				
			List<Surgery> itemsList =surgeryService.displayList();//Consumes the CashierService displayItems()
			System.out.println("			+________________________________________________  Surgery Details ________________________________________________+ ");
			System.out.println("");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Surgery ID	"+"\t" +"Surgery Type	"+"\t" +"Surgeon Name	"+"\t" + "Surgery Fee	"+"\t" + "Discount Percentage	"+"\t" + "Total Amount	"+"\t");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
		for(Surgery tempItem: itemsList ) {
			System.out.println(tempItem.getSurgeryId()+"\t\t"+tempItem.getSurgeryType()+"\t\t\t"+tempItem.getSurgeonName()+"\t\t"+tempItem.getSurgeryPrice()+"\t\t\t"+tempItem.getSurgerydiscount()+"\t\t\t\t"+tempItem.getFinalCharge()+"\t\t"+"\n");
			
			
			
			
			
			
			
		
		   System.out.println("___________________________________________________________________________________________");
		
			
			}

		System.out.println("Press 0 to go back home or Press any other key to continue");
			
			backToHome=input.nextLine();
			}
			
			while(!(backToHome.equals("0")));
			

		}
		else if(selection == 6) {//search applied surgeries
			do {
			
			System.out.println("Enter the Patient ID:");
			

			String patientID = input.nextLine();
			int result =surgeryService.searchSurgeryDetails(patientID);//Consumes the SurgeryService searchSurgeryDetails()
			 String msg = (result ==1) ? "Your Search Was Found :) " :"Not Found :( ";
			   System.out.println(msg);
			
			System.out.println("Press 0 to go back home or Press any other key to continue");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));
		}
		
		else if(selection == 7) {//Exits form the surgery consumer program
			return;
		}
		}while(!exit);
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {//stop life cycle method
		System.out.println("<<<<<<<<<<<<<<<< Suergery Consumer Stopped >>>>>>>>>>>>>>>>");
		context.ungetService(surgeryServiceReference);
	}

}

