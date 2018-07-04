package com.neuedu.model.po;

public class InOutList {
	private String type;
	private String listid;
	private int product_id;
	private String product_name;
	private int wh_id;
	private String wh_name;
	private float price;
	private int amount;
	private float allprice;
	private String date;
	public String getType() {
		return type;
	}
	public int getProduct_id() {
		return product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public int getWh_id() {
		return wh_id;
	}
	public String getWh_name() {
		return wh_name;
	}
	public float getPrice() {
		return price;
	}
	public int getAmount() {
		return amount;
	}
	public float getAllprice() {
		return allprice;
	}
	public String getDate() {
		return date;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public void setWh_id(int wh_id) {
		this.wh_id = wh_id;
	}
	public void setWh_name(String wh_name) {
		this.wh_name = wh_name;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setAllprice(float allprice) {
		this.allprice = allprice;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getListid() {
		return listid;
	}
	public void setListid(String listid) {
		this.listid = listid;
	}
	
}
