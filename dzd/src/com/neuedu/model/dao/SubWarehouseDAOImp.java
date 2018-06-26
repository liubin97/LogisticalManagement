/**
 * @package com.neuedu.model.dao
 * @author liubin
 * @date 2018年6月20日
*/
package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.model.po.Product;
import com.neuedu.model.po.RecvGoodsInfo;
import com.neuedu.model.po.ReturnRegisterInfo;
import com.neuedu.model.po.SubWarehouseInInfo;
import com.neuedu.utils.DBUtil;

import net.sf.json.JSONObject;

public class SubWarehouseDAOImp implements SubWarehouseDAO{
	Connection conn;
	
	public SubWarehouseDAOImp(Connection conn) {
		this.conn = conn;
	}

	/* 
	 * 获取分站入库任务单
	 */
	public JSONObject getTaskListIn(int task_id) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		JSONObject json = new JSONObject();
		try {
			ps = conn.prepareStatement(" select * from task_order_view where task_list_id =? and task_status=2  ");
			ps.setInt(1, task_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				json.put("task_list_id", task_id);
				json.put("product_name", getProductById(rs.getInt("prod_id")).getProduct_name());
				json.put("product_num", rs.getInt("amount"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		return json;
	}
	
	/* 
	 * 获取分站出库任务单
	 */
	public JSONObject getTaskListOut(int task_id) {
		PreparedStatement ps = null;
		JSONObject json = new JSONObject();
		try {
			ps = conn.prepareStatement(" select * from task_order_view where task_list_id =? and task_status=4  ");
			ps.setInt(1, task_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				json.put("task_list_id", task_id);
				json.put("product_name", getProductById(rs.getInt("prod_id")).getProduct_name());
				json.put("product_num", rs.getInt("amount"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		return json;
	}

	/* 
	 * 获取商品信息
	 */
	public Product getProductById(int product_id) {
		PreparedStatement ps = null;
		Product pro = new Product();
		try {
			ps = conn.prepareStatement(" select * from product where product_id=? ");
			ps.setInt(1, product_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				pro.setProduct_name(rs.getString("product_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return pro;
	}
	
	/* 
	 * 修改任务单状态
	 */
	public void editTaskListStatus(int tasklist_id, int status) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update task_list set task_status =? where task_list_id =? ");
			ps.setInt(1, status);
			ps.setInt(2, tasklist_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* 
	 * 插入调拨入库信息
	 */
	public void insertInInfo(SubWarehouseInInfo swin) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" insert into sub_warehouse_in_info (task_list_id,note,operate_date) values(?,?,?) ");
			ps.setInt(1, swin.getTask_list_id());
			ps.setString(2, swin.getNote());
			ps.setDate(3, new Date(swin.getOperate_date().getTime()));
			ps.executeUpdate();
			editTaskListStatus(swin.getTask_list_id(), 3);//更改任务单状态到分站库房入库
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
	}

	/* 
	 * 插入领货信息
	 */
	public void insertRecvGoodsInfo(RecvGoodsInfo rin) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" insert into recv_goods_info (task_list_id,recv_person,recv_date,operate_date,note) 	"
					+ " values(?,?,?,?,?) ");
			ps.setInt(1, rin.getTask_list_id());
			ps.setString(2, rin.getRecv_person());
			ps.setDate(3, new Date(rin.getRecv_date().getTime()));
			ps.setDate(4, new Date(rin.getOperate_date().getTime()));
			ps.setString(5, rin.getNote());
			ps.executeUpdate();
			editTaskListStatus(rin.getTask_list_id(), 5);//更改任务单状态到分站库房出库
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
	}

	/* 
	 * 获取退货入库 任务单
	 */
	public JSONObject getReturnInTaskList(int task_id) {
		PreparedStatement ps = null;
		JSONObject json = new JSONObject();
		try {
			ps = conn.prepareStatement(" select * from task_order_return_view where task_list_id =? and task_status=8  ");
			ps.setInt(1, task_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				json.put("task_list_id", task_id);
				json.put("product_name", getProductById(rs.getInt("prod_id")).getProduct_name());
				json.put("product_num", rs.getInt("actual_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		return json;
	}

	/* 
	 * 插入退货登记信息
	 */
	public void insertReturnRegisterInfo(ReturnRegisterInfo rin) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" insert into return_register_info (task_list_id,actual_number,operate_date) "
					+ "values(?,?,?) ");
			ps.setInt(1, rin.getTask_id());
			ps.setInt(2, rin.getActual_num());
			ps.setDate(3, new Date(rin.getOperate_date().getTime()));
			ps.executeUpdate();
			editTaskListStatus(rin.getTask_id(), 9);//更改任务单状态到分站库房退货入库
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		
	}

}
