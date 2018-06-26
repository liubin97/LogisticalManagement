/**
 * @package com.neuedu.model.po
 * @author liubin
 * @date 2018年6月19日
*/
package com.neuedu.model.po;

import java.util.Date;

public class CenReturnInInfo {
	private int cen_return_in_info_id;
	private int task_list_id;
	private Date return_date;
	private int actual_num;
	public int getCen_return_in_info_id() {
		return cen_return_in_info_id;
	}
	public void setCen_return_in_info_id(int cen_return_in_info_id) {
		this.cen_return_in_info_id = cen_return_in_info_id;
	}
	
	public int getTask_list_id() {
		return task_list_id;
	}
	public void setTask_list_id(int task_list_id) {
		this.task_list_id = task_list_id;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public int getActual_num() {
		return actual_num;
	}
	public void setActual_num(int actual_num) {
		this.actual_num = actual_num;
	}
	
}
