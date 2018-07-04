package com.neuedu.model.po;

public class Invoice {
	private int invoiceNumber;
	private double amount;
	private String registrationDate;
	private String useDate;
	private String lostInvalidatedDate;
	private String substationName;
	private String invoiceStatus;
	private int orderNumber;
	public Invoice(int invoiceNumber, double amount, String registrationDate,
			String useDate, String lostInvalidatedDate, String substationName,
			String invoiceStatus, int orderNumber) {

		this.invoiceNumber = invoiceNumber;
		this.amount = amount;
		this.registrationDate = registrationDate;
		this.useDate = useDate;
		this.lostInvalidatedDate = lostInvalidatedDate;
		this.substationName = substationName;
		this.invoiceStatus = invoiceStatus;
		this.orderNumber = orderNumber;
	}
	public Invoice(){
		
		this.invoiceNumber = 0;
		this.amount = 0;
		this.registrationDate = "";
		this.useDate = "";
		this.lostInvalidatedDate = "";
		this.substationName = "";
		this.invoiceStatus = "";
		this.orderNumber = 0;
	}
	public int getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getUseDate() {
		return useDate;
	}
	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
	public String getLostInvalidatedDate() {
		return lostInvalidatedDate;
	}
	public void setLostInvalidatedDate(String lostInvalidatedDate) {
		this.lostInvalidatedDate = lostInvalidatedDate;
	}
	public String getSubstationName() {
		return substationName;
	}
	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
}
