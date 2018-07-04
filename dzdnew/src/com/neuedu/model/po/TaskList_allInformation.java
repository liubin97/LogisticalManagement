package com.neuedu.model.po;

import java.sql.Date;

public class TaskList_allInformation {
	private int order_id;
	private int tasklist_id;
	private int substation_id;
	
	private Date creatDate;
	private int taskState;
	private int taskType;
	
	private String clientName;
	private String clientAddress;
	private String clientTel;
	
	private int deliveryStaff_id;
	private String deliveryStaffName;
	private int delivery_staff_tel;
	
	private int is_invoice;
	
	public int getDelivery_staff_tel() {
		return delivery_staff_tel;
	}
	public void setDelivery_staff_tel(int delivery_staff_tel) {
		this.delivery_staff_tel = delivery_staff_tel;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getTasklist_id() {
		return tasklist_id;
	}
	public void setTasklist_id(int tasklist_id) {
		this.tasklist_id = tasklist_id;
	}
	public int getTaskState() {
		return taskState;
	}
	public void setTaskState(int taskState) {
		this.taskState = taskState;
	}
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	public int getSubstation_id() {
		return substation_id;
	}
	public void setSubstation_id(int substation_id) {
		this.substation_id = substation_id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public int getDeliveryStaff_id() {
		return deliveryStaff_id;
	}
	public void setDeliveryStaff_id(int deliveryStaff_id) {
		this.deliveryStaff_id = deliveryStaff_id;
	}
	public String getDeliveryStaffName() {
		return deliveryStaffName;
	}
	public void setDeliveryStaffName(String deliveryStaffName) {
		this.deliveryStaffName = deliveryStaffName;
	}
	public int getTaskType() {
		return taskType;
	}
	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}
	public int getIs_invoice() {
		return is_invoice;
	}
	public void setIs_invoice(int is_invoice) {
		this.is_invoice = is_invoice;
	}
	public String getClientTel() {
		return clientTel;
	}
	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}
}
