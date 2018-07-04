package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.Invoice;
import com.neuedu.model.po.OrderInfor;
import com.neuedu.model.po.SettleListInfor;
import com.neuedu.model.po.SettleSubstationInfor;
import com.neuedu.model.po.SettleSupplyInfor;
import com.neuedu.model.po.Settlement;

public interface FinanceDAO {
	
	public List<SettleSupplyInfor> inquireSuppliersUnsettled(Settlement settlement, int pageSize, int pageNum);
	public List<SettleListInfor> inquireSuppliersSettled(Settlement settlement, int pageSize, int pageNum); 

	public void confirmSuppliers(String[] idIn, String[] idOut);
	public void changeSupOutStatus(String idOut);
	public void changeSupInStatus(String idIn);
	public List<OrderInfor> getOrderInfor(Settlement settlement, int pageSize, int pageNum);
	public List<SettleSubstationInfor> inquireSubstationsettled(Settlement settlement, int pageSize, int pageNum);
	public int confirmSubstation(String[] ids);
	public void invoiceRegiser(Invoice invoice);
	public Invoice	invoiceUse(int invoiceNumber, String subName);
	public Invoice	invoiceInvalid(int invoiceNumber);
	public List<Invoice> invoiceInqByinvNum(int invoiceNum, String subName, int pageSize, int pageNum);
	public List<Invoice> invoiceInqByOrderNum(int OrderNum, String subName, int pageSize, int pageNum);
	public Invoice getInvoiceById(int id);
	public void noteSupplySettleInfor(List<SettleListInfor> listInfor);
	public void noteSubSettleInfor(List<SettleSubstationInfor> listInfor);
	public List<String> getSupplier();
	public List<String> getSubstation();
	public List<String> getDateForSupUnsettle();
	public List<String> getDateForSubUnsettle();
	public List<String> getDateForSupSettled();
	public List<String> getDateForSubsettled();
	public int selectPageCountForSup(Settlement settlement, int pageSize);
	public int selectPageCountForSub(Settlement settlement, int pageSize);
	public int selectPageCountByInvNum(int  invoiceNum, String subName, int pageSize);
	public int selectPageCountByOrderNum(int orderNum, String subName, int pageSize);
	public int selectPageCountForSubUnsettle(Settlement settlement, int pageSize);
	public int selectPageCountForSupUnsettle(Settlement settlement, int pageSize);
	public boolean registerTestInvoice(String invoiceNumber);
	public boolean registerTestOrder(String orderNumber);
	public boolean isRegisterOrder(String orderNumber);
	public int getInvoiceStatus(String invoiceNumber);




}
