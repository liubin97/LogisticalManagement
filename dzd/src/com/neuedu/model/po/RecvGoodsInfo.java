/**
 * @package com.neuedu.model.po
 * @author liubin
 * @date 2018Äê6ÔÂ19ÈÕ
*/
package com.neuedu.model.po;

import java.util.Date;

public class RecvGoodsInfo {
	private int recv_goods_info_id;
	private int task_list_id;
	private String recv_person;
	private Date recv_date;
	private Date operate_date;
	private String note;
	public int getRecv_goods_info_id() {
		return recv_goods_info_id;
	}
	public void setRecv_goods_info_id(int recv_goods_info_id) {
		this.recv_goods_info_id = recv_goods_info_id;
	}
	public int getTask_list_id() {
		return task_list_id;
	}
	public void setTask_list_id(int task_list_id) {
		this.task_list_id = task_list_id;
	}
	public String getRecv_person() {
		return recv_person;
	}
	public void setRecv_person(String recv_person) {
		this.recv_person = recv_person;
	}
	public Date getRecv_date() {
		return recv_date;
	}
	public void setRecv_date(Date recv_date) {
		this.recv_date = recv_date;
	}
	public Date getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(Date operate_date) {
		this.operate_date = operate_date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
