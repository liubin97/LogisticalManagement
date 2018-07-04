package com.neuedu.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.dao.SupplierDAO;
import com.neuedu.model.dao.SupplierDAOImp;
import com.neuedu.model.po.Supplier;
import com.neuedu.utils.DBUtil;



public class SupplierService {
	private SupplierService() {
	}
	
	private static SupplierService service = new SupplierService();
	
	public static SupplierService getInstance() {
		return service;
	}
	
	/*
	//Ĭ��ҳ��
	public int defaultPageSum(int pageSize) {
		Connection conn = DBUtil.getConn();
		SupplierDAO dao = new SupplierDAOImp(conn);
		return dao.defaultPageSum(pageSize);
	}
	*/
	
	/*
	//Ĭ����ʾ���й�Ӧ����Ϣ
	public List<Supplier> defaultDisplayAll(int pageSize, int defaultPageSum) {
		Connection conn = DBUtil.getConn();
		SupplierDAO dao = new SupplierDAOImp(conn);
		return dao.defaultDisplayAll(pageSize, defaultPageSum);
	}
	*/
	
	
	
	//ע���µĹ�Ӧ��
	public void register(Supplier supplier) throws ClassNotFoundException, SQLException{
		//��ȡ�����ݿ������
		Connection conn = DBUtil.getConn();
		//��������
		DBUtil.beginTransaction(conn);
		try {
			SupplierDAO dao = new SupplierDAOImp(conn);
			dao.register(supplier);
			//�ύ
			DBUtil.commit(conn);
		} catch (Exception e) {
			//�ع�
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	
	
	//��ҳ��ѯ
	public List<Supplier>  querySupplier(String supplierName, String supplierAddress, String contactName, String contactPhone, String bankOfRegistration, String bankAccount, String faxNo, String zipCode, String legalPerson, String status, int pageSize, int pageNum){
		Connection conn = DBUtil.getConn();
		SupplierDAO dao = new SupplierDAOImp(conn);
		return dao.querySupplier(supplierName, supplierAddress, contactName, contactPhone, bankOfRegistration, bankAccount, faxNo, zipCode, legalPerson, status, pageSize, pageNum);
	}
	
	//��ѯҳ��
	public int pageSum(String supplierName, String supplierAddress, String contactName, String contactPhone, String bankOfRegistration, String bankAccount, String faxNo, String zipCode, String legalPerson, String status, int pageSize) {
		Connection conn = DBUtil.getConn();
		SupplierDAO dao = new SupplierDAOImp(conn);
		return dao.pageSum(supplierName, supplierAddress, contactName, contactPhone, bankOfRegistration, bankAccount, faxNo, zipCode, legalPerson, status, pageSize);
	}
	
	
	//���¹�Ӧ����Ϣ
	public void modifySupplier(String sN, Supplier supplier) {
		//��ȡ�����ݿ������
		Connection conn = DBUtil.getConn();
		//��������
		DBUtil.beginTransaction(conn);
		try {
			SupplierDAO dao = new SupplierDAOImp(conn);
			dao.modifySupplier(sN, supplier);
			//�ύ
			DBUtil.commit(conn);
		} catch (Exception e) {
			//�ع�
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	
	
	//����ɾ��
	public  void  deleteSupplier(String SupplierName){
		//��ȡ�����ݿ������
		Connection conn = DBUtil.getConn();
		//��������
		DBUtil.beginTransaction(conn);
		try {
			SupplierDAO dao = new SupplierDAOImp(conn);
			dao.deleteSupplier(SupplierName);
			//�ύ
			DBUtil.commit(conn);
		} catch (Exception e) {
			//�ع�
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	
	//�ָ���Ӧ����Ϣ
	public void restoreSupplier(String supplierName) {
		//��ȡ�����ݿ������
		Connection conn = DBUtil.getConn();
		//��������
		DBUtil.beginTransaction(conn);
		try {
			SupplierDAO dao = new SupplierDAOImp(conn);
			dao.restoreSupplier(supplierName);
			//�ύ
			DBUtil.commit(conn);
		} catch (Exception e) {
			//�ع�
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	
	//����ɾ��
	public  void  deleteSupplier(String[] supplierNames){
		//��ȡ�����ݿ������
		Connection conn = DBUtil.getConn();
		//��������
		DBUtil.beginTransaction(conn);
		try {
			SupplierDAO dao = new SupplierDAOImp(conn);
			dao.deleteSupplier(supplierNames);
			//�ύ
			DBUtil.commit(conn);
		} catch (Exception e) {
			//�ع�
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	
	//�����ָ�
	public  void  restoreSupplier(String[] supplierNames){
		//��ȡ�����ݿ������
		Connection conn = DBUtil.getConn();
		//��������
		DBUtil.beginTransaction(conn);
		try {
			SupplierDAO dao = new SupplierDAOImp(conn);
			dao.restoreSupplier(supplierNames);
			//�ύ
			DBUtil.commit(conn);
		} catch (Exception e) {
			//�ع�
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	

	
	
	//getSupplier  by ��Ӧ����
	public Supplier getSupplierBySupplierName(String supplierName) {
		Connection conn = DBUtil.getConn();
		SupplierDAO dao = new SupplierDAOImp(conn);
		return dao.getSupplierBySupplierName(supplierName);
	}
	
	
	//�ж��û����Ƿ��ظ�
	public boolean validateSupplierName(String username){
		Connection conn = DBUtil.getConn();
		SupplierDAO dao = new SupplierDAOImp(conn);
		int i = dao.validateSupplierName(username);
		DBUtil.closeConn(conn);
		if(i==0){
			return false;
		}else{
			return true;
		}
	}
	
	
}
