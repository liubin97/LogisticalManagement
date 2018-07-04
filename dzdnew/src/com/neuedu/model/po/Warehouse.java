package com.neuedu.model.po;

import java.util.Date;

public class Warehouse {
	private int wh_id;
	private String wh_name;
	private String wh_addr;
	private String wh_admin;
	private int wh_level;
	private int status;
	private String operator;
	private String operator_date;
	public int getWh_id() {
		return wh_id;
	}
	public String getWh_name() {
		return wh_name;
	}
	public String getWh_addr() {
		return wh_addr;
	}
	public String getWh_admin() {
		return wh_admin;
	}
	public int getWh_level() {
		return wh_level;
	}
	public int getStatus() {
		return status;
	}
	public String getOperator() {
		return operator;
	}
	public String getOperator_date() {
		return operator_date;
	}
	public void setWh_id(int wh_id) {
		this.wh_id = wh_id;
	}
	public void setWh_name(String wh_name) {
		this.wh_name = wh_name;
	}
	public void setWh_addr(String wh_addr) {
		this.wh_addr = wh_addr;
	}
	public void setWh_admin(String wh_admin) {
		this.wh_admin = wh_admin;
	}
	public void setWh_level(int wh_level) {
		this.wh_level = wh_level;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public void setOperator_date(String operator_date) {
		this.operator_date = operator_date;
	}
	
	
}
