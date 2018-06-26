/**
 * @package com.neuedu.model.dao
 * @author liubin
 * @date 2018年6月20日
*/
package com.neuedu.model.dao;

import java.sql.Date;

import com.neuedu.model.po.CenWarehouseInInfo;
import com.neuedu.model.po.Product;
import com.neuedu.model.po.PurchaseSupplier;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface CenWarehouseDAO {
    public JSONObject getPurchaseInfo(int psid);
    public int getOrderIdByPsId(int psid);
    public String getWarehouseNameById(int id);
    public Product getProductById(int product_id);
    public JSONArray getTaskListByDate(Date date, int pageNum);
    public int getTaskListPageCount(Date date);
    public void insertInWarehouseInfo(CenWarehouseInInfo cwin);
    public void editOrderStatus(int order_id, int status);
    public void editTaskListStatus(int tasklist_id,int status);
    public void editStoragNum(int num,int flag);
    public void insertOutWarehouseInfo(int []ids);
}

