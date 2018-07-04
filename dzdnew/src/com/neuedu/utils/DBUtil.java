package com.neuedu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class DBUtil {
	//锟斤拷取锟斤拷锟捷匡拷锟斤拷锟斤拷锟?
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dzdnew?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "root", "0000");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//锟斤拷锟斤拷锟斤拷锟斤拷
	public static void beginTransaction(Connection conn){
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//锟结交锟斤拷锟斤拷
	public static void commit(Connection conn){
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//锟截癸拷
	public static void rollback(Connection conn){
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//锟截憋拷锟斤拷锟斤拷
	public static void closeConn(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//锟截憋拷PS
	public static void closePS(PreparedStatement ps){
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static boolean testNumber(String number){
		boolean flag = false;
		Pattern pattern = Pattern.compile("^[1-9]{1}[0-9]{0,7}$");
		if(pattern.matcher(number).matches()){
			flag = true;
		}else{
			return flag;
		}
		return flag;
		
	}
}

