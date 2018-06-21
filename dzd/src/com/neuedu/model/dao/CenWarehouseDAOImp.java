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
import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import com.neuedu.model.po.CenWarehouseInInfo;
import com.neuedu.model.po.PurchaseSupplier;
import com.neuedu.utils.DBUtil;

import net.sf.json.JSONObject;

public class CenWarehouseDAOImp implements CenWarehouseDAO{
	Connection conn;
	
	public CenWarehouseDAOImp(Connection conn) {
		this.conn = conn;
	}

	/* 
	 * 查询购货单
	 */
	public JSONObject getPurchaseInfo(int psid) {
		JSONObject json = null;
		Map<String,String> map = new HashMap<String,String>();
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		int productid = 0;
		try {
			ps1 = conn.prepareStatement(" select * from purchase_supplier where ps_id=? ");
			ps1.setInt(1, psid);
			ResultSet rs = ps1.executeQuery();
			
			if(rs.next()) {
				productid = rs.getInt("prod_id");
				map.put("productnum", String.valueOf(rs.getInt("pur_num")));
				map.put("ps_id", String.valueOf(psid));	
			}
			ps2 = conn.prepareStatement(" select * from product where product_id=? ");
			ps2.setInt(1, productid);
			ResultSet rs2 = ps2.executeQuery();
			if(rs2.next()) {
				System.out.println(rs2.getString("product_name"));
				map.put("productname", rs2.getString("product_name"));
			}
			
			json = JSONObject.fromObject(map);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps1);
			DBUtil.closePS(ps2);
		}
		return json;
		
	}

	/* 
	 * 插入购货入库单
	 */
	public void insertInWarehouseInfo(CenWarehouseInInfo cwin) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
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

}
