package com.neuedu.model.po;

public class PaymentResult {
	private int substation_id;
	private int prod_id;
	private String product_name;
	private int amount;
	private float summoney;
	public int getSubstation_id() {
		return substation_id;
	}
	public void setSubstation_id(int substation_id) {
		this.substation_id = substation_id;
	}
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getSummoney() {
		return summoney;
	}
	public void setSummoney(float summoney) {
		this.summoney = summoney;
	}
	
}
