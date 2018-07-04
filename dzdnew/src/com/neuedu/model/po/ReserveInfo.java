package com.neuedu.model.po;

public class ReserveInfo {
	private int res_id;
	private int wh_id;
	private int pro_id;
	private int warn_num;
	public int getRes_id() {
		return res_id;
	}
	public int getWh_id() {
		return wh_id;
	}
	public int getPro_id() {
		return pro_id;
	}
	public int getWarn_num() {
		return warn_num;
	}
	public int getMax_num() {
		return max_num;
	}
	public int getStatus() {
		return status;
	}
	public String getOperator() {
		return operator;
	}
	public String getOperatordate() {
		return operatordate;
	}
	public int getRes_num() {
		return res_num;
	}
	private int max_num;
	private int status;
	private String operator;
	private String operatordate;
	private int res_num;
	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}
	public void setWh_id(int wh_id) {
		this.wh_id = wh_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public void setWarn_num(int warn_num) {
		this.warn_num = warn_num;
	}
	public void setMax_num(int max_num) {
		this.max_num = max_num;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public void setOperatordate(String operatordate) {
		this.operatordate = operatordate;
	}
	public void setRes_num(int res_num) {
		this.res_num = res_num;
	}
}
