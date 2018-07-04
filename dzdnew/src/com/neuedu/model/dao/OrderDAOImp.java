package com.neuedu.model.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.po.Order;

public class OrderDAOImp implements OrderDAO {

	Connection conn;
	
	public OrderDAOImp(Connection conn){
		this.conn = conn;
	}
	
	
	public List<Order> selectOrder(int orderType, int orderStatement, Date finishDate, int pageNum){
		List<Order> list = new ArrayList<Order>();
		StringBuffer sbf = new StringBuffer(" select * from `order` where 1=1 and status = 2 ");

//		sbf.append(" select count(*) cc from `order` where 1=1 ");
		if (orderType != 0) {
			sbf.append(" and type=? ");
		}else{
			sbf.append(" and (type=1 or type=3) ");
		}
		
		if(orderStatement != 0){
			sbf.append(" and status=? ");
		}if(finishDate !=null) {
			sbf.append(" and finish_date=? ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement( " select a.* from ( "
					+ sbf.toString() 
					+ " )a limit ? , ? ");
			int index=1;
			if(orderType != 0){
				ps.setInt(index, orderType);
				index++;
			}
			if(orderStatement != 0){
				ps.setInt(index, orderStatement);
				index++;

			}
			if(finishDate !=null) {
				ps.setDate(index, finishDate);
				index++;
			}
			ps.setInt(index, (pageNum-1)*5);
			index++;
			ps.setInt(index, 5);
			index++;

			//新建order类给其添加数据
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setOrder_id(rs.getInt("order_id"));
				order.setStatus(rs.getInt("status"));
				order.setType(rs.getInt("type"));
				order.setFinish_date(rs.getDate("finish_date"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}


	public int countPage(int orderType, int orderStatement, Date finishDate) {
		// TODO Auto-generated method stub
		int count = 0;
		StringBuffer sbf = new StringBuffer(" select count(*) cc from `order` where 1=1 and status = 2");
//		sbf.append(" select count(*) cc from `order` where 1=1 ");
		if (orderType != 0) {
			sbf.append(" and type=? ");
		}if(orderStatement != 0){
			sbf.append(" and status=? ");
		}if(finishDate !=null) {
			sbf.append(" and finish_date=? ");
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if(orderType != 0){
				ps.setInt(index, orderType);
				index++;
			}
			if(orderStatement != 0){
				ps.setInt(index, orderStatement);
				index++;
			}
			if(finishDate !=null) {
				ps.setDate(index, finishDate);
				index++;
			}
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("cc");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	
}
