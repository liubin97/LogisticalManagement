package com.neuedu.model.po;

public class Satisfaction {
	private String satisfaction;
	private String client_name;
	private int prod_id;
	private String product_name;
	private float sum_money;
	private int task_list_id;
	private String do_date;
	public String getSatisfaction() {
		return satisfaction;
	}
	public int getProd_id() {
		return prod_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public float getSum_money() {
		return sum_money;
	}
	public int getTask_list_id() {
		return task_list_id;
	}
	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public void setSum_money(float sum_money) {
		this.sum_money = sum_money;
	}
	public void setTask_list_id(int task_list_id) {
		this.task_list_id = task_list_id;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getDo_date() {
		return do_date;
	}
	public void setDo_date(String do_date) {
		this.do_date = do_date;
	}
	
}
