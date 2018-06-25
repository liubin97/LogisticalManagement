package com.neuedu.model.po;

import java.util.Date;

public class Product {
	private int product_id;
	private String product_name;
	private int twotitle_id;
	private String measurement;
	private String type = null;
	private String vendor;
	private String notes;
	private String operator;
	private float original_price;
	private float cost_price;
	private float discount;
	private int supplier_id;
	private Date quality_period;
	private int is_return;
	private int is_exchange;
	private int status;
	private Date operatordate;
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getTwotitle_id() {
		return twotitle_id;
	}
	public void setTwotitle_id(int twotitle_id) {
		this.twotitle_id = twotitle_id;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public float getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(float original_price) {
		this.original_price = original_price;
	}
	public float getCost_price() {
		return cost_price;
	}
	public void setCost_price(float cost_price) {
		this.cost_price = cost_price;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public int getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}
	public Date getQuality_period() {
		return quality_period;
	}
	public void setQuality_period(Date quality_period) {
		this.quality_period = quality_period;
	}
	public int getIs_return() {
		return is_return;
	}
	public void setIs_return(int is_return) {
		this.is_return = is_return;
	}
	public int getIs_exchange() {
		return is_exchange;
	}
	public void setIs_exchange(int is_exchange) {
		this.is_exchange = is_exchange;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getOperatordate() {
		return operatordate;
	}
	public void setOperatordate(Date operatordate) {
		this.operatordate = operatordate;
	}
	
}
