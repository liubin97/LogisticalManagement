/**
 * @package com.neuedu.model.dao
 * @author liubin
 * @date 2018Äê6ÔÂ20ÈÕ
*/
package com.neuedu.model.dao;

import com.neuedu.model.po.PurchaseSupplier;

import net.sf.json.JSONObject;

public interface CenWarehouseDAO {
    public JSONObject getPurchaseInfo(int psid);
}

