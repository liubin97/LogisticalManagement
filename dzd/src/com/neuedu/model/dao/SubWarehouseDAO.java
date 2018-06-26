/**
 * @package com.neuedu.model.dao
 * @author liubin
 * @date 2018Äê6ÔÂ19ÈÕ
*/
package com.neuedu.model.dao;

import com.neuedu.model.po.RecvGoodsInfo;
import com.neuedu.model.po.ReturnRegisterInfo;
import com.neuedu.model.po.SubWarehouseInInfo;

import net.sf.json.JSONObject;

public interface SubWarehouseDAO {
	public JSONObject getTaskListIn(int task_id);
	public JSONObject getTaskListOut(int task_id);
	public JSONObject getReturnInTaskList(int task_id);
	public void insertInInfo(SubWarehouseInInfo swin);
	public void editTaskListStatus(int tasklist_id, int status);
	public void insertRecvGoodsInfo(RecvGoodsInfo swin);
	public void insertReturnRegisterInfo(ReturnRegisterInfo rin);
}
