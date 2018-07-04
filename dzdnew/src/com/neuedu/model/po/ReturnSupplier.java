package com.neuedu.model.po;

public class ReturnSupplier {
	private int rs_id;
	private int criid;
	private int prod_id;
	private String product_name;
	private int supplier_id;
	private int rs_num;
	private float price;
	private String date;
	public void setRs_id(int rs_id) {
		this.rs_id = rs_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}
	public void setRs_num(int rs_num) {
		this.rs_num = rs_num;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getRs_id() {
		return rs_id;
	}
	public int getProd_id() {
		return prod_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public int getSupplier_id() {
		return supplier_id;
	}
	public int getRs_num() {
		return rs_num;
	}
	public float getPrice() {
		return price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCriid() {
		return criid;
	}
	public void setCriid(int criid) {
		this.criid = criid;
	}
}
