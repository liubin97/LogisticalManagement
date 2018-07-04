package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.po.Product;
import com.neuedu.utils.DBUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ManageProductDAOImp implements ManageProductDAO{
	Connection conn = null;
	public ManageProductDAOImp(Connection conn) {
		this.conn = conn ;
	}
	@Override
	public void addProduct(Product product) {

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into "
					+ "product(product_name,twotitle_id,measurement,type,vendor,operator"
					+ ",original_price,cost_price,discount,supplier_id,quality_period,is_return"
					+ ",is_exchange,status,operatordate,notes)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, product.getProduct_name());
			ps.setInt(2, product.getTwotitle_id());
			ps.setString(3, product.getMeasurement());
			ps.setString(4, product.getType());
			ps.setString(5, product.getVendor());
			ps.setString(6, product.getOperator());
			ps.setFloat(7, product.getOriginal_price());
			ps.setFloat(8, product.getCost_price());
			ps.setFloat(9, product.getDiscount());
			ps.setInt(10, product.getSupplier_id());
			ps.setDate(11, product.getQuality_period());
			ps.setInt(12, product.getIs_return());
			ps.setInt(13, product.getIs_exchange());
			ps.setInt(14, product.getStatus());
			ps.setDate(15, product.getOperatordate());
			ps.setString(16, product.getNotes());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
	}
	@Override
	public List<Product> selectProductByName(String product_name, int pageNum, int pageSize) {

		PreparedStatement ps = null;
		List<Product> list = new ArrayList<Product>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from product where status=0 ");
		if(product_name!=null&&!"".equals(product_name)) {
			sbf.append("and product_name = ?");
		}
		try {
			ps = conn.prepareStatement(sbf.toString()+" "
					+ "limit "
					+ (pageNum-1)*pageSize
					+","
					+pageSize);
			if(product_name!=null&&!"".equals(product_name)) {
				ps.setString(1,product_name);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setProduct_id(rs.getInt("product_id"));
				
				product.setProduct_name(rs.getString("product_name"));
				product.setNotes(rs.getString("notes"));
				product.setCost_price(rs.getFloat("cost_price"));
				product.setOriginal_price(rs.getFloat("original_price"));
				product.setDiscount(rs.getFloat("discount"));
				product.setIs_exchange(rs.getInt("is_exchange"));
				product.setIs_return(rs.getInt("is_return"));
				product.setMeasurement(rs.getString("measurement"));
				product.setQuality_period(rs.getDate("quality_period"));
				product.setSupplier_id(rs.getInt("supplier_id"));
				product.setTwotitle_id(rs.getInt("twotitle_id"));
				product.setType(rs.getString("type"));
				product.setVendor(rs.getString("vendor"));
				product.setStatus(rs.getInt("status"));
				product.setOperator(rs.getString("operator"));
				product.setOperatordate(rs.getDate("operatordate"));
				list.add(product);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return list;
	}
	@Override
	public int selectProductByNameCount(String product_name, int pageSize) {
		int count = 0;
		PreparedStatement ps = null;
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select count(*) cc from product where status=0 ");
		if(product_name!=null&&!"".equals(product_name)) {
			sbf.append("and product_name = ?");
		}
		try {
			ps = conn.prepareStatement(sbf.toString());
			if(product_name!=null&&!"".equals(product_name)) {
				ps.setString(1,product_name);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt("cc");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		int pagecount = 0 ;
		if(count%pageSize==0) {
			pagecount = count/pageSize;
		}else {
			pagecount = count/pageSize+1;
		}
		
		return pagecount;
	}
	@Override
	public List<Product> selectProductById(int product_id, int pageNum, int pageSize) {

				PreparedStatement ps = null;
				List<Product> list = new ArrayList<Product>();
				StringBuffer sbf = new StringBuffer("");
				sbf.append("select * from product where status=0 ");
				if(product_id!=0) {
					sbf.append("and product_id = ?");
				}
				try {
					ps = conn.prepareStatement(sbf.toString()+" "
							+ "limit "
							+ (pageNum-1)*pageSize
							+","
							+pageSize);
					if(product_id!=0) {
						ps.setInt(1,product_id);
					}
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						Product product = new Product();
						product.setProduct_id(rs.getInt("product_id"));
						product.setProduct_name(rs.getString("product_name"));
						product.setNotes(rs.getString("notes"));
						product.setCost_price(rs.getFloat("cost_price"));
						product.setOriginal_price(rs.getFloat("original_price"));
						product.setDiscount(rs.getFloat("discount"));
						product.setIs_exchange(rs.getInt("is_exchange"));
						product.setIs_return(rs.getInt("is_return"));
						product.setMeasurement(rs.getString("measurement"));
						product.setQuality_period(rs.getDate("quality_period"));
						product.setSupplier_id(rs.getInt("supplier_id"));
						product.setTwotitle_id(rs.getInt("twotitle_id"));
						product.setType(rs.getString("type"));
						product.setVendor(rs.getString("vendor"));
						product.setStatus(rs.getInt("status"));
						product.setOperator(rs.getString("operator"));
						product.setOperatordate(rs.getDate("operatordate"));
						list.add(product);
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}finally {
					DBUtil.closePS(ps);
				}
				return list;
	}
	@Override
	public int selectProductByIdCount(int product_id, int pageSize) {
		int count = 0;
		PreparedStatement ps = null;
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select count(*) cc from product where status=0 ");
		if(product_id!=0) {
			sbf.append("and product_id = ?");
		}
		try {
			ps = conn.prepareStatement(sbf.toString());
			if(product_id!=0) {
				ps.setInt(1,product_id);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt("cc");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		int pagecount = 0 ;
		if(count%pageSize==0) {
			pagecount = count/pageSize;
		}else {
			pagecount = count/pageSize+1;
		}
		
		return pagecount;
	}
	@Override
	public List<Product> selectProductByTwo(String twotitle_name,int pageNum, int pageSize) {

		PreparedStatement ps = null;
		List<Product> list = new ArrayList<Product>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from product where status=0 ");
		if(twotitle_name!=null&&!"".equals(twotitle_name)) {
			sbf.append("and twotitle_id = "
					+ "(select twotitle_id from twotitle where twotitle_name=? )");
		}
		try {
			ps = conn.prepareStatement(sbf.toString()+" "
					+ "limit "
					+ (pageNum-1)*pageSize
					+","
					+pageSize);
			if(twotitle_name!=null&&!"".equals(twotitle_name)) {
				ps.setString(1,twotitle_name);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setProduct_id(rs.getInt("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setNotes(rs.getString("notes"));
				product.setCost_price(rs.getFloat("cost_price"));
				product.setOriginal_price(rs.getFloat("original_price"));
				product.setDiscount(rs.getFloat("discount"));
				product.setIs_exchange(rs.getInt("is_exchange"));
				product.setIs_return(rs.getInt("is_return"));
				product.setMeasurement(rs.getString("measurement"));
				product.setQuality_period(rs.getDate("quality_period"));
				product.setSupplier_id(rs.getInt("supplier_id"));
				product.setTwotitle_id(rs.getInt("twotitle_id"));
				product.setType(rs.getString("type"));
				product.setVendor(rs.getString("vendor"));
				product.setStatus(rs.getInt("status"));
				product.setOperator(rs.getString("operator"));
				product.setOperatordate(rs.getDate("operatordate"));
				list.add(product);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return list;
	}
	@Override
	public int selectProductByTwoCount(String twotitle_name, int pageSize) {

		int count = 0;
		PreparedStatement ps = null;
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select count(*) cc from product where status=0 ");
		if(twotitle_name!=null&&!"".equals(twotitle_name)) {
			sbf.append("and twotitle_id = "
					+ "(select twotitle_id from twotitle where twotitle_name=? )");
		}
		try {
			ps = conn.prepareStatement(sbf.toString());
			if(twotitle_name!=null&&!"".equals(twotitle_name)) {
				ps.setString(1,twotitle_name);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt("cc");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		int pagecount = 0 ;
		if(count%pageSize==0) {
			pagecount = count/pageSize;
		}else {
			pagecount = count/pageSize+1;
		}
		
		return pagecount;
	}
	@Override
	public List<Product> selectProductByOne(String onetitle_name, int pageNum, int pageSize) {

				PreparedStatement ps = null;
				List<Product> list = new ArrayList<Product>();
				StringBuffer sbf = new StringBuffer("");
				sbf.append("select p.* from product p where p.status=0 ");
				if(onetitle_name!=null&&!"".equals(onetitle_name)) {
					sbf.append("and p.twotitle_id in(select t.twotitle_id from twotitle t where t.status=0  and t.onetitle_id =(select o.onetitle_id from onetitle o where o.status=0 and o.onetitle_name=?))");
				}
				try {
					ps = conn.prepareStatement(sbf.toString()+" "
							+ "limit "
							+ (pageNum-1)*pageSize
							+","
							+pageSize);
					if(onetitle_name!=null&&!"".equals(onetitle_name)) {
						ps.setString(1,onetitle_name);
					}
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						Product product = new Product();
						product.setProduct_id(rs.getInt("product_id"));
						product.setProduct_name(rs.getString("product_name"));
						product.setNotes(rs.getString("notes"));
						product.setCost_price(rs.getFloat("cost_price"));
						product.setOriginal_price(rs.getFloat("original_price"));
						product.setDiscount(rs.getFloat("discount"));
						product.setIs_exchange(rs.getInt("is_exchange"));
						product.setIs_return(rs.getInt("is_return"));
						product.setMeasurement(rs.getString("measurement"));
						product.setQuality_period(rs.getDate("quality_period"));
						product.setSupplier_id(rs.getInt("supplier_id"));
						product.setTwotitle_id(rs.getInt("twotitle_id"));
						product.setType(rs.getString("type"));
						product.setVendor(rs.getString("vendor"));
						product.setStatus(rs.getInt("status"));
						product.setOperator(rs.getString("operator"));
						product.setOperatordate(rs.getDate("operatordate"));
						list.add(product);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					DBUtil.closePS(ps);
				}
				return list;
	}
	@Override
	public int selectProductByOneCount(String onetitle_name, int pageSize) {
				int count = 0;
				PreparedStatement ps = null;	
				StringBuffer sbf = new StringBuffer("");
				sbf.append("select count(*) cc from product where status=0 ");
				if(onetitle_name!=null&&!"".equals(onetitle_name)) {
					sbf.append("and p.twotitle_id in(select t.twotitle_id from twotitle t where t.status=0  and t.onetitle_id =(select o.onetitle_id from onetitle o where o.status=0 and o.onetitle_name=?))");
				}
				try {
					ps = conn.prepareStatement(sbf.toString());
					if(onetitle_name!=null&&!"".equals(onetitle_name)) {
						ps.setString(1,onetitle_name);
					}
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						count = rs.getInt("cc");
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}finally {
					DBUtil.closePS(ps);
				}
				int pagecount = 0 ;
				if(count%pageSize==0) {
					pagecount = count/pageSize;
				}else {
					pagecount = count/pageSize+1;
				}
				
				return pagecount;
	}
	@Override
	public Product selectProductById(int product_id) {
		PreparedStatement ps = null;
		Product product = new Product();
		try {
			ps = conn.prepareStatement("select * from product where product_id=?");
			ps.setInt(1, product_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				product.setProduct_id(rs.getInt("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setNotes(rs.getString("notes"));
				product.setCost_price(rs.getFloat("cost_price"));
				product.setOriginal_price(rs.getFloat("original_price"));
				product.setDiscount(rs.getFloat("discount"));
				product.setIs_exchange(rs.getInt("is_exchange"));
				product.setIs_return(rs.getInt("is_return"));
				product.setMeasurement(rs.getString("measurement"));
				product.setQuality_period(rs.getDate("quality_period"));
				product.setSupplier_id(rs.getInt("supplier_id"));
				product.setTwotitle_id(rs.getInt("twotitle_id"));
				product.setType(rs.getString("type"));
				product.setVendor(rs.getString("vendor"));
				product.setStatus(rs.getInt("status"));
				product.setOperator(rs.getString("operator"));
				product.setOperatordate(rs.getDate("operatordate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		
		return product;
	}
	@Override
	public JSONArray supplierList() {
		PreparedStatement ps = null;
		JSONArray suppliers = new JSONArray();
		try {
			ps = conn.prepareStatement("select supplier_id,supplier_name from supplier where status=0");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject json = new JSONObject();
				json.put("supplier_id", rs.getInt("supplier_id"));
				json.put("supplier_name", rs.getString("supplier_name"));
				suppliers.add(json);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return suppliers;
	}
	@Override
	public String selectSupplierNameById(int supplier_id) {
		PreparedStatement ps = null;
		String supplierName = "";
		try {
			
			ps = conn.prepareStatement("select supplier_name from supplier where status=0 and supplier_id=?");
			ps.setInt(1, supplier_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {	
				supplierName = rs.getString("supplier_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return supplierName;
	}
	@Override
	public void deleteProduct(int product_id) {
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement("update product set status=1 where product_id=?");
			ps.setInt(1, product_id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		
	}
	@Override
	public void updateProduct(Product product) {
		
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("update product set "
					+ "product_name=?,measurement=?,type=?,vendor=?,operator=?,original_price=?"
					+ ",cost_price=?,discount=?,supplier_id=?,quality_period=?,is_return=?"
					+ ",is_exchange=?,status=?,operatordate=?,notes=?,twotitle_id=?"
					+ " where product_id=?");
			ps.setString(1, product.getProduct_name());
			ps.setString(2, product.getMeasurement());
			ps.setString(3, product.getType());
			ps.setString(4, product.getVendor());
			ps.setString(5, product.getOperator());
			ps.setFloat(6, product.getOriginal_price());
			ps.setFloat(7, product.getCost_price());
			ps.setFloat(8, product.getDiscount());
			ps.setInt(9, product.getSupplier_id());
			ps.setDate(10, product.getQuality_period());
			ps.setInt(11, product.getIs_return());
			ps.setInt(12, product.getIs_exchange());
			ps.setInt(13, product.getStatus());
			ps.setDate(14, product.getOperatordate());
			ps.setString(15, product.getNotes());
			ps.setInt(16, product.getTwotitle_id());
			ps.setInt(17, product.getProduct_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		
	}
	@Override
	public List<Product> selectProductByName(String product_name) {
		PreparedStatement ps = null;
		List<Product> list = new ArrayList<Product>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from product where status=0 ");
		if(product_name!=null&&!"".equals(product_name)) {
			sbf.append("and product_name = ?");
		}
		try {
			ps = conn.prepareStatement(sbf.toString());
			if(product_name!=null&&!"".equals(product_name)) {
				ps.setString(1,product_name);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setProduct_id(rs.getInt("product_id"));
				
				product.setProduct_name(rs.getString("product_name"));
				product.setNotes(rs.getString("notes"));
				product.setCost_price(rs.getFloat("cost_price"));
				product.setOriginal_price(rs.getFloat("original_price"));
				product.setDiscount(rs.getFloat("discount"));
				product.setIs_exchange(rs.getInt("is_exchange"));
				product.setIs_return(rs.getInt("is_return"));
				product.setMeasurement(rs.getString("measurement"));
				product.setQuality_period(rs.getDate("quality_period"));
				product.setSupplier_id(rs.getInt("supplier_id"));
				product.setTwotitle_id(rs.getInt("twotitle_id"));
				product.setType(rs.getString("type"));
				product.setVendor(rs.getString("vendor"));
				product.setStatus(rs.getInt("status"));
				product.setOperator(rs.getString("operator"));
				product.setOperatordate(rs.getDate("operatordate"));
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return list;
	}
}
