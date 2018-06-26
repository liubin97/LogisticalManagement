/**
 * @package com.neuedu.model.po
 * @author liubin
 * @date 2018年6月19日
*/
package com.neuedu.model.po;

import java.util.Date;

public class SubWarehouseInInfo {
	private int sub_warehouse_in_info;
	private int task_list_id;
	private String note;
	private Date operate_date;
	public int getSub_warehouse_in_info() {
		return sub_warehouse_in_info;
	}
	public void setSub_warehouse_in_info(int sub_warehouse_in_info) {
		this.sub_warehouse_in_info = sub_warehouse_in_info;
	}
	public int getTask_list_id() {
		return task_list_id;
	}
	public void setTask_list_id(int task_list_id) {
		this.task_list_id = task_list_id;
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
