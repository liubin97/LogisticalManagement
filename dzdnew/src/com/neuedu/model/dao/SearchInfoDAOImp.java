package com.neuedu.model.dao;

import java.sql.Connection;
import com.neuedu.*;
import com.neuedu.model.po.HotProduct;
import com.neuedu.model.po.Implementation;
import com.neuedu.model.po.InOutList;
import com.neuedu.model.po.ResNumInfo;
import com.neuedu.model.po.Satisfaction;
import com.neuedu.model.po.Warehouse;
import com.neuedu.utils.DBUtil;
import com.sun.istack.internal.Pool.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchInfoDAOImp implements SearchInfoDAO{

	Connection conn;
	
	public SearchInfoDAOImp(Connection conn){
		this.conn = conn;
	}
	public List<ResNumInfo> searchResNum(String proname,int pageno){
		PreparedStatement ps = null;
		List<ResNumInfo> list=new ArrayList<ResNumInfo>();
		ResNumInfo rni=null;
		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("  select * from  resnuminfo where 1=1 ");
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
				rni=new ResNumInfo();
				rni.setWhname(rs.getString("wh_name"));
				rni.setProname(rs.getString("product_name"));
				rni.setRes_num(rs.getString("res_num"));
				rni.setReturn_num(rs.getString("return_num"));
				rni.setCan_distribute(rs.getString("can_distribute"));
				rni.setHas_distribute(rs.getString("has_distribute"));
				
				list.add(rni);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return list;
		
	}
	
	public int calPagecountRes(String proname){
		PreparedStatement ps = null;
		List<ResNumInfo> list=new ArrayList<ResNumInfo>();
		ResNumInfo rni=null;
		int count=0;
		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("  select count(*) cc from  resnuminfo where 1=1 ");
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
	public List<InOutList> searchInOut(String startdate,String enddate,int proid,String type,int pageno){
		PreparedStatement ps = null;
		List<InOutList> list=new ArrayList<InOutList>();
		
		System.out.println("有出入单");
		if("入库".equals(type)){
		try {
			InOutList iol=null;
			StringBuffer sbf = new StringBuffer("");
			sbf.append("select * from( (select *  from  cen_return_in where do_date between ? and ? and prod_id=? )");
			sbf.append("union all");
			sbf.append("(select *  from  cen_warehouse_in where do_date between ? and ? and prod_id=?)");
			sbf.append("union all");
			sbf.append("(select *  from  sub_warehouse_in where do_date between ? and ? and prod_id=?)) as a ");
			sbf.append("limit ?,?");
			ps = conn.prepareStatement(sbf.toString());
			ps.setString(1, startdate);
			ps.setString(2, enddate);
			ps.setInt(3, proid);
			ps.setString(4, startdate);
			ps.setString(5, enddate);
			ps.setInt(6, proid);
			ps.setString(7, startdate);
			ps.setString(8, enddate);
			ps.setInt(9, proid);
				int start=(pageno-1)*5;
				int end=pageno*5;
				ps.setInt(10, start);
				ps.setInt(11, 5);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				iol=new InOutList();
				iol.setProduct_id(rs.getInt("prod_id"));
				iol.setProduct_name(rs.getString("product_name"));
				iol.setAllprice(rs.getFloat("sum_money"));
				iol.setAmount(rs.getInt("amount"));
				iol.setDate(rs.getString("do_date"));
				iol.setPrice(rs.getFloat("original_price"));
				iol.setWh_id(rs.getInt("wh_id"));
				iol.setWh_name(rs.getString("wh_name"));
				iol.setType(type);
				iol.setListid(rs.getString("listid"));
				list.add(iol);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
		}
		
		if("出库".equals(type)){
			
			try {
				InOutList iol=null;
				StringBuffer sbf = new StringBuffer("");
				sbf.append("select * from ((select *  from  cen_return_out where do_date between ? and ? and prod_id=? )");
				sbf.append(" union all ");
				sbf.append("(select *  from  cen_warehouse_out where do_date between ? and ? and prod_id=?)");
				sbf.append(" union all ");
				sbf.append("(select *  from  sub_return_out where do_date between ? and ? and prod_id=?)) as a ");
				sbf.append(" limit ?,?");
				ps = conn.prepareStatement(sbf.toString());
				
				ps.setString(1, startdate);
				ps.setString(2, enddate);
				ps.setInt(3, proid);
				ps.setString(4, startdate);
				ps.setString(5, enddate);
				ps.setInt(6, proid);
				ps.setString(7, startdate);
				ps.setString(8, enddate);
				ps.setInt(9, proid);
					int start=(pageno-1)*5;
					int end=pageno*5;
					ps.setInt(10, start);
					ps.setInt(11, 5);
					System.out.println(ps.toString());
				ResultSet rs= ps.executeQuery();
				System.out.println("有出入单");
				while(rs.next()){
					System.out.println("有出入单");
					iol=new InOutList();
					iol.setProduct_id(rs.getInt("prod_id"));
					iol.setProduct_name(rs.getString("product_name"));
					iol.setAllprice(rs.getFloat("sum_money"));
					iol.setAmount(rs.getInt("amount"));
					iol.setDate(rs.getString("do_date"));
					iol.setPrice(rs.getFloat("original_price"));
					iol.setWh_id(rs.getInt("wh_id"));
					iol.setWh_name(rs.getString("wh_name"));
					iol.setType(type);
					iol.setListid(rs.getString("listid"));
					System.out.println(iol.getProduct_name()+"123");
					list.add(iol);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.closePS(ps);
			}
			
		}
		return list;
		
	}
	
	
	public int calPagecountInOut(String startdate,String enddate,int proid,String type){
		PreparedStatement ps = null;
		List<InOutList> list=new ArrayList<InOutList>();
		InOutList iol=null;
		int count=0;
		if("入库".equals(type)){
		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("select count(*) cc from ((select *  from  cen_return_in where do_date between ? and ? and prod_id=? )");
			sbf.append("union all");
			sbf.append("(select *  from  cen_warehouse_in where do_date between ? and ? and prod_id=?)");
			sbf.append("union all");
			sbf.append("(select *  from  sub_warehouse_in where do_date between ? and ? and prod_id=?)) as a");
			
			ps = conn.prepareStatement(sbf.toString());
			ps.setString(1, startdate);
			ps.setString(2, enddate);
			ps.setInt(3, proid);
			ps.setString(4, startdate);
			ps.setString(5, enddate);
			ps.setInt(6, proid);
			ps.setString(7, startdate);
			ps.setString(8, enddate);
			ps.setInt(9, proid);
				
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("cc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
		}
		
		if("出库".equals(type)){
			
			try {
				StringBuffer sbf = new StringBuffer("");
				sbf.append("select count(*) cc from ((select *  from  cen_return_out where do_date between ? and ? and prod_id=? )");
				sbf.append("union all");
				sbf.append("(select *  from  cen_warehouse_out where do_date between ? and ? and prod_id=?)");
				sbf.append("union all");
				sbf.append("(select *  from  sub_return_out where do_date between ? and ? and prod_id=?)) as a");
				
				ps = conn.prepareStatement(sbf.toString());
				ps.setString(1, startdate);
				ps.setString(2, enddate);
				ps.setInt(3, proid);
				ps.setString(4, startdate);
				ps.setString(5, enddate);
				ps.setInt(6, proid);
				ps.setString(7, startdate);
				ps.setString(8, enddate);
				ps.setInt(9, proid);
					
				ResultSet rs= ps.executeQuery();
				if(rs.next()){
					count = rs.getInt("cc");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.closePS(ps);
			}
			
		}
		int pagecount = 0;
		if(count%5==0){
			pagecount = count/5;
		}else{
			pagecount = count/5+1;
		}
		return pagecount;
		
	}
	
	public List<HotProduct> searchHotProduct(String startdate,String enddate){
		
		PreparedStatement ps = null;
		List<HotProduct> list=new ArrayList<HotProduct>();
		
		
		try {
			HotProduct hp=null;
			StringBuffer sbf = new StringBuffer("");
			sbf.append("select * from ((select product_id,product_name,onetitle_name,twotitle_name,original_price,cost_price,discount,supplier_name,sum(amount) sum from hotproduct where create_date between ? and ? group by product_id order by sum desc) as a) limit 0,5");
		
			ps = conn.prepareStatement(sbf.toString());
			ps.setString(1, startdate);
			ps.setString(2, enddate);
		
				
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				hp=new HotProduct();
				hp.setProduct_id(rs.getInt("product_id"));
				hp.setProduct_name(rs.getString("product_name"));
				hp.setOnetitle(rs.getString("onetitle_name"));
				hp.setTwotitle(rs.getString("twotitle_name"));
				hp.setSupplier_name(rs.getString("supplier_name"));
				hp.setCost_price(rs.getFloat("cost_price"));
				hp.setDiscount(rs.getFloat("discount"));
				hp.setSellnum(rs.getInt("sum"));
				hp.setOriginal_price(rs.getFloat("original_price"));
				
				list.add(hp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
		return list;
		
	}
	
	public List<Implementation> searchImplement(String startdate,String enddate){
		PreparedStatement ps = null;
		List<Implementation> list=new ArrayList<Implementation>();
		
		
		try {
			Implementation im=null;
			StringBuffer sbf = new StringBuffer("");
			sbf.append("select wh_id,wh_name,sum(sum_money) sum1,sum(amount) sum2,count(*) cc from implementation where do_date between ? and ? group by wh_id");
		
			ps = conn.prepareStatement(sbf.toString());
			ps.setString(1, startdate);
			ps.setString(2, enddate);
		
				
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				im=new Implementation();
				im.setWh_id(rs.getInt("wh_id"));
				im.setWh_name(rs.getString("wh_name"));
				im.setCc(rs.getInt("cc"));
				im.setSum_amount(rs.getInt("sum2"));
				im.setSum_money(rs.getInt("sum1"));
				
				
				list.add(im);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
		return list;
	}
	
	public List<Satisfaction> searchSatisfaction(String startdate,String enddate,int pageno){
		PreparedStatement ps = null;
		List<Satisfaction> list=new ArrayList<Satisfaction>();
		Satisfaction sf=null;
		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("select * from satisfaction where do_date between ? and ?");
		
			 ps = conn.prepareStatement(sbf.toString()+" limit ?,?");
		
				int start=(pageno-1)*5;
				int end=pageno*5;
				ps.setString(1, startdate);
				ps.setString(2, enddate);
				ps.setInt(3, start);
				ps.setInt(4, 5);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				sf=new Satisfaction();
				sf.setProd_id(rs.getInt("prod_id"));
				sf.setProduct_name(rs.getString("product_name"));
				sf.setSatisfaction(rs.getString("satisfaction"));
				sf.setSum_money(rs.getFloat("sum_money"));
				sf.setTask_list_id(rs.getInt("task_list_id"));
				sf.setClient_name(rs.getString("client_name"));
				list.add(sf);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return list;
		
		
	}

	public int calPagecountSf(String startdate,String enddate){
		PreparedStatement ps = null;
		int count=0;
		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("select count(*) cc from satisfaction where do_date between ? and ?");
		
			 ps = conn.prepareStatement(sbf.toString());
		

				ps.setString(1, startdate);
				ps.setString(2, enddate);
	
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
	
}
