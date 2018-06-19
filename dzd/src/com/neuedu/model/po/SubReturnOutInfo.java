/**
 * @package com.neuedu.model.po
 * @author liubin
 * @date 2018Äê6ÔÂ19ÈÕ
*/
package com.neuedu.model.po;

import java.util.Date;

public class SubReturnOutInfo {
	private int sub_return_out_info_id;
	private int task_list_id;
	private Date return_date;
	private Date operate_date;
	public int getSub_return_out_info_id() {
		return sub_return_out_info_id;
	}
	public void setSub_return_out_info_id(int sub_return_out_info_id) {
		this.sub_return_out_info_id = sub_return_out_info_id;
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
	public Date getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(Date operate_date) {
		this.operate_date = operate_date;
	}
	
}
