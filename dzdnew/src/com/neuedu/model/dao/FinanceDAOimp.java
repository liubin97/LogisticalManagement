package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.neuedu.model.po.Invoice;
import com.neuedu.model.po.OrderInfor;
import com.neuedu.model.po.SettleListInfor;
import com.neuedu.model.po.SettleSubstationInfor;
import com.neuedu.model.po.SettleSupplyInfor;
import com.neuedu.model.po.Settlement;
import com.neuedu.utils.DBUtil;

public class FinanceDAOimp implements FinanceDAO {
	Connection conn;
	public FinanceDAOimp(Connection conn){
		this.conn = conn;
	}
	@Override		//查询与供应商未结算信息
	public List<SettleSupplyInfor> inquireSuppliersUnsettled(Settlement settlement, int pageSize, int pageNum) {
		
		List<SettleSupplyInfor> settleList = new ArrayList<SettleSupplyInfor>();
		//进货
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  SELECT ps.ps_id,sp.supplier_name,ps.prod_id,pd.product_name,ps.price,ps.pur_num,ps.date,\"进货\" as type "
				+ " FROM supplier AS sp ,purchase_supplier AS ps ,product AS pd "
				+ " WHERE ps.sup_id = sp.supplier_id AND pd.product_id = ps.prod_id AND ps.settle_status=0 AND ps.status=0 ");
		//退货
		StringBuffer sbfOut = new StringBuffer("");
		sbfOut.append(" SELECT rs.rs_id,sp.supplier_name,rs.prod_id,pd.product_name,rs.price,rs.rs_num,rs.date,\"退货\" as type "
				+ " FROM return_supplier AS rs ,supplier AS sp ,product AS pd "
				+ " WHERE  rs.prod_id = pd.product_id AND rs.sup_id = sp.supplier_id and rs.settle_status=0 and rs.status=0 ");
		
		if(!"0".equals(settlement.getName())){
			sbf.append(" and sp.supplier_name=? ");
			sbfOut.append(" and sp.supplier_name=? ");
		}
		if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
			sbf.append(" and ps.prod_id=?");
			sbfOut.append(" and rs.prod_id=?");
		}
		if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
			sbf.append(" and pd.product_name=?");
			sbfOut.append(" and pd.product_name=?");
		}
		if(!"0".equals(settlement.getDate())){
			sbf.append(" and DATE_FORMAT(ps.date,\"%Y-%m\")=? ");
			sbfOut.append(" and DATE_FORMAT(rs.date,\"%Y-%m\")=? ");
		}
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sbf.toString()+" union "+sbfOut.toString()+" limit "+ pageSize*(pageNum-1)+","+pageSize);
			int index = 1;
			if(!"0".equals(settlement.getName())){
				ps.setString(index, settlement.getName());
				index++;
			}
			if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
				ps.setInt(index,Integer.parseInt(settlement.getItemCode()));
				index++;
			}
			if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
				ps.setString(index,settlement.getItemName());
				index++;
			}
			if(!"0".equals(settlement.getDate())){
				ps.setString(index,settlement.getDate());
				index++;
			}		
			if(!"0".equals(settlement.getName())){
				ps.setString(index, settlement.getName());
				index++;
			}
			if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
				ps.setInt(index,Integer.parseInt(settlement.getItemCode()));
				index++;
			}
			if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
				ps.setString(index,settlement.getItemName());
				index++;
			}
			if(!"0".equals(settlement.getDate())){
				ps.setString(index,settlement.getDate());
			}
			//执行
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				SettleSupplyInfor settSupInfor = new SettleSupplyInfor();
				settSupInfor.setId(rs.getInt(1));
				settSupInfor.setSupplyName(rs.getString(2));
				settSupInfor.setItemCode(rs.getInt(3)+"");
				settSupInfor.setItemName(rs.getString(4));
				settSupInfor.setPrice(rs.getDouble(5));
				settSupInfor.setQuantity(rs.getInt(6));
				settSupInfor.setDate(rs.getDate(7).toString());
				settSupInfor.setKind(rs.getString(8));
				settleList.add(settSupInfor);
			}
			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return settleList;
	}

	@Override//查询与供应商已结算信息
	public List<SettleListInfor> inquireSuppliersSettled(Settlement settlement, int pageSize, int pageNum) {
		List<SettleListInfor> inforList = new ArrayList<SettleListInfor>();

		StringBuffer sbf = new StringBuffer("");
		sbf.append(" select * from balance_supplier_goods_info as bs_infor where status=0 ");
		if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
			sbf.append(" and item_code=? ");
		}
		if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
			sbf.append(" and item_name=? ");
		}
		if(!"0".equals(settlement.getName())){
			sbf.append(" and supplier_name=? ");
		}
		if(!"0".equals(settlement.getDate())){
			sbf.append(" and DATE_FORMAT(bs_infor.balance_date,\"%Y-%m\")=? ");
		}
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sbf.toString()+" limit "+ pageSize*(pageNum-1)+","+pageSize);

			int index = 1;
			if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
				ps.setInt(index, Integer.parseInt(settlement.getItemCode()));
				index++;
			}
			if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
				ps.setString(index, settlement.getItemName());
				index++;
			}
			if(!"0".equals(settlement.getName())){
				ps.setString(index, settlement.getName());
				index++;
			}
			if(!"0".equals(settlement.getDate())){
				ps.setString(index, settlement.getDate());
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				SettleListInfor infor = new SettleListInfor();
				infor.setSupplyName(rs.getString(2));
				infor.setItemCode(rs.getInt(3)+"");
				infor.setItemName(rs.getString(4));
				infor.setPrice(rs.getDouble(5));
				infor.setQuantityIn(rs.getInt(6));
				infor.setQuantityOut(rs.getInt(7));
				infor.setQuantity(rs.getInt(8));
				infor.setDate(rs.getDate(10).toString());
				inforList.add(infor);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
		
		return inforList;
	}



	@Override
	public void confirmSuppliers(String[] idIn, String[] idOut) {
		// 更改进货退货结算状态
		int lengthIn=0,lengthOut=0;
		if(idIn!=null){
			lengthIn = idIn.length;
		}
		if(idOut!=null){
			lengthOut = idOut.length;
		}
		for(int i = 0;i<lengthIn;i++){
			changeSupInStatus(idIn[i]);
		}
		for(int i = 0;i<lengthOut;i++){
			changeSupOutStatus(idOut[i]);
		}
		
	}
	@Override//通过退货id更改退货单结算状态
	public void changeSupOutStatus(String idOut) {
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement(" update return_supplier set settle_status=? ,operator=?,operatorDate=? where rs_id=? ");
			ps.setInt(1, 1);
			ps.setString(2, "sss");
			ps.setDate(3,new Date(new java.util.Date().getTime()));
			ps.setInt(4, Integer.parseInt(idOut));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
	}
	@Override//通过进货单更改进货单结算状态
	public void changeSupInStatus(String idIn) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update purchase_supplier set settle_status=?,operator=?,operatorDate=? where ps_id=? ");
			ps.setInt(1, 1);
			ps.setString(2, "sss");
			ps.setDate(3,new Date(new java.util.Date().getTime()));
			ps.setInt(4, Integer.parseInt(idIn));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
		
	}

	
	
	public List<OrderInfor> getOrderInfor(Settlement settlement, int pageSize, int pageNum) {
		List<OrderInfor> inforList = new ArrayList<OrderInfor>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append(" SELECT ts.task_list_id,wh.wh_name,od.prod_id,pd.product_name,od.price,od.amount,od.sum_money,1 as type,od.finish_date  "
				+ " FROM task_list AS ts ,`order` AS od ,warehouse AS wh ,product AS pd "
				+ "  WHERE ts.order_id = od.order_id AND ts.substation_id = wh.wh_id AND od.prod_id = pd.product_id and ts.settle_status=0 and ts.task_status=6 ");
		StringBuffer sbfOut = new StringBuffer("");
		sbfOut.append(" SELECT ts.task_list_id,wh.wh_name,od.prod_id,pd.product_name,od.price,od.amount,od.sum_money,3 as type,od.finish_date "
				+ " FROM task_list AS ts ,`order` AS od ,warehouse AS wh ,product AS pd "
				+ " WHERE ts.order_id = od.order_id AND ts.substation_id = wh.wh_id AND od.prod_id = pd.product_id and ts.settle_status=0 and ts.task_status=11 ");
		if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
			sbf.append(" and od.prod_id=? ");
			sbfOut.append(" and od.prod_id=? ");
		}
		if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
			sbf.append(" and pd.product_name=? ");
			sbfOut.append(" and pd.product_name=? ");
		}
		if(!"0".equals(settlement.getName())){
			sbf.append(" and wh.wh_name=? ");
			sbfOut.append(" and wh.wh_name=? ");
		}
		if(!"0".equals(settlement.getDate())){
			sbf.append(" and DATE_FORMAT(od.finish_date,\"%Y-%m\")=? ");
			sbfOut.append(" and DATE_FORMAT(od.finish_date,\"%Y-%m\")=? ");
		}
		try {
			int index = 1;
			PreparedStatement ps = conn.prepareStatement(sbf.toString()+" union "+sbfOut.toString()+" limit "+ pageSize*(pageNum-1)+","+pageSize);
			if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
				ps.setInt(index, Integer.parseInt(settlement.getItemCode()));
				index++;
			}
			if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
				ps.setString(index, settlement.getItemName());
				index++;
			}
			if(!"0".equals(settlement.getName())){
				ps.setString(index, settlement.getName());
				index++;
			}
			if(!"0".equals(settlement.getDate())){
				ps.setString(index, settlement.getDate());
				index++;
			}
			if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
				ps.setInt(index, Integer.parseInt(settlement.getItemCode()));
				index++;
			}
			if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
				ps.setString(index, settlement.getItemName());
				index++;
			}
			if(!"0".equals(settlement.getName())){
				ps.setString(index, settlement.getName());
				index++;
			}
			if(!"0".equals(settlement.getDate())){
				ps.setString(index, settlement.getDate());
				
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				OrderInfor infor = new OrderInfor();
				infor.setTaskId(rs.getInt(1));
				infor.setSubName(rs.getString(2));
				infor.setItemCode(rs.getInt(3)+"");
				infor.setItemName(rs.getString(4));
				infor.setPrice(rs.getDouble(5));
				infor.setQuantity(rs.getInt(6));
				infor.setAmount(rs.getDouble(7));
				infor.setType(rs.getInt(8));
				infor.setDate(rs.getDate(9).toString());
				inforList.add(infor);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inforList;
	}

	@Override//查询与分站已结算信息
	public List<SettleSubstationInfor> inquireSubstationsettled(
			Settlement settlement, int pageSize, int pageNum) {
		List<SettleSubstationInfor> inforList = new ArrayList<SettleSubstationInfor>();

		StringBuffer sbf = new StringBuffer("");
		sbf.append(" select * from balance_substation_goods_info as bs_infor where status=0 ");
		if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
			sbf.append(" and item_code=? ");
		}
		if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
			sbf.append(" and item_name=? ");
		}
		if(!"0".equals(settlement.getName())){
			sbf.append(" and substation_name=? ");
		}
		if(!"0".equals(settlement.getDate())){
			sbf.append(" and DATE_FORMAT(bs_infor.balance_date,\"%Y-%m\")=? ");
		}
		PreparedStatement ps = null;
		try {
			int index = 1;
			ps = conn.prepareStatement(sbf.toString()+" limit "+ pageSize*(pageNum-1)+","+pageSize);
			if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
				ps.setInt(index, Integer.parseInt(settlement.getItemCode()));
				index++;
			}
			if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
				ps.setString(index, settlement.getItemName());
				index++;
			}
			if(!"0".equals(settlement.getName())){
				ps.setString(index, settlement.getName());
				index++;
			}
			if(!"0".equals(settlement.getDate())){
				ps.setString(index, settlement.getDate());
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				SettleSubstationInfor infor = new SettleSubstationInfor();
				infor.setSubName(rs.getString(2));
				infor.setItemCode(rs.getInt(3)+"");
				infor.setItemName(rs.getString(4));
				infor.setPrice(rs.getDouble(5));
				infor.setQuantityIn(rs.getInt(6));
				infor.setAmountIn(rs.getDouble(7));
				infor.setQuantityOut(rs.getInt(8));
				infor.setAmountOut(rs.getDouble(9));
				infor.setDate(rs.getDate(10).toString());
				inforList.add(infor);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
		
		return inforList;
	}

	@Override//确认与分站结算清单，更改结算状态
	public int confirmSubstation(String[] ids) {
		PreparedStatement ps = null;
		int value = 1;
		try {
			ps = conn.prepareStatement(" update task_list set settle_status=1,operator=?,operatorDate=? where task_list_id=? ");
			for(int i = 0;i<ids.length;i++){
				ps.setString(1, "sss");
				ps.setDate(2,new Date(new java.util.Date().getTime()));
				ps.setInt(3, Integer.parseInt(ids[i]));
				ps.addBatch();
			}

			ps.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return value;
	}

	@Override//发票登记
	public void invoiceRegiser(Invoice invoice) {
		PreparedStatement ps = null;
		try {
			/*ps = conn.prepareStatement(" insert into emp value(?,?)");
			ps.setInt(1, 1);
			ps.setString(2, "sss");
			ps.executeUpdate();*/
			Date date = new Date(new java.util.Date().getTime());
			invoice.setRegistrationDate(date.toString());
			ps = conn.prepareStatement(" insert into invoice "
					+ "(invoice_number,invoice_amount,registration_date,use_date,"
					+ "lost_invalidated_date,substation_name,invoice_status,order_number,status,operator,operatorDate) value(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, invoice.getInvoiceNumber());
			ps.setDouble(2, invoice.getAmount());
			ps.setDate(3, date);
			ps.setDate(4, null);
			ps.setDate(5, null);
			ps.setString(6, invoice.getSubstationName());
			ps.setInt(7, 0);
			ps.setInt(8, invoice.getOrderNumber());
			ps.setInt(9, 0);
			ps.setString(10,"sss");
			ps.setDate(11, date);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
	}

	@Override//发票领用
	public Invoice invoiceUse(int invoiceNumber, String subName) {
		PreparedStatement ps = null;
		Date date = new Date(new java.util.Date().getTime());
		try {
			ps = conn.prepareStatement(" update invoice set substation_name=?,use_date=?,invoice_status=?,operator=?,operatorDate=? where invoice_number=? ");
			ps.setString(1, subName);
			ps.setDate(2,date);
			ps.setInt(3, 1);
			ps.setString(4, "sss");
			ps.setDate(5,date);
			ps.setInt(6, invoiceNumber);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
		return getInvoiceById(invoiceNumber);
	}

	@Override//发票丢失或作废
	public Invoice invoiceInvalid(int invoiceNumber) {
		PreparedStatement ps = null;
		try {
			Date date = new Date(new java.util.Date().getTime());
			ps = conn.prepareStatement(" update invoice set lost_invalidated_date=?,invoice_status=?,operator=?,operatorDate=? where invoice_number=? ");
			ps.setDate(1,date);
			ps.setInt(2, 3);
			ps.setString(3, "sss");
			ps.setDate(4,date);
			ps.setInt(5, invoiceNumber);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
		return getInvoiceById(invoiceNumber);
	}

	@Override//按发票号码与分站名称查询发票
	public List<Invoice> invoiceInqByinvNum(int invoiceNum, String subName, int pageSize, int pageNum) {
		List<Invoice> list = new ArrayList<Invoice>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select invoice_number from  invoice where 1=1  ");
		if(!"0".equals(subName)){
			sbf.append(" and substation_name=? ");
		}
		if(invoiceNum != 0){
			sbf.append(" and invoice_number=? ");
		}
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sbf.toString()+" limit "+ pageSize*(pageNum-1)+","+pageSize);
			int index = 1;
			if(!"0".equals(subName)){
				ps.setString(index, subName);
				index++;
			}
			if(invoiceNum != 0){
				ps.setInt(index, invoiceNum);
			}
			//执行
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Invoice invoice = getInvoiceById(rs.getInt(1));
				list.add(invoice);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return list;
	}
	@Override//按订单号与分站名称查询发票
	public List<Invoice> invoiceInqByOrderNum(int OrderNum, String subName, int pageSize, int pageNum) {
		List<Invoice> list = new ArrayList<Invoice>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select invoice_number from  invoice where 1=1  ");
		if(!"0".equals(subName)){
			sbf.append(" and substation_name=? ");
		}
		if(OrderNum != 0){
			sbf.append(" and order_number=? ");
		}
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sbf.toString()+" limit "+ pageSize*(pageNum-1)+","+pageSize);
			int index = 1;
			if(!"0".equals(subName)){
				ps.setString(index, subName);
				index++;
			}
			if(OrderNum != 0){
				ps.setInt(index, OrderNum);
			}
			//执行
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Invoice invoice = getInvoiceById(rs.getInt(1));
				list.add(invoice);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return list;
	}
	@Override//获得某个发票的信息
	public Invoice getInvoiceById(int id) {
		PreparedStatement ps = null;
		Invoice invoice = new Invoice();
		try {
			ps = conn.prepareStatement(" select * from invoice where invoice_number=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				invoice.setInvoiceNumber(rs.getInt(2));
				invoice.setAmount(rs.getDouble(3));
				invoice.setRegistrationDate(rs.getDate(4).toString());
				if(rs.getDate(5)==null){
					invoice.setUseDate("无");
				}else{
					invoice.setUseDate(rs.getDate(5).toString());
				}
				if(rs.getDate(6)==null){
					invoice.setLostInvalidatedDate("无");
				}else{
					invoice.setLostInvalidatedDate(rs.getDate(6).toString());
				}
				if(rs.getString(7)==null||"".equals(rs.getString(7))){
					invoice.setSubstationName("无");
				}else{
					invoice.setSubstationName(rs.getString(7));
				}
				String InvoiceStatus = "";
				int status = rs.getInt(8);
				switch(status){
				case 0: 
					InvoiceStatus = "正常";
					break;
				case 1:
					InvoiceStatus = "分站已领用";
					break;
				case 2:
					InvoiceStatus = "用户已领用";
					break;
				case 3:
					InvoiceStatus = "已作废";
					break;
				}
				invoice.setInvoiceStatus(InvoiceStatus);
				invoice.setOrderNumber(rs.getInt(9));

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return invoice;
	}


	
	@Override//记录与供应商结算信息
	public void noteSupplySettleInfor(List<SettleListInfor> listInfor) {
		PreparedStatement ps = null;
		try {
			Date date = new Date(new java.util.Date().getTime());
			ps= conn.prepareStatement(" insert into balance_supplier_goods_info" 
					+ "(supplier_name,item_code,item_name,price,quantity_in,quantity_out,quantity,total_amount,balance_date,status,operator,operatorDate) "
					+ " value(?,?,?,?,?,?,?,?,?,?,?,?)");
			for(int i =0;i<listInfor.size();i++){
				ps.setString(1, listInfor.get(i).getSupplyName());
				ps.setInt(2, Integer.parseInt(listInfor.get(i).getItemCode()));
				ps.setString(3, listInfor.get(i).getItemName());
				ps.setDouble(4, listInfor.get(i).getPrice());
				ps.setInt(5,listInfor.get(i).getQuantityIn());
				ps.setInt(6,listInfor.get(i).getQuantityOut());
				ps.setInt(7, listInfor.get(i).getQuantity());
				ps.setDouble(8, listInfor.get(i).getQuantity()*listInfor.get(i).getPrice());
				ps.setDate(9, date);
				ps.setInt(10, 0);
				ps.setString(11, "sss");
				ps.setDate(12,date);
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
	}
	@Override//记录与分站结算信息
	public void noteSubSettleInfor(List<SettleSubstationInfor> listInfor) {
		PreparedStatement ps = null;
		try {
			Date date = new Date(new java.util.Date().getTime());
			ps= conn.prepareStatement(" insert into balance_substation_goods_info" 
					+ "(substation_name,item_code,item_name,price,quantity_out,amount_out,quantity_in,amount_in,balance_date,status,operator,operatorDate) "
					+ " value(?,?,?,?,?,?,?,?,?,?,?,?)");
			for(int i =0;i<listInfor.size();i++){
				ps.setString(1, listInfor.get(i).getSubName());
				ps.setInt(2, Integer.parseInt(listInfor.get(i).getItemCode()));
				ps.setString(3, listInfor.get(i).getItemName());
				ps.setDouble(4, listInfor.get(i).getPrice());
				ps.setInt(5,listInfor.get(i).getQuantityOut());
				ps.setDouble(6,listInfor.get(i).getAmountOut());
				ps.setInt(7, listInfor.get(i).getQuantityIn());
				ps.setDouble(8,listInfor.get(i).getAmountIn());
				ps.setDate(9, date);
				ps.setInt(10, 0);
				ps.setString(11, "sss");
				ps.setDate(12,date);
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
	}
	
	@Override//获取已有供应商
	public List<String> getSupplier() {
		PreparedStatement ps = null;
		List<String> supplierList = new ArrayList<String>();
		try {
			ps = conn.prepareStatement("SELECT  supplier.supplier_name FROM supplier where status=0");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String supplier = rs.getString(1);
				supplierList.add(supplier);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return supplierList;
	}
	@Override//获取已有分站
	public List<String> getSubstation() {
		PreparedStatement ps = null;
		List<String> substationList = new ArrayList<String>();
		try {
			ps = conn.prepareStatement("SELECT  warehouse.wh_name FROM warehouse where status=0");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String substation = rs.getString(1);
				substationList.add(substation);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return substationList;
	}
	@Override//获取与供应商未结算时间段
	public List<String> getDateForSupUnsettle() {
		PreparedStatement ps = null;
		List<String> dateList = new ArrayList<String>();
		try {
			ps = conn.prepareStatement(" SELECT DISTINCT * from (SELECT DATE_FORMAT(ps.date,\"%Y-%m\") FROM purchase_supplier as ps where ps.settle_status=0 "
					+ " union all SELECT DATE_FORMAT(rs.date,\"%Y-%m\") from return_supplier as rs where rs.settle_status=0) as date ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String date = rs.getString(1);
				dateList.add(date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return dateList;
	}
	@Override//获取与供应商已结算时间段
	public List<String> getDateForSupSettled() {
		PreparedStatement ps = null;
		List<String> dateList = new ArrayList<String>();
		try {
			ps = conn.prepareStatement("SELECT DISTINCT DATE_FORMAT(bs_infor.balance_date,\"%Y-%m\") FROM balance_supplier_goods_info as bs_infor where status=0");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String date = rs.getString(1);
				dateList.add(date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return dateList;
	}
	@Override//获取与分站未结算时间段
	public List<String> getDateForSubUnsettle() {
		PreparedStatement ps = null;
		List<String> dateList = new ArrayList<String>();
		try {
			ps = conn.prepareStatement(" SELECT DISTINCT DATE_FORMAT(od.finish_date,\"%Y-%m\")  "
					+ " FROM task_list AS tk ,`order` AS od "
					+ " WHERE tk.order_id = od.order_id ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String date = rs.getString(1);
				dateList.add(date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return dateList;
	}
	@Override//获取与分站已结算时间段
	public List<String> getDateForSubsettled() {
		PreparedStatement ps = null;
		List<String> dateList = new ArrayList<String>();
		try {
			ps = conn.prepareStatement("SELECT DISTINCT DATE_FORMAT(bs_infor.balance_date,\"%Y-%m\") FROM balance_substation_goods_info as bs_infor where status=0");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String date = rs.getString(1);
				dateList.add(date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return dateList;
	}
	@Override//查询与供应商已结算页数
	public int selectPageCountForSup(Settlement settlement, int pageSize) {
		int count = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select count(*) cc  from  balance_supplier_goods_info as bs_infor where 1=1 and status=0 ");
		if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
			sbf.append(" and item_code=? ");
		}
		if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
			sbf.append(" and item_name=? ");
		}
		if(!"0".equals(settlement.getName())){
			sbf.append(" and supplier_name=? ");
		}
		if(!"0".equals(settlement.getDate())){
			sbf.append(" and DATE_FORMAT(bs_infor.balance_date,\"%Y-%m\")=? ");
		}
		PreparedStatement ps = null;
		try {
			int index = 1;
			ps = conn.prepareStatement(sbf.toString());
			if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
				ps.setInt(index, Integer.parseInt(settlement.getItemCode()));
				index++;
			}
			if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
				ps.setString(index, settlement.getItemName());
				index++;
			}
			if(!"0".equals(settlement.getName())){
				ps.setString(index, settlement.getName());
				index++;
			}
			if(!"0".equals(settlement.getDate())){
				ps.setString(index, settlement.getDate());
			}
			//执行
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("cc");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		int pagecount = 0;
		
		if(count%pageSize==0)
		{
			pagecount = count/pageSize;
		}
		else{
			pagecount = count/pageSize +1;
		}
		return pagecount;
	}
	@Override//查询与供应商未结算页数
	public int selectPageCountForSupUnsettle(Settlement settlement, int pageSize) {
		int count = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  SELECT ps.ps_id,sp.supplier_name,ps.prod_id,pd.product_name,pd.cost_price,ps.pur_num,ps.date,\"进货\" as type "
				+ " FROM supplier AS sp ,purchase_supplier AS ps ,product AS pd "
				+ " WHERE ps.sup_id = sp.supplier_id AND pd.product_id = ps.prod_id AND ps.settle_status=0 AND ps.status=0 ");
		StringBuffer sbfOut = new StringBuffer("");
		sbfOut.append(" SELECT rs.rs_id,sp.supplier_name,rs.prod_id,pd.product_name,rs.price,rs.rs_num,rs.date,\"退货\" as type "
				+ " FROM return_supplier AS rs ,supplier AS sp ,product AS pd "
				+ " WHERE  rs.prod_id = pd.product_id AND rs.sup_id = sp.supplier_id and rs.settle_status=0 and rs.status=0 ");
		
		PreparedStatement ps = null;
		if(!"0".equals(settlement.getName())){
			sbf.append(" and sp.supplier_name=? ");
			sbfOut.append(" and sp.supplier_name=? ");
		}
		if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
			sbf.append(" and ps.prod_id=?");
			sbfOut.append(" and rs.prod_id=?");
		}
		if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
			sbf.append(" and pd.product_name=?");
			sbfOut.append(" and pd.product_name=?");
		}
		if(!"0".equals(settlement.getDate())){
			sbf.append(" and DATE_FORMAT(ps.date,\"%Y-%m\")=? ");
			sbfOut.append(" and DATE_FORMAT(rs.date,\"%Y-%m\")=? ");
		}
		
		try {
			ps = conn.prepareStatement(" select count(*) cc from("+sbf.toString()+" union "+sbfOut.toString()+") as infor");
			int index = 1;
			if(!"0".equals(settlement.getName())){
				ps.setString(index, settlement.getName());
				index++;
			}
			if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
				ps.setInt(index,Integer.parseInt(settlement.getItemCode()));
				index++;
			}
			if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
				ps.setString(index,settlement.getItemName());
				index++;
			}
			if(!"0".equals(settlement.getDate())){
				ps.setString(index,settlement.getDate());
				index++;
			}		
			if(!"0".equals(settlement.getName())){
				ps.setString(index, settlement.getName());
				index++;
			}
			if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
				ps.setInt(index,Integer.parseInt(settlement.getItemCode()));
				index++;
			}
			if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
				ps.setString(index,settlement.getItemName());
				index++;
			}
			if(!"0".equals(settlement.getDate())){
				ps.setString(index,settlement.getDate());
			}
			//执行
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("cc");
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		int pagecount = 0;
		
		if(count%pageSize==0)
		{
			pagecount = count/pageSize;
		}
		else{
			pagecount = count/pageSize +1;
		}
		return pagecount;
	}
	@Override//查询与分站已结算页数
	public int selectPageCountForSub(Settlement settlement, int pageSize) {
		int count = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select count(*) cc  from  balance_substation_goods_info as bs_infor where 1=1 and status=0 ");
		if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
			sbf.append(" and item_code=? ");
		}
		if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
			sbf.append(" and item_name=? ");
		}
		if(!"0".equals(settlement.getName())){
			sbf.append(" and substation_name=? ");
		}
		if(!"0".equals(settlement.getDate())){
			sbf.append(" and DATE_FORMAT(bs_infor.balance_date,\"%Y-%m\")=? ");
		}
		PreparedStatement ps = null;
		try {
			int index = 1;
			ps = conn.prepareStatement(sbf.toString());
			if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
				ps.setInt(index, Integer.parseInt(settlement.getItemCode()));
				index++;
			}
			if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
				ps.setString(index, settlement.getItemName());
				index++;
			}
			if(!"0".equals(settlement.getName())){
				ps.setString(index, settlement.getName());
				index++;
			}
			if(!"0".equals(settlement.getDate())){
				ps.setString(index, settlement.getDate());
			}
			//执行
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("cc");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		int pagecount = 0;
		
		if(count%pageSize==0)
		{
			pagecount = count/pageSize;
		}
		else{
			pagecount = count/pageSize +1;
		}
		return pagecount;
	}
	@Override//查询与分站未结算页数
	public int selectPageCountForSubUnsettle(Settlement settlement, int pageSize) {
		int count = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append(" SELECT count(*) cc  "
				+ " FROM task_list AS ts ,`order` AS od ,warehouse AS wh ,product AS pd "
				+ "  WHERE ts.order_id = od.order_id AND ts.substation_id = wh.wh_id AND od.prod_id = pd.product_id and ts.settle_status=0 and (ts.task_status=6 or ts.task_status=11) ");
		if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
			sbf.append(" and od.prod_id=? ");
		}
		if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
			sbf.append(" and pd.product_name=? ");
		}
		if(!"0".equals(settlement.getName())){
			sbf.append(" and wh.wh_name=? ");
		}
		if(!"0".equals(settlement.getDate())){
			sbf.append(" and DATE_FORMAT(od.finish_date,\"%Y-%m\")=? ");
		}
		try {
			int index = 1;
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			if(settlement.getItemCode()!=null&&!"".equals(settlement.getItemCode())){
				ps.setInt(index, Integer.parseInt(settlement.getItemCode()));
				index++;
			}
			if(settlement.getItemName()!=null&&!"".equals(settlement.getItemName())){
				ps.setString(index, settlement.getItemName());
				index++;
			}
			if(!"0".equals(settlement.getName())){
				ps.setString(index, settlement.getName());
				index++;
			}
			if(!"0".equals(settlement.getDate())){
				ps.setString(index, settlement.getDate());
				
			}
	
			//执行
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("cc");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pagecount = 0;
		
		if(count%pageSize==0)
		{
			pagecount = count/pageSize;
		}
		else{
			pagecount = count/pageSize +1;
		}
		return pagecount;
	}
	@Override//查询通过发票号码与分站名称查询得到的页数
	public int selectPageCountByInvNum(int invoiceNum, String subName, int pageSize) {

		int count = 0;
		StringBuffer sbf = new StringBuffer("");
		PreparedStatement ps = null;
		sbf.append("  select count(*) cc from  invoice where 1=1 and status=0 ");
		if(!"0".equals(subName)){
			sbf.append(" and substation_name=? ");
		}
		if(invoiceNum != 0){
			sbf.append(" and invoice_number=? ");
		}
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(!"0".equals(subName)){
				ps.setString(index, subName);
				index++;
			}
			if(invoiceNum != 0){
				ps.setInt(index, invoiceNum);
			}
			//执行
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				count = rs.getInt("cc");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DBUtil.closePS(ps);
		}
		int pagecount = 0;
		
		if(count%pageSize==0)
		{
			pagecount = count/pageSize;
		}
		else{
			pagecount = count/pageSize +1;
		}
		return pagecount;
	}
	@Override//查询通过订单号码与分站名称查询得到的页数
	public int selectPageCountByOrderNum(int orderNum, String subName, int pageSize) {

		int count = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select count(*) cc from  invoice where 1=1 and status=0 ");
		if(!"0".equals(subName)){
			sbf.append(" and substation_name=? ");
		}
		if(orderNum != 0){
			sbf.append(" and order_number=? ");
		}
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(!"0".equals(subName)){
				ps.setString(index, subName);
				index++;
			}
			if(orderNum != 0){
				ps.setInt(index, orderNum);
			}
			//执行
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				count = rs.getInt("cc");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		int pagecount = 0;
		
		if(count%pageSize==0)
		{
			pagecount = count/pageSize;
		}
		else{
			pagecount = count/pageSize +1;
		}
		return pagecount;
	}
	@Override//验证登记页面的发票号码
	public boolean registerTestInvoice(String invoiceNumber) {
		boolean flag = false;
		
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement("select count(*) cc from invoice where invoice_number=? ");
			ps.setInt(1,Integer.parseInt(invoiceNumber));
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				if(rs.getInt("cc") == 0){
					flag = true;
				}
				else{
					flag = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}

		return flag;
	}
	@Override//验证登记页面的订单号码
	public boolean registerTestOrder(String orderNumber) {
		boolean flag = false;
		
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement("select count(*) cc from invoice where invoice_status != 3 and status=0 and order_number=? ");
			ps.setInt(1,Integer.parseInt(orderNumber));
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				if(rs.getInt("cc") == 0){
					flag = true;
				}
				else{
					flag = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}

		return flag;
	}
	@Override//返回该发票号码的状态
	public int getInvoiceStatus(String invoiceNumber) {
		int invoiceStatus = -1;
		
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement("select invoice_status from invoice where status=0 and invoice_number=? ");
			ps.setInt(1,Integer.parseInt(invoiceNumber));
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				invoiceStatus = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}

		return invoiceStatus;
	}
	@Override//判断订单号是否需要发票、订单号是否存在
	public boolean isRegisterOrder(String orderNumber) {
		boolean flag = false;
		
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement("select * from `order` where status!=5 and is_invoice=2 and type=1 and order_id=? ");
			ps.setInt(1,Integer.parseInt(orderNumber));
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}

		return flag;
	}

	







}
