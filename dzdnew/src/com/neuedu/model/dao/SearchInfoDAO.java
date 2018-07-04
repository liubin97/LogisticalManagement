package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.*;

public interface SearchInfoDAO {
	List<ResNumInfo> searchResNum(String proname,int pageno);
	int calPagecountRes(String proname);
	public List<InOutList> searchInOut(String startdate,String enddate,int proid,String type,int pageno);
	int calPagecountInOut(String startdate,String enddate,int proid,String type);
	List<HotProduct> searchHotProduct(String startdate,String enddate);
	List<Implementation> searchImplement(String startdate,String enddate);
	List<Satisfaction> searchSatisfaction(String startdate,String enddate,int pageno);
	int calPagecountSf(String startdate,String enddate);
	/*List<Product> searchHotProduct();
	List<Implementation> searchImplement();
	List<Satisfaction> searchSatisfaction();*/
}
