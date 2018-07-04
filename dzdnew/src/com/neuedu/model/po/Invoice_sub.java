package com.neuedu.model.po;

public class Invoice_sub {
	private int invoice_id;
	private double invoice_price;
	private String useDate;
	private String invaliddDate;
	private String substation;
	private String invoiceStatus;
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	public double getInvoice_price() {
		return invoice_price;
	}
	public void setInvoice_price(double invoice_price) {
		this.invoice_price = invoice_price;
	}
	public String getUseDate() {
		return useDate;
	}
	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
	public String getInvaliddDate() {
		return invaliddDate;
	}
	public void setInvaliddDate(String invaliddDate) {
		this.invaliddDate = invaliddDate;
	}
	public String getSubstation() {
		return substation;
	}
	public void setSubstation(String substation) {
		this.substation = substation;
	}
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	
}
