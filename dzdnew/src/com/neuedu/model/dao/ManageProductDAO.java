package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ManageProductDAO {

	void addProduct(Product product);

	List<Product> selectProductByName(String product_name, int pageNum, int pageSize);

	int selectProductByNameCount(String product_name, int pageSize);

	List<Product> selectProductById(int product_id, int pageNum, int pageSize);

	int selectProductByIdCount(int product_id, int pageSize);

	List<Product> selectProductByTwo(String twotitle_name, int pageSize, int pageSize2);

	int selectProductByTwoCount(String twotitle_name, int pageSize);

	List<Product> selectProductByOne(String onetitle_name, int pageNum, int pageSize);

	int selectProductByOneCount(String onetitle_name, int pageSize);

	Product selectProductById(int product_id);

	JSONArray supplierList();

	String selectSupplierNameById(int supplier_id);

	void deleteProduct(int product_id);

	void updateProduct(Product product);

	List<Product> selectProductByName(String product_name);

}
