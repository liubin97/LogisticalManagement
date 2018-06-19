/**
 * @package com.neuedu.model.po
 * @author liubin
 * @date 2018Äê6ÔÂ19ÈÕ
*/
package com.neuedu.model.po;

import java.util.Date;

public class CenWarehouseInInfo {
	private int cen_warehouse_in_info_id;
	private int ps_id;
	private int actual_num;
	private String note;
	private Date date;
	private Date operate_date;
	public int getCen_warehouse_in_info_id() {
		return cen_warehouse_in_info_id;
	}
	public void setCen_warehouse_in_info_id(int cen_warehouse_in_info_id) {
		this.cen_warehouse_in_info_id = cen_warehouse_in_info_id;
	}
	public int getPs_id() {
		return ps_id;
	}
	public void setPs_id(int ps_id) {
		this.ps_id = ps_id;
	}
	public int getActual_num() {
		return actual_num;
	}
	public void setActual_num(int actual_num) {
		this.actual_num = actual_num;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(Date operate_date) {
		this.operate_date = operate_date;
	}

}
