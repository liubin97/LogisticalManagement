/**
 * @package com.neuedu.model.po
 * @author liubin
 * @date 2018年6月19日
*/
package com.neuedu.model.po;

import java.util.Date;

public class CenReturnOutInfo {
	private int cen_return_out_info_id;
	private int rs_id;
	private int actual_num;
	private Date operate_date;
	public int getCen_return_out_info_id() {
		return cen_return_out_info_id;
	}
	public void setCen_return_out_info_id(int cen_return_out_info_id) {
		this.cen_return_out_info_id = cen_return_out_info_id;
	}
	public int getRs_id() {
		return rs_id;
	}
	public void setRs_id(int rs_id) {
		this.rs_id = rs_id;
	}
	public Date getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(Date operate_date) {
		this.operate_date = operate_date;
	}
	public int getActual_num() {
		return actual_num;
	}
	public void setActual_num(int actual_num) {
		this.actual_num = actual_num;
	}
	
}
