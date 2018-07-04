package com.neuedu.model.po;

import java.util.Date;

public class OneTitle {

	private String onetitle_name;
	private int onetitle_id;
	private String onetitle_describe;
	private int status;
	private String operator;
	private Date operatoedate;
	
	public String getOnetitle_name() {
		return onetitle_name;
	}
	public void setOnetitle_name(String onetitle_name) {
		this.onetitle_name = onetitle_name;
	}
	public int getOnetitle_id() {
		return onetitle_id;
	}
	public void setOnetitle_id(int onetitle_id) {
		this.onetitle_id = onetitle_id;
	}
	public String getOnetitle_describe() {
		return onetitle_describe;
	}
	public void setOnetitle_describe(String onetitle_describe) {
		this.onetitle_describe = onetitle_describe;
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
