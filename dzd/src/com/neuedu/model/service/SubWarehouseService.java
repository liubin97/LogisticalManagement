/**
 * @package com.neuedu.model.service
 * @author liubin
 * @date 2018��6��19��
*/
package com.neuedu.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import com.neuedu.model.dao.CenWarehouseDAO;
import com.neuedu.model.dao.CenWarehouseDAOImp;
import com.neuedu.model.dao.SubWarehouseDAO;
import com.neuedu.model.dao.SubWarehouseDAOImp;
import com.neuedu.model.po.RecvGoodsInfo;
import com.neuedu.model.po.ReturnRegisterInfo;
import com.neuedu.model.po.SubWarehouseInInfo;
import com.neuedu.utils.DBUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubWarehouseService {
	private static SubWarehouseService service = new SubWarehouseService();
	
	//����ģʽ
	public static SubWarehouseService getInstance() {
		return service;
	}
	//��ȡ������� 
	public JSONObject getTaskListIn(int task_id) {
		Connection conn = DBUtil.getConn();
		SubWarehouseDAO swd = new SubWarehouseDAOImp(conn);
		JSONObject json = swd.getTaskListIn(task_id);
		return json;
	}
	//���������Ϣ
	public void insertInInfo(SubWarehouseInInfo swin) throws SQLException {
		
		Connection conn = DBUtil.getConn();
		//��������
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
	//��ȡ��������
	public JSONObject getTaskListOut(int task_id) {
		Connection conn = DBUtil.getConn();
		SubWarehouseDAO swd = new SubWarehouseDAOImp(conn);
		JSONObject json = swd.getTaskListOut(task_id);
		return json;
	}
	
	//���������Ϣ
	public void insertRecvGoodsInfo(RecvGoodsInfo rin) throws SQLException {
		Connection conn = DBUtil.getConn();
		//��������
		DBUtil.beginTransaction(conn);
		try {
			SubWarehouseDAO swd = new SubWarehouseDAOImp(conn);
			swd.insertRecvGoodsInfo(rin);
			DBUtil.commit(conn);
		}catch (Exception e) {
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}
	}
	
	//��ȡ�˻��Ǽ�����
	public JSONObject getReturnInTaskList(int task_id) {
		Connection conn = DBUtil.getConn();
		SubWarehouseDAO swd = new SubWarehouseDAOImp(conn);
		JSONObject json = swd.getReturnInTaskList(task_id);
		return json;
	}
	
	//�����˻��Ǽ���Ϣ
	public void insertReturnRegisterInfo(ReturnRegisterInfo rin) throws SQLException {
		Connection conn = DBUtil.getConn();
		//��������
		DBUtil.beginTransaction(conn);
		try {
			SubWarehouseDAO swd = new SubWarehouseDAOImp(conn);
			swd.insertReturnRegisterInfo(rin);
			DBUtil.commit(conn);
		}catch (Exception e) {
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}
	}
	
	//��ѯ�˻�������Ϣ
	public JSONArray getReturnOutTaskList(Date start_date,Date end_date,int pagenum) {
		Connection conn = DBUtil.getConn();
		SubWarehouseDAO swd = new SubWarehouseDAOImp(conn);
		JSONArray json = swd.getReturnOutTaskList(start_date, end_date,pagenum);
		return json;
	}
	//��ѯ�˻�����ҳ��
	public int getReturnOutPage(Date start_date,Date end_date) {
		Connection conn = DBUtil.getConn();
		SubWarehouseDAO swd = new SubWarehouseDAOImp(conn);
		int count = swd.getReturnOutPage(start_date, end_date);
		return count;
	}
	
	//�����˻�������Ϣ
	public void insertReturnOutInfo(int []ids) throws SQLException {
		Connection conn = DBUtil.getConn();
		//��������
		DBUtil.beginTransaction(conn);
		try {
			SubWarehouseDAO swd = new SubWarehouseDAOImp(conn);
			swd.insertReturnOutInfo(ids);
			DBUtil.commit(conn);
		}catch (Exception e) {
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}
	}
	
}
