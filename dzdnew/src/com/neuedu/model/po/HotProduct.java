package com.neuedu.model.po;

import java.sql.Date;

public class HotProduct {
	private int product_id;
	private String product_name;
	private String onetitle;
	private String twotitle;
	private float original_price;
	private float cost_price;
	private float discount;
	private String supplier_name;
	private int sellnum;
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public void setOnetitle(String onetitle) {
		this.onetitle = onetitle;
	}
	public void setTwotitle(String twotitle) {
		this.twotitle = twotitle;
	}
	public void setOriginal_price(float original_price) {
		this.original_price = original_price;
	}
	public void setCost_price(float cost_price) {
		this.cost_price = cost_price;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public void setSellnum(int sellnum) {
		this.sellnum = sellnum;
	}
	public int getProduct_id() {
		return product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public String getOnetitle() {
		return onetitle;
	}
	public String getTwotitle() {
		return twotitle;
	}
	public float getOriginal_price() {
		return original_price;
	}
	public float getCost_price() {
		return cost_price;
	}
	public float getDiscount() {
		return discount;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public int getSellnum() {
		return sellnum;
	}
}
