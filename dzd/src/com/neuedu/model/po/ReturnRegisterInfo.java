/**
 * @package com.neuedu.model.po
 * @author liubin
 * @date 2018年6月19日
*/
package com.neuedu.model.po;

import java.util.Date;

public class ReturnRegisterInfo {
	private int return_register_info_id;
	private int task_id;
	private int actual_num;
	private Date operate_date;
	public int getReturn_register_info_id() {
		return return_register_info_id;
	}
	public void setReturn_register_info_id(int return_register_info_id) {
		this.return_register_info_id = return_register_info_id;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public int getActual_num() {
		return actual_num;
	}
	public void setActual_num(int actual_num) {
		this.actual_num = actual_num;
	}
	public Date getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(Date operate_date) {
		this.operate_date = operate_date;
	}
	
}
