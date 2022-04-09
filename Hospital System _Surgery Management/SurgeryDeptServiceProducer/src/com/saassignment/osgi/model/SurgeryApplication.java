package com.saassignment.osgi.model;
//Item model class
public class SurgeryApplication {
	private int applicationId;
	private String patientID;
	private String patientName;
	private String surType;
	private String contactNumber;
	//private double finalCharge;
	
	public SurgeryApplication(int applicationId, String patientID,String patientName,String surType ,String contactNumber) {
		super();
		this.applicationId = applicationId;
		this.patientID = patientID;
		this.patientName = patientName;
		this.surType= surType;
		this.contactNumber = contactNumber;
		//calculateFinalcharge();
		
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getSurType() {
		return surType;
	}

	public void setSurType(String surType) {
		this.surType = surType;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
}
	
	/*public int getSurgeryId() {
		return applicationId;
	}
	public void setsurgeryId(int surgeryId) {
		this.applicationId = surgeryId;
	}
	public String getSurgeryType() {
		return patientID;
	}
	public void setSurgeryType(String surgeryType) {
		this.patientID = surgeryType;
	}
	public String getSurgeonName() {
		return patientName;
	}
	public void setSurgeonName(String surgeonName) {
		this.patientID = surgeonName;
	}
	public double getSurgeryPrice() {
		return surgeryPrice;
	}
	public void setSurgeryPrice(double surgeryPrice) {
		this.surgeryPrice = surgeryPrice;
		calculateFinalcharge();
	}
	public double getSurgerydiscount() {
		return surgerydiscount;
	}
	public void setSurgerydiscount(double discount) {
		this.surgerydiscount = discount;
		calculateFinalcharge();
	}
	public double getFinalCharge() {
		return finalCharge;
	}
	public void calculateFinalcharge() {
		this.finalCharge = surgeryPrice * ((100.00- surgerydiscount)/100.00) ;
	}



}*/
	
	
