package com.neuedu.model.service;



import java.sql.Connection;
import java.util.List;

import com.neuedu.model.dao.*;

import com.neuedu.model.po.*;
import com.neuedu.utils.DBUtil;


public class ResService {

	
	private ResService(){
		
	}
	
	private static ResService service=new ResService();
	public static ResService getInstance(){
		return service;
	}
	
	public void addRes(ReserveInfo res){
		Connection conn = DBUtil.getConn();

		DBUtil.beginTransaction(conn);
		try {
			ManageResDAO dao = new ManageResDAOImp(conn);
			dao.addRes(res);
			
			DBUtil.commit(conn);
		} catch (Exception e) {
			
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	public void updateRes(ReserveInfo res){
		Connection conn = DBUtil.getConn();

		DBUtil.beginTransaction(conn);
		try {
			ManageResDAO dao = new ManageResDAOImp(conn);
			dao.updateRes(res);
			
			DBUtil.commit(conn);
		} catch (Exception e) {
			
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	public boolean haveRes(ReserveInfo res){
		Connection conn = DBUtil.getConn();
		//��������
		ManageResDAO dao=new ManageResDAOImp(conn);
		return dao.haveRes(res);
	}
	public String acProname(int pro_id){
		Connection conn = DBUtil.getConn();
		//��������
		ManageResDAO dao=new ManageResDAOImp(conn);
		return dao.acProname(pro_id);
	}
	public String acWhname(int wh_id){
		Connection conn = DBUtil.getConn();
		//��������
		ManageResDAO dao=new ManageResDAOImp(conn);
		return dao.acWhname(wh_id);
}
	
	
	
}
