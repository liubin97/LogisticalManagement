/**
 * @package com.neuedu.model.service
 * @author liubin
 * @date 2018Äê6ÔÂ19ÈÕ
*/
package com.neuedu.model.service;

import com.neuedu.model.dao.CenWarehouseDAO;
import com.neuedu.model.dao.CenWarehouseDAOImp;
import com.neuedu.model.po.PurchaseSupplier;
import com.neuedu.utils.DBUtil;

import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.SQLException;

public class CenWarehouseService {
	private static CenWarehouseService service = new CenWarehouseService();
	
	
	public static CenWarehouseService getInstance() {
		return service;
	}

	public JSONObject getPurchaseInfo(int psid){
		Connection conn = DBUtil.getConn();
		CenWarehouseDAO cwd = new CenWarehouseDAOImp(conn);
		JSONObject ps = cwd.getPurchaseInfo(psid);
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}

}
