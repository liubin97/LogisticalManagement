package com.neuedu.model.po;

public class PurchaseMsg {

	private int prod_id;
	private String prod_name;
	private int order_id;
	private int supplier_id;
	private String onetitle_name;
	private String twotitle_name;
	private int warn_num;
	private int can_distribute;
	private int outofstock;
	private String measurement;
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}
	public void setOnetitle_name(String onetitle_name) {
		this.onetitle_name = onetitle_name;
	}
	public void setTwotitle_name(String twotitle_name) {
		this.twotitle_name = twotitle_name;
	}
	public void setWarn_num(int warn_num) {
		this.warn_num = warn_num;
	}
	public void setCan_distribute(int can_distribute) {
		this.can_distribute = can_distribute;
	}
	public void setOutofstock(int outofstock) {
		this.outofstock = outofstock;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public int getProd_id() {
		return prod_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public int getOrder_id() {
		return order_id;
	}
	public int getSupplier_id() {
		return supplier_id;
	}
	public String getOnetitle_name() {
		return onetitle_name;
	}
	public String getTwotitle_name() {
		return twotitle_name;
	}
	public int getWarn_num() {
		return warn_num;
	}
	public int getCan_distribute() {
		return can_distribute;
	}
	public int getOutofstock() {
		return outofstock;
	}
	public String getMeasurement() {
		return measurement;
	}
	
}
