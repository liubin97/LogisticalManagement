package com.neuedu.model.service;

import java.sql.Connection;
import java.util.List;

import com.neuedu.*;
import com.neuedu.model.dao.ManageWhDAO;
import com.neuedu.model.dao.ManageWhDAOImp;
import com.neuedu.model.dao.SearchInfoDAO;
import com.neuedu.model.dao.SearchInfoDAOImp;
import com.neuedu.model.po.HotProduct;
import com.neuedu.model.po.Implementation;
import com.neuedu.model.po.InOutList;
import com.neuedu.model.po.ResNumInfo;
import com.neuedu.model.po.Satisfaction;
import com.neuedu.model.po.Warehouse;
import com.neuedu.utils.DBUtil;

public class InfoService {

	private InfoService(){
	}
	
	private static InfoService service = new InfoService();
	
	public static InfoService getInstance(){
		return service;
	}
	
	public List<ResNumInfo> searchResNum(String proname,int pageno){
		Connection conn = DBUtil.getConn();
		SearchInfoDAO dao = new SearchInfoDAOImp(conn);
		return dao.searchResNum(proname, pageno);
		
	}
	public int calPagecountRes(String proname){
		Connection conn = DBUtil.getConn();
		SearchInfoDAO dao = new SearchInfoDAOImp(conn);
		return dao.calPagecountRes(proname);
		
	}
	public List<InOutList> searchInOut(String startdate,String enddate,int proid,String type,int pageno){
		Connection conn = DBUtil.getConn();
		SearchInfoDAO dao = new SearchInfoDAOImp(conn);
		return dao.searchInOut(startdate, enddate, proid, type, pageno);
	}
	
	public int calPagecountInOut(String startdate,String enddate,int proid,String type){
		Connection conn = DBUtil.getConn();
		SearchInfoDAO dao = new SearchInfoDAOImp(conn);
		return dao.calPagecountInOut(startdate, enddate, proid, type);
		
	}
	public List<HotProduct> searchHotProduct(String startdate,String enddate){
		Connection conn = DBUtil.getConn();
		SearchInfoDAO dao = new SearchInfoDAOImp(conn);
		return dao.searchHotProduct(startdate, enddate);
	}
	public List<Implementation> searchImplement(String startdate,String enddate){
		Connection conn = DBUtil.getConn();
		SearchInfoDAO dao = new SearchInfoDAOImp(conn);
		return dao.searchImplement(startdate, enddate);
	}
	public List<Satisfaction> searchSatisfaction(String startdate,String enddate,int pageno){
		Connection conn = DBUtil.getConn();
		SearchInfoDAO dao = new SearchInfoDAOImp(conn);
		return dao.searchSatisfaction(startdate, enddate, pageno);
	}
	public int calPagecountSf(String startdate,String enddate){
		Connection conn = DBUtil.getConn();
		SearchInfoDAO dao = new SearchInfoDAOImp(conn);
		return dao.calPagecountSf(startdate, enddate);
	}
	
	
}
