package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.PaymentResult;
//缴款查询的DAO接口
public interface PaymentDao {
	public List<PaymentResult> getPayment(String begintime,String endtime,int substation_id,int pageNum);
	public int selectPageCount(String begintime,String endtime,int substation_id);
}
