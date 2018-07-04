package com.neuedu.model.service;
import java.sql.Connection;
import java.util.List;

import com.neuedu.model.dao.*;

import com.neuedu.model.po.*;
import com.neuedu.utils.DBUtil;

public class PRService {


	private PRService(){
		
	}
	
	private static PRService service=new PRService();
	public static PRService getInstance(){
		return service;
	}
	
	
	public List<PurchaseMsg> searchWarn(String proname,int pageno){
		Connection conn = DBUtil.getConn();
		PurchaseReturnDAO dao = new PurchaseReturnDAOImp(conn);
		return dao.searchWarn(proname, pageno);
		
	}
	public int calPagecountWarn(String proname){
		Connection conn = DBUtil.getConn();
		PurchaseReturnDAO dao = new PurchaseReturnDAOImp(conn);
		return dao.calPagecountWarn(proname);
		
	}
	public List<PurchaseMsg> searchOutofstock(String proname,int pageno){
		Connection conn = DBUtil.getConn();
		PurchaseReturnDAO dao = new PurchaseReturnDAOImp(conn);
		return dao.searchOutofstock(proname, pageno);
		
	}
	public int calPagecountOutofstock(String proname){
		Connection conn = DBUtil.getConn();
		PurchaseReturnDAO dao = new PurchaseReturnDAOImp(conn);
		return dao.calPagecountOutofstock(proname);
		
	}
	public int newPSwarn(PurchaseSupplier psr){
		Connection conn = DBUtil.getConn();
		PurchaseReturnDAO dao = new PurchaseReturnDAOImp(conn);
		return dao.newPSwarn(psr);
	}
	public int newPSstock(PurchaseSupplier psr){
		Connection conn = DBUtil.getConn();
		PurchaseReturnDAO dao = new PurchaseReturnDAOImp(conn);
		return dao.newPSstock(psr);
	}
	public PurchaseSupplier asPs(int prod_id,int pnum,String do_date){
		Connection conn = DBUtil.getConn();
		PurchaseReturnDAO dao = new PurchaseReturnDAOImp(conn);
		return dao.acPs(prod_id, pnum, do_date);
		
	}


	public PurchaseSupplier asPs1(int prod_id, int order_id, int pnum, String do_date) {
		// TODO 自动生成的方法存根
		Connection conn = DBUtil.getConn();
		PurchaseReturnDAO dao = new PurchaseReturnDAOImp(conn);
		return dao.acPs1(prod_id,order_id, pnum, do_date);
	}
	public void returnSupplier(ReturnSupplier rsr){
		Connection conn = DBUtil.getConn();

		DBUtil.beginTransaction(conn);
		try {
			PurchaseReturnDAO dao = new PurchaseReturnDAOImp(conn);
			dao.returnSupplier(rsr);
			
			DBUtil.commit(conn);
		} catch (Exception e) {
			
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	public List<ReturnSupplier> searchReturn(int supplier_id,String starttime,String endtime,int prod_id){
		Connection conn = DBUtil.getConn();
		PurchaseReturnDAO dao = new PurchaseReturnDAOImp(conn);
		return dao.searchReturn(supplier_id, starttime, endtime, prod_id);
	
	}
	
}
