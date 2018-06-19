/**
 * @package com.neuedu.model.po
 * @author liubin
 * @date 2018Äê6ÔÂ19ÈÕ
*/
package com.neuedu.model.po;

import java.util.Date;

public class SubWarehouseInInfo {
	private int sub_warehouse_in_info;
	private int distribution_list_id;
	private String note;
	private Date operate_date;
	public int getSub_warehouse_in_info() {
		return sub_warehouse_in_info;
	}
	public void setSub_warehouse_in_info(int sub_warehouse_in_info) {
		this.sub_warehouse_in_info = sub_warehouse_in_info;
	}
	public int getDistribution_list_id() {
		return distribution_list_id;
	}
	public void setDistribution_list_id(int distribution_list_id) {
		this.distribution_list_id = distribution_list_id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(Date operate_date) {
		this.operate_date = operate_date;
	}
	
	
}
