package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.neuedu.model.po.Supplier;
import com.neuedu.utils.DBUtil;

public class SupplierDAOImp implements SupplierDAO{
	
	
	Connection conn;
	
	public SupplierDAOImp(Connection conn){
		this.conn = conn;
	}
	
	/*
	@Override//Ĭ��ҳ������
	public int defaultPageSum(int pageSize) {
		// TODO Auto-generated method stub
		int sum = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select count(*) cc  from  supplier where 1=1  ");
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			//ִ��
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				sum = rs.getInt("cc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int pageSum = 0;
		if(sum%pageSize==0){
			pageSum = sum/pageSize;
		}else{
			pageSum = sum/pageSize+1;
		}
		return pageSum;
	}
	*/

	/*
	@Override//Ĭ���б�
	public List<Supplier> defaultDisplayAll(int pageSize, int pageNum) {
		// TODO Auto-generated method stub
		List<Supplier> list = new ArrayList<Supplier>();
		
		int pre = pageSize*(pageNum-1);
		String previous = Integer.toString(pre);
		String size = Integer.toString(pageSize);
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select *  from  supplier where status=1 limit " + previous + ", " + size);
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			//ִ��
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Supplier s = new Supplier();
				s.setId(rs.getInt("supplier_id"));
				s.setSupplierName(rs.getString("supplier_name"));
				s.setSupplierAddress(rs.getString("supplier_address"));
				s.setContactName(rs.getString("contact_name"));
				s.setContactPhone(rs.getString("contact_phone"));
				s.setBankOfRegistration(rs.getString("bank_of_registration"));
				s.setBankAccount(rs.getString("bank_account"));
				s.setFaxNo(rs.getString("fax_no"));
				s.setZipCode(rs.getString("zipcode"));
				s.setLegalPerson(rs.getString("legal_person"));
				s.setRemark(rs.getString("remark"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	*/
	
	
	

	@Override
	public void register(Supplier supplier) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        String currentTime = df.format(System.currentTimeMillis()); 
			
			ps = conn.prepareStatement(" insert into supplier(supplier_name, supplier_address, contact_name, contact_phone, bank_of_registration, bank_account, fax_no, zipcode, legal_person, remark, status, operator, operator_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, supplier.getSupplierName());
			ps.setString(2, supplier.getSupplierAddress());
			ps.setString(3, supplier.getContactName());
			ps.setString(4, supplier.getContactPhone());
			ps.setString(5, supplier.getBankOfRegistration());
			ps.setString(6, supplier.getBankAccount());
			ps.setString(7, supplier.getFaxNo());
			ps.setString(8, supplier.getZipCode());
			ps.setString(9, supplier.getLegalPerson());
			ps.setString(10, supplier.getRemark());
			ps.setInt(11, 0);
			ps.setString(12, "Admin");
			ps.setString(13, currentTime);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}

	
	@Override//��ѯ��ҳ����
	public int pageSum(String supplierName, String supplierAddress, String contactName, String contactPhone, String bankOfRegistration, String bankAccount, String faxNo, String zipCode, String legalPerson, String status, int pageSize) {
		// TODO Auto-generated method stub
		int sum = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select count(*) cc  from  supplier where 1=1  ");
		if(supplierName != null && !"".equals(supplierName)){
			sbf.append(" and supplier_name=? ");
		}
		if(supplierAddress != null && !"".equals(supplierAddress)){
			sbf.append(" and supplier_address=? ");
		}
		if(contactName != null && !"".equals(contactName)) {
			sbf.append(" and contact_name=? ");
		}
		if(contactPhone != null && !"".equals(contactPhone)) {
			sbf.append(" and contact_phone=? ");
		}
		if(bankOfRegistration != null && !"".equals(bankOfRegistration)) {
			sbf.append(" and bank_of_registration=? ");
		}
		if(bankAccount != null && !"".equals(bankAccount)) {
			sbf.append(" and bank_account=? ");
		}
		if(faxNo != null && !"".equals(faxNo)) {
			sbf.append(" and fax_no=? ");
		}
		if(zipCode != null && !"".equals(zipCode)) {
			sbf.append(" and zipcode=? ");
		}
		if(legalPerson != null && !"".equals(legalPerson)) {
			sbf.append(" and legal_person=? ");
		}
		if(status != null && !"".equals(status)) {
			sbf.append(" and status=?");
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if(supplierName != null && !"".equals(supplierName)){
				ps.setString(index, supplierName);
				index++;
			}
			if(supplierAddress != null && !"".equals(supplierAddress)){
				ps.setString(index, supplierAddress);
				index++;
			}
			if(contactName != null && !"".equals(contactName)) {
				ps.setString(index, contactName);
				index++;
			}
			if(contactPhone != null && !"".equals(contactPhone)) {
				ps.setString(index, contactPhone);
				index++;
			}
			if(bankOfRegistration != null && !"".equals(bankOfRegistration)) {
				ps.setString(index, bankOfRegistration);
				index++;
			}
			if(bankAccount != null && !"".equals(bankAccount)) {
				ps.setString(index, bankAccount);
				index++;
			}
			if(faxNo != null && !"".equals(faxNo)) {
				ps.setString(index, faxNo);
				index++;
			}
			if(zipCode != null && !"".equals(zipCode)) {
				ps.setString(index, zipCode);
				index++;
			}
			if(legalPerson != null && !"".equals(legalPerson)) {
				ps.setString(index, legalPerson);
				index++;
			}
			if(status != null && !"".equals(status)) {
				ps.setInt(index, Integer.parseInt(status));
				index++;
			}
			//ִ��
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				sum = rs.getInt("cc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int pageSum = 0;
		if(sum%pageSize==0){
			pageSum = sum/pageSize;
		}else{
			pageSum = sum/pageSize+1;
		}
		return pageSum;
	}

	
	
	@Override//�ܲ�ѯ
	public List<Supplier> querySupplier(String supplierName, String supplierAddress, String contactName, String contactPhone, String bankOfRegistration, String bankAccount, String faxNo, String zipCode, String legalPerson, String status, int pageSize, int pageNum) {
		// TODO Auto-generated method stub
		List<Supplier> list = new ArrayList<Supplier>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select *  from  supplier where 1=1  ");
		if(supplierName != null && !"".equals(supplierName)){
			sbf.append(" and supplier_name=? ");
		}
		if(supplierAddress != null && !"".equals(supplierAddress)){
			sbf.append(" and supplier_address=? ");
		}
		if(contactName != null && !"".equals(contactName)) {
			sbf.append(" and contact_name=? ");
		}
		if(contactPhone != null && !"".equals(contactPhone)) {
			sbf.append(" and contact_phone=? ");
		}
		if(bankOfRegistration != null && !"".equals(bankOfRegistration)) {
			sbf.append(" and bank_of_registration=? ");
		}
		if(bankAccount != null && !"".equals(bankAccount)) {
			sbf.append(" and bank_account=? ");
		}
		if(faxNo != null && !"".equals(faxNo)) {
			sbf.append(" and fax_no=? ");
		}
		if(zipCode != null && !"".equals(zipCode)) {
			sbf.append(" and zipcode=? ");
		}
		if(legalPerson != null && !"".equals(legalPerson)) {
			sbf.append(" and legal_person=? ");
		}
		if(status != null && !"".equals(status)) {
			sbf.append(" and status=?");
		}
		try {
			int pre = pageSize*(pageNum-1);
			String previous = Integer.toString(pre);
			String size = Integer.toString(pageSize);
			StringBuffer sbfNew = new StringBuffer("");
			//sbfNew.append("  select *  from  supplier where 1=1 limit " + previous + ", " + size);
			sbfNew.append("  select *  from  supplier where 1=1  ");
			if(supplierName != null && !"".equals(supplierName)){
				sbfNew.append(" and supplier_name=? ");
			}
			if(supplierAddress != null && !"".equals(supplierAddress)){
				sbfNew.append(" and supplier_address=? ");
			}
			if(contactName != null && !"".equals(contactName)) {
				sbfNew.append(" and contact_name=? ");
			}
			if(contactPhone != null && !"".equals(contactPhone)) {
				sbfNew.append(" and contact_phone=? ");
			}
			if(bankOfRegistration != null && !"".equals(bankOfRegistration)) {
				sbfNew.append(" and bank_of_registration=? ");
			}
			if(bankAccount != null && !"".equals(bankAccount)) {
				sbfNew.append(" and bank_account=? ");
			}
			if(faxNo != null && !"".equals(faxNo)) {
				sbfNew.append(" and fax_no=? ");
			}
			if(zipCode != null && !"".equals(zipCode)) {
				sbfNew.append(" and zipcode=? ");
			}
			if(legalPerson != null && !"".equals(legalPerson)) {
				sbfNew.append(" and legal_person=? ");
			}
			if(status != null && !"".equals(status)) {
				sbfNew.append(" and status=?");
			}
			sbfNew.append(" limit "+ previous + ", " + size);
			
			
			PreparedStatement ps = conn.prepareStatement(sbfNew.toString());
			int index=1;
			if(supplierName != null && !"".equals(supplierName)){
				ps.setString(index, supplierName);
				index++;
			}
			if(supplierAddress != null && !"".equals(supplierAddress)){
				ps.setString(index, supplierAddress);
				index++;
			}
			if(contactName != null && !"".equals(contactName)) {
				ps.setString(index, contactName);
				index++;
			}
			if(contactPhone != null && !"".equals(contactPhone)) {
				ps.setString(index, contactPhone);
				index++;
			}
			if(bankOfRegistration != null && !"".equals(bankOfRegistration)) {
				ps.setString(index, bankOfRegistration);
				index++;
			}
			if(bankAccount != null && !"".equals(bankAccount)) {
				ps.setString(index, bankAccount);
				index++;
			}
			if(faxNo != null && !"".equals(faxNo)) {
				ps.setString(index, faxNo);
				index++;
			}
			if(zipCode != null && !"".equals(zipCode)) {
				ps.setString(index, zipCode);
				index++;
			}
			if(legalPerson != null && !"".equals(legalPerson)) {
				ps.setString(index, legalPerson);
				index++;
			}
			if(status != null && !"".equals(status)) {
				ps.setInt(index, Integer.parseInt(status));
				index++;
			}
			//ִ��
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Supplier s = new Supplier();
				s.setId(rs.getInt("supplier_id"));
				s.setSupplierName(rs.getString("supplier_name"));
				s.setSupplierAddress(rs.getString("supplier_address"));
				s.setContactName(rs.getString("contact_name"));
				s.setContactPhone(rs.getString("contact_phone"));
				s.setBankOfRegistration(rs.getString("bank_of_registration"));
				s.setBankAccount(rs.getString("bank_account"));
				s.setFaxNo(rs.getString("fax_no"));
				s.setZipCode(rs.getString("zipcode"));
				s.setLegalPerson(rs.getString("legal_person"));
				s.setRemark(rs.getString("remark"));
				s.setStatus(rs.getInt("status"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override//�޸Ĺ�Ӧ����Ϣ
	public void modifySupplier(String sN, Supplier s) {
		// TODO Auto-generated method stub
		
		int id = 0;
		PreparedStatement ps = null;
		try {
				ps = conn.prepareStatement(" select supplier_id  from  supplier  "
						+ "  where supplier_name = ? ");
				ps.setString(1, sN);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					id = (rs.getInt("supplier_id"));
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement psNew = null;
		try {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        String currentTime = df.format(System.currentTimeMillis()); 
			
	        psNew = conn.prepareStatement(" update supplier set supplier_name=?,supplier_address=?,contact_name=?,contact_phone=?,bank_of_registration=?,bank_account=?,fax_no=?,zipcode=?,legal_person=?,remark=?,status=?,operator=?, operator_date=?"
					+ " where supplier_id=?  ");
	        psNew.setString(1, s.getSupplierName());
	        psNew.setString(2, s.getSupplierAddress());
	        psNew.setString(3, s.getContactName());
	        psNew.setString(4, s.getContactPhone());
	        psNew.setString(5, s.getBankOfRegistration());
	        psNew.setString(6, s.getBankAccount());
	        psNew.setString(7, s.getFaxNo());
	        psNew.setString(8, s.getZipCode());
	        psNew.setString(9, s.getLegalPerson());
	        psNew.setString(10, s.getRemark());
	        psNew.setInt(11, s.getStatus());
	        psNew.setString(12, "Admin");
	        psNew.setString(13, currentTime);
	        psNew.setInt(14, id);
	        psNew.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}

	@Override//����ɾ��  ͨ��  ��Ӧ����
	public void deleteSupplier(String supplierName) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        String currentTime = df.format(System.currentTimeMillis()); 
			
			ps = conn.prepareStatement(" update supplier set status=?,operator=?,operator_date=? "
					+ " where supplier_name =? ");
			ps.setInt(1, 1);
			ps.setString(2, "Admin");
			ps.setString(3, currentTime);
			ps.setString(4, supplierName);
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		
	}
	


	
	@Override//�ָ���Ӧ����Ϣ
	public void restoreSupplier(String supplierName) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        String currentTime = df.format(System.currentTimeMillis()); 
			
			ps = conn.prepareStatement(" update supplier set status=?,operator=?,operator_date=?"
					+ " where supplier_name=?  ");
			ps.setInt(1, 0);
			ps.setString(2, "Admin");
			ps.setString(3, currentTime);
			ps.setString(4, supplierName);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}
	
	
	@Override//����ɾ��
	public void deleteSupplier(String[] supplierNames) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        String currentTime = df.format(System.currentTimeMillis()); 
			
			ps = conn.prepareStatement(" update supplier set status=?,operator=?,operator_date=? "
					+ " where supplier_name=?");
			
			for(int i = 0;i<supplierNames.length;i++){
				ps.setInt(1, 1);
				ps.setString(2, "Admin");
				ps.setString(3, currentTime);
				ps.setString(4, supplierNames[i]);
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}
	
	
	@Override//�����ָ���Ӧ����Ϣ
	public void restoreSupplier(String[] supplierNames) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        String currentTime = df.format(System.currentTimeMillis()); 
			
			ps = conn.prepareStatement(" update supplier set status=?,operator=?,operator_date=? "
					+ " where supplier_name=?");
			
			for(int i = 0;i<supplierNames.length;i++){
				ps.setInt(1, 0);
				ps.setString(2, "Admin");
				ps.setString(3, currentTime);
				ps.setString(4, supplierNames[i]);
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}
	

	@Override//ͨ����Ӧ������ȡ�ù�Ӧ��
	public Supplier getSupplierBySupplierName(String supplierName) {
		// TODO Auto-generated method stub
		Supplier s = new Supplier();
		PreparedStatement ps = null;
		try {
				ps = conn.prepareStatement(" select *  from  supplier  "
						+ "  where supplier_name = ? ");
				ps.setString(1, supplierName);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					s.setId(rs.getInt("supplier_id"));
					s.setSupplierName(rs.getString("supplier_name"));
					s.setSupplierAddress(rs.getString("supplier_address"));
					s.setContactName(rs.getString("contact_name"));
					s.setContactPhone(rs.getString("contact_phone"));
					s.setBankOfRegistration(rs.getString("bank_of_registration"));
					s.setBankAccount(rs.getString("bank_account"));
					s.setFaxNo(rs.getString("fax_no"));
					s.setZipCode(rs.getString("zipcode"));
					s.setLegalPerson(rs.getString("legal_person"));
					s.setRemark(rs.getString("remark"));
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	
	//��֤��Ӧ����
	@Override
	public int validateSupplierName(String sName) {
		// TODO Auto-generated method stub
		PreparedStatement ps;
		int i = 0;
		try {
			ps = conn.prepareStatement("  select count(supplier_name)  from supplier  where supplier_name=? ");
			ps.setString(1, sName);
			ResultSet rs = ps.executeQuery();
			i = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}






}
