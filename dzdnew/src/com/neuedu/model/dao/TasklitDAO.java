package com.neuedu.model.dao;

import java.sql.Date;
import java.util.List;

import com.neuedu.model.po.Order;
import com.neuedu.model.po.TaskList_Information;
import com.neuedu.model.po.Warehouse;

public interface TasklitDAO {

	public List<TaskList_Information> selectTasklist(int taskType, int taskStatus, int substationId, int tasklistId,
			String clientName, String telNumber, Date finishDate, int pageNum);

	public int countPage(int taskType, int taskStatus, int substationId, int tasklistId, String clientName,
			String telNumber, Date finishDate);

	public void createTasklist(int orderId, int substationId);
	
	public int chooseSubstation(int orderId);
	
	public List<Integer> getOrderId();
	
	public List<Warehouse> getSubstation();
}
