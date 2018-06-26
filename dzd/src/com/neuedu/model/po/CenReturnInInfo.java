/**
 * @package com.neuedu.model.po
 * @author liubin
 * @date 2018年6月19日
*/
package com.neuedu.model.po;

import java.util.Date;

public class CenReturnInInfo {
	private int cen_return_in_info_id;
	private int sub_return_out_info_id;
	private Date return_date;
	private Date operate_date;
	public int getCen_return_in_info_id() {
		return cen_return_in_info_id;
	}
	public void setCen_return_in_info_id(int cen_return_in_info_id) {
		this.cen_return_in_info_id = cen_return_in_info_id;
	}
	public int getSub_return_out_info_id() {
		return sub_return_out_info_id;
	}
	public void setSub_return_out_info_id(int sub_return_out_info_id) {
		this.sub_return_out_info_id = sub_return_out_info_id;
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
