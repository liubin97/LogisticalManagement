/**
 * @package com.neuedu.model.dao
 * @author liubin
 * @date 2018��6��20��
*/
package com.neuedu.model.dao;

import com.neuedu.model.po.PurchaseSupplier;

import net.sf.json.JSONObject;

public interface CenWarehouseDAO {
    public JSONObject getPurchaseInfo(int psid);
}

