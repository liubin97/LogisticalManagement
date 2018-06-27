package com.neuedu.model.dao;

import java.sql.Connection;

import com.neuedu.model.po.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.neuedu.utils.DBUtil;

public class LoginDAOImp implements LoginDAO{
	
	Connection conn;
	
	public LoginDAOImp(Connection conn){
		this.conn = conn;
	}

	
	public Manager validateManager(String admin,String password) {
		// TODO 自动生成的方法存根
		Manager m=null;
		try {
			String  sql = "select manager_id,manager.admin,password,name,telephone,type,sub_id "
					+ "from manager left join manager_substation on manager.admin=manager_substation.admin "
					+ "where manager.admin=? and password=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, admin);
			ps.setString(2, password);
			System.out.println(admin+password);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				System.out.println("youle");
				m=new Manager();
				m.setAdmin(rs.getString("admin"));
				m.setManager_id(rs.getInt("manager_id"));
				m.setName(rs.getString("name"));
				m.setTelephone(rs.getString("telephone"));
				m.setType(rs.getInt("type"));
				m.setSub_id(rs.getInt("sub_id"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return m;
	}
	

	
}
