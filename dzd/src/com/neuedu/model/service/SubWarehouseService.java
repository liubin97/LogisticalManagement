/**
 * @package com.neuedu.model.service
 * @author liubin
 * @date 2018ï¿½ï¿½6ï¿½ï¿½19ï¿½ï¿½
*/
package com.neuedu.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.neuedu.model.dao.CenWarehouseDAO;
import com.neuedu.model.dao.CenWarehouseDAOImp;
import com.neuedu.model.dao.SubWarehouseDAO;
import com.neuedu.model.dao.SubWarehouseDAOImp;
import com.neuedu.model.po.SubWarehouseInInfo;
import com.neuedu.utils.DBUtil;

import net.sf.json.JSONObject;

public class SubWarehouseService {
	private static SubWarehouseService service = new SubWarehouseService();
	
	//µ¥ÀýÄ£Ê½
	public static SubWarehouseService getInstance() {
		return service;
	}
	
	public JSONObject getTaskListIn(int task_id) {
		Connection conn = DBUtil.getConn();
		SubWarehouseDAO swd = new SubWarehouseDAOImp(conn);
		JSONObject json = swd.getTaskListIn(task_id);
		return json;
	}
	
	public void insertInInfo(SubWarehouseInInfo swin) throws SQLException {
		
		Connection conn = DBUtil.getConn();
		//¿ªÆôÊÂÎñ
		DBUtil.beginTransaction(conn);
		try {
			SubWarehouseDAO swd = new SubWarehouseDAOImp(conn);
			swd.insertInInfo(swin);
			DBUtil.commit(conn);
		}catch (Exception e) {
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}
		
	}
	
}
