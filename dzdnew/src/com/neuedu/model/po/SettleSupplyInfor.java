package com.neuedu.model.po;

public class SettleSupplyInfor {
	private int id;
	private String supplyName;
	private String kind;
	private String itemCode;
	private String itemName;
	private double price;
	private int quantity;
	
	private String date;
	public SettleSupplyInfor(int id, String supplyName, String kind,
			String itemCode, String itemName, double price, int quantity,
			String date) {
		
		this.id = id;
		this.supplyName = supplyName;
		this.kind = kind;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
		this.date = date;
	}
	public SettleSupplyInfor() {
		id = 0;
		supplyName = "";
		kind = "";
		itemCode = "";
		itemName = "";
		price = 0;
		quantity = 0;
		date = "";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
