package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.po.PaymentResult;
import com.neuedu.utils.DBUtil;
import com.neuedu.utils.DateUtil;


//缴款查询接口实现
public class PaymentDaoImp implements PaymentDao {
	
	Connection conn;
	
	public PaymentDaoImp(Connection conn){
		this.conn = conn;
	}
	//根据task_list表order表product表分组查询该分站每个商品在一段时间内的总金额总数量
	public List<PaymentResult> getPayment(String begintime,String endtime,int substation_id,int pageNum){
		List<PaymentResult> list = new ArrayList<PaymentResult>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("SELECT `order`.prod_id, product.product_name, sum(`order`.amount) AS amount , sum(`order`.sum_money) "
				+ "AS summoney FROM	`order`,task_list,product WHERE task_list.order_id = `order`.order_id "
				+ "AND `order`.prod_id = product.product_id AND `order`. STATUS = 4 " + 
				"AND `order`.type = 1 AND task_list.substation_id = ? AND ( `order`.finish_date BETWEEN ? AND ?) "
				+ " GROUP BY `order`.prod_id");
		try {
			
			ps = conn.prepareStatement( " select a.* from ( "
					+ sbf.toString() 
					+ " )a limit ? , ? ");
			
			ps.setInt(1, substation_id);
			ps.setDate(2,DateUtil.strToDate(begintime) );
			ps.setDate(3, DateUtil.strToDate(endtime));
			ps.setInt(4, 5*(pageNum-1));
			ps.setInt(5, 5);
			
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next()) {
				PaymentResult pr = new PaymentResult();
				
				pr.setAmount(rs.getInt("amount"));
				pr.setProd_id(rs.getInt("prod_id"));
				pr.setProduct_name(rs.getString("product_name"));
				pr.setSummoney(rs.getFloat("summoney"));
				
				pr.setSubstation_id(substation_id);
				
				list.add(pr);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return list;
	}
	
	//查询出记录一共占多少页
	public int selectPageCount(String begintime,String endtime,int substation_id) {
		int count=0;
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select count(*)cc from `order`,task_list where task_list.order_id = `order`.order_id ");
		sbf.append(" and `order`.status = 4 ");
		sbf.append(" and `order`.type = 1 ");
		sbf.append("and task_list.substation_id = ? ");
		sbf.append("and `order`.finish_date between ? and ? ");
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			ps.setInt(1, substation_id);
			ps.setDate(2,DateUtil.strToDate(begintime) );
			ps.setDate(3, DateUtil.strToDate(endtime));
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("cc");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
