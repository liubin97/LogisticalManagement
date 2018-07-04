package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.model.po.*;
import com.neuedu.utils.DBUtil;

import java.util.*;

public class PurchaseReturnDAOImp implements PurchaseReturnDAO{

	Connection conn;
	
	public PurchaseReturnDAOImp(Connection conn){
		this.conn = conn;
	}
	
	public List<PurchaseMsg> searchWarn(String proname,int pageno){
		PreparedStatement ps = null;
		List<PurchaseMsg> list=new ArrayList<PurchaseMsg>();
		PurchaseMsg pm=null;
		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("  select *  from  pmsg where 1=1 ");
			if(proname != null && !"".equals(proname)){
				sbf.append(" and product_name=? ");
			}
			 ps = conn.prepareStatement(sbf.toString()+" limit ?,?");
			int index=1;
				if(proname != null && !"".equals(proname)){
					ps.setString(index, proname);
					index++;
				}
				int start=(pageno-1)*5;
				int end=pageno*5;
				ps.setInt(index, start);
				index++;
				ps.setInt(index, 5);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				pm=new PurchaseMsg();
				pm.setProd_id(rs.getInt("product_id"));
				pm.setCan_distribute(rs.getInt("can_distribute"));
				pm.setMeasurement(rs.getString("measurement"));
				pm.setOnetitle_name(rs.getString("onetitle_name"));
				pm.setTwotitle_name(rs.getString("twotitle_name"));
				pm.setOutofstock(rs.getInt("outofstock"));
				pm.setProd_name(rs.getString("product_name"));
				pm.setWarn_num(rs.getInt("warn_num"));
				pm.setSupplier_id(rs.getInt("supplier_id"));
				list.add(pm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return list;
		
		
		
	}
	public int calPagecountWarn(String proname){
		PreparedStatement ps = null;

		int count=0;
		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("  select count(*) cc  from  pmsg where 1=1");
			if(proname != null && !"".equals(proname)){
				sbf.append(" and product_name=? ");
			}
			 ps = conn.prepareStatement(sbf.toString());
			int index=1;
				if(proname != null && !"".equals(proname)){
					ps.setString(index, proname);
					index++;
				}
				
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				count=rs.getInt("cc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		int pagecount = 0;
		if(count%5==0){
			pagecount = count/5;
		}else{
			pagecount = count/5+1;
		}
		return pagecount;

		
	}
	public List<PurchaseMsg> searchOutofstock(String proname,int pageno){
		PreparedStatement ps = null;
		List<PurchaseMsg> list=new ArrayList<PurchaseMsg>();
		PurchaseMsg pm=null;
		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("  select *  from  poutmsg where 1=1 ");
			if(proname != null && !"".equals(proname)){
				sbf.append(" and product_name=? ");
			}
			 ps = conn.prepareStatement(sbf.toString()+" limit ?,?");
			int index=1;
				if(proname != null && !"".equals(proname)){
					ps.setString(index, proname);
					index++;
				}
				int start=(pageno-1)*5;
				int end=pageno*5;
				ps.setInt(index, start);
				index++;
				ps.setInt(index, 5);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				pm=new PurchaseMsg();
				pm.setProd_id(rs.getInt("prod_id"));
				pm.setCan_distribute(rs.getInt("can_distribute"));
				pm.setMeasurement(rs.getString("measurement"));
				pm.setOnetitle_name(rs.getString("onetitle_name"));
				pm.setTwotitle_name(rs.getString("twotitle_name"));
				pm.setOutofstock(rs.getInt("outofstock"));
				pm.setProd_name(rs.getString("product_name"));
				pm.setWarn_num(rs.getInt("warn_num"));
				pm.setOrder_id(rs.getInt("order_id"));
				pm.setSupplier_id(rs.getInt("supplier_id"));
				list.add(pm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return list;
		
	}
	public int calPagecountOutofstock(String proname){
		PreparedStatement ps = null;
	
		int count=0;
		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("  select count(*) cc from  poutmsg where 1=1");
			if(proname != null && !"".equals(proname)){
				sbf.append(" and product_name=? ");
			}
			 ps = conn.prepareStatement(sbf.toString());
			int index=1;
				if(proname != null && !"".equals(proname)){
					ps.setString(index, proname);
					index++;
				}
				
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				count=rs.getInt("cc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		int pagecount = 0;
		if(count%5==0){
			pagecount = count/5;
		}else{
			pagecount = count/5+1;
		}
		return pagecount;
		
	}
	public int newPSwarn(PurchaseSupplier psr){
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		int newid=0;
		try {
			ps = conn.prepareStatement(" insert into purchase_supplier(sup_id,prod_id,pur_num,date,status,settle_status,price,sum_price) values (?,?,?,?,?,?,?,?)");
			ps.setInt(1, psr.getSup_id());
			ps.setInt(2, psr.getProd_id());
			ps.setInt(3, psr.getPur_num());
			ps.setString(4, psr.getDate());
			ps.setInt(5,psr.getStatus());
			ps.setInt(6,psr.getSettle_status());
			ps.setFloat(7, psr.getPrice());
			ps.setFloat(8, psr.getSum_price());
			ps.executeUpdate();
			ps1 = conn.prepareStatement("select LAST_INSERT_ID() as newid");
			ResultSet rs= ps1.executeQuery();
			rs.next();
			newid=rs.getInt("newid");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
			DBUtil.closePS(ps1);
		}
		return newid;
		
	}
	public int newPSstock(PurchaseSupplier psr){
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		int newid = 0;
		try {
			ps = conn.prepareStatement(" insert into purchase_supplier(sup_id,prod_id,pur_num,date,status,settle_status,price,sum_price,order_id) values (?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, psr.getSup_id());
			ps.setInt(2, psr.getProd_id());
			ps.setInt(3, psr.getPur_num());
			ps.setString(4, psr.getDate());
			ps.setInt(5,psr.getStatus());
			ps.setInt(6,psr.getSettle_status());
			ps.setFloat(7, psr.getPrice());
			ps.setFloat(8, psr.getSum_price());
			ps.setInt(9, psr.getOrder_id());
			ps.executeUpdate();
			ps1 = conn.prepareStatement("select LAST_INSERT_ID() as newid");
			ResultSet rs= ps1.executeQuery();
			rs.next();
			newid=rs.getInt("newid");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
			DBUtil.closePS(ps1);
		}
		return newid;
		
	}
	public PurchaseSupplier acPs(int prod_id,int pnum,String do_date){
		PreparedStatement ps = null;
		float price=0.0f;
		float sum_money=0.0f;
		int supplier_id=0;
		String prod_name = null;
		PurchaseSupplier psr=null;
		try {
			String  sql = "select * from product where status=0 and product_id=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, prod_id);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				price=rs.getFloat("original_price");
				supplier_id=rs.getInt("supplier_id");
				prod_name=rs.getString("product_name");
			}
			sum_money=price*pnum;
			psr=new PurchaseSupplier();
			psr.setDate(do_date);
			psr.setPrice(price);
			psr.setPur_num(pnum);
			psr.setProd_id(prod_id);
			psr.setSettle_status(0);
			psr.setSup_id(supplier_id);
			psr.setStatus(0);
			psr.setSum_price(sum_money);
			psr.setProd_name(prod_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return psr;
		
	}
	
	public PurchaseSupplier acPs1(int prod_id,int order_id,int pnum,String do_date){
		PreparedStatement ps = null;
		float price=0.0f;
		float sum_money=0.0f;
		int supplier_id=0;
		String prod_name = null;
		PurchaseSupplier psr=null;
		try {
			String  sql = "select * from product where status=0 and product_id=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, prod_id);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				price=rs.getFloat("original_price");
				supplier_id=rs.getInt("supplier_id");
				prod_name=rs.getString("product_name");
			}
			sum_money=price*pnum;
			psr=new PurchaseSupplier();
			psr.setDate(do_date);
			psr.setPrice(price);
			psr.setPur_num(pnum);
			psr.setProd_id(prod_id);
			psr.setSettle_status(0);
			psr.setSup_id(supplier_id);
			psr.setStatus(0);
			psr.setSum_price(sum_money);
			psr.setProd_name(prod_name);
			psr.setOrder_id(order_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return psr;
		
	}
	public List<ReturnSupplier> searchReturn(int supplier_id,String starttime,String endtime,int prod_id){
		PreparedStatement ps = null;
		List<ReturnSupplier> list=new ArrayList<ReturnSupplier>();
		ReturnSupplier rsr=null;
		try {
			String  sql = "select * from returnsup where date between ? and ? and supplier_id=? and prod_id=? and return_status=0";
			
			ps=conn.prepareStatement(sql);
			ps.setString(1, starttime);
			ps.setString(2, endtime);
			ps.setInt(3, supplier_id);
			ps.setInt(4, prod_id);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				rsr=new ReturnSupplier();
				rsr.setCriid(rs.getInt("criid"));
				System.out.println("hah"+rs.getInt("criid"));
				rsr.setPrice(rs.getFloat("price"));		
				rsr.setProd_id(rs.getInt("prod_id"));
				rsr.setProduct_name(rs.getString("product_name"));
				rsr.setRs_num(rs.getInt("rs_num"));
				rsr.setSupplier_id(rs.getInt("supplier_id"));
				rsr.setDate(rs.getString("date"));
				list.add(rsr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return list;		
		
	}
	
	
	public void returnSupplier(ReturnSupplier rsr){
		
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" insert into return_supplier(sup_id,prod_id,rs_num,date,status,settle_status,price) values (?,?,?,?,0,0,?)");
			ps.setInt(1, rsr.getSupplier_id());
			ps.setInt(2, rsr.getProd_id());
			ps.setInt(3, rsr.getRs_num());
			ps.setString(4, rsr.getDate());
			ps.setFloat(5, rsr.getPrice());
		
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		try {
			ps = conn.prepareStatement(" update cen_return_in_info set return_status=10 where cen_return_in_info_id=? ");
			ps.setInt(1, rsr.getCriid());
		
		
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}
}
