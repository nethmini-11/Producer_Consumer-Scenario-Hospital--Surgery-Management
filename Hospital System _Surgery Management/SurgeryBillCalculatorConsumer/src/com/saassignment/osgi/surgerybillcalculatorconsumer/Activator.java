package com.saassignment.osgi.surgerybillcalculatorconsumer;

import java.util.List;



import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.saassignment.osgi.billingservice.BillingService;
import com.saassignment.osgi.model.Surgery;


public class Activator implements BundleActivator {
	ServiceReference surgerybillServiceReference;
	private boolean exit =false;
	Scanner input = new Scanner(System.in);

	
	
	
	
	@Override
	public void start(BundleContext context) throws Exception { //start - Life cycle method
		System.out.println("");
		System.out.println("				|++++++++++  Hospital Surgery Billing consumer started  ++++++++++|");
		System.out.println("");
		surgerybillServiceReference = context.getServiceReference(BillingService.class.getName());
		@SuppressWarnings("unchecked")
		BillingService billingService =  (BillingService)context.getService(surgerybillServiceReference);		//Instance of billingserivice
		do {
		int selection = 4;
		do {
			System.out.println("________________________________________________   Welcome to Surgery Billing  ________________________________________________");
			System.out.println("");
		
		System.out.println("						Select an option to continue ...");
		System.out.println("");
		System.out.println("						1.View Surgery Details List");
		System.out.println("						2.Calculate Estimate Surgery Bill");
		System.out.println("						3.Exit");
		
		
		System.out.println("");
		System.out.println("Enter an number to continue : " );
		selection = input.nextInt();
		
		 input.nextLine();
		 if(selection == 3) {//Exits from the billing consumer program
			 exit = true;
			 
		 }
		 
		 
		 if(selection !=1 && selection !=2 && selection !=3) {
			 System.out.println("Invalid Selection !!!");
		 }
		}while(selection !=1 && selection !=2 && selection !=3);
		
		   String  backToHome = null;
		   String calculateFinalBill = null;
		   int itemCount = -1;
			if (selection == 1) {//View surgery list added by the surgery management
				do {
					
				List<Surgery> itemsList =billingService.displayList();
				System.out.println("			+________________________________________________   Surgery Details ________________________________________________+  ");
				System.out.println("");
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Surgery ID	"+"\t" +"Surgery Type	"+"\t" +"Surgeon Name	"+"\t" + "Surgery Fee	"+"\t" + "Discount Percentage	"+"\t" + "Total Amount	"+"\t");
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
			for(Surgery tempItem: itemsList ) {
				System.out.println(tempItem.getSurgeryId()+"\t\t"+tempItem.getSurgeryType()+"\t\t\t"+tempItem.getSurgeonName()+"\t\t"+tempItem.getSurgeryPrice()+"\t\t\t"+tempItem.getSurgerydiscount()+"\t\t\t\t"+tempItem.getFinalCharge()+"\t\t"+"\n");
				
				
				
			
			   System.out.println("__________________________________________________________________________________________________________________________________________  ");
			
				
				}

			System.out.println("Press 0 to go back home or Press any other key to continue");
				
				backToHome=input.nextLine();
				}
				
				while(!(backToHome.equals("0")));
				

			}
			
			else if(selection == 2) {//calculate estimate surgery bill 
				do {
					do {
						
				System.out.println("			+________________________________________________   Calculate Your Surgery Bill	 ________________________________________________+ " + "\n");
				System.out.println("Enter the item id");
				int id = input.nextInt();
				
				System.out.println("Enter the Insurance Coverage :");
				int insuarance = input.nextInt();
				
				System.out.println("Enter the selected package fee :");
				int pckg = input.nextInt();
				
				System.out.println("Enter the number of days prescribed for surgery :");
				int day = input.nextInt();

				
				int result =billingService.generateBill(id, insuarance,pckg,day);//Consumes the BillingService generateBill()
				if(result == -1) {
					 System.out.println("");
					 System.out.println("Please enter a valid item number!!");
				}
				else {
					itemCount++;
				}
				input.nextLine();
				System.out.println("");
				System.out.println("Do you want to calculate your bill (yes) or Press any key to continue :");	
				calculateFinalBill=input.nextLine();
				
				
						}while(!(calculateFinalBill.equals("yes")));
					
						System.out.println("");
						System.out.println("");
						String[][] billDetails= billingService.dispalybillDetails();//Consumes the billingservice displaybillDetails()
						
						String format = "%-25s";
						System.out.println("");
						System.out.printf(format, "Surgery ID:");
						System.out.printf(format, "Surgery Type:");
						System.out.printf(format, "Insurance Coverage:");
						System.out.printf(format, "Selected Package Fee:");
						System.out.printf(format, "Prescribed Surgery days:");
						System.out.printf(format, "Total Fee:");
						System.out.println("");
						for (int i=0; i<=itemCount; i++) {
							  for (int j=0; j<billDetails[i].length; j++) {
							System.out.printf(format,billDetails[i][j]);
							  }
							  System.out.println("");
							  System.out.println("");
						System.out.println("			+________________________________________________  Estimated Surgery Bill ________________________________________________+");
							  }
						System.out.println("");
						  System.out.println("");
						System.out.println("                                                          __________");
						System.out.println("Subtotal:                                     		  " + billingService.displayFinalBillAmount());
						System.out.println("                                                          __________");	  
						System.out.println("                                                          __________");
						System.out.println("");
						System.out.println("_____________________________________________________________________________________");
						System.out.println("");
						System.out.println(" ****** Please Note That Rs.2500 of Fixed Hospital charge Hae been Added To Your Bill ****** ");
						//for()
				  
						itemCount=-1;
						
				System.out.println("Press 0 to go back home or Press any other key to continue");
				
				backToHome=input.nextLine();
				
				}
				
				while(!(backToHome.equals("0")));
			}
			else if(selection == 3) {
				return;
			}
		}while(!exit);
		
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {//Stop -Life cycle method 
		System.out.println("<<<<<<<<<<<<<<<< Suergery Billing Consumer Stopped >>>>>>>>>>>>>>>>");
		context.ungetService(surgerybillServiceReference);
	}

}
