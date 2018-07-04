package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.po.Order;
import com.neuedu.model.po.TaskList_Information;
import com.neuedu.model.po.Tasklist;
import com.neuedu.model.po.Warehouse;
import com.neuedu.utils.DateUtil;

public class TasklitDAOImp implements TasklitDAO {

	Connection conn;

	public TasklitDAOImp(Connection conn) {
		this.conn = conn;
	}

	public List<TaskList_Information> selectTasklist(int taskType, int taskStatus, int substationId, int tasklistId,
			String clientName, String telNumber, Date finishDate, int pageNum) {
		// TODO Auto-generated method stub
		List<TaskList_Information> list = new ArrayList<TaskList_Information>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select `order`.deliver_addr, `order`.consignee,`order`.finish_date,"
				+ " `order`.is_invoice,task_list.substation_id,task_list.task_list_id,`order`.type, task_list.task_status, `order`.order_id, "
				+ " `order`.consignee_tel from  task_list join `order` on task_list.order_id = `order`.order_id  where 1=1");
		if (taskType != 0) {
			sbf.append(" and `order`.type = ?");
		}
		if (taskStatus != 0) {
			if(taskStatus == 1) {
				sbf.append(" and (task_list.task_status = 1 or task_list.task_status = 7) ");
			}
			if(taskStatus == 2) {
				sbf.append(" and (task_list.task_status = 4 or task_list.task_status = 8) ");
			}
			if(taskStatus == 3) {
				sbf.append(" and (task_list.task_status = 5) ");
			}
			if(taskStatus == 4) {
				sbf.append(" and (task_list.task_status = 6 or task_list.task_status = 11) ");
			}
		}else {
			sbf.append(" and (task_list.task_status = 1 or task_list.task_status = 7 or task_list.task_status = 4 or task_list.task_status = 8 or task_list.task_status = 5 or task_list.task_status = 6 or task_list.task_status = 11 )");
		}
		if (substationId != 0) {
			sbf.append(" and task_list.substation_id = ?");
		}
		if (tasklistId != 0) {
			sbf.append(" and task_list.task_list_id = ?");
		}
		if (clientName != null && !"".equals(clientName)) {
			sbf.append(" and `order`.consignee = ?");
		}
		if (telNumber != null && !"".equals(telNumber)) {
			sbf.append(" and `order`.consignee_tel = ?");
		}
		if (finishDate != null) {
			sbf.append(" and `order`.finish_date=? ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(" select a.* from ( " + sbf.toString() + " )a limit ? , ? ");
			int index = 1;
			if (taskType != 0) {
				ps.setInt(index, taskType);
				index++;
			}
//			if (taskStatus != 0) {
//				ps.setInt(index, taskStatus);
//				index++;
//			}
			if (substationId != 0) {
				ps.setInt(index, substationId);
				index++;
			}
			if (tasklistId != 0) {
				ps.setInt(index, tasklistId);
				index++;
			}
			if (clientName != null && !"".equals(clientName)) {
				ps.setString(index, clientName);
				index++;
			}
			if (telNumber != null && !"".equals(telNumber)) {
				ps.setString(index, telNumber);
				index++;
			}
			if (finishDate != null) {
				ps.setDate(index, finishDate);
				index++;
			}
			ps.setInt(index, (pageNum - 1) * 5);
			index++;
			ps.setInt(index, 5);
			index++;

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaskList_Information tasklist = new TaskList_Information();

				tasklist.setClientName(rs.getString("consignee"));
				tasklist.setClientTel(rs.getString("consignee_tel"));
				tasklist.setFinishDate(rs.getDate("finish_date"));
				tasklist.setSubstationId(rs.getInt("substation_id"));
				tasklist.setTasklistId(rs.getInt("task_list_id"));
				tasklist.setTaskStatus(rs.getInt("task_status"));
				tasklist.setTaskType(rs.getInt("type"));

				list.add(tasklist);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public int countPage(int taskType, int taskStatus, int substationId, int tasklistId, String clientName,
			String telNumber, Date finishDate) {
		// TODO Auto-generated method stub
		int count = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append(" ( select `order`.deliver_addr, `order`.consignee,`order`.finish_date,"
				+ " `order`.is_invoice,task_list.substation_id,task_list.task_list_id,`order`.type, task_list.task_status, `order`.order_id, "
				+ " `order`.consignee_tel from  task_list join `order` on task_list.order_id = `order`.order_id  where 1=1");
		if (taskType != 0) {
			sbf.append(" and `order`.type = ?");
		}
		if (taskStatus != 0) {
			sbf.append(" and task_list.task_status = ?");
		}
		if (substationId != 0) {
			sbf.append(" and task_list.substation_id = ?");
		}
		if (tasklistId != 0) {
			sbf.append(" and task_list.task_list_id = ?");
		}
		if (clientName != null && !"".equals(clientName)) {
			sbf.append(" and `order`.consignee = ?");
		}
		if (telNumber != null && !"".equals(telNumber)) {
			sbf.append(" and `order`.consignee_tel = ?");
		}
		if (finishDate != null) {
			sbf.append(" and `order`.finish_date=? ");
		}
		sbf.append(" )a");
		try {
			PreparedStatement ps = conn.prepareStatement("select count(*) cc from "+sbf.toString());
			int index = 1;
			if (taskType != 0) {
				ps.setInt(index, taskType);
				index++;
			}
			if (taskStatus != 0) {
				ps.setInt(index, taskStatus);
				index++;
			}
			if (substationId != 0) {
				ps.setInt(index, substationId);
				index++;
			}
			if (tasklistId != 0) {
				ps.setInt(index, tasklistId);
				index++;
			}
			if (clientName != null && !"".equals(clientName)) {
				ps.setString(index, clientName);
				index++;
			}
			if (telNumber != null && !"".equals(telNumber)) {
				ps.setString(index, telNumber);
				index++;
			}
			if (finishDate != null) {
				ps.setDate(index, finishDate);
				index++;
			}

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("cc");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
		}

		int pagecount = 0;
		if (count % 5 == 0) {
			pagecount = count / 5;
		} else {
			pagecount = count / 5 + 1;
		}
		return pagecount;

	}

	public void createTasklist(int orderId, int substationId) {
		// TODO Auto-generated method stub
		Tasklist tasklist = new Tasklist();
		int tag = 0;
		tasklist.initialize();
		tasklist.setOrder_id(orderId);
		tasklist.setSubstation_id(substationId);
		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("select * from `order` where `order`.order_id = ? ");
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tag = rs.getInt("type");
			}
			if (tag == 1) {
				tasklist.setTask_status(1);
			}
			if (tag == 3) {
				tasklist.setTask_status(7);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			StringBuffer sbf1 = new StringBuffer("");
			sbf1.append(
					"insert into task_list (substation_id, order_id, task_status,  settle_status, task_list_create_date) values (?,?,?,?,now())");
			PreparedStatement ps1 = conn.prepareStatement(sbf1.toString());
			ps1.setInt(1, tasklist.getSubstation_id());
			ps1.setInt(2, tasklist.getOrder_id());
			ps1.setInt(3, tasklist.getTask_status());
			ps1.setInt(4, tasklist.getSettle_status());
			int flag = ps1.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("update `order` set status = 3  where order_id = ? ");
			PreparedStatement ps;
			ps = conn.prepareStatement(sbf.toString());
			ps.setInt(1, orderId);
			int flag = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public int chooseSubstation(int orderId) {
		// TODO Auto-generated method stub

		int finalWhId = 0;
		int count;
		int allCount = 1000;
//		System.out.println("choosestation location");
		StringBuffer sbf = new StringBuffer("");
		List<Integer> idList = new ArrayList<Integer>();
		sbf.append("select deliver_addr from `order` where `order`.order_id = ?");
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sbf.toString());
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String deliverAddress = rs.getString("deliver_addr");

			StringBuffer sbf1 = new StringBuffer("");
			sbf1.append("select wh_id, wh_addr from warehouse where wh_id<>1 ");
			PreparedStatement ps1;

			ps1 = conn.prepareStatement(sbf1.toString());
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				String wh_name = rs1.getString("wh_addr");
				if (deliverAddress.contains(wh_name)) {
					int warehouse_id = rs1.getInt("wh_id");
					System.out.println(warehouse_id);
					idList.add(warehouse_id);
				}
			}
			
			//如果没有找到地址中有相同字段的分站，则选取所有分站进行当前工作量比较
			if(idList.size()==0) {
				rs1 = ps1.executeQuery();
				while(rs1.next()) {
					idList.add(rs1.getInt("wh_id"));
				}
			}
			

			for (Integer wh_id : idList) {
//				System.out.println(wh_id);
				sbf = new StringBuffer("");
				sbf.append(
						"select count(*) cc from task_list where substation_id = ? and task_status <> 6 and task_status <>11");
				ps = conn.prepareStatement(sbf.toString());
				ps.setInt(1, wh_id);
				rs = ps.executeQuery();
				if (rs.next()) {
					count = rs.getInt("cc");
				}else {
					count = 100;
				}
				if(count < allCount) {
					allCount = count;
					finalWhId = wh_id;
				}
			}			


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(finalWhId);
		return finalWhId;

	}

	public List<Integer> getOrderId() {
		// TODO Auto-generated method stub
		List<Integer> resultList = new ArrayList<Integer>();

		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from (select * from `order` where status = 2)a where datediff(now(),`create _date`) >= 10 or datediff(finish_date , now() ) <= 0");
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sbf.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int tempOrderId = rs.getInt("order_id");
				resultList.add(tempOrderId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return resultList;
	}

	@Override
	public List<Warehouse> getSubstation() {
		// TODO Auto-generated method stub
		
		StringBuffer sbf = new StringBuffer("");
		List<Warehouse> wh = new ArrayList<Warehouse>();
		Warehouse tempwh = null;
		sbf.append("select * from warehouse where wh_id<>1");
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sbf.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tempwh=new Warehouse();
				tempwh.setWh_id(rs.getInt("wh_id"));
				tempwh.setWh_name(rs.getString("wh_name"));
				wh.add(tempwh);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wh;

		
		
		
	}

}
