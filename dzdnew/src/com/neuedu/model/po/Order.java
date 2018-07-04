package com.neuedu.model.po;

import java.lang.String;
import java.sql.Date;

public class Order {
	private int order_id;
	private int client_id;
	private int status;
	private int prod_id;
	private int amount;
	private String unit;
	private float price;
	private float discount;
	private float sum_money;
	private String deliver_substation;
	private Date finish_date;
	private Date create_date;
	private int type;
	private String deliver_addr;
	private String consignee;
	private String consignee_tel;
	private String consignee_zip_cod;
	private int is_invoice;
	private int operator;
	private Date operatorDate;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public float getSum_money() {
		return sum_money;
	}
	public void setSum_money(float sum_money) {
		this.sum_money = sum_money;
	}
	public String getDeliver_substation() {
		return deliver_substation;
	}
	public void setDeliver_substation(String deliver_substation) {
		this.deliver_substation = deliver_substation;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDeliver_addr() {
		return deliver_addr;
	}
	public void setDeliver_addr(String deliver_addr) {
		this.deliver_addr = deliver_addr;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getConsignee_tel() {
		return consignee_tel;
	}
	public void setConsignee_tel(String consignee_tel) {
		this.consignee_tel = consignee_tel;
	}
	public String getConsignee_zip_cod() {
		return consignee_zip_cod;
	}
	public void setConsignee_zip_cod(String consignee_zip_cod) {
		this.consignee_zip_cod = consignee_zip_cod;
	}
	public int getIs_invoice() {
		return is_invoice;
	}
	public void setIs_invoice(int is_invoice) {
		this.is_invoice = is_invoice;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public Date getFinish_date() {
		return finish_date;
	}
	public void setFinish_date(Date finish_date) {
		this.finish_date = finish_date;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}

	
}
