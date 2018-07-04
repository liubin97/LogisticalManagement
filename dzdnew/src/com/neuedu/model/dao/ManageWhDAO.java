package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.Warehouse;

public interface ManageWhDAO {

	void addWarehouse(Warehouse wh);

	Warehouse haveCenterWarehouse();

	List<Warehouse> searchWarehouse(String name,int pageno);

	int calPagecount(String name);

	void deleteWarehouse(int whid,String operator);

	int haveProductIn(int whid);

	void modifyWarehouse(Warehouse wh);

}
