/**
 * @package com.neuedu.model.service
 * @author liubin
 * @date 2018楠烇拷6閺堬拷19閺冿拷
*/
package com.neuedu.model.service;

import com.neuedu.model.dao.CenWarehouseDAO;
import com.neuedu.model.dao.CenWarehouseDAOImp;
import com.neuedu.model.po.CenWarehouseInInfo;
import com.neuedu.model.po.PurchaseSupplier;
import com.neuedu.utils.DBUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class CenWarehouseService {
	private static CenWarehouseService service = new CenWarehouseService();
	
	
	public static CenWarehouseService getInstance() {
		return service;
	}
	
	public JSONObject getPurchaseInfo(int psid) throws SQLException{
		Connection conn = DBUtil.getConn();
		CenWarehouseDAO cwd = new CenWarehouseDAOImp(conn);
		JSONObject ps = cwd.getPurchaseInfo(psid);
		
		DBUtil.closeConn(conn);;
		
		return ps;
	}
	
	public void insertInWarehouseInfo(CenWarehouseInInfo cwin) throws SQLException {
		Connection conn = DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		try {
			CenWarehouseDAO cwd = new CenWarehouseDAOImp(conn);
			cwd.insertInWarehouseInfo(cwin);
			DBUtil.commit(conn);
		}catch (Exception e) {
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}
	}
	
	public void editOrderStatus(int order_id ,int status) throws SQLException {
		Connection conn = DBUtil.getConn();
		CenWarehouseDAO cwd = new CenWarehouseDAOImp(conn);
		cwd.editOrderStatus(order_id,status);
		conn.close();
	}
	
	public JSONArray getTaskListByDate(Date date,int pageNum) throws SQLException {
		Connection conn = DBUtil.getConn();
		CenWarehouseDAO cwd = new CenWarehouseDAOImp(conn);
		JSONArray json = cwd.getTaskListByDate(date,pageNum);
		DBUtil.closeConn(conn);
		return json;
	}
	
	public int getTaskListPageCount(Date date) {
		Connection conn = DBUtil.getConn();
		CenWarehouseDAO cwd = new CenWarehouseDAOImp(conn);
		int count = cwd.getTaskListPageCount(date);
		return count;
	}
	
	public void insertOutWarehouseInfo(int []ids) throws SQLException {
		Connection conn = DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		try {
			CenWarehouseDAO cwd = new CenWarehouseDAOImp(conn);
			cwd.insertOutWarehouseInfo(ids);
			DBUtil.commit(conn);
		}catch (Exception e) {
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}
	}

}
