package com.neuedu.model.po;

public class Manager {
	private int manager_id;
	private String admin;
	private String name;
	private String telephone;
	private int type;
	private int sub_id;
	public int getManager_id() {
		return manager_id;
	}
	public String getAdmin() {
		return admin;
	}
	public String getName() {
		return name;
	}
	public String getTelephone() {
		return telephone;
	}
	public int getType() {
		return type;
	}
	public int getSub_id() {
		return sub_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	
}
