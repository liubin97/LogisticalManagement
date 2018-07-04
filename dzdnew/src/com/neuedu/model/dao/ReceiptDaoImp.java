package com.neuedu.model.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.neuedu.model.po.*;
import com.neuedu.utils.DBUtil;
import com.neuedu.utils.DateUtil;

//回执DAO实现
public class ReceiptDaoImp implements ReceiptDao {
	
	Connection conn;
	
	public ReceiptDaoImp(Connection conn){
		this.conn = conn;
	}
	
	public void EntryReceipt(Receipt rec) {
		PreparedStatement ps = null;
		
		java.util.Date now=new java.util.Date();    
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式    
		String operator_date = dateFormat.format(now);
//		System.out.println(operator_date);
		try {
			//将新的回执插入数据库
			ps = conn.prepareStatement("insert into receipt (satisfaction, note, task_list_id) values(?,?,?)");
			
			ps.setString(1, rec.getClientSatis());
			ps.setString(2, rec.getReceiptnote());
			ps.setInt(3, rec.getTasklist_id());
			ps.executeUpdate();
			
			//更改任务单订单状态
			ps = conn.prepareStatement(" update `order`,task_list set order.status = ? ,task_list.task_status = ? ,`order`.operatorDate = ? "
					+ "where `order`.order_id = task_list.order_id and task_list.task_list_id = ?");
			ps.setInt(1,4);
			ps.setInt(2, 6);
			ps.setDate(3, DateUtil.strToDate(operator_date));
			ps.setInt(4, rec.getTasklist_id());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
	}
}
