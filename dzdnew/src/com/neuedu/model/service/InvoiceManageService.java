package com.neuedu.model.service;

import java.sql.Connection;

import com.neuedu.model.dao.InvoiceSubDao;
import com.neuedu.model.dao.InvoiceSubDaoImp;
import com.neuedu.model.dao.ReceiptDao;
import com.neuedu.model.dao.ReceiptDaoImp;
import com.neuedu.model.po.Invoice_sub;
import com.neuedu.utils.DBUtil;

public class InvoiceManageService {

	private InvoiceManageService() {
		
	}
	
	private static InvoiceManageService service = new InvoiceManageService();
	
	public static InvoiceManageService getInstance(){
		return service;
	}
	
	public Invoice_sub getInvoice(int tasklist_id) {
		Connection conn = DBUtil.getConn();
		InvoiceSubDao invoice = new InvoiceSubDaoImp(conn);
		return invoice.getInvoice(tasklist_id);
	}
}
