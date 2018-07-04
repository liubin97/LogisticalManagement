package com.neuedu.model.service;



import java.sql.Connection;
import java.util.List;

import com.neuedu.model.dao.ManageWhDAO;
import com.neuedu.model.dao.ManageWhDAOImp;

import com.neuedu.model.po.Warehouse;
import com.neuedu.utils.DBUtil;

public class WhService {

	
	private WhService(){
		
	}
	
	private static WhService service=new WhService();
	public static WhService getInstance(){
		return service;
	}
	
	public void addWarehouse(Warehouse wh){
		Connection conn = DBUtil.getConn();

		DBUtil.beginTransaction(conn);
		try {
			ManageWhDAO dao = new ManageWhDAOImp(conn);
			dao.addWarehouse(wh);
			
			DBUtil.commit(conn);
		} catch (Exception e) {
			
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
		
	}
	public boolean haveCenterWarehouse(){
		
		Connection conn = DBUtil.getConn();
		//��������
		ManageWhDAO dao=new ManageWhDAOImp(conn);
		if(dao.haveCenterWarehouse()==null){
			return false;
		}else{
			return true;
		}
	}
	public List<Warehouse> searchWarehouse(String name,int pageno){
		Connection conn = DBUtil.getConn();
		ManageWhDAO dao = new ManageWhDAOImp(conn);
		return dao.searchWarehouse(name,pageno);
		
	}
	public int calPagecount(String name){
		Connection conn = DBUtil.getConn();
		ManageWhDAO dao = new ManageWhDAOImp(conn);
		return dao.calPagecount(name);
		
	}
	public void deleteWarehouse(int whid,String operator){
		Connection conn = DBUtil.getConn();

		DBUtil.beginTransaction(conn);
		try {
			ManageWhDAO dao = new ManageWhDAOImp(conn);
			dao.deleteWarehouse(whid,operator);	
			DBUtil.commit(conn);
		} catch (Exception e) {
			
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	public boolean haveProductIn(int whid){
		Connection conn = DBUtil.getConn();
		//��������
		ManageWhDAO dao=new ManageWhDAOImp(conn);
		if(dao.haveProductIn(whid)!=0){
			return true;
		}else{
			return false;
		}
	}
	public void modifyWarehouse(Warehouse wh){
		Connection conn = DBUtil.getConn();

		DBUtil.beginTransaction(conn);
		try {
			ManageWhDAO dao = new ManageWhDAOImp(conn);
			dao.modifyWarehouse(wh);	
			DBUtil.commit(conn);
		} catch (Exception e) {
			
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}

	

}
