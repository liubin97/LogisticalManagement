package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.*;
import com.neuedu.*;
import com.neuedu.utils.DBUtil;


public class ManageResDAOImp implements ManageResDAO{

	
	Connection conn;
	
	public ManageResDAOImp(Connection conn) {
			this.conn = conn;
		}
	
	public void addRes(ReserveInfo res){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" insert into wh_reserve_info(wh_id,prod_id,warn_num,max_num,status,operator,operatordate,res_num) values (?,?,?,?,?,?,?,?)");
			ps.setInt(1, res.getWh_id());
			ps.setInt(2, res.getPro_id());
			ps.setInt(3, res.getWarn_num());
			ps.setInt(4, res.getMax_num());
			ps.setInt(5, res.getStatus());
			ps.setString(6, res.getOperator());
			ps.setString(7,res.getOperatordate());
			ps.setInt(8,res.getRes_num());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}
	public void updateRes(ReserveInfo res){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update wh_reserve_info set warn_num=?,max_num=?,operator=?,operatordate=? where wh_id=? and prod_id=? and status=0 ");
			ps.setInt(1, res.getWarn_num());
			ps.setInt(2, res.getMax_num());
			ps.setString(3, res.getOperator());
			ps.setString(4,res.getOperatordate());
			ps.setInt(5,res.getWh_id());
			ps.setInt(6, res.getPro_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}
	
	public boolean haveRes(ReserveInfo res){
		PreparedStatement ps = null;
		try {
			String  sql = "select * from wh_reserve_info where status=0 and wh_id=? and prod_id=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, res.getWh_id());
			ps.setInt(2, res.getPro_id());
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				return true;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return false;
	}

	@Override
	public String acProname(int pro_id) {
		// TODO 自动生成的方法存根
		PreparedStatement ps = null;
		String name="";
		try {
			String  sql = "select * from product where status=0 and product_id=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pro_id);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				name=rs.getString("product_name");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return name;
	}

	@Override
	public String acWhname(int wh_id) {
		// TODO 自动生成的方法存根
		PreparedStatement ps = null;
		String name="";
		try {
			String  sql = "select * from warehouse where status=0 and wh_id=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, wh_id);
			ResultSet rs= ps.executeQuery();
			System.out.println("fdasiodfjioa");
			if(rs.next()){
				System.out.println("有致");
				name=rs.getString("wh_name");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return name;
	}

	

}
