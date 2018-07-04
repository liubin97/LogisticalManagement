package com.neuedu.model.po;

import java.util.Date;

public class Tasklist {
	private int task_list_id;
	private int substation_id;
	private int order_id;
	private int task_status;
	private int delivery_staff_id;
	private int settle_status;
	private Date task_list_create_date;
	
	public int getTask_list_id() {
		return task_list_id;
	}
	public void setTask_list_id(int task_list_id) {
		this.task_list_id = task_list_id;
	}
	public int getSubstation_id() {
		return substation_id;
	}
	public void setSubstation_id(int substation_id) {
		this.substation_id = substation_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getTask_status() {
		return task_status;
	}
	public void setTask_status(int task_status) {
		this.task_status = task_status;
	}
	public int getDelivery_staff_id() {
		return delivery_staff_id;
	}
	public void setDelivery_staff_id(int delivery_staff_id) {
		this.delivery_staff_id = delivery_staff_id;
	}
	public int getSettle_status() {
		return settle_status;
	}
	public void setSettle_status(int settle_status) {
		this.settle_status = settle_status;
	}
	public Date getTask_list_create_date() {
		return task_list_create_date;
	}
	public void setTask_list_create_date(Date task_list_create_date) {
		this.task_list_create_date = task_list_create_date;
	}
	
	public void initialize() {
		this.delivery_staff_id = 1;
		this.settle_status = 0;
	}
	
	
}
