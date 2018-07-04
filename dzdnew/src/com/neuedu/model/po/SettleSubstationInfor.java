package com.neuedu.model.po;

import java.util.ArrayList;
import java.util.List;

public class SettleSubstationInfor {
	private List<Integer> ids;
	private String subName;
	private String itemCode;
	private String itemName;
	private double price;
	private int quantityOut;
	private double amountIn;
	private int quantityIn;
	private double amountOut;
	private String date;
	public SettleSubstationInfor(List<Integer> ids, String subName, String itemCode,
			String itemName, double price, int quantityOut, double amountIn,
			int quantityIn, double amountOut, String date) {
		
		this.setIds(ids);
		this.subName = subName;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.price = price;
		this.quantityOut = quantityOut;
		this.amountIn = amountIn;
		this.quantityIn = quantityIn;
		this.amountOut = amountOut;
		this.date = date;
	}
	public SettleSubstationInfor(){
		this.setIds(new ArrayList<Integer>());
		this.subName = "";
		this.itemCode = "";
		this.itemName = "";
		this.price = 0;
		this.quantityOut = 0;
		this.amountIn = 0;
		this.quantityIn = 0;
		this.amountOut = 0;
		this.date = "";
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
	public int getQuantityOut() {
		return quantityOut;
	}
	public void setQuantityOut(int quantityOut) {
		this.quantityOut = quantityOut;
	}
	public double getAmountIn() {
		return amountIn;
	}
	public void setAmountIn(double amountIn) {
		this.amountIn = amountIn;
	}
	public int getQuantityIn() {
		return quantityIn;
	}
	public void setQuantityIn(int quantityIn) {
		this.quantityIn = quantityIn;
	}
	public double getAmountOut() {
		return amountOut;
	}
	public void setAmountOut(double amountOut) {
		this.amountOut = amountOut;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
}
