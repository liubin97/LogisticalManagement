package com.neuedu.model.service;

import java.sql.Connection;
import java.util.List;

import com.neuedu.model.dao.ManageProductDAO;
import com.neuedu.model.dao.ManageProductDAOImp;

import com.neuedu.model.po.Product;
import com.neuedu.utils.DBUtil;

import net.sf.json.JSONArray;


public class ManageProductService {
	private ManageProductService(){
		
	}
	private static ManageProductService service = new ManageProductService();
	public static ManageProductService getInstance() {
		return service;
	}
	public void addProduct(Product product) {
		Connection conn = DBUtil.getConn();
		DBUtil.beginTransaction(conn);
		try {
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		dao.addProduct(product);
		DBUtil.commit(conn);
		}catch(Exception e) {
			DBUtil.rollback(conn);
		}finally {
			DBUtil.closeConn(conn);
		}	
	}
	public List<Product> selectProductByName(String product_name, int pageNum, int pageSize) {
		Connection conn = DBUtil.getConn();
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		List<Product> list = dao.selectProductByName(product_name,pageNum,pageSize);
		DBUtil.closeConn(conn);
		return list;
	}
	public int selectProductByNameCount(String product_name, int pageSize) {
		Connection conn = DBUtil.getConn();
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		int count = dao.selectProductByNameCount(product_name,pageSize);
		DBUtil.closeConn(conn);
		return count;
	}
	public List<Product> selectProductById(int product_id, int pageNum, int pageSize) {
		Connection conn = DBUtil.getConn();
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		List<Product> list = dao.selectProductById(product_id,pageNum,pageSize);
		DBUtil.closeConn(conn);
		return list;
	
	}
	public int selectProductByIdCount(int product_id, int pageSize) {
		Connection conn = DBUtil.getConn();
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		int count = dao.selectProductByIdCount(product_id,pageSize);
		DBUtil.closeConn(conn);
		return count;
	}
	public List<Product> selectProductByTwo(String twotitle_name, int pageNum, int pageSize) {
		Connection conn = DBUtil.getConn();
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		List<Product> list = dao.selectProductByTwo(twotitle_name,pageNum,pageSize);
		DBUtil.closeConn(conn);
		return list;
	}
	public int selectProductByTwoCount(String twotitle_name, int pageSize) {
		Connection conn = DBUtil.getConn();
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		int count = dao.selectProductByTwoCount(twotitle_name,pageSize);
		DBUtil.closeConn(conn);
		return count;
	}
	public List<Product> selectProductByOne(String onetitle_name, int pageNum, int pageSize) {
		Connection conn = DBUtil.getConn();
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		List<Product> list = dao.selectProductByOne(onetitle_name,pageNum,pageSize);
		DBUtil.closeConn(conn);
		return list;
	}
	public int selectProductByOneCount(String onetitle_name, int pageSize) {
		Connection conn = DBUtil.getConn();
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		int count = dao.selectProductByOneCount(onetitle_name,pageSize);
		DBUtil.closeConn(conn);
		return count;
	}
	public Product selectProductById(int product_id) {
		Connection conn = DBUtil.getConn();
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		Product product = dao.selectProductById(product_id);
		DBUtil.closeConn(conn);
		return product;
	}
	public JSONArray supplierList() {
		Connection conn = DBUtil.getConn();
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		JSONArray gson = dao.supplierList();
		DBUtil.closeConn(conn);
		return gson;
	}
	public String selectSupplierNameById(int supplier_id) {
		Connection conn = DBUtil.getConn();
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		String supplierName = dao.selectSupplierNameById(supplier_id);
		DBUtil.closeConn(conn);
		return supplierName;
	}
	public void deleteProduct(int product_id) {
				Connection conn = DBUtil.getConn();
				DBUtil.beginTransaction(conn);
				try {
				ManageProductDAO dao = new ManageProductDAOImp(conn);
				dao.deleteProduct(product_id);
				DBUtil.commit(conn);
				}catch(Exception e) {
					DBUtil.rollback(conn);
				}finally {
					DBUtil.closeConn(conn);
				}
		
	}
	public void updateProduct(Product product) {
		Connection conn = DBUtil.getConn();
		DBUtil.beginTransaction(conn);
		try {
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		dao.updateProduct(product);
		DBUtil.commit(conn);
		}catch(Exception e) {
			DBUtil.rollback(conn);
		}finally {
			DBUtil.closeConn(conn);
		}
	}
	public List<Product> selectProductByName(String product_name) {
		Connection conn = DBUtil.getConn();
		ManageProductDAO dao = new ManageProductDAOImp(conn);
		List<Product> list = dao.selectProductByName(product_name);
		DBUtil.closeConn(conn);
		return list;
	}
}
