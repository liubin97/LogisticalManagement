package com.neuedu.model.po;

public class PurchaseSupplier {
	private int sup_id;
	private int order_id;
	private int prod_id;
	private String prod_name;
	private int pur_num;
	private String date;
	private float price;
	private float sum_price;
	private int settle_status;
	private int status;
	public int getSup_id() {
		return sup_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public int getProd_id() {
		return prod_id;
	}
	public int getPur_num() {
		return pur_num;
	}
	public String getDate() {
		return date;
	}
	public float getPrice() {
		return price;
	}
	public float getSum_price() {
		return sum_price;
	}
	public int getSettle_status() {
		return settle_status;
	}
	public int getStatus() {
		return status;
	}
	public void setSup_id(int sup_id) {
		this.sup_id = sup_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public void setPur_num(int pur_num) {
		this.pur_num = pur_num;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setSum_price(float sum_money) {
		this.sum_price = sum_money;
	}
	public void setSettle_status(int settle_status) {
		this.settle_status = settle_status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	
}
