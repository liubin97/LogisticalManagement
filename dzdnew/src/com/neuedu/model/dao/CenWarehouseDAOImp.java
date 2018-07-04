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
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import com.neuedu.model.po.CenReturnInInfo;
import com.neuedu.model.po.CenReturnOutInfo;
import com.neuedu.model.po.CenWarehouseInInfo;
import com.neuedu.model.po.Product;
import com.neuedu.model.po.PurchaseSupplier;
import com.neuedu.utils.DBUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CenWarehouseDAOImp implements CenWarehouseDAO{
	private Connection conn;
	private final int pageSize = 6;
	public CenWarehouseDAOImp(Connection conn) {
		this.conn = conn;
	}

	/* 
	 * 查询购货单
	 */
	public JSONObject getPurchaseInfo(int psid) {
		JSONObject json = new JSONObject();
		PreparedStatement ps1 = null;
		int product_id = 0;
		try {
			ps1 = conn.prepareStatement(" select * from purchase_supplier where ps_id=? and status=0 ");
			ps1.setInt(1, psid);
			ResultSet rs = ps1.executeQuery();
			if(rs.next()) {
				product_id = rs.getInt("prod_id");
				json.put("productname", getProductById(product_id).getProduct_name());
				json.put("productnum", rs.getInt("pur_num"));
				json.put("ps_id", psid);	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps1);
		}
		return json;	
	}
	
	//根据购货单号查询order_id
	public int getOrderIdByPsId(int psid) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		int order_id = 0;
		try {
			ps = conn.prepareStatement(" select * from purchase_supplier where ps_id=?  ");
			ps.setInt(1, psid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				order_id = rs.getInt("order_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return order_id;
	}
	//通过购货单号获取产品ID
	public int getProductIdByPsId(int ps_id) {
		PreparedStatement ps = null;
		int prod_id = 0;
		try {
			ps = conn.prepareStatement(" select * from purchase_supplier where ps_id=?  ");
			ps.setInt(1, ps_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				prod_id = rs.getInt("prod_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return prod_id;
	}

	/* 
	 * 插入购货入库单
	 * 并根据购货单中是否有订单Id修改库存和订单状态
	 * 修改购货单状态
	 */
	public void insertInWarehouseInfo(CenWarehouseInInfo cwin) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		int order_id = getOrderIdByPsId(cwin.getPs_id());
		if(order_id != 0) {
			editOrderStatus(order_id, 2);
			editStoragNum(cwin.getActual_num(),getProductIdByPsId(cwin.getPs_id()), 2);
		}else {
			editStoragNum(cwin.getActual_num(),getProductIdByPsId(cwin.getPs_id()),1);
		}
		editPurchaseStatus(cwin.getPs_id(), 1);
		try {
			ps = conn.prepareStatement("insert into cen_warehouse_in_info"
					+ "(`ps_id`,`actual_number`,`date`,`note`,`operate_date`) values(?,?,?,?,?)");
			ps.setInt(1, cwin.getPs_id());
			ps.setInt(2, cwin.getActual_num());
			ps.setDate(3, new Date(cwin.getDate().getTime()));
			ps.setString(4, cwin.getNote());
			ps.setDate(5, new Date(cwin.getOperate_date().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
	}
	//修改购货单状态
	public void editPurchaseStatus(int ps_id, int status) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update `purchase_supplier` set `status`=? where ps_id=? ");
			ps.setInt(1, status);
			ps.setInt(2, ps_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
	}

	/* 
	 * 修改订单状态
	 */
	public void editOrderStatus(int order_id,int status) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update `order` set `status`=? where order_id=? ");
			ps.setInt(1, status);
			ps.setInt(2, order_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
	}

	/* 
	 * 修改任务单状态
	 */
	public void editTaskListStatus(int tasklist_id, int status) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update task_list set task_status =? where task_list_id =? ");
			ps.setInt(1, status);
			ps.setInt(2, tasklist_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
	}
	
	/* 
	 * 修改库存量
	 */
	public void editStoragNum(int num,int product_id,int flag) {
		// TODO Auto-generated method stub
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
			if(flag==1) {//修改增加总量、可分配
				ps1 = conn.prepareStatement(" update wh_reserve_info set res_num = res_num+?  where prod_id=? ");
				ps1.setInt(1, num);
				ps1.setInt(2, product_id);
				ps1.executeUpdate();
				ps2 = conn.prepareStatement(" update wh_res_num set can_distribute = can_distribute+?  where prod_id=? ");
				ps2.setInt(1, product_id);
				ps2.setInt(1, num);
				ps2.executeUpdate();
			}else if(flag==2) {//修改增加总量、已分配
				ps1 = conn.prepareStatement(" update wh_reserve_info set res_num = res_num+?  where prod_id=? ");
				ps1.setInt(1, num);
				ps1.setInt(2, product_id);
				ps1.executeUpdate();
				ps2 = conn.prepareStatement(" update wh_res_num set has_distribute = has_distribute+?  where prod_id=? ");
				ps2.setInt(1, num);
				ps2.setInt(2, product_id);
				ps2.executeUpdate();
			}else if(flag==3) {//修改增加总量、退回数量
				ps1 = conn.prepareStatement(" update wh_reserve_info set res_num = res_num+?  where prod_id=? ");
				ps1.setInt(1, num);
				ps1.setInt(2, product_id);
				ps1.executeUpdate();
				ps2 = conn.prepareStatement(" update wh_res_num set return_num = return_num+?  where prod_id=? ");
				ps2.setInt(1, num);
				ps2.setInt(2, product_id);
				ps2.executeUpdate();
			}else if(flag==4) {//修改减少总数量、退回数量
				ps1 = conn.prepareStatement(" update wh_reserve_info set res_num = res_num-?  where prod_id=? ");
				ps1.setInt(1, num);
				ps1.setInt(2, product_id);
				ps1.executeUpdate();
				ps2 = conn.prepareStatement(" update wh_res_num set return_num = return_num-?  where prod_id=? ");
				ps2.setInt(1, num);
				ps2.setInt(2, product_id);
				ps2.executeUpdate();
			}else if(flag==5) {//修改减少总量、已分配
				ps1 = conn.prepareStatement(" update wh_reserve_info set res_num = res_num-?  where prod_id=? ");
				ps1.setInt(1, num);
				ps1.setInt(2, product_id);
				ps1.executeUpdate();
				ps2 = conn.prepareStatement(" update wh_res_num set has_distribute = has_distribute-?  where prod_id=? ");
				ps2.setInt(1, num);
				ps2.setInt(2, product_id);
				ps2.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps1);
			DBUtil.closePS(ps2);
		}
	}

	/* 
	 * 通过日期查询要出库任务单信息
	 */
	public JSONArray getTaskListByDate(Date date, int pageNum) {
		PreparedStatement ps = null;
		JSONArray jsonarr = new JSONArray();
		try {
			ps = conn.prepareStatement(" select * from task_order_view where finish_date=? and task_status=1 "
					+ " limit "+(pageSize*(pageNum-1))+" , "+pageSize);
			ps.setDate(1, date);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("order_id", rs.getInt("order_id"));
				obj.put("task_list_id", rs.getInt("task_list_id"));
				obj.put("product_name", getProductById(rs.getInt("prod_id")).getProduct_name());
				obj.put("substation", getWarehouseNameById(rs.getInt("substation_id")));	
				obj.put("product_num", rs.getString("amount"));
				Date tempdate = new Date( rs.getDate("finish_date").getTime());
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String newdate = sdf.format(tempdate);
				obj.put("finish_date", newdate);
				jsonarr.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		return jsonarr;
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
		} finally {
			DBUtil.closePS(ps);
		}	
		return pro;
	}

	/* 
	 * 查询分站名称
	 */
	public String getWarehouseNameById(int id) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String name = "";
		try {
			ps = conn.prepareStatement(" select * from warehouse where wh_id=? ");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				name = rs.getString("wh_name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		return name;
	}

	/* 
	 * 获取页码数
	 */
	public int getTaskListPageCount(Date date) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		int pagecount = 0;
		
		try {
			ps = conn.prepareStatement(" select count(*) from task_order_view where finish_date=? and task_status=1 ");
			ps.setDate(1, date);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				pagecount = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		return (int) Math.ceil(pagecount / (pageSize * 1.0));
	}
	
	//获取产品数量通过任务单号
	public int getProductNumByTaskId(int task_id) {
		int num = 0;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" select * from task_order_view where task_list_id = ? ");
			ps.setInt(1, task_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				num = rs.getInt("amount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	/* 
	 * 插入出库信息
	 */
	public void insertOutWarehouseInfo(int[] ids) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		Date date = new Date(new java.util.Date().getTime());
		try {
			for(int i = 0;i<ids.length;i++) {
				ps = conn.prepareStatement(" insert into cen_warehouse_out_info (`task_list_id`,`operate_date`) values(?,?)");
				ps.setInt(1, ids[i]);
				ps.setDate(2, date);
				ps.executeUpdate();
				ps.close();
				editTaskListStatus(ids[i],2);//修改任务单状态到中心库房出库;
				editStoragNum(getProductNumByTaskId(ids[i]), getProductIdByTaskId(ids[i]), 5);//修改库存
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
	}

	/* 
	 * 查询退货入库信息
	 */
	public JSONObject getReturnInInfo(int task_id) {
		PreparedStatement ps = null;
		JSONObject json = new JSONObject();
		try {
			ps = conn.prepareStatement(" select * from task_order_return_view where "
					+ " task_status=10 and task_list_id=? ");
			ps.setInt(1, task_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				json.put("productname", getProductById(rs.getInt("prod_id")).getProduct_name());
				json.put("productnum",rs.getInt("actual_num"));
				json.put("taskid", rs.getInt("task_list_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}

		return json;
	}
	//根据任务单获取产品ID
	public int getProductIdByTaskId(int task_id) {
		PreparedStatement ps = null;
		int prod_id = 0;
		try {
			ps = conn.prepareStatement(" select * from task_order_view where task_list_id=?  ");
			ps.setInt(1, task_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				prod_id = rs.getInt("prod_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return prod_id;
	}

	/* 
	 * 插入退货入库信息
	 * 修改库存
	 */
	public void insertReturnInInfo(CenReturnInInfo crin) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" insert into cen_return_in_info (task_list_id,return_date,actual_num) values (?,?,?) ");
			ps.setInt(1, crin.getTask_list_id());
			ps.setDate(2, new Date(crin.getReturn_date().getTime()));
			ps.setInt(3, crin.getActual_num());
			ps.executeUpdate();
			editTaskListStatus(crin.getTask_list_id(), 11);//修改任务单状态到中心库房入库
			editStoragNum(crin.getActual_num(),getProductIdByTaskId(crin.getTask_list_id()), 3);//修改库存数量
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		
	}

	/* 
	 * 查询退货 出库信息
	 */
	public JSONObject getReturnOutInfo(int rsid) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		JSONObject json = new JSONObject();
		try {
			ps = conn.prepareStatement(" select * from return_supplier where rs_id=? and status = 0 ");
			ps.setInt(1, rsid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				json.put("rsid", rs.getInt("rs_id"));
				json.put("productname", getProductById(rs.getInt("prod_id")).getProduct_name());
				json.put("productnum", rs.getInt("rs_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		return json;
	}
	
	//通过退货单获取产品ID
	public int getProductIdByRsId(int rs_id){
		PreparedStatement ps = null;
		int prod_id = 0;
		try {
			ps = conn.prepareStatement(" select * from return_supplier where rs_id=?  ");
			ps.setInt(1, rs_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				prod_id = rs.getInt("prod_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return prod_id;
	}

	/* 
	 * 插入退货出库信息
	 * 修改库存
	 * 更改退货单状态
	 */
	public void insertReturnOutInfo(CenReturnOutInfo croi) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" insert into cen_return_out_info (rs_id,operate_date,actual_num) values (?,?,?) ");
			ps.setInt(1, croi.getRs_id());
			ps.setDate(2, new Date(croi.getOperate_date().getTime()));
			ps.setInt(3, croi.getActual_num());
			ps.executeUpdate();
			editStoragNum(croi.getActual_num(),getProductIdByRsId(croi.getRs_id()), 4);//修改库存
			editRerutnStatus(croi.getRs_id(), 1);//修改退货单状态
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		
	}
	//更改退货单状态
	public void editRerutnStatus(int rsid,int status) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update return_supplier  set status=? where rs_id=? ");
			ps.setInt(1, status);
			ps.setInt(2, rsid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
	}

	//查询库房信息
	public JSONArray getSubstationInfo() {
		PreparedStatement ps = null;
		JSONArray jsonarr = new JSONArray();
		try {
			ps = conn.prepareStatement(" SELECT * FROM warehouse where wh_level != 0 ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject json = new JSONObject();
				json.put("substation_id", rs.getInt("wh_id"));
				json.put("substation_name", rs.getString("wh_name"));
				jsonarr.add(json);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		return jsonarr;
		
	}
	//获取分发单
	public JSONArray getDistribution(int sub_id,Date date,String product_name,int pageNum) {
		JSONArray jsonarr = new JSONArray();
		PreparedStatement ps = null;
		int product_id = getProductIdByName(product_name);
		try {
			ps = conn.prepareStatement(" select * from cen_wh_out_task_view where task_status = 2 and "
					+ " out_date = ? and substation_id=? and prod_id=? "
					+ " limit "+(pageSize*(pageNum-1))+" , "+pageSize);
			ps.setDate(1, new Date(date.getTime()));
			ps.setInt(2, sub_id);
			ps.setInt(3, product_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject json = new JSONObject();
				json.put("product_id", product_id);
				json.put("product_name", product_name);
				json.put("product_price", rs.getFloat("price"));
				json.put("discount",rs.getFloat("discount"));
				json.put("sum_money", rs.getFloat("sum_money"));
				json.put("task_id", rs.getInt("task_list_id"));
				json.put("substation", getWarehouseNameById(sub_id));
				json.put("substation_id", sub_id);
				json.put("product_num", rs.getString("amount"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String newdate = sdf.format(rs.getDate("out_date"));
				json.put("out_date", newdate);
				jsonarr.add(json);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		return jsonarr;
	}
	
	//获取分发单页数
	public int getDistributionPageCount(int sub_id,Date date,String product_name) {
		int count = 0;
		PreparedStatement ps = null;
		int product_id = getProductIdByName(product_name);
		try {
			ps = conn.prepareStatement(" select count(*) from cen_wh_out_task_view where  task_status = 2 and "
					+ " out_date=? and substation_id=? and prod_id=? ");
			ps.setDate(1, new Date(date.getTime()));
			ps.setInt(2, sub_id);
			ps.setInt(3, product_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		return (int) Math.ceil(count / (pageSize * 1.0));
	}
	
	//根据商品名称查询Id
	public int getProductIdByName(String product_name) {
		PreparedStatement ps = null;
		int id = 0;
		try {
			ps = conn.prepareStatement(" select * from product where product_name = ? ");
			ps.setString(1, product_name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("product_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		return id;
	}
	
	//查询要打印的分发单
	public JSONObject getPrintDis(int task_id) {
		PreparedStatement ps = null;
		JSONObject json = new JSONObject();
		try {
			ps = conn.prepareStatement(" select * from cen_wh_out_task_view where task_list_id=? ");
			ps.setInt(1, task_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				json.put("product_id", rs.getInt("prod_id"));
				json.put("product_name", getProductById(rs.getInt("prod_id")).getProduct_name());
				json.put("product_price", rs.getFloat("price"));
				json.put("discount",rs.getFloat("discount"));
				json.put("sum_money", rs.getFloat("sum_money"));
				json.put("task_id", rs.getInt("task_list_id"));
				json.put("substation", rs.getInt("substation_id"));
				json.put("substation_id", rs.getInt("substation_id"));
				json.put("product_num", rs.getString("amount"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String newdate = sdf.format(rs.getDate("out_date"));
				json.put("out_date", newdate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		return json;
	}

	
}
