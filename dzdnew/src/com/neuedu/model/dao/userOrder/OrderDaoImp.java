package com.neuedu.model.dao.userOrder;

import com.neuedu.model.dao.userOrder.OrderDao;
import com.neuedu.model.po.user.Order;
import com.neuedu.model.po.user.Product;
import com.neuedu.model.po.Returns;
import com.neuedu.model.po.Unsubscribe;
import com.neuedu.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImp implements OrderDao {


    Connection conn;//数据库连接实例

    public OrderDaoImp(Connection conn){
        this.conn =  conn;
    }

    //获取一级目录
    @Override
    public String getOnetitle() {

        StringBuffer sbf = new StringBuffer("");
        sbf.append("[");
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" select onetitle_id,onetitle_name from onetitle where 1=1 ");
            //执行查询
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                sbf.append("{");
                sbf.append("\"v\":");
                sbf.append(rs.getString("onetitle_id")+",");
                sbf.append("\"n\":\"");
                sbf.append(rs.getString("onetitle_name")+"\"");
                if (rs.next()) {
                    sbf.append("},");
                    rs.previous();
                }
                else {
                    sbf.append("}");
                }

            }
         sbf.append("]");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sbf.toString();
    }

    //获取二级目录
    @Override
    public String getTwotitle(int onetitle_id) {

        StringBuffer sbf = new StringBuffer("");
        sbf.append("[");
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" select twotitle_id,twotitle_name from twotitle where 1=1 and onetitle_id="+onetitle_id);
            //执行查询
            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                sbf.append("{");
                sbf.append("\"v\":");
                sbf.append(rs.getString("twotitle_id")+",");
                sbf.append("\"n\":\"");
                sbf.append(rs.getString("twotitle_name")+"\"");
                if (rs.next()) {
                    sbf.append("},");
                    rs.previous();
                }
                else {
                    sbf.append("}");
                }

            }
            sbf.append("]");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sbf.toString();
    }

    //获取产品目录
    @Override
    public String getProductList(int twotitle_id) {

        //保存product Json数组
        StringBuffer sbf = new StringBuffer("");
        sbf.append("[");
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("  select product_id,product_name,measurement,original_price,notes,discount from product where 1=1 and twotitle_id="+twotitle_id);
            //执行查询
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                sbf.append("{");
                sbf.append("\"id\":");
                sbf.append(rs.getString("product_id")+",");
                sbf.append("\"name\":\"");
                sbf.append(rs.getString("product_name")+"\",");
                sbf.append("\"unit\":\"");
                sbf.append(rs.getString("measurement")+"\",");
                sbf.append("\"price\":\"");
                sbf.append(rs.getString("original_price")+"\",");
                sbf.append("\"notes\":\"");
                sbf.append(rs.getString("notes")+"\",");
                sbf.append("\"discount\":");
                sbf.append(rs.getFloat("discount"));
                if (rs.next()) {
                    sbf.append("},");
                    rs.previous();
                }
                else {
                    sbf.append("}");
                }
            }
            sbf.append("]");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sbf.toString();
    }

    //获取选中产品
    @Override
    public Product getProduct(int prod_id) {

        Product p = new Product();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(" select product_id,product_name,measurement,original_price,discount,notes from product where 1=1 and product_id= "+prod_id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setUnit(rs.getString("measurement"));
                p.setPrice(rs.getFloat("original_price"));
                p.setDiscount(rs.getFloat("discount"));
                p.setDescription(rs.getString("notes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    //库存够吗？
    @Override
    public boolean isLack(int prod_id,int amount) {

        int stock = 0;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" select can_distribute from wh_res_num where 1=1 and prod_id =  "+prod_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                stock = rs.getInt("can_distribute");
            }
            //库存量够
            if(stock>=amount){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //创建订单
    @Override
    public int CreateOrder(Order order) throws SQLException {

        //sql语句
        PreparedStatement ps = null;
        int id=0;//保存生成的ID
        try {
            ps = conn.prepareStatement(" insert into `order` (client_id,status,prod_id,amount,unit,price,discount,sum_money,finish_date,`create _date`,type,deliver_addr,consignee,consignee_tel,consignee_zip_cod,is_invoice,operator,operatorDate)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getClient_id());
            ps.setInt(2, order.getStatus());
            ps.setInt(3,order.getProd_id());
            ps.setInt(4,order.getAmount());
            ps.setString(5, order.getUnit());
            ps.setFloat(6, (float) order.getPrice());
            ps.setFloat(7, (float) order.getDiscount());
            ps.setFloat(8, (float) order.getSum_monney());
            ps.setDate(9, order.getFinish_date());
            ps.setDate(10,order.getCreate_date());
            ps.setInt(11, order.getType());
            ps.setString(12,order.getDeliver_addr());
            ps.setString(13, order.getConsignee());
            ps.setString(14, order.getConsignee_tel());
            ps.setString(15, order.getConsignee_zip_cod());
            ps.setInt(16, order.getInvoice());
            ps.setInt(17, order.getOperator());
            ps.setDate(18, order.getOperatorDate());
            ps.executeUpdate();
            ResultSet  rs = ps.getGeneratedKeys();

            if (rs != null&&rs.next()) {
                id=rs.getInt(1);
                //System.out.println("当前的主键为："+id);
            }
            System.out.println("订单创建成功");
        }
        catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        finally{
            DBUtil.closePS(ps);
        }
        return id;
    }

    //修改货量
    @Override
    public void ChangeStock(int prod_id,int amount) {


        PreparedStatement ps = null;
        try {
            String sql = " update wh_res_num set can_distribute=can_distribute-"+amount+", has_distribute=has_distribute+"+amount+" where prod_id= "+prod_id;
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBUtil.closePS(ps);
        }

    }
    //创建缺货单
    @Override
    public void createOutOfStock(int prod_id, int amount, int order_id) {

        //sql语句
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" insert into outofstock(prod_id, amount, status, order_id) values(?,?,?,?)");
            ps.setInt(1, prod_id);
            ps.setInt(2,amount);
            ps.setInt(3,0);
            ps.setInt(4,order_id);
            ps.executeUpdate();

        }
        catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        finally{
            DBUtil.closePS(ps);
        }
    }

    //获取用户信息
    @Override
    public List<Integer> getUserId(String name, String tel, String idcard) {

        List<Integer> u = new ArrayList<Integer>();
        StringBuffer sbf = new StringBuffer("");
        sbf.append("  select user_id  from  user where 1=1  ");
        if(name != null && !"".equals(name)){
            sbf.append(" and name=? ");
        }

        if(tel != null && !"".equals(tel)){
            sbf.append(" and tel=? ");
        }

        if(idcard != null && !"".equals(idcard)){
            sbf.append(" and id_card_num=? ");
        }
        //只显示没有被删除的用户
        sbf.append(" and status=1 ");
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(sbf.toString());
            int index = 1;
            if(name != null && !"".equals(name)){
                ps.setString(index,name);
                index++;
            }
            if(tel != null && !"".equals(tel)){
                ps.setString(index,tel);
                index++;
            }
            if(idcard != null && !"".equals(idcard)){
                ps.setString(index,idcard);
            }

            //执行查询
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                u.add(rs.getInt("user_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DBUtil.closePS(ps);
        }
        return u;
    }

    //订单信息查询

    /*  flag
     *  1-退订的查询
     *  2-退货查询
    */
    @Override
    public List<Order> selectUserOreder(List<Integer> uids, int pageSize, int pageNum,int flag) {

        List<Order> order = new ArrayList<Order>();
        StringBuffer sbf = new StringBuffer("");

        if(flag == 1){//1-退订的查询

            sbf.append("  select *  from  `order` where 1=1");
            sbf.append(" and  type = 1 and (status =2 or status =1) ");
            System.out.println("此次查询为退订");
        }
        if(flag == 2){//2-退货查询

           sbf.append(" select o.*,p.product_name from `order` o left join product p on o.prod_id = p.product_id where 1=1");
           sbf.append(" and  o.type = 1 and o.status =4 ");
           System.out.println("此次查询为退货");

        }
        String ids = uids.toString().replace('[','(').replace(']',')');

        sbf.append(" and client_id in "+ids);
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sbf.toString()+" limit "+(pageNum-1)*pageSize+" , "+ pageSize);
            //执行查询
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Order o = new Order();

                o.setOrder_id( rs.getInt("order_id"));
                o.setClient_id(rs.getInt("client_id"));
                o.setStatus(rs.getInt("status"));
                o.setProd_id( rs.getInt("prod_id"));
                o.setAmount( rs.getInt("amount"));
                o.setUnit(  rs.getString("unit"));
                o.setPrice( rs.getFloat("price"));
                o.setDiscount( rs.getFloat("discount"));
                o.setSum_monney( rs.getFloat("sum_money"));
                o.setFinish_date(rs.getDate("finish_date"));
                o.setCreate_date(rs.getDate("create _date"));
                o.setType( rs.getInt("type"));
                o.setDeliver_addr( rs.getString("deliver_addr"));
                o.setConsignee( rs.getString("consignee"));
                o.setConsignee_tel( rs.getString("consignee_tel"));
                o.setConsignee_zip_cod(  rs.getString("consignee_zip_cod"));
                o.setInvoice( rs.getInt("is_invoice"));
                if(flag == 2){
                    o.setProd_name(rs.getString("product_name"));
                }

                order.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally{
            DBUtil.closePS(ps);
        }

        return order;
    }


    /*  flag
     *  1-退订的查询
     *  2-退货查询
     */
    //获取查询页数
    @Override
    public int selectPageCount(List<Integer> uids, int pageSize, int pageNum,int flag) {

        int count = 0;
        StringBuffer sbf = new StringBuffer("");
        sbf.append("  select count(*) cc  from  `order` where 1=1 ");

        if(flag == 1){//退订的查询页码
            sbf.append(" and  type = 1 and (status =2 or status =1) ");

        }
        if(flag == 2){//退货查询页码
            sbf.append(" and  type = 1 and status =4 ");

        }

        String ids = uids.toString().replace('[','(').replace(']',')');

        sbf.append(" and client_id in "+ids);
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sbf.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                count = rs.getInt("cc");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DBUtil.closePS(ps);
        }
        return count%pageSize==0? count/pageSize : count/pageSize+1;
    }


    //获取订单
    @Override
    public Order getOrder(int order_id) {

        Order o = new Order();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" "+" select o.*,p.product_name from `order` o left join product p on o.prod_id = p.product_id where o.order_id="+order_id);
            ResultSet rs = ps.executeQuery();
            //获取数据
            if(rs.next()){
                o.setOrder_id( rs.getInt("order_id"));
                o.setClient_id(rs.getInt("client_id"));
                o.setStatus(rs.getInt("status"));
                o.setProd_id( rs.getInt("prod_id"));
                o.setAmount( rs.getInt("amount"));
                o.setUnit(  rs.getString("unit"));
                o.setPrice( rs.getFloat("price"));
                o.setDiscount( rs.getFloat("discount"));
                o.setSum_monney( rs.getFloat("sum_money"));
                o.setFinish_date(rs.getDate("finish_date"));
                o.setCreate_date(rs.getDate("create _date"));
                o.setType( rs.getInt("type"));
                o.setDeliver_addr( rs.getString("deliver_addr"));
                o.setConsignee( rs.getString("consignee"));
                o.setConsignee_tel( rs.getString("consignee_tel"));
                o.setConsignee_zip_cod(  rs.getString("consignee_zip_cod"));
                o.setInvoice( rs.getInt("is_invoice"));
                o.setProd_name(rs.getString("product_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }


    //生成退订订单
    @Override
    public void CreateUnsubscribe(Unsubscribe us) {
        //sql语句
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        try {

            ps = conn.prepareStatement(" insert into unsubscribe(order_id, finish_date, `create _date`, date, reason, operator, operatorDate) values(?,?,?,?,?,?,?)");
            ps.setInt(1, us.getOrder_id());
            ps.setDate(2, us.getFinish_date());
            ps.setDate(3,us.getCreate_date());
            ps.setDate(4, us.getRefund_time());
            ps.setString(5,us.getReason());
            ps.setInt(6, us.getOperator());
            ps.setDate(7, us.getOperatorDate());
            ps.executeUpdate();

            //更新订单状态
            ps2 = conn.prepareStatement(" update `order` set type = 2 where order_id ="+us.getOrder_id());
            ps2.executeUpdate();

            //更新库存
            String sql = " update wh_res_num set can_distribute=can_distribute+"+us.getAmount()+", has_distribute=has_distribute-"+us.getAmount()+" where prod_id= "+us.getProd_id();
            ps3 = conn.prepareStatement(sql);
            ps3.executeUpdate();

            System.out.println("退订订单生成成功");
        }
        catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        finally{
            DBUtil.closePS(ps);
        }
    }


    //生成退货单
    @Override
    public void CreateReturn(Returns r) throws SQLException {

        //sql语句
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        try {

            ps = conn.prepareStatement(" insert into `return` (order_id, finish_date, `create _date`, amount, date, reason, operator, operatorDate)  values(?,?,?,?,?,?,?,?)");
            ps.setInt(1, r.getOrder_id());
            ps.setDate(2, r.getFinish_date());
            ps.setDate(3,r.getCreate_date());
            ps.setInt(4, r.getAmount());
            ps.setDate(5, r.getReturn_time());
            ps.setString(6,r.getReason());
            ps.setInt(7, r.getOperator());
            ps.setDate(8, r.getOperatorDate());
            ps.executeUpdate();

            //更新order表
            ps2 = conn.prepareStatement(" update `order` set type = 3,status = 2 where order_id ="+r.getOrder_id());
            ps2.executeUpdate();

            //更新库存
            String sql = " update wh_res_num set can_distribute=can_distribute+"+r.getAmount()+", has_distribute=has_distribute-"+r.getAmount()+" where prod_id= "+r.getProd_id();
            ps3 = conn.prepareStatement(sql);
            ps3.executeUpdate();


        }
        catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        finally{
            DBUtil.closePS(ps);
        }
    }


    //订单信息查询
    @Override
    public List<Order> selectOrder(java.sql.Date begin, java.sql.Date end, int order_id, int type, int status, String name, String tel, int pageSize, int pageNum) {


        List<Order> order = new ArrayList<Order>();
        StringBuffer sbf = new StringBuffer("");
        sbf.append(" select o.*,p.product_name from `order` o left join product p on o.prod_id = p.product_id where 1=1 ");
        PreparedStatement ps = null;
        if(begin != null){

            //两个时间条件非空
            if(end != null){
                sbf.append(" and  `create _date` between ? and ? ");
            }
            //只有开始时间
            else {
                sbf.append(" and  `create _date` >= ? ");
            }

        }
        //只有结束时间
        if(begin == null && end != null) {

            sbf.append(" and  `create _date` <= ? ");
        }
        //包含订单号
        if(order_id != 0){

            sbf.append(" and order_id= "+order_id);
        }
        //包含订单类型
        if(type != 0){

            sbf.append(" and o.type= "+type);
        }
        //包含订单状态
        if(status != 0){

            sbf.append(" and o.status= "+status);
        }
        //包含客户姓名
        if(name != null && !"".equals(name)){

            sbf.append(" and consignee= ?");
        }
        //包含用户电话
        if(tel != null && !"".equals(tel)){

            sbf.append(" and consignee_tel= ? ");
        }
        try {
            ps = conn.prepareStatement(sbf.toString()+" limit "+(pageNum-1)*pageSize+" , "+ pageSize);
            int index = 1;//记录插入位置
            if(begin != null){

                //两个时间条件非空
                if(end != null){
                    ps.setDate(index,begin);
                    index++;
                    ps.setDate(index,end);
                    index++;
                }
                //只有开始时间
                else {
                    ps.setDate(index,begin);
                    index++;
                }
            }
            //只有结束时间
            if(begin == null && end != null) {
                ps.setDate(index,end);
                index++;
            }
            //包含客户姓名
            if(name != null && !"".equals(name)){
                ps.setString(index,name);
                index++;

            }
            //包含用户电话
            if(tel != null && !"".equals(tel)){

                ps.setString(index,tel);
                index++;
            }
            //执行查询
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Order o = new Order();
                o.setOrder_id( rs.getInt("order_id"));
                o.setClient_id(rs.getInt("client_id"));
                o.setStatus(rs.getInt("status"));
                o.setProd_id( rs.getInt("prod_id"));
                o.setAmount( rs.getInt("amount"));
                o.setUnit(  rs.getString("unit"));
                o.setPrice( rs.getFloat("price"));
                o.setDiscount( rs.getFloat("discount"));
                o.setSum_monney( rs.getFloat("sum_money"));
                o.setFinish_date(rs.getDate("finish_date"));
                o.setCreate_date(rs.getDate("create _date"));
                o.setType( rs.getInt("type"));
                o.setDeliver_addr( rs.getString("deliver_addr"));
                o.setConsignee( rs.getString("consignee"));
                o.setConsignee_tel( rs.getString("consignee_tel"));
                o.setConsignee_zip_cod(  rs.getString("consignee_zip_cod"));
                o.setInvoice( rs.getInt("is_invoice"));
                o.setProd_name(rs.getString("product_name"));
                order.add(o);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally{
            DBUtil.closePS(ps);
        }

        return order;
    }


    //订单查询总页数
    @Override
    public int selectOrderCount(java.sql.Date begin, java.sql.Date end, int order_id, int type, int status, String name, String tel, int pageSize) {

        int count = 0;
        StringBuffer sbf = new StringBuffer("");
        sbf.append("  select count(*) cc  from  `order` where 1=1 ");
        PreparedStatement ps = null;
        if(begin != null){

            //两个时间条件非空
            if(end != null){
                sbf.append(" and  `create _date` between ? and ? ");
            }
            //只有开始时间
            else {
                sbf.append(" and  `create _date` >= ? ");
            }

        }
        //只有结束时间
        if(begin == null && end != null) {

            sbf.append(" and  `create _date` <= ? ");
        }
        //包含订单号
        if(order_id != 0){

            sbf.append(" and order_id= "+order_id);
        }
        //包含订单类型
        if(type != 0){

            sbf.append(" and type= "+type);
        }
        //包含订单状态
        if(status != 0){

            sbf.append(" and status= "+status);
        }
        //包含客户姓名
        if(name != null && !"".equals(name)){

            sbf.append(" and consignee= ?");
        }
        //包含用户电话
        if(tel != null && !"".equals(tel)){

            sbf.append(" and consignee_tel= ? ");
        }

        try {
            ps = conn.prepareStatement(sbf.toString());
            int index = 1;//记录插入位置
            if(begin != null){

                //两个时间条件非空
                if(end != null){
                    ps.setDate(index,begin);
                    index++;
                    ps.setDate(index,end);
                    index++;
                }
                //只有开始时间
                else {
                    ps.setDate(index,begin);
                    index++;
                }
            }
            //只有结束时间
            if(begin == null && end != null) {
                ps.setDate(index,end);
                index++;
            }
            //包含客户姓名
            if(name != null && !"".equals(name)){
                ps.setString(index,name);
                index++;

            }
            //包含用户电话
            if(tel != null && !"".equals(tel)){

                ps.setString(index,tel);
                index++;
            }
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                count = rs.getInt("cc");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DBUtil.closePS(ps);
        }
        return count%pageSize==0? count/pageSize : count/pageSize+1;
    }
}
