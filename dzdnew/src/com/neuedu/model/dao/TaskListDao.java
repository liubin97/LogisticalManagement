package com.neuedu.model.dao;

import java.sql.Date;
import java.util.List;

import com.neuedu.model.po.TaskList_allInformation;
import com.neuedu.model.po.delivery_staff;

//任务单DAO
public interface TaskListDao {
	public int selectPageCount(int substation_id,Date begintime,int taskstate,int tasktype);
	public List<TaskList_allInformation> selectTaskList(int substation_id,Date begintime,int taskstate,int tasktype,int pageNum);
	public TaskList_allInformation getTaskListById(int tasklist_id);
	public List<delivery_staff> getAlldeliver();
	public void AllocateDeliver(int taskType,int tasklist_id,int deliver_id);
}
