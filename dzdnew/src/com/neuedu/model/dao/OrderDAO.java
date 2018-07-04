package com.neuedu.model.dao;

import java.sql.Date;
import java.util.List;

import com.neuedu.model.po.*;


public interface OrderDAO {

	public List<Order> selectOrder(int orderType, int orderStatement, Date finishDate, int pageNum);
	
	public int countPage(int orderType, int orderStatement, Date finishDate);

}
