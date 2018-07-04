package com.neuedu.model.service;

import java.sql.Connection;

import com.neuedu.model.dao.ReceiptDao;
import com.neuedu.model.dao.ReceiptDaoImp;
import com.neuedu.model.po.Receipt;
import com.neuedu.utils.DBUtil;

public class ReceiptService {
	
	private ReceiptService() {
		
	}
	
	private static ReceiptService service = new ReceiptService();
	
	public static ReceiptService getInstance(){
		return service;
	}
	
	public void EntryReceipt(Receipt rec) {
		Connection conn = DBUtil.getConn();
		ReceiptDao receiptdao = new ReceiptDaoImp(conn);
		receiptdao.EntryReceipt(rec);
	}
}
