package com.neuedu.model.dao.userOrder;

import com.neuedu.model.po.user.Order;
import com.neuedu.model.po.user.Product;
import com.neuedu.model.po.Returns;
import com.neuedu.model.po.Unsubscribe;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    //获取一级目录
    public String getOnetitle();

    //获取二级目录
    public String getTwotitle(int onetitle_id);

    //获取产品目录
    public String getProductList(int twotitle_id);

    //获取选中产品
    public Product getProduct(int prod_id);

    //库存够吗？
    public boolean isLack(int prod_id, int amount);

    //创建订单
    public int CreateOrder(Order order) throws SQLException;

    //修改货量
    public void ChangeStock(int prod_id, int amount);

    //创建缺货单
    public void createOutOfStock(int prod_id, int amount, int order_id) throws SQLException;

    //获取用户信息
    public List<Integer> getUserId(String name, String tel, String idcard);

    //退货换货订单信息查询
    public List<Order> selectUserOreder(List<Integer> uids, int pageSize, int pageNum, int flag);

    //查询页面的总数
    public int selectPageCount(List<Integer> uids, int pageSize, int pageNum,int flag);

    //获取订单
    public Order getOrder(int order_id);

    //生成退订订单
    public void CreateUnsubscribe(Unsubscribe us) throws SQLException;

    //生成退货单
    public void CreateReturn(Returns r) throws SQLException;

    //订单信息查询
    public List<Order> selectOrder(java.sql.Date begin,java.sql.Date end,int order_id,int type,int status,String name,String tel, int pageSize, int pageNum);


    //订单查询总页数
    public int selectOrderCount(java.sql.Date begin,java.sql.Date end,int order_id,int type,int status,String name,String tel, int pageSize);
}