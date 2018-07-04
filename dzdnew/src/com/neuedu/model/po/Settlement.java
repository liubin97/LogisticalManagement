package com.neuedu.model.po;

public class Settlement {
	private String name;
	private String date;
	private String itemCode;
	private String itemName;
	public Settlement(){
		name = "";
		date = "";
		itemCode = "";
		itemName = "";		
	}
	public Settlement(String name,String date,String itemCode,String itemName){
		this.name = name;
		this.date = date;
		this.itemCode = itemCode;
		this.itemName = itemName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
}
