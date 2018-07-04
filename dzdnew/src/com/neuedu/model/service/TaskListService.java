package com.neuedu.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.neuedu.model.po.*;
import com.neuedu.utils.DBUtil;
import com.neuedu.utils.DateUtil;
import com.neuedu.model.dao.*;

public class TaskListService {
	private TaskListService() {
		
	}
	
	private static TaskListService service = new TaskListService();
	
	public static TaskListService getInstance(){
		return service;
	}
	
	public List<TaskList_allInformation> queryTaskList(int substation_id,String begintime,String taskstate,String tasktype,int pageNum){
		
		Connection conn = DBUtil.getConn();
		TaskListDao tasklist = new TaskListDaoImp(conn);
		
		System.out.println(DateUtil.strToDate(begintime));
		
		return tasklist.selectTaskList(substation_id, DateUtil.strToDate(begintime), Integer.parseInt(taskstate), Integer.parseInt(tasktype), pageNum);
		
	}
	
	public int selectPageCount(int substation_id,String begintime,String taskstate,String tasktype) {
		Connection conn = DBUtil.getConn();
		TaskListDao tasklist = new TaskListDaoImp(conn);
		return tasklist.selectPageCount(substation_id, DateUtil.strToDate(begintime), Integer.parseInt(taskstate), Integer.parseInt(tasktype));
	}
	
	public void AllocateDeliver(int taskType,int tasklist_id,int deliver_id) {
		Connection conn = DBUtil.getConn();
		TaskListDao tasklist = new TaskListDaoImp(conn);
		tasklist.AllocateDeliver(taskType, tasklist_id, deliver_id);
	}
	
	public TaskList_allInformation getTaskListById(int tasklist_id) {
		Connection conn = DBUtil.getConn();
		TaskListDao tasklist = new TaskListDaoImp(conn);
		return tasklist.getTaskListById(tasklist_id);
	}
	
	public List<delivery_staff> getAlldeliver(){
		Connection conn = DBUtil.getConn();
		TaskListDao tasklist = new TaskListDaoImp(conn);
		return tasklist.getAlldeliver();
		
	}

	public List<TaskList_Information> selectTasklist(int taskType, int taskStatus, int substationId, int tasklistId,
			String clientName, String telNumber, Date finishDate, int pageNum) {
		Connection conn = DBUtil.getConn();
		TasklitDAO dao = new TasklitDAOImp(conn);
		return dao.selectTasklist(taskType, taskStatus, substationId, tasklistId, clientName, telNumber, finishDate,
				pageNum);
	}

	public int countPage(int taskType, int taskStatus, int substationId, int tasklistId, String clientName,
			String telNumber, Date finishDate) {
		Connection conn = DBUtil.getConn();
		TasklitDAO dao = new TasklitDAOImp(conn);
		return dao.countPage(taskType, taskStatus, substationId, tasklistId, clientName, telNumber, finishDate);
	}
	
	public void createTasklist(int orderId, int substationId) {
		Connection conn = DBUtil.getConn();
		TasklitDAO dao = new TasklitDAOImp(conn);
		dao.createTasklist(orderId, substationId);
	}
	
	public int distributeAuto(int orderId) {
		Connection conn = DBUtil.getConn();
		TasklitDAO dao = new TasklitDAOImp(conn);
		int substationId = dao.chooseSubstation(orderId);
		dao.createTasklist(orderId, substationId);
		return substationId;
	}
	
	public void distribute() {
		
		Connection conn = DBUtil.getConn();
		TasklitDAO dao = new TasklitDAOImp(conn);
		
		List<Integer> resultList = dao.getOrderId();
		for(Integer orderId:resultList) {
			int substationId = dao.chooseSubstation(orderId);
			dao.createTasklist(orderId, substationId);
		}
		
	}
	
	public List<Warehouse> getSubstation() {
		Connection conn = DBUtil.getConn();
		TasklitDAO dao = new TasklitDAOImp(conn);

		List<Warehouse> wh = dao.getSubstation();
		return wh;
		
	}
	
}
