package com.neuedu.model.po;

import java.util.ArrayList;
import java.util.List;

public class SettleListInfor {
	private List<Integer> idIn;
	private List<Integer> idOut;
	private String supplyName;
	private String itemCode;
	private String itemName;
	private double price;
	private int quantityIn;
	private int quantityOut;
	private int quantity;
	private String date;
	public SettleListInfor(List<Integer> idIn,List<Integer> idOut, String supplyName, String itemCode,
			String itemName, double price, int quantityIn, int quantityOut,
			int quantity) {
		super();
		this.idIn = idIn;
		this.idOut = idOut;
		this.supplyName = supplyName;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.price = price;
		this.quantityIn = quantityIn;
		this.quantityOut = quantityOut;
		this.quantity = quantity;
	}
	public SettleListInfor(){
		this.setIdIn(null);
		this.setIdOut(null);
		this.supplyName = "";
		this.itemCode = "";
		this.itemName = "";
		this.price = 0;
		this.quantityIn = 0;
		this.quantityOut = 0;
		this.quantity = 0;
	}
	public List<Integer> getIdIn() {
		return idIn;
	}
	public void setIdIn(List<Integer> idIn) {
		this.idIn = idIn;
	}
	public List<Integer> getIdOut() {
		return idOut;
	}
	public void setIdOut(List<Integer> idOut) {
		this.idOut = idOut;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
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
	public int getQuantityIn() {
		return quantityIn;
	}
	public void setQuantityIn(int quantityIn) {
		this.quantityIn = quantityIn;
	}
	public int getQuantityOut() {
		return quantityOut;
	}
	public void setQuantityOut(int quantityOut) {
		this.quantityOut = quantityOut;
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
