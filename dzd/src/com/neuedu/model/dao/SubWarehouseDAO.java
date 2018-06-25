/**
 * @package com.neuedu.model.dao
 * @author liubin
 * @date 2018��6��19��
*/
package com.neuedu.model.dao;

import com.neuedu.model.po.SubWarehouseInInfo;

import net.sf.json.JSONObject;

public interface SubWarehouseDAO {
	public JSONObject getTaskListIn(int task_id);
	public void insertInInfo(SubWarehouseInInfo swin);
	public void editTaskListStatus(int tasklist_id, int status);
}
