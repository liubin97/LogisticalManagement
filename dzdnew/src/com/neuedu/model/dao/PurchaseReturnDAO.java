package com.neuedu.model.dao;

import java.sql.Connection;
import java.util.List;

import com.neuedu.model.po.PurchaseMsg;
import com.neuedu.model.po.PurchaseSupplier;
import com.neuedu.model.po.ReturnSupplier;

public interface PurchaseReturnDAO {
	
	
	List<PurchaseMsg> searchWarn(String proname,int pageno);
	int calPagecountWarn(String proname);
	List<PurchaseMsg> searchOutofstock(String proname,int pageno);
	int calPagecountOutofstock(String proname);
	int newPSwarn(PurchaseSupplier psr);
	int newPSstock(PurchaseSupplier psr);
	PurchaseSupplier acPs(int prod_id,int pnum,String do_date);
	PurchaseSupplier acPs1(int prod_id, int order_id, int pnum, String do_date);
	List<ReturnSupplier> searchReturn(int supplier_id,String starttime,String endtime,int prod_id);
	void returnSupplier(ReturnSupplier rsr);
}
