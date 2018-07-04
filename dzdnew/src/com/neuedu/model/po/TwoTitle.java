package com.neuedu.model.po;

import java.util.Date;

public class TwoTitle {
	private String twotitle_name;
	private int twotitle_id;
	private String twotitle_describe;
	private int status;
	private String operator;
	private Date operatoedate;
	private int onetitle_id;
	public int getOnetitle_id() {
		return onetitle_id;
	}
	public void setOnetitle_id(int onetitle_id) {
		this.onetitle_id = onetitle_id;
	}
	public String getTwotitle_name() {
		return twotitle_name;
	}
	public void setTwotitle_name(String twotitle_name) {
		this.twotitle_name = twotitle_name;
	}
	public int getTwotitle_id() {
		return twotitle_id;
	}
	public void setTwotitle_id(int twotitle_id) {
		this.twotitle_id = twotitle_id;
	}
	public String getTwotitle_describe() {
		return twotitle_describe;
	}
	public void setTwotitle_describe(String twotitle_describe) {
		this.twotitle_describe = twotitle_describe;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperatoedate() {
		return operatoedate;
	}
	public void setOperatoedate(Date operatoedate) {
		this.operatoedate = operatoedate;
	}
	
	
}
