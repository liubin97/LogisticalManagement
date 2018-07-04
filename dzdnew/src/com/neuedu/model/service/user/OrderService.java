package com.neuedu.model.service.user;


import com.neuedu.model.dao.userOrder.OrderDao;
import com.neuedu.model.dao.userOrder.OrderDaoImp;
import com.neuedu.model.po.user.Order;
import com.neuedu.model.po.user.Product;
import com.neuedu.model.po.Returns;
import com.neuedu.model.po.Unsubscribe;
import com.neuedu.utils.DBUtil;
import java.sql.Connection;
import java.util.List;

public class OrderService {

    private OrderService(){

    }

    private static OrderService service = new OrderService();

    public static OrderService getInstance(){
        return service;
    }

    //获取一级目录
    public String getOnetitle(){
        Connection conn = DBUtil.getConn();
        OrderDao dao = new OrderDaoImp(conn);
        return dao.getOnetitle();
    }

    //获取二级目录
    public String getTwotitle(int onetitle_id){
        Connection conn = DBUtil.getConn();
        OrderDao dao = new OrderDaoImp(conn);
        return dao.getTwotitle(onetitle_id);

    }

    //获取产品列表
    public String getProductList(int twotitle_id){
        Connection conn = DBUtil.getConn();
        OrderDao dao = new OrderDaoImp(conn);
        return dao.getProductList(twotitle_id);
    }

    //获取产品
    public Product getProduct(int prod_id){
        Connection conn = DBUtil.getConn();
        OrderDao dao = new OrderDaoImp(conn);
        return dao.getProduct(prod_id);
    }



    //库存够吗？
    public boolean isLack(int prod_id,int amount){
        Connection conn = DBUtil.getConn();
        OrderDao dao = new OrderDaoImp(conn);
        return dao.isLack(prod_id,amount);
    }

    //创建订单
    public int CreateOrder(Order order) {

        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        //开启事务
        DBUtil.beginTransaction(conn);
        //增删改要开启事务，在出异常时进行回滚，否则提交
        int id = 0;

        try {
            OrderDao dao = new OrderDaoImp(conn);
            id = dao.CreateOrder(order);
            //提交
            DBUtil.commit(conn);
        }
        catch (Exception e) {
            //回滚
            DBUtil.rollback(conn);
        }
        finally{
            DBUtil.closeConn(conn);
        }

        return id;

    }


    //修改货量
    public void ChangeStock(int prod_id,int amount) {

        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        //开启事务
        DBUtil.beginTransaction(conn);
        try {
            OrderDao dao = new OrderDaoImp(conn);
            dao.ChangeStock(prod_id,amount);
            //提交
            DBUtil.commit(conn);
        } catch (Exception e) {
            //回滚
            DBUtil.rollback(conn);
        }finally{
            DBUtil.closeConn(conn);
        }
    }

    //创建缺货单
    public void createOutOfStock(int prod_id,int amount,int order_id){

        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        //开启事务
        DBUtil.beginTransaction(conn);
        //增删改要开启事务，在出异常时进行回滚，否则提交

        try {
            OrderDao dao = new OrderDaoImp(conn);
            dao.createOutOfStock(prod_id,amount,order_id);
            //提交
            DBUtil.commit(conn);
        }
        catch (Exception e) {
            //回滚
            DBUtil.rollback(conn);
        }
        finally{
            DBUtil.closeConn(conn);
        }

    }

    //获取用户信息
    public List<Integer> getUserId(String name, String tel, String idcard) {

        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        OrderDao dao = new OrderDaoImp(conn);
        return dao.getUserId(name,tel,idcard);
    }

    //订单信息查询
    public List<Order> selectUserOreder(List<Integer> uids, int pageSize, int pageNum,int flag) {

        Connection conn = DBUtil.getConn();
        OrderDao dao = new OrderDaoImp(conn);
        return dao.selectUserOreder(uids,pageSize,pageNum,flag);
    }

    //查询页面的总数
    public int selectPageCount(List<Integer> uids, int pageSize, int pageNum,int flag){

        Connection conn = DBUtil.getConn();
        OrderDao dao = new OrderDaoImp(conn);
        return dao.selectPageCount(uids,pageSize,pageNum,flag);
    }

    //获取订单
    public Order getOrder(int order_id){
        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        OrderDao dao = new OrderDaoImp(conn);
        return dao.getOrder(order_id);
    }

    //生成退订订单
    public void CreateUnsubscribe(Unsubscribe us){

        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        //开启事务
        DBUtil.beginTransaction(conn);
        //增删改要开启事务，在出异常时进行回滚，否则提交
        try {
            OrderDao dao = new OrderDaoImp(conn);
            dao.CreateUnsubscribe(us);
            //提交
            DBUtil.commit(conn);
        }
        catch (Exception e) {
            //回滚
            DBUtil.rollback(conn);
        }
        finally{
            DBUtil.closeConn(conn);
        }

    }


    //生成退货单
    public void CreateReturn(Returns r){

        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        //开启事务
        DBUtil.beginTransaction(conn);
        //增删改要开启事务，在出异常时进行回滚，否则提交

        try {
            OrderDao dao = new OrderDaoImp(conn);
            dao.CreateReturn(r);
            //提交
            DBUtil.commit(conn);
        }
        catch (Exception e) {
            //回滚
            DBUtil.rollback(conn);
        }
        finally{
            DBUtil.closeConn(conn);
        }
    }

    //订单信息查询
    public List<Order> selectOrder(java.sql.Date begin,java.sql.Date end,int order_id,int type,int status,String name,String tel, int pageSize, int pageNum){
        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        OrderDao dao = new OrderDaoImp(conn);
        return dao.selectOrder(begin,end,order_id,type,status,name,tel,pageSize,pageNum);
    }

    //订单查询总页数
    public int selectOrderCount(java.sql.Date begin, java.sql.Date end, int order_id, int type, int status, String name, String tel, int pageSize) {
        Connection conn = DBUtil.getConn();
        OrderDao dao = new OrderDaoImp(conn);
        return dao.selectOrderCount(begin, end, order_id, type, status, name, tel, pageSize);
    }
}
