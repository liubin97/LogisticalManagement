package com.neuedu.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.neuedu.model.dao.FinanceDAO;
import com.neuedu.model.dao.FinanceDAOimp;
import com.neuedu.model.po.Invoice;
import com.neuedu.model.po.OrderInfor;
import com.neuedu.model.po.SettleListInfor;
import com.neuedu.model.po.SettleSubstationInfor;
import com.neuedu.model.po.SettleSupplyInfor;
import com.neuedu.model.po.Settlement;
import com.neuedu.utils.DBUtil;

public class FinanceService {
	final int PAGE_SIZE_MAX = 10000;//当每页显示行数为0时，pageSize的默认值
	private static FinanceService financeService = new FinanceService();
	private FinanceService(){
		
	}
	public static FinanceService getInstance(){
		return financeService;
	}
	public List<SettleSupplyInfor> inquireSuppliersUnsettled(Settlement settlement, int pageSize, int pageNum) {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		if(pageSize==0){
			pageSize = PAGE_SIZE_MAX;
		}
		return dao.inquireSuppliersUnsettled(settlement,pageSize,pageNum);
	}
	public List<SettleListInfor> inquireSuppliersSettled(Settlement settlement, int pageSize, int pageNum) {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		if(pageSize==0){
			pageSize = PAGE_SIZE_MAX;
		}
		return dao.inquireSuppliersSettled(settlement,pageSize,pageNum);
	} 
	public List<SettleListInfor> settlementInfor(
			List<SettleSupplyInfor> settleSupplyInfor) {
		// 统计商品
		List<SettleListInfor> listInfor = new ArrayList<SettleListInfor>();
		for(int j = 0;j<settleSupplyInfor.size();j++){
			boolean isList = false;
				if(listInfor.size()==0){
					List<Integer> idIn = new ArrayList<Integer>();
					List<Integer> idOut = new ArrayList<Integer>();
					if(settleSupplyInfor.get(j).getKind().equals("进货")){
						idIn.add(settleSupplyInfor.get(j).getId());
					}
					if(settleSupplyInfor.get(j).getKind().equals("退货")){
						idOut.add(settleSupplyInfor.get(j).getId());
					}
					SettleListInfor settleList = new SettleListInfor(idIn,idOut,settleSupplyInfor.get(j).getSupplyName(),settleSupplyInfor.get(j).getItemCode(),settleSupplyInfor.get(j).getItemName(),settleSupplyInfor.get(j).getPrice(),0,0,0);
					if(settleSupplyInfor.get(j).getKind().equals("进货")){
						settleList.setQuantityIn(settleSupplyInfor.get(j).getQuantity());
					}
					if(settleSupplyInfor.get(j).getKind().equals("退货")){
						settleList.setQuantityOut(settleSupplyInfor.get(j).getQuantity());
					}
					listInfor.add(settleList);
					continue;
				}

				
			for(int i = 0;i<listInfor.size();i++){
				if(settleSupplyInfor.get(j).getItemCode().equals(listInfor.get(i).getItemCode())&&settleSupplyInfor.get(j).getSupplyName().equals(listInfor.get(i).getSupplyName())){
					if(settleSupplyInfor.get(j).getKind().equals("进货")){
						listInfor.get(i).setQuantityIn(listInfor.get(i).getQuantityIn()+settleSupplyInfor.get(j).getQuantity());
						listInfor.get(i).getIdIn().add(settleSupplyInfor.get(j).getId());
					}
					if(settleSupplyInfor.get(j).getKind().equals("退货")){
						listInfor.get(i).setQuantityOut(listInfor.get(i).getQuantityOut()+settleSupplyInfor.get(j).getQuantity());
						listInfor.get(i).getIdOut().add(settleSupplyInfor.get(j).getId());
					}

					isList = true;
					break;
				}
				
			}
			if(!isList){
				List<Integer> idIn = new ArrayList<Integer>();
				List<Integer> idOut = new ArrayList<Integer>();
				if(settleSupplyInfor.get(j).getKind().equals("进货")){
					idIn.add(settleSupplyInfor.get(j).getId());
				}
				if(settleSupplyInfor.get(j).getKind().equals("退货")){
					idOut.add(settleSupplyInfor.get(j).getId());
				}

				SettleListInfor settleList = new SettleListInfor(idIn,idOut,settleSupplyInfor.get(j).getSupplyName(),settleSupplyInfor.get(j).getItemCode(),settleSupplyInfor.get(j).getItemName(),settleSupplyInfor.get(j).getPrice(),0,0,0);
				if(settleSupplyInfor.get(j).getKind().equals("进货")){
					settleList.setQuantityIn(settleSupplyInfor.get(j).getQuantity());
				}
				if(settleSupplyInfor.get(j).getKind().equals("退货")){
					settleList.setQuantityOut(settleSupplyInfor.get(j).getQuantity());
				}
				listInfor.add(settleList);
			}
		}

		return listInfor;
	}
	
	public int confirmSuppliers(String[] idIn,String[] idOut) {
		Connection conn = DBUtil.getConn();
		int value=1;
		//开启事务
		DBUtil.beginTransaction(conn);
		
		try {
			FinanceDAO dao = new FinanceDAOimp(conn);
			dao.confirmSuppliers(idIn,idOut);
			//提交
			DBUtil.commit(conn);
			
		} catch (Exception e) {
			//回滚
			value = 0;
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
		return value;
	}
	public List<SettleSubstationInfor> SubstationUnsettledList(List<OrderInfor> orderList) {
		// 统记与分站结算订单
		List<SettleSubstationInfor> listInfor = new ArrayList<SettleSubstationInfor>();
		for(int i =0;i<orderList.size();i++){
			boolean isList = false;
			if(listInfor.size()==0){
				List<Integer> ids = new ArrayList<Integer>();
				ids.add(orderList.get(i).getTaskId());
				SettleSubstationInfor settleList = new SettleSubstationInfor(ids,orderList.get(i).getSubName(),orderList.get(i).getItemCode(),
						orderList.get(i).getItemName(),orderList.get(i).getPrice(),0,0,
						0,0,"");
				if(orderList.get(i).getType()==1){		
					settleList.setQuantityOut(orderList.get(i).getQuantity());
					settleList.setAmountIn(orderList.get(i).getAmount());
				}
				if(orderList.get(i).getType()==3){
					settleList.setQuantityIn(orderList.get(i).getQuantity());
					settleList.setAmountOut(orderList.get(i).getAmount());
				}
				listInfor.add(settleList);
				continue;
			}
			for(int j= 0;j<listInfor.size();j++){
				
				if(orderList.get(i).getItemCode().equals(listInfor.get(j).getItemCode())&&orderList.get(i).getSubName().equals(listInfor.get(j).getSubName())){
					if(orderList.get(i).getType()==1){
						listInfor.get(j).setQuantityOut(listInfor.get(j).getQuantityOut()+orderList.get(i).getQuantity());
						listInfor.get(j).setAmountIn(listInfor.get(j).getAmountIn()+orderList.get(i).getAmount());
					}
					if(orderList.get(i).getType()==3){
						listInfor.get(j).setQuantityIn(listInfor.get(j).getQuantityIn()+orderList.get(i).getQuantity());
						listInfor.get(j).setAmountOut(listInfor.get(j).getAmountOut()+orderList.get(i).getAmount());
					}
					isList = true;
					break;
				}
			}
			if(!isList){
				List<Integer> ids = new ArrayList<Integer>();
				ids.add(orderList.get(i).getTaskId());
				SettleSubstationInfor settleList = new SettleSubstationInfor(ids,orderList.get(i).getSubName(),orderList.get(i).getItemCode(),
						orderList.get(i).getItemName(),orderList.get(i).getPrice(),0,0,
						0,0,"");
				if(orderList.get(i).getType()==1){		
					settleList.setQuantityOut(orderList.get(i).getQuantity());
					settleList.setAmountIn(orderList.get(i).getAmount());
				}
				if(orderList.get(i).getType()==3){
					settleList.setQuantityIn(orderList.get(i).getQuantity());
					settleList.setAmountOut(orderList.get(i).getAmount());
				}
				listInfor.add(settleList);
			}
		}
		return listInfor;
	}

	public List<OrderInfor>	getOrderInfor(Settlement settlement, int pageSize, int pageNum) {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		if(pageSize==0){
			pageSize = PAGE_SIZE_MAX;
		}
		return dao.getOrderInfor(settlement,pageSize,pageNum);
	}
	public List<SettleSubstationInfor> inquireSubstationsettled(Settlement settlement, int pageSize, int pageNum) {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		if(pageSize==0){
			pageSize = PAGE_SIZE_MAX;
		}
		return dao.inquireSubstationsettled(settlement,pageSize,pageNum);
	}
	public int confirmSubstation(String[] ids) {
		Connection conn = DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		int value = 0;
		try {
			FinanceDAO dao = new FinanceDAOimp(conn);
			value = dao.confirmSubstation(ids);
			//提交
			DBUtil.commit(conn);
			
		} catch (Exception e) {
			//回滚
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
		return value;
	}
	public void invoiceRegiser(Invoice invoice) {
		Connection conn = DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		try {
			FinanceDAO dao = new FinanceDAOimp(conn);
			dao.invoiceRegiser(invoice);
			//提交
			DBUtil.commit(conn);
			
		} catch (Exception e) {
			//回滚
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	public Invoice	invoiceUse(int invoiceNumber,String subName) {
		Connection conn = DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		Invoice temp = null;
		try {
			FinanceDAO dao = new FinanceDAOimp(conn);
			 temp = dao.invoiceUse(invoiceNumber,subName);
			//提交
			DBUtil.commit(conn);
			
		} catch (Exception e) {
			//回滚
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
		return temp;
	}
	public Invoice	invoiceInvalid(int invoiceNumber) {
		Connection conn = DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		Invoice temp = null;
		try {
			FinanceDAO dao = new FinanceDAOimp(conn);
			temp = dao.invoiceInvalid(invoiceNumber);
			//提交
			DBUtil.commit(conn);
			
		} catch (Exception e) {
			//回滚
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
		return temp;
	}


	public void noteSupplySettleInfor(List<SettleListInfor> listInfor) {
		Connection conn = DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		try {
			FinanceDAO dao = new FinanceDAOimp(conn);
			dao.noteSupplySettleInfor(listInfor);
			//提交
			DBUtil.commit(conn);
			
		} catch (Exception e) {
			//回滚
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
		
	}
	public void noteSubSettleInfor(List<SettleSubstationInfor> listInfor) {
		Connection conn = DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		try {
			FinanceDAO dao = new FinanceDAOimp(conn);
			dao.noteSubSettleInfor(listInfor);
			//提交
			DBUtil.commit(conn);
			
		} catch (Exception e) {
			//回滚
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	//通过发票号码和分站名称查询发票
	public List<Invoice> invoiceInqByInvNum(int parseInt, String subName, int pageSize, int pageNum) {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		if(pageSize==0){
			pageSize = PAGE_SIZE_MAX;
		}
		return dao.invoiceInqByinvNum(parseInt,subName,pageSize,pageNum);
	}
	//通过订单号和分站名称查询发票
	public List<Invoice> invoiceInqByOrderNum(int parseInt, String subName, int pageSize, int pageNum) {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		if(pageSize==0){
			pageSize = PAGE_SIZE_MAX;
		}
		return dao.invoiceInqByOrderNum(parseInt,subName,pageSize,pageNum);
	}
	//获取已有供应商
	public List<String> getSupplier() {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		return dao.getSupplier();
		
	}
	//获取已有分站名称
	public List<String> getSubstation() {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		return dao.getSubstation();
	}
	//获取与供应商未结算时间段
	public List<String> getDateForSupUnsettle() {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		return dao.getDateForSupUnsettle();
	}
	//获取与供应商已结算时间段
	public List<String> getDateForSupSettled() {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		return dao.getDateForSupSettled();
	}
	//获取与分站结算时间段
	public List<String> getDateForSubUnsettle() {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		return dao.getDateForSubUnsettle();
	}
	public List<String> getDateForSubsettled() {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		return dao.getDateForSubsettled();
	}
	public int selectPageCountForSup(Settlement settlement, int pageSize) {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		if(pageSize==0){
			pageSize = PAGE_SIZE_MAX;
		}
		return dao.selectPageCountForSup(settlement,pageSize);
	}
	public int selectPageCountForSupUnsettle(Settlement settlement, int pageSize) {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		if(pageSize==0){
			pageSize = PAGE_SIZE_MAX;
		}
		return dao.selectPageCountForSupUnsettle(settlement,pageSize);
	}
	public int selectPageCountForSub(Settlement settlement, int pageSize) {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		if(pageSize==0){
			pageSize = PAGE_SIZE_MAX;
		}
		return dao.selectPageCountForSub(settlement,pageSize);
	}
	public int selectPageCountForSubUnsettle(Settlement settlement, int pageSize) {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		if(pageSize==0){
			pageSize = PAGE_SIZE_MAX;
		}
		return dao.selectPageCountForSubUnsettle(settlement,pageSize);
	}
	public int selectPageCountByInvNum(int  invoiceNum, String subName, int pageSize) {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		if(pageSize==0){
			pageSize = PAGE_SIZE_MAX;
		}
		return dao.selectPageCountByInvNum(invoiceNum,subName, pageSize);
	}
	public int selectPageCountByOrderNum(int OrderNum, String subName, int pageSize) {
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		if(pageSize==0){
			pageSize = PAGE_SIZE_MAX;
		}
		return dao.selectPageCountByOrderNum(OrderNum,subName, pageSize);
	}
	public boolean registerTestInvoice(String invoiceNumber) {
		boolean flag = DBUtil.testNumber(invoiceNumber);
		
		if(!flag){
			return flag;
		}
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		
		return dao.registerTestInvoice(invoiceNumber);
	}
	public boolean registerTestAmount(String amount) {
		boolean flag = false;
		Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
		if(pattern.matcher(amount).matches()){
			flag = true;
		}
		else{
			return flag;
		}
		return flag;
	}
	public boolean registerTestOrder(String orderNumber) {
	boolean flag = DBUtil.testNumber(orderNumber);
		
		if(!flag){
			return flag;
		}
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		
		return dao.registerTestOrder(orderNumber)&&dao.isRegisterOrder(orderNumber);
	}
	public boolean useTestInvoice(String invoiceNumber) {
		boolean flag = DBUtil.testNumber(invoiceNumber);
		
		if(!flag){
			return flag;
		}
		
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		
		if(dao.getInvoiceStatus(invoiceNumber)==0){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	public boolean invalidTestInvoice(String invoiceNumber) {
		boolean flag = DBUtil.testNumber(invoiceNumber);
		
		if(!flag){
			return flag;
		}
		
		Connection conn = DBUtil.getConn();
		FinanceDAO dao = new FinanceDAOimp(conn);
		
		if(dao.getInvoiceStatus(invoiceNumber)!=3&&dao.getInvoiceStatus(invoiceNumber)!=-1){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}




}
