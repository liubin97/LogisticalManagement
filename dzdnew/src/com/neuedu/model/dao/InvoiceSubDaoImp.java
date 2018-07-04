package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.model.po.Invoice_sub;
//发票DAO实现
public class InvoiceSubDaoImp implements InvoiceSubDao{
	Connection conn;
	
	public InvoiceSubDaoImp(Connection conn){
		this.conn=conn;
	}
	
	
	//根据任务单号查询订单号在查询发票信息
	public Invoice_sub getInvoice(int tasklist_id) {
		Invoice_sub invoice = new Invoice_sub();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select invoice.lost_invalidated_date,invoice.invoice_number,"
					+ " invoice.invoice_amount,invoice.invoice_status,invoice.substation_name,invoice.use_date "
					+ " from invoice,task_list where task_list.order_id = invoice.order_number and task_list.task_list_id = ? ");
			ps.setInt(1, tasklist_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				invoice.setInvaliddDate(rs.getString("lost_invalidated_date"));
				invoice.setInvoice_id(rs.getInt("invoice_number"));
				invoice.setInvoice_price(rs.getDouble("invoice_amount"));
				if(rs.getInt("invoice_status")==0) {
					invoice.setInvoiceStatus("正常");
				}
				if(rs.getInt("invoice_status")==1) {
					invoice.setInvoiceStatus("已领用");
				}
				if(rs.getInt("invoice_status")==4) {
					invoice.setInvoiceStatus("作废");
				}
				invoice.setSubstation(rs.getString("substation_name"));
				invoice.setUseDate(rs.getDate("use_date").toString());
				
			}else {
				return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return invoice;
	}
}
