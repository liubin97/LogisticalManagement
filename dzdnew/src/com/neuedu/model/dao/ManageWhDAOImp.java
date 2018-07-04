package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.Manager;
import com.neuedu.model.po.Warehouse;
import com.neuedu.utils.DBUtil;

public class ManageWhDAOImp implements ManageWhDAO{

		Connection conn;
	
	public ManageWhDAOImp(Connection conn) {
			this.conn = conn;
		}



	public void addWarehouse(Warehouse wh) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" insert into warehouse(wh_name,wh_addr,wh_admin,wh_level,status,operator,operator_date) values (?,?,?,?,?,?,?)");
			ps.setString(1, wh.getWh_name());
			ps.setString(2, wh.getWh_addr());
			ps.setString(3, wh.getWh_admin());
			ps.setInt(4, wh.getWh_level());
			ps.setInt(5, wh.getStatus());
			ps.setString(6, wh.getOperator());
			ps.setString(7,wh.getOperator_date());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}
	
	public Warehouse haveCenterWarehouse() {
		PreparedStatement ps = null;
		Warehouse wh=null;
		try {
			String  sql = "select * from warehouse where status=0 and wh_level=0";
			ps=conn.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				System.out.println("youzhongxin");
				wh=new Warehouse();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return wh;
	}
	public List<Warehouse> searchWarehouse(String name,int pageno){
		PreparedStatement ps = null;
		List<Warehouse> list=new ArrayList<Warehouse>();
		Warehouse wh=null;
		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("  select *  from  warehouse where 1=1 and status=0 ");
			if(name != null && !"".equals(name)){
				sbf.append(" and wh_name=? ");
			}
			 ps = conn.prepareStatement(sbf.toString()+" limit ?,?");
			int index=1;
				if(name != null && !"".equals(name)){
					ps.setString(index, name);
					index++;
				}
				int start=(pageno-1)*5;
				int end=pageno*5;
				ps.setInt(index, start);
				index++;
				ps.setInt(index, 5);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				wh=new Warehouse();
				wh.setWh_id(rs.getInt("wh_id"));
				wh.setWh_name(rs.getString("wh_name"));
				wh.setWh_addr(rs.getString("wh_addr"));
				wh.setWh_admin(rs.getString("wh_admin"));
				wh.setWh_level(rs.getInt("wh_level"));
				wh.setStatus(rs.getInt("status"));
				wh.setOperator(rs.getString("operator"));
				wh.setOperator_date(rs.getString("operator_date"));
				list.add(wh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return list;
		
	}
	public int calPagecount(String name){
		PreparedStatement ps = null;
		List<Warehouse> list=new ArrayList<Warehouse>();
		Warehouse wh=null;
		int count=0;
		try {
			StringBuffer sbf = new StringBuffer("");
			sbf.append("  select count(*) cc  from  warehouse where 1=1 and status=0");
			if(name != null && !"".equals(name)){
				sbf.append(" and wh_name=? ");
			}
			 ps = conn.prepareStatement(sbf.toString());
			int index=1;
				if(name != null && !"".equals(name)){
					ps.setString(index, name);
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
	public void deleteWarehouse(int whid,String operator){
		
		Date   now   =   new   Date();    
		SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy-MM-dd ");//可以方便地修改日期格式    
		String   operator_date   =   dateFormat.format(   now   );  
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update warehouse set status=1,operator=?,operator_date=? where wh_id=? ");
			ps.setString(1, operator);
			ps.setString(2, operator_date);
			ps.setInt(3,whid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}
	public int haveProductIn(int whid){
		PreparedStatement ps = null;
		int num=0;
		try {
			String  sql = "select sum(res_num) ss from wh_reserve_info where status=0 and wh_id=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, whid);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				num=rs.getInt("ss");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return num;
	}
	public void modifyWarehouse(Warehouse wh){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update warehouse set wh_name=?,wh_addr=?,wh_admin=?,wh_level=?,status=?,operator=?,operator_date=? where wh_id=? and status=0 ");
			ps.setString(1, wh.getWh_name());
			ps.setString(2, wh.getWh_addr());
			ps.setString(3, wh.getWh_admin());
			ps.setInt(4, wh.getWh_level());
			ps.setInt(5, wh.getStatus());
			ps.setString(6, wh.getOperator());
			ps.setString(7,wh.getOperator_date());
			ps.setInt(8, wh.getWh_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}



	
}
