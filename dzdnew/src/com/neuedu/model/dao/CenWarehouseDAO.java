/**
 * @package com.neuedu.model.dao
 * @author liubin
 * @date 2018年6月20日
*/
package com.neuedu.model.dao;

import java.sql.Date;

import com.neuedu.model.po.CenReturnInInfo;
import com.neuedu.model.po.CenReturnOutInfo;
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
    public void editPurchaseStatus(int ps_id,int status);
    public JSONObject getReturnInInfo(int task_id);
    public JSONObject getReturnOutInfo(int rsid);
    public int getDistributionPageCount(int sub_id,Date date,String product_name);
    public int getProductIdByName(String product_name);
    public void insertInWarehouseInfo(CenWarehouseInInfo cwin);
    public void editOrderStatus(int order_id, int status);
    public void editTaskListStatus(int tasklist_id,int status);
    public void editStoragNum(int num,int product_id,int flag);
    public void insertOutWarehouseInfo(int []ids);
    public void insertReturnInInfo(CenReturnInInfo crin);
    public void insertReturnOutInfo(CenReturnOutInfo croi);
    public void editRerutnStatus(int rsid,int status);
    public JSONArray getSubstationInfo();
    public JSONArray getDistribution(int sub_id,Date date,String product_name,int pageNum) ;
    public JSONObject getPrintDis(int task_id);
}

