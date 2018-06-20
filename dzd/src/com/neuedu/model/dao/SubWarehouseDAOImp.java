/**
 * @package com.neuedu.model.dao
 * @author liubin
 * @date 2018Äê6ÔÂ20ÈÕ
*/
package com.neuedu.model.dao;

import java.sql.Connection;

public class SubWarehouseDAOImp implements SubWarehouseDAO{
	Connection conn;
	
	public SubWarehouseDAOImp(Connection conn) {
		this.conn = conn;
	}

}
