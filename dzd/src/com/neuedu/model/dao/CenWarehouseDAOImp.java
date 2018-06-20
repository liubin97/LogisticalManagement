/**
 * @package com.neuedu.model.dao
 * @author liubin
 * @date 2018年6月20日
*/
package com.neuedu.model.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.neuedu.model.po.PurchaseSupplier;

import net.sf.json.JSONObject;

public class CenWarehouseDAOImp implements CenWarehouseDAO{
	Connection conn;
	
	public CenWarehouseDAOImp(Connection conn) {
		this.conn = conn;
	}

	/* (non-Javadoc)
	 * @see com.neuedu.model.dao.CenWarehouseDAO#getPurchaseInfo(int)
	 */
	public JSONObject getPurchaseInfo(int psid) {
		PurchaseSupplier ps = new PurchaseSupplier();
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("productname", "商品");
		map.put("productnum", "1");
		map.put("ps_id", "1");
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}

}
