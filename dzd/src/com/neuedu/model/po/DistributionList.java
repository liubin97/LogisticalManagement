/**
 * @package com.neuedu.model.po
 * @author liubin
 * @date 2018Äê6ÔÂ19ÈÕ
*/
package com.neuedu.model.po;

import java.util.Date;

public class DistributionList {
	private int distribution_list_id;
	private int task_list_id;
	private String distributor;
	private String signer;
	private Date operate_date;
	public int getDistribution_list_id() {
		return distribution_list_id;
	}
	public void setDistribution_list_id(int distribution_list_id) {
		this.distribution_list_id = distribution_list_id;
	}
	public int getTask_list_id() {
		return task_list_id;
	}
	public void setTask_list_id(int task_list_id) {
		this.task_list_id = task_list_id;
	}
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	public String getSigner() {
		return signer;
	}
	public void setSigner(String signer) {
		this.signer = signer;
	}
	public Date getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(Date operate_date) {
		this.operate_date = operate_date;
	}

}
