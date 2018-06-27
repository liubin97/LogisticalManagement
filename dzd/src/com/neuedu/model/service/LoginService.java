package com.neuedu.model.service;

import java.sql.Connection;

import com.neuedu.utils.DBUtil;

import com.neuedu.model.dao.*;
import com.neuedu.model.po.Manager;

public class LoginService {

	
	private LoginService(){
	}
	
	private static LoginService service = new LoginService();
	
	public static LoginService getInstance(){
		return service;
	}
	public Manager validateLogin(String admin,String password){
		Connection conn = DBUtil.getConn();
		//��������
		LoginDAO dao=new LoginDAOImp(conn);
		Manager m=dao.validateManager(admin,password);
		return m;
	}

}
