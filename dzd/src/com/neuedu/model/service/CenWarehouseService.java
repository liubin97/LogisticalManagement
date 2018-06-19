/**
 * @package com.neuedu.model.service
 * @author liubin
 * @date 2018年6月19日
*/
package com.neuedu.model.service;

public class CenWarehouseService {
	private static CenWarehouseService service = new CenWarehouseService();
	
	//单例模式
	public static CenWarehouseService getInstance() {
		return service;
	}

}
