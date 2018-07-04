package com.neuedu.model.service;

import java.sql.Connection;
import java.util.List;

import com.neuedu.model.dao.ManageTitleDAO;
import com.neuedu.model.dao.ManageTitleDAOImp;
import com.neuedu.model.po.OneTitle;
import com.neuedu.model.po.TwoTitle;
import com.neuedu.utils.DBUtil;

public class ManageTitleService {

	private ManageTitleService(){
		
	}
	private static ManageTitleService service = new ManageTitleService();
	public static ManageTitleService getInstance() {
		return service;
	}

	public void newOneTitle(OneTitle oneTitle) {
		Connection conn = DBUtil.getConn();
		DBUtil.beginTransaction(conn);
		try {
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		dao.addOneTitle(oneTitle);
		DBUtil.commit(conn);
		}catch(Exception e) {
			DBUtil.rollback(conn);
		}finally {
			DBUtil.closeConn(conn);
		}
	}

	public void newTwoTitle(TwoTitle twoTitle) {

				Connection conn = DBUtil.getConn();
				DBUtil.beginTransaction(conn);
				try {
				ManageTitleDAO dao = new ManageTitleDAOImp(conn);
				dao.addTwoTitle(twoTitle);
				DBUtil.commit(conn);
				}catch(Exception e) {
					DBUtil.rollback(conn);
				}finally {
					DBUtil.closeConn(conn);
				}
	}
	public List<OneTitle> selectOneTitle(String onetitle_name) {
				Connection conn = DBUtil.getConn();
				ManageTitleDAO dao = new ManageTitleDAOImp(conn);
				List<OneTitle> list = dao.selectOneList(onetitle_name);
				DBUtil.closeConn(conn);
				return list;
	}
	public List<TwoTitle> selectTwoTitle(String twotitle_name) {
				Connection conn = DBUtil.getConn();
				ManageTitleDAO dao = new ManageTitleDAOImp(conn);
				List<TwoTitle> list = dao.selectTwoList(twotitle_name);
				DBUtil.closeConn(conn);
				return list;
	}
	public List<OneTitle> selectOneTitle(String onetitle_name, int pageNum, int pageSize) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		List<OneTitle> list = dao.selectOneList(onetitle_name,pageNum,pageSize);
		DBUtil.closeConn(conn);
		return list;
	}

	public List<TwoTitle> selectTwoTitle(String twotitle_name, int pageNum, int pageSize) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		List<TwoTitle> list = dao.selectTwoList(twotitle_name,pageNum,pageSize);
		DBUtil.closeConn(conn);
		return list;
	}
	public int selectOnePage(String onetitle_name, int pageSize) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		int onepage = dao.selectOnePage(onetitle_name,pageSize);
		DBUtil.closeConn(conn);
		return onepage;
	}
	public int selectTwoPage(String twotitle_name, int pageSize) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		int twopage = dao.selectTwoPage(twotitle_name,pageSize);
		DBUtil.closeConn(conn);
		return twopage;
	}
	public void updateTwoTitle(TwoTitle twoTitle) {

				Connection conn = DBUtil.getConn();

				DBUtil.beginTransaction(conn);
				try {
				ManageTitleDAO dao = new ManageTitleDAOImp(conn);
				dao.updateTwoTitle(twoTitle);

				DBUtil.commit(conn);
				}catch(Exception e) {
					DBUtil.rollback(conn);
				}finally {
					DBUtil.closeConn(conn);
				}
	}
	public void updateOneTitle(OneTitle oneTitle) {

			Connection conn = DBUtil.getConn();

			DBUtil.beginTransaction(conn);
			try {
				ManageTitleDAO dao = new ManageTitleDAOImp(conn);
				dao.updateOneTitle(oneTitle);

				DBUtil.commit(conn);
			}catch(Exception e) {
				DBUtil.rollback(conn);
			}finally {
				DBUtil.closeConn(conn);
			}
	}
	public String selectOneNameById(int onetitle_id) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		String onename = dao.selectOneNameById(onetitle_id);
		DBUtil.closeConn(conn);
		return onename;
	}
	public void deleteTwoTitle(int twotitle_id) {

		Connection conn = DBUtil.getConn();

		DBUtil.beginTransaction(conn);
		try {
			ManageTitleDAO dao = new ManageTitleDAOImp(conn);
			dao.deleteTwoTitle(twotitle_id);

			DBUtil.commit(conn);
		}catch(Exception e) {
			DBUtil.rollback(conn);
		}finally {
			DBUtil.closeConn(conn);
		}
	}
	public void deleteOneTitle(int onetitle_id) {

		Connection conn = DBUtil.getConn();

		DBUtil.beginTransaction(conn);
		try {
			ManageTitleDAO dao = new ManageTitleDAOImp(conn);
			dao.deleteOneTitle(onetitle_id);

			DBUtil.commit(conn);
		}catch(Exception e) {
			DBUtil.rollback(conn);
		}finally {
			DBUtil.closeConn(conn);
		}
	}
	public String oneTitleIsDelete(int onetitle_id) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		String isdelete = dao.oneTitleIsDelete(onetitle_id);
		DBUtil.closeConn(conn);
		return isdelete;
	}
	public List<TwoTitle> selectTwoTitle(int onetitle_id) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		List<TwoTitle> list =dao.selectTwoList(onetitle_id);
		DBUtil.closeConn(conn);
		return list;
	}
	public String selectTwoNameById(int twotitle_id) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		String twoname = dao.selectTwoNameById(twotitle_id);
		DBUtil.closeConn(conn);
		return twoname;
	}
	public String selectOneNameByTwoId(int twotitle_id) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		String onename = dao.selectOneNameByTwoId(twotitle_id);
		DBUtil.closeConn(conn);
		return  onename;
	}
	public List<OneTitle> selectOneTitleById(int onetitle_id) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		List<OneTitle> list = dao.selectOneTitleById(onetitle_id);
		DBUtil.closeConn(conn);
		return list;
	}
	public List<TwoTitle> selectTwoTitleById(int twotitle_id) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		List<TwoTitle> list = dao.selectTwoTitleById(twotitle_id);
		DBUtil.closeConn(conn);
		return list;
	}
	public String twoTitleIsDelete(int twotitle_id) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		String isdelete =  dao.twoTitleIsDelete(twotitle_id);
		DBUtil.closeConn(conn);
		return isdelete;
	}

	public int selectOneIdByTwoId(int twotitle_id) {
		Connection conn = DBUtil.getConn();
		ManageTitleDAO dao = new ManageTitleDAOImp(conn);
		int count = dao.selectOneIdByTwoId(twotitle_id);
		DBUtil.closeConn(conn);
		return count;
	}
}
