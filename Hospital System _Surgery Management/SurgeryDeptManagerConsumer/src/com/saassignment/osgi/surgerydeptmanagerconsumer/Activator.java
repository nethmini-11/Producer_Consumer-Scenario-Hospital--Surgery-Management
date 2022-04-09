package com.saassignment.osgi.surgerydeptmanagerconsumer;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.saassignment.osgi.datastore.DataStore;
import com.saassignment.osgi.managementservice.ManagementService;
import com.saassignment.osgi.model.Surgery;

public class Activator implements BundleActivator {
	ServiceReference surgeryDeptServiceReference;
	Scanner input = new Scanner(System.in);

	boolean exit = false;

	@Override
	public void start(BundleContext context) throws Exception {//Start life cycle method
		System.out.println("				|++++++++++  Hospital Surgery Manager Consumer Started  ++++++++++|");
		System.out.println("");
		surgeryDeptServiceReference = context.getServiceReference(ManagementService.class.getName());
		@SuppressWarnings("unchecked")
		ManagementService surgeryDeptService = (ManagementService) context.getService(surgeryDeptServiceReference);		//Instance of surgerymanagerService
		
		

		do {
		int selection = 7;
		do {
		System.out.println("________________________________________________  Welcome to Surgery Management  ________________________________________________");
	    System.out.println("");
		System.out.println("						Select an option to continue ...");
		System.out.println("");
		System.out.println("						1.Add a new surgery details ");
		System.out.println("						2.Update an exsisiting surgery details");
		System.out.println("						3.Delete an exsisiting surgery details");
		System.out.println("						4.List surgery details");
		System.out.println("						5.Search surgery details");
		System.out.println("						6.Exit");
		
		System.out.println("");
		System.out.println("Enter a Number to continue : " );
		 selection = input.nextInt();
		
		 input.nextLine();
		 if(selection == 6) {
			 exit = true;
		 }
		 
		 if(selection !=1 && selection !=2 && selection !=3 && selection !=4 && selection !=5 && selection!=6) {
			 System.out.println("Please enter a valid selection");
		 }
		}while(selection !=1 && selection !=2 && selection !=3 && selection !=4 && selection !=5 && selection!=6 );
		
	
        String  backToHome = null;
		if (selection == 1) {//Add surgery details
			do {
			System.out.println("Surgery Type : ");
			String surgeryType = input.nextLine();
			
			System.out.println("Surgeon Name : ");
			String surgeonName = input.nextLine();

			System.out.println("Surgery Fee : ");
			double surgeryFee = input.nextDouble();

			System.out.println("Discount Percentage : ");
			double surgeryDiscount = input.nextDouble();
			input.nextLine();
			int result =surgeryDeptService.addSurgicalDetails(surgeryType,surgeonName, surgeryFee, surgeryDiscount);//Consumes the SurgeryManagerService addSurgicalsDetails()
			 String msg = (result ==1) ? "Added successfully!" :"please enter a valid name";
			   System.out.println(msg);
			System.out.println("Press 0 to go back home or Press any other key to continue");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));

		}else if (selection == 2) {//update surgery detail list
			do {
				
				System.out.println("Surgery Type : ");
				String updateSurgeryType = input.nextLine();
				
				System.out.println("Surgeon Name : ");
				String updateSurgeonName = input.nextLine();

				System.out.println("Surgery Fee : ");
				double updateSurgeryFee = input.nextDouble();

				System.out.println("Discount Percentage : ");
				double updateSurgeryDiscount = input.nextDouble();
				input.nextLine();
				
			int result =surgeryDeptService.updateSurgicalDetails(updateSurgeryType,updateSurgeonName,updateSurgeryFee,updateSurgeryDiscount);//Consumes the ManagerService updateItems()
			  String msg = (result ==1) ? "Updated successfully!" :"please enter a valid name";
			   System.out.println(msg);
           System.out.println("Press 0 to go back home or Press any other key to continue");
			
			backToHome=input.nextLine();
			
			}while(!(backToHome.equals("0")));
			
		}
		else if (selection == 3) {//remove existing surgery details
			do {
			System.out.println("Enter the Surgery Type :");

			String surgeryType = input.nextLine();
			int result =surgeryDeptService.removeSurgicalDetails(surgeryType);//Consumes the SurgeryManagerService removeSurgicalDetails()
			   String msg = (result ==1) ? "Removed successfully !" :"Please check the surgery Type and try again !!";
			   System.out.println(msg);
              System.out.println("Press 0 to go back home or Press any other key to continue");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));


		}
		else if(selection == 4) {//display surgery details list
			do {
				List<Surgery> surgeryList =surgeryDeptService.listSurgicalDetails();//Consumes the SurgeryManagerConsumer listSurgicalDetails()
				System.out.println("");
				System.out.println("			+ ____________________________________ Surgery Detail List  ____________________________________ +");
				System.out.println("");
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("ID	"+"\t" +"Surgery Type	"+"\t"+"Surgeon Name	"+"\t" + "Surgery Fee	"+"\t" + "Discount Percentage	"+"\t" + "Total Fee	"+"\t");
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
				
			for(Surgery tempItem: surgeryList ) {
				
				System.out.println(tempItem.getSurgeryId()+"\t\t"+tempItem.getSurgeryType()+"\t\t\t"+tempItem.getSurgeonName()+"\t\t"+tempItem.getSurgeryPrice()+"\t\t\t"+tempItem.getSurgerydiscount()+"\t\t\t\t"+tempItem.getFinalCharge()+"\t\t"+"\n");
				
				
				
				
			}
			System.out.println("			   ___________________________________________________________________________________________");
            System.out.println("Press 0 to go back home or Press any other key to continue");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));

		}
		else if(selection == 5) {//search surgery details
			do {
			
			System.out.println("Enter the Surgery Type :");
			

			String surgeryType = input.nextLine();
			int result =surgeryDeptService.searchSurgicalDetails(surgeryType);//Consumes the ManagerService searchItems()
			 String msg = (result ==1) ? "Your Search Was Found :) " :"Not Found :( ";
			   System.out.println(msg);
			
			System.out.println("Press 0 to go back home or Press any other key to continue");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));
		}
		
		else if(selection == 6) {//Exits form the Surgery Manager consumer program
			return;
		}
		}while(!exit);
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {//stop life cycle method
		System.out.println("<<<<<<<<<<<<<<<< Suergery Manager Consumer Stopped >>>>>>>>>>>>>>>>");
		context.ungetService(surgeryDeptServiceReference);
	}

}
