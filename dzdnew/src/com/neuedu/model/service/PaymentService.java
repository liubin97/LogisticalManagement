package com.neuedu.model.service;

import java.sql.Connection;
import java.util.List;

import com.neuedu.model.dao.PaymentDao;
import com.neuedu.model.dao.PaymentDaoImp;
import com.neuedu.model.po.PaymentResult;
import com.neuedu.utils.DBUtil;

public class PaymentService {
	
	private PaymentService() {
		
	}
	
	private static PaymentService service = new PaymentService();
	
	public static PaymentService getInstance(){
		return service;
	}
	
	public List<PaymentResult> getPayment(String begintime,String endtime,int substation_id,int pageNum){
		Connection conn = DBUtil.getConn();
		PaymentDao pd = new PaymentDaoImp(conn);
		return pd.getPayment(begintime, endtime, substation_id,pageNum);
	}
	
	public int selectPageCount(String begintime,String endtime,int substation_id) {
		Connection conn = DBUtil.getConn();
		PaymentDao pd = new PaymentDaoImp(conn);
		return pd.selectPageCount(begintime, endtime, substation_id);
	}
}
