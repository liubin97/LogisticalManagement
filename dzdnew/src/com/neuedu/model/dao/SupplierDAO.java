package com.neuedu.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.po.Supplier;

public interface SupplierDAO {
	
	/*
	public int defaultPageSum(int pageSize);

	public List<Supplier> defaultDisplayAll(int pageSize, int defaultPageSum);
	*/
	
	public void register(Supplier supplier) throws SQLException;
	
	
	public int pageSum(String supplierName, String supplierAddress, String contactName, String contactPhone, String bankOfRegistration, String bankAccount, String faxNo, String zipCode, String legalPerson, String status, int pageSize);
	
	public List<Supplier> querySupplier(String supplierName, String supplierAddress, String contactName, String contactPhone, String bankOfRegistration, String bankAccount, String faxNo, String zipCode, String legalPerson, String status, int pageSize,
			int pageNum);
	

	
	public void modifySupplier(String sN, Supplier supplier);
	
	public void deleteSupplier(String supplierName);
	
	public void deleteSupplier(String[] supplierNames);
	
	public void restoreSupplier(String supplierName);
	
	public void restoreSupplier(String[] supplierNames);
	
	public Supplier getSupplierBySupplierName(String supplierName);


	public int validateSupplierName(String sName);

	



}
