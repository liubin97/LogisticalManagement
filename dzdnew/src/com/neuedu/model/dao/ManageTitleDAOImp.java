package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.po.OneTitle;
import com.neuedu.model.po.TwoTitle;
import com.neuedu.utils.DBUtil;

public class ManageTitleDAOImp implements ManageTitleDAO{
	Connection conn;
	
	public ManageTitleDAOImp(Connection conn){
		this.conn = conn;
	}

	@Override
	public void addOneTitle(OneTitle oneTitle) {
		PreparedStatement ps = null;
		java.sql.Date sqlDate = new java.sql.Date(oneTitle.getOperatoedate().getTime());
		try {
			ps = conn.prepareStatement("insert into "
					+ "onetitle(onetitle_name,onetitle_decribe,status,operator,operatordate)"
					+ " values(?,?,?,?,?)");
			ps.setString(1, oneTitle.getOnetitle_name());
			ps.setString(2, oneTitle.getOnetitle_describe());
			ps.setInt(3, oneTitle.getStatus());
			ps.setString(4, oneTitle.getOperator());
			ps.setDate(5, sqlDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		
	}

	@Override
	public void addTwoTitle(TwoTitle twoTitle) {
		PreparedStatement ps = null;
		java.sql.Date sqlDate = new java.sql.Date(twoTitle.getOperatoedate().getTime());
		try {
			ps = conn.prepareStatement("insert into "
					+ " twotitle(twotitle_name,twotitle_describe,onetitle_id,status,operator,operatordate)"
					+ "  values(?,?,?,?,?,?)");
			ps.setString(1, twoTitle.getTwotitle_name());
			ps.setString(2, twoTitle.getTwotitle_describe());
			ps.setInt(3, twoTitle.getOnetitle_id());
			ps.setInt(4, twoTitle.getStatus());
			ps.setString(5, twoTitle.getOperator());
			ps.setDate(6, sqlDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
	}

	@Override
	public List<OneTitle> selectOneList(String onetitle_name) {
		
		PreparedStatement ps = null;
		List<OneTitle> list = new ArrayList<OneTitle>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from onetitle where status=0 ");
		if(onetitle_name != null && !"".equals(onetitle_name)) {
			sbf.append(" and onetitle_name=? ");
		}
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(onetitle_name != null && !"".equals(onetitle_name)) {
				ps.setString(index, onetitle_name);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				OneTitle oneTitle = new OneTitle();
				oneTitle.setOnetitle_id(rs.getInt("onetitle_id"));
				
				oneTitle.setOnetitle_name(rs.getString("onetitle_name"));
				oneTitle.setOnetitle_describe(rs.getString("onetitle_decribe"));
				oneTitle.setStatus(rs.getInt("status"));
				oneTitle.setOperator(rs.getString("operator"));
				oneTitle.setOperatoedate(rs.getDate("operatordate"));
				list.add(oneTitle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return list;
	}

	@Override
	public List<TwoTitle> selectTwoList(String twotitle_name) {
		PreparedStatement ps = null;
		List<TwoTitle> list = new ArrayList<TwoTitle>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from twotitle where status=0 ");
		if(twotitle_name != null && !"".equals(twotitle_name)) {
			sbf.append(" and twotitle_name=? ");
		}
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(twotitle_name != null && !"".equals(twotitle_name)) {
				ps.setString(index, twotitle_name);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TwoTitle twoTitle = new TwoTitle();
				twoTitle.setTwotitle_id(rs.getInt("twotitle_id"));
				twoTitle.setTwotitle_name(rs.getString("twotitle_name"));
				twoTitle.setTwotitle_describe(rs.getString("twotitle_describe"));
				twoTitle.setOnetitle_id(rs.getInt("onetitle_id"));
				twoTitle.setStatus(rs.getInt("status"));
				twoTitle.setOperator(rs.getString("operator"));
				twoTitle.setOperatoedate(rs.getDate("operatordate"));
				list.add(twoTitle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return list;
	}

	@Override
	public List<OneTitle> selectOneList(String onetitle_name, int pageNum, int pageSize) {//��ҳ��ѯ
		PreparedStatement ps = null;
		List<OneTitle> list = new ArrayList<OneTitle>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from onetitle where status=0 ");
		if(onetitle_name != null && !"".equals(onetitle_name)) {
			sbf.append(" and onetitle_name=? ");
		}
		try {
			 ps = conn.prepareStatement(sbf.toString()
					 +" limit "
					+ (pageNum-1)*pageSize
					+ ","
					+ pageSize);
			int index = 1;
			if(onetitle_name != null && !"".equals(onetitle_name)) {
				ps.setString(index, onetitle_name);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				OneTitle oneTitle = new OneTitle();
				oneTitle.setOnetitle_id(rs.getInt("onetitle_id"));
				oneTitle.setOnetitle_name(rs.getString("onetitle_name"));
				oneTitle.setOnetitle_describe(rs.getString("onetitle_decribe"));
				oneTitle.setStatus(rs.getInt("status"));
				oneTitle.setOperator(rs.getString("operator"));
				oneTitle.setOperatoedate(rs.getDate("operatordate"));
				list.add(oneTitle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return list;
	}

	@Override
	public List<TwoTitle> selectTwoList(String twotitle_name, int pageNum, int pageSize) {
		PreparedStatement ps = null;
		List<TwoTitle> list = new ArrayList<TwoTitle>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from twotitle where status=0 ");
		if(twotitle_name != null && !"".equals(twotitle_name)) {
			sbf.append(" and twotitle_name=? ");
		}
		try {
			 ps = conn.prepareStatement(sbf.toString()
					 +" limit "
					+ (pageNum-1)*pageSize
					+ ","
					+ pageSize);
			int index = 1;
			if(twotitle_name != null && !"".equals(twotitle_name)) {
				ps.setString(index, twotitle_name);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TwoTitle twoTitle = new TwoTitle();
				twoTitle.setTwotitle_id(rs.getInt("twotitle_id"));
				
				twoTitle.setTwotitle_name(rs.getString("twotitle_name"));
				twoTitle.setTwotitle_describe(rs.getString("twotitle_describe"));
				twoTitle.setOnetitle_id(rs.getInt("onetitle_id"));
				twoTitle.setStatus(rs.getInt("status"));
				twoTitle.setOperator(rs.getString("operator"));
				twoTitle.setOperatoedate(rs.getDate("operatordate"));
				list.add(twoTitle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return list;
	}

	@Override
	public int selectOnePage(String onetitle_name, int pageSize) {
		int count=0;
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select count(*) cc from onetitle where status=0 ");
		if(onetitle_name != null && !"".equals(onetitle_name)) {
			sbf.append(" and onetitle_name=? ");
		}
		try {
			 ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(onetitle_name != null && !"".equals(onetitle_name)) {
				ps.setString(index, onetitle_name);
			}
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				 count = rs.getInt("cc");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		int pagecount=0;
		if(count%pageSize==0) {
			pagecount = count/pageSize;
		}else {
			pagecount = count/pageSize+1;
		}
		
		return pagecount;
	}

	@Override
	public int selectTwoPage(String twotitle_name, int pageSize) {
				int count=0;
				PreparedStatement ps = null;
				StringBuffer sbf = new StringBuffer("");
				sbf.append("select count(*) cc from twotitle where status=0 ");
				if(twotitle_name != null && !"".equals(twotitle_name)) {
					sbf.append(" and twotitle_name=? ");
				}
				try {
					 ps = conn.prepareStatement(sbf.toString());
					int index = 1;
					if(twotitle_name != null && !"".equals(twotitle_name)) {
						ps.setString(index, twotitle_name);
					}
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						 count = rs.getInt("cc");
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					DBUtil.closePS(ps);
				}
				int pagecount=0;
				if(count%pageSize==0) {
					pagecount = count/pageSize;
				}else {
					pagecount = count/pageSize+1;
				}
				
				return pagecount;
	}

	@Override
	public void updateTwoTitle(TwoTitle twoTitle) {

		PreparedStatement ps = null;
		java.sql.Date sqlDate = new java.sql.Date(twoTitle.getOperatoedate().getTime());
		try {
			ps = conn.prepareStatement("update "
					+ " twotitle set twotitle_name=?,twotitle_describe=?,onetitle_id=?,status=?,operator=?,operatordate=?"
					+ " where twotitle_id=?");
			ps.setString(1, twoTitle.getTwotitle_name());
			
			ps.setString(2, twoTitle.getTwotitle_describe());
			ps.setInt(3, twoTitle.getOnetitle_id());
			ps.setInt(4, twoTitle.getStatus());
			ps.setString(5, twoTitle.getOperator());
			ps.setDate(6, sqlDate);
			ps.setInt(7, twoTitle.getTwotitle_id());
			ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
	}

	@Override
	public void updateOneTitle(OneTitle oneTitle) {

		PreparedStatement ps = null;
		java.sql.Date sqlDate = new java.sql.Date(oneTitle.getOperatoedate().getTime());
		try {
			ps = conn.prepareStatement("update "
					+ " onetitle set onetitle_name=?,onetitle_decribe=?,status=?,operator=?,operatordate=?"
					+ " where onetitle_id=? ");
			ps.setString(1, oneTitle.getOnetitle_name());
			ps.setString(2, oneTitle.getOnetitle_describe());
			ps.setInt(3, oneTitle.getStatus());
			ps.setString(4, oneTitle.getOperator());
			ps.setDate(5, sqlDate);
			ps.setInt(6, oneTitle.getOnetitle_id());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
	}

	@Override
	public String selectOneNameById(int onetitle_id) {
		PreparedStatement ps = null;
		String onetitle_name = "";
		try {
			ps = conn.prepareStatement("select onetitle_name from onetitle where onetitle_id=?");
			ps.setInt(1, onetitle_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				onetitle_name = rs.getString("onetitle_name");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		
		return onetitle_name;
	}

	@Override
	public void deleteTwoTitle(int twotitle_id) {
		PreparedStatement ps = null;
		try {
			 ps = conn.prepareStatement("update "
					+ "twotitle set status=? "
					+ "where twotitle_id=? ");
			ps.setInt(1, 1);
			ps.setInt(2, twotitle_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		
	}

	@Override
	public void deleteOneTitle(int onetitle_id) {
		PreparedStatement ps = null;
		try {
			 ps = conn.prepareStatement("update "
					+ "onetitle set status=? "
					+ "where onetitle_id=? ");
			ps.setInt(1, 1);
			ps.setInt(2, onetitle_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
	}

	@Override
	public String oneTitleIsDelete(int onetitle_id) {
		PreparedStatement ps = null;
		int count = 0;
		try {
			ps = conn.prepareStatement("select count(*) cc from twotitle "
					+ " where status=0 and onetitle_id=?");
			ps.setInt(1, onetitle_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				 count = rs.getInt("cc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		if(count>0) {
			return "false";
		}else {
			return "true";
		}
	}

	@Override
	public List<TwoTitle> selectTwoList(int onetitle_id) {
		PreparedStatement ps = null;
		List<TwoTitle> list = new ArrayList<TwoTitle>();
		try {
			ps = conn.prepareStatement("select * from twotitle where"
					+ " onetitle_id=?");
			ps.setInt(1, onetitle_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TwoTitle twoTitle = new TwoTitle();
				twoTitle.setTwotitle_id(rs.getInt("twotitle_id"));
				twoTitle.setTwotitle_name(rs.getString("twotitle_name"));
				twoTitle.setTwotitle_describe(rs.getString("twotitle_describe"));
				twoTitle.setOnetitle_id(rs.getInt("onetitle_id"));
				twoTitle.setStatus(rs.getInt("status"));
				twoTitle.setOperator(rs.getString("operator"));
				twoTitle.setOperatoedate(rs.getDate("operatordate"));
				list.add(twoTitle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return list;
	}

	@Override
	public String selectTwoNameById(int twotitle_id) {
		String twotitle_name = "";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select twotitle_name from twotitle where twotitle_id=?");
			ps.setInt(1, twotitle_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				twotitle_name = rs.getString("twotitle_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return twotitle_name;
	}

	@Override
	public String selectOneNameByTwoId(int twotitle_id) {
		String onetitle_name = "";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select o.onetitle_name from "
					+ " twotitle as t,onetitle as o"
					+ " where t.twotitle_id=? and t.onetitle_id = o.onetitle_id ");
			ps.setInt(1, twotitle_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				onetitle_name = rs.getString("onetitle_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return onetitle_name;
	}

	@Override
	public List<OneTitle> selectOneTitleById(int onetitle_id) {
		PreparedStatement ps = null;
		List<OneTitle> list = new ArrayList<OneTitle>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from onetitle where status=0 ");
		if(onetitle_id!=0) {
			sbf.append(" and onetitle_id=? ");
		}
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(onetitle_id!=0) {
				ps.setInt(index, onetitle_id);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				OneTitle oneTitle = new OneTitle();
				oneTitle.setOnetitle_id(rs.getInt("onetitle_id"));
				
				oneTitle.setOnetitle_name(rs.getString("onetitle_name"));
				oneTitle.setOnetitle_describe(rs.getString("onetitle_decribe"));
				oneTitle.setStatus(rs.getInt("status"));
				oneTitle.setOperator(rs.getString("operator"));
				oneTitle.setOperatoedate(rs.getDate("operatordate"));
				list.add(oneTitle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return list;
	}

	@Override
	public List<TwoTitle> selectTwoTitleById(int twotitle_id) {
		PreparedStatement ps = null;
		List<TwoTitle> list = new ArrayList<TwoTitle>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from twotitle where status=0 ");
		if(twotitle_id!=0) {
			sbf.append(" and twotitle_id=? ");
		}
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(twotitle_id!=0) {
				ps.setInt(index, twotitle_id);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TwoTitle twoTitle = new TwoTitle();
				twoTitle.setTwotitle_id(rs.getInt("twotitle_id"));
				twoTitle.setTwotitle_name(rs.getString("twotitle_name"));
				twoTitle.setTwotitle_describe(rs.getString("twotitle_describe"));
				twoTitle.setOnetitle_id(rs.getInt("onetitle_id"));
				twoTitle.setStatus(rs.getInt("status"));
				twoTitle.setOperator(rs.getString("operator"));
				twoTitle.setOperatoedate(rs.getDate("operatordate"));
				list.add(twoTitle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return list;
	}

	@Override
	public String twoTitleIsDelete(int twotitle_id) {
		PreparedStatement ps = null;
		int count = 0;
		try {
			ps = conn.prepareStatement("select count(*) cc from product "
					+ " where status=0 and twotitle_id=?");
			ps.setInt(1, twotitle_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				 count = rs.getInt("cc");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		if(count>0) {
			return "false";
		}else {
			return "true";
		}
	}

	@Override
	public int selectOneIdByTwoId(int twotitle_id) {
		PreparedStatement ps = null;
		int onetitle_id = 0;
		try {
			ps = conn.prepareStatement("select onetitle_id from twotitle where twotitle_id=?");
			ps.setInt(1, twotitle_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				onetitle_id = rs.getInt("onetitle_id");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		
		return onetitle_id;
	}
}
