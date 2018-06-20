/**
 * @package com.neuedu.model.service
 * @author liubin
 * @date 2018��6��19��
*/
package com.neuedu.model.service;

public class SubWarehouseService {
	private static SubWarehouseService service = new SubWarehouseService();
	
	//单例模式
	public static SubWarehouseService getInstance() {
		return service;
	}
	
}
