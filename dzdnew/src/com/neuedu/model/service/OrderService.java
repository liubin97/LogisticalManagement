package com.neuedu.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.neuedu.utils.*;
import com.neuedu.model.dao.OrderDAO;
import com.neuedu.model.dao.OrderDAOImp;
import com.neuedu.model.po.*;


public class OrderService {
	private OrderService(){
	}
	
	private static OrderService service = new OrderService();

	
	public static OrderService getInstance(){
		return service;
	}
	
	
	public List<Order>  selectOrder(int orderType, int taskStatement, Date finishDate, int pageNum){
		Connection conn = DBUtil.getConn();
		OrderDAO dao = new OrderDAOImp(conn);
		return dao.selectOrder(orderType, taskStatement, finishDate, pageNum);
	}
	
	public int countPage(int orderType, int orderStatement, Date finishDate) {
		Connection conn = DBUtil.getConn();
		OrderDAO dao = new OrderDAOImp(conn);
		return dao.countPage(orderType, orderStatement, finishDate);
	}
	
	
	
}
