package com.neuedu.model.po;

public class Receipt {
	private int tasklist_id;
	private String clientSatis;
	private String receiptnote;
	public int getTasklist_id() {
		return tasklist_id;
	}
	public void setTasklist_id(int tasklist_id) {
		this.tasklist_id = tasklist_id;
	}
	public String getClientSatis() {
		return clientSatis;
	}
	public void setClientSatis(String clientSatis) {
		this.clientSatis = clientSatis;
	}
	public String getReceiptnote() {
		return receiptnote;
	}
	public void setReceiptnote(String receiptnote) {
		this.receiptnote = receiptnote;
	}
}
