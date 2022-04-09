package com.saassignment.osgi.model;
//Item model class
public class Surgery {
	private int surgeryId;
	private String surgeryType;
	private String surgeonName;
	private double surgeryPrice;
	private double surgerydiscount;
	private double finalCharge;
	
	public Surgery(int surgeryId, String surgeryType,String surgeonName , double surgeryFee, double surgeryDiscount) {
		super();
		this.surgeryId = surgeryId;
		this.surgeryType = surgeryType;
		this.surgeonName = surgeonName;
		this.surgeryPrice = surgeryFee;
		this.surgerydiscount = surgeryDiscount;
		calculateFinalcharge();
		
	}
	public int getSurgeryId() {
		return surgeryId;
	}
	public void setsurgeryId(int surgeryId) {
		this.surgeryId = surgeryId;
	}
	public String getSurgeryType() {
		return surgeryType;
	}
	public void setSurgeryType(String surgeryType) {
		this.surgeryType = surgeryType;
	}
	public String getSurgeonName() {
		return surgeonName;
	}
	public void setSurgeonName(String surgeonName) {
		this.surgeonName = surgeonName;
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



}
