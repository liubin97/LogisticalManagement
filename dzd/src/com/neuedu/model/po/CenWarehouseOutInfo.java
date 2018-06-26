/**
 * @package com.neuedu.model.po
 * @author liubin
 * @date 2018年6月19日
*/
package com.neuedu.model.po;

import java.util.Date;

public class CenWarehouseOutInfo {
	private int cen_warehouse_out_info_id;
 	private int task_list_id;
	private Date operate_date;
	public int getCen_warehouse_out_info_id() {
		return cen_warehouse_out_info_id;
	}
	public void setCen_warehouse_out_info_id(int cen_warehouse_out_info_id) {
		this.cen_warehouse_out_info_id = cen_warehouse_out_info_id;
	}
	public int getTask_list_id() {
		return task_list_id;
	}
	public void setTask_list_id(int task_list_id) {
		this.task_list_id = task_list_id;
	}
	public Date getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(Date operate_date) {
		this.operate_date = operate_date;
	}
	
}
