package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.po.*;
import com.neuedu.utils.DBUtil;
//任务单DAO实现
public class TaskListDaoImp implements TaskListDao{
	Connection conn;
	
	public TaskListDaoImp(Connection conn){
		this.conn = conn;
	}
	//查出一共可以分几页
	
	public int selectPageCount(int substation_id,Date begintime,int taskstate,int tasktype){
		int count = 0;
		int flag=0;
		/*送货 
		 * 未分配3 
		 * 已分配5
		 *  已完成 6    
		 *   退货
		 *   未分配7 
		 *   已分配8 
		 *   */
		if(tasktype==1&&taskstate==1) {
			flag=3;	
		}
		if(tasktype==1&&taskstate==2) {
			flag=5;
		}
		if(tasktype==1&&taskstate==3) {
			flag=6;
		}
		if(tasktype==3&&taskstate==1) {
			flag=7;
		}
		if(tasktype==3&&taskstate==2) {
			flag=8;
		}
		if(tasktype==3&&taskstate==3) {
			flag=11;
		}
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select count(*) cc  from task_list join `order` on task_list.order_id = `order`.order_id  where ");
		sbf.append(" task_list.substation_id = ? ");
		sbf.append("and task_list.task_status = ? ");
		sbf.append("and `order`.type = ? ");
		sbf.append("and task_list.task_list_create_date = ? ");
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			ps.setInt(1, substation_id);
			ps.setInt(2, flag);
			ps.setInt(3, tasktype);
			ps.setDate(4, begintime);
			//ִ��
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("cc");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int pagecount = 0;
		if(count%5==0){
			pagecount = count/5;
		}else{
			pagecount = count/5+1;
		}
		return pagecount;
		
	}
	
	public List<TaskList_allInformation> selectTaskList(int substation_id,Date begintime,int taskstate,int tasktype,int pageNum) {
//		System.out.println(substation_id);
//		System.out.println(begintime.toString());
//		System.out.println(taskstate);
//		System.out.println(tasktype);
//		System.out.println(pageNum);
		List<TaskList_allInformation> list = new ArrayList<TaskList_allInformation>();
		int flag=0;
		/*3 4  6     7 8 */
		if(tasktype==1&&taskstate==1) {
			flag=3;	
		}
		if(tasktype==1&&taskstate==2) {
			flag=5;
		}
		if(tasktype==1&&taskstate==3) {
			flag=6;
		}
		if(tasktype==3&&taskstate==1) {
			flag=7;
		}
		if(tasktype==3&&taskstate==2) {
			flag=8;
		}
		if(tasktype==3&&taskstate==3) {
			flag=11;
		}
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select `order`.deliver_addr, `order`.consignee,task_list.task_list_create_date,order.consignee_tel,"
				+ "`order`.is_invoice,task_list.substation_id,task_list.task_list_id,`order`.type, task_list.task_status, `order`.order_id "
				+ "from  task_list join `order` on task_list.order_id = `order`.order_id  where ");
		sbf.append("task_list.substation_id = ? ");
		sbf.append("and task_list.task_status = ?  ");
		sbf.append("and `order`.type = ?  ");
		sbf.append("and task_list.task_list_create_date = ?  ");
		System.out.println(flag);
		System.out.println(tasktype);
		try {
			PreparedStatement ps = conn.prepareStatement( " select a.* from ( "
					+ sbf.toString() 
					+ " )a limit ? , ? ");
			
//			PreparedStatement ps = conn.prepareStatement( " select b.* from ( "
//					+ " select a.*,rownum rw from ( "
//					+ sbf.toString() +  "  ) a "
//					+ " where rownum<= "+ (5*pageNum) +" ) b  "
//					+ " where rw>"+ 5*(pageNum-1));

			ps.setInt(1, substation_id);
			ps.setInt(2, flag);
			ps.setInt(3, tasktype);
			ps.setDate(4, begintime);
			ps.setInt(5, 5*(pageNum-1));
			ps.setInt(6, 5);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				TaskList_allInformation tasklist  = new TaskList_allInformation();
				
				tasklist.setClientAddress(rs.getString("deliver_addr"));
				tasklist.setClientTel(rs.getString("consignee_tel"));
				//System.out.println(tasklist.getClientAddress());
				tasklist.setClientName(rs.getString("consignee"));
				tasklist.setCreatDate(rs.getDate("task_list_create_date"));
				tasklist.setIs_invoice(rs.getInt("is_invoice"));
				tasklist.setOrder_id(rs.getInt("order_id"));
				tasklist.setSubstation_id(rs.getInt("substation_id"));
				tasklist.setTasklist_id(rs.getInt("task_list_id"));
				if(rs.getInt("task_status")==3||rs.getInt("task_status")==7) {
					tasklist.setTaskState(1);
				}
				if(rs.getInt("task_status")==5||rs.getInt("task_status")==8) {
					tasklist.setTaskState(2);
				}
				if(rs.getInt("task_status")==6||rs.getInt("task_status")==11) {
					tasklist.setTaskState(3);
				}
				tasklist.setTaskType(rs.getInt("type"));
				
				if(tasklist.getTaskState()==5||tasklist.getTaskState()==8) {
					getTaskListDeliver(tasklist.getDeliveryStaff_id(),tasklist);
				}
				
				list.add(tasklist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void AllocateDeliver(int taskType,int tasklist_id,int deliver_id) {
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement(" update task_list set delivery_staff_id = ?,task_status = ? where task_list_id = ?");
			ps.setInt(1, deliver_id);
			if(taskType==1) {
				ps.setInt(2, 4);
			}else {
				ps.setInt(2, 8);
			}
			ps.setInt(3, tasklist_id);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getTaskListDeliver(int delivery_staff_id,TaskList_allInformation tasklist) {
		try {
			PreparedStatement ps = conn.prepareStatement("select * from delivery_staff where delivery_staff.delivery_staff_id = ?");
			ps.setInt(1, delivery_staff_id);
			System.out.println(delivery_staff_id);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			tasklist.setDelivery_staff_tel(rs.getInt("delivery_staff_telephone"));
			tasklist.setDeliveryStaffName(rs.getString("delivery_staff_name"));
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public TaskList_allInformation getTaskListById(int tasklist_id) {
		TaskList_allInformation tasklist = new TaskList_allInformation();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select `order`.deliver_addr, `order`.consignee , task_list.task_list_create_date , `order`.consignee_tel , "
				+ "`order`.is_invoice , task_list.substation_id , task_list.task_list_id , `order`.type , task_list.task_status, `order`.order_id "
				+ "from  task_list join `order` on task_list.order_id = `order`.order_id  where ");
		sbf.append(" task_list.task_list_id = ? ");
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			ps.setInt(1, tasklist_id);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			tasklist.setClientAddress(rs.getString("deliver_addr"));
			tasklist.setClientTel(rs.getString("consignee_tel"));
			tasklist.setClientName(rs.getString("consignee"));
			tasklist.setCreatDate(rs.getDate("task_list_create_date"));
			tasklist.setIs_invoice(rs.getInt("is_invoice"));
			tasklist.setOrder_id(rs.getInt("order_id"));
			tasklist.setSubstation_id(rs.getInt("substation_id"));
			tasklist.setTasklist_id(rs.getInt("task_list_id"));
			
			if(rs.getInt("task_status")==3||rs.getInt("task_status")==7) {
				tasklist.setTaskState(1);
			}
			if(rs.getInt("task_status")==5||rs.getInt("task_status")==8) {
				tasklist.setTaskState(2);
			}
			if(rs.getInt("task_status")==6||rs.getInt("task_status")==11) {
				tasklist.setTaskState(3);
			}
			tasklist.setTaskType(rs.getInt("type"));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return tasklist;
	}
	
	
	public List<delivery_staff> getAlldeliver(){
		List<delivery_staff> list = new ArrayList<delivery_staff>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from delivery_staff ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				delivery_staff staff = new delivery_staff();
				staff.setDelivery_staff_id(rs.getInt("delivery_staff_id"));
				staff.setDelivery_staff_name(rs.getString("delivery_staff_name"));
				staff.setDelivery_staff_tel(rs.getInt("delivery_staff_telephone"));
				
				list.add(staff);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}





