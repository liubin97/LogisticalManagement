package com.neuedu.model.po;

import java.sql.Date;

public class TaskList_Information {
	private int tasklistId;
	private int substationId;
	
	private Date finishDate;
	private int taskStatus;
	private int taskType;
	
	private String clientName;
	private String clientTel;
	public int getTasklistId() {
		return tasklistId;
	}
	public void setTasklistId(int tasklistId) {
		this.tasklistId = tasklistId;
	}
	public int getSubstationId() {
		return substationId;
	}
	public void setSubstationId(int substationId) {
		this.substationId = substationId;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public int getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}
	public int getTaskType() {
		return taskType;
	}
	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientTel() {
		return clientTel;
	}
	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}
	

	
}
