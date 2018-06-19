/**
 * @package com.neuedu.model.po
 * @author liubin
 * @date 2018Äê6ÔÂ19ÈÕ
*/
package com.neuedu.model.po;

import java.util.Date;

public class CenReturnOutInfo {
	private int cen_return_out_info_id;
	private int cen_return_in_info_id;
	private Date operate_date;
	public int getCen_return_out_info_id() {
		return cen_return_out_info_id;
	}
	public void setCen_return_out_info_id(int cen_return_out_info_id) {
		this.cen_return_out_info_id = cen_return_out_info_id;
	}
	public int getCen_return_in_info_id() {
		return cen_return_in_info_id;
	}
	public void setCen_return_in_info_id(int cen_return_in_info_id) {
		this.cen_return_in_info_id = cen_return_in_info_id;
	}
	public Date getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(Date operate_date) {
		this.operate_date = operate_date;
	}
	
}
