package com.neuedu.model.po;

public class OrderInfor {
	private int taskId;
	private String subName;
	private int type;
	private String itemCode;
	private String itemName;
	private double price;
	private int quantity;
	private double amount;
	private String date;
	public OrderInfor(int taskId, String subName,int type,String itemCode,
			String itemName, double price, int quantity, double amount) {
		super();
		this.taskId = taskId;
		this.subName = subName;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
		this.amount = amount;
		this.type = type;
	}
	public OrderInfor(){
		this.taskId = 0;
		this.subName = "";
		this.itemCode = "";
		this.itemName = "";
		this.price = 0;
		this.quantity = 0;
		this.amount = 0;
		this.type = -1;
		this.date = "";
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
