package com.neuedu.model.dao;

import com.neuedu.model.po.OperatorInfo;
import com.neuedu.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperatorDaoImp implements  OperatorDao{

    Connection conn;//数据库连接实例

    public OperatorDaoImp(Connection conn) {
        this.conn = conn;
    }


    //获取操作员数据
    @Override
    public String getOperator() {

        StringBuffer sbf = new StringBuffer("");
        sbf.append("[{\"v\":\"\",\"n\":\"请选择操作员\"},");
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" select manager_id,name from manager where type = 1");
            //执行查询
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                sbf.append("{");
                sbf.append("\"v\":");
                sbf.append(rs.getString("manager_id")+",");
                sbf.append("\"n\":\"");
                sbf.append(rs.getString("name")+"\"");
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

    //获取操作员新订操作量
    @Override
    public List<OperatorInfo> getNewInfo(Date begin, Date end, int operator, String name) {

        List<OperatorInfo> info = new ArrayList<OperatorInfo>();
        StringBuffer sbf = new StringBuffer("");
        sbf.append(" select product_name,sum(sum_money) as sum_money, COUNT(*) as count_num,sum(amount) as tol_amount from op_new where 1=1 and operator="+operator);
        PreparedStatement ps = null;
        if(begin != null){

            //两个时间条件非空
            if(end != null){
                sbf.append(" and  operatorDate between ? and ? ");
            }
            //只有开始时间
            else {
                sbf.append(" and  operatorDate >= ? ");
            }

        }
        //只有结束时间
        if(begin == null && end != null) {

            sbf.append(" and  operatorDate <= ? ");
        }
        //有产品姓名
        if(name != null && !"".equals(name)){

            sbf.append(" and product_name= ?");
        }
        sbf.append(" group by product_name ");

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
            //有产品姓名
            if(name != null && !"".equals(name)){
                ps.setString(index,name);
                index++;

            }

            //执行查询
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                OperatorInfo o = new OperatorInfo();
                o.setAmount(rs.getInt("tol_amount"));
                o.setCount_num(rs.getInt("count_num"));
                o.setProd_name(rs.getString("product_name"));
                o.setSum_money(rs.getFloat("sum_money"));
                info.add(o);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DBUtil.closePS(ps);
        }


        return info;
    }

    //获取操作员退订操作量
    @Override
    public List<OperatorInfo> getReturnInfo(Date begin, Date end, int operator, String name) {

        List<OperatorInfo> info = new ArrayList<OperatorInfo>();
        StringBuffer sbf = new StringBuffer("");
        sbf.append(" select product_name,sum(sum_money) as sum_money, COUNT(*) as count_num,sum(amount) as tol_amount from  op_return where 1=1 and operator="+operator);

        PreparedStatement ps = null;
        if(begin != null){

            //两个时间条件非空
            if(end != null){
                sbf.append(" and  operatorDate between ? and ? ");
            }
            //只有开始时间
            else {
                sbf.append(" and  operatorDate >= ? ");
            }

        }
        //只有结束时间
        if(begin == null && end != null) {

            sbf.append(" and  operatorDate <= ? ");
        }
        //有产品姓名
        if(name != null && !"".equals(name)){

            sbf.append(" and product_name= ?");
        }
        sbf.append(" group by product_name ");

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
            //有产品姓名
            if(name != null && !"".equals(name)){
                ps.setString(index,name);
                index++;

            }

            //执行查询
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                OperatorInfo o = new OperatorInfo();
                o.setAmount(rs.getInt("tol_amount"));
                o.setCount_num(rs.getInt("count_num"));
                o.setProd_name(rs.getString("product_name"));
                o.setSum_money(rs.getFloat("sum_money"));
                info.add(o);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally{
            DBUtil.closePS(ps);
        }

        return info;
    }

    //获取操作员退货操作量
    @Override
    public List<OperatorInfo> getUnsubscribeInfo(Date begin, Date end, int operator, String name) {

        List<OperatorInfo> info = new ArrayList<OperatorInfo>();
        StringBuffer sbf = new StringBuffer("");
        sbf.append(" select product_name,sum(sum_money) as sum_money, COUNT(*) as count_num,sum(amount) as tol_amount from  task_order_view where 1=1 and operator="+operator);

        PreparedStatement ps = null;
        if(begin != null){

            //两个时间条件非空
            if(end != null){
                sbf.append(" and  operatorDate between ? and ? ");
            }
            //只有开始时间
            else {
                sbf.append(" and  operatorDate >= ? ");
            }

        }
        //只有结束时间
        if(begin == null && end != null) {

            sbf.append(" and  operatorDate <= ? ");
        }
        //有产品姓名
        if(name != null && !"".equals(name)){

            sbf.append(" and product_name= ?");
        }
        sbf.append(" group by product_name ");

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
            //有产品姓名
            if(name != null && !"".equals(name)){
                ps.setString(index,name);
                index++;

            }

            //执行查询
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                OperatorInfo o = new OperatorInfo();
                o.setAmount(rs.getInt("tol_amount"));
                o.setCount_num(rs.getInt("count_num"));
                o.setProd_name(rs.getString("product_name"));
                o.setSum_money(rs.getFloat("sum_money"));
                info.add(o);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally{
            DBUtil.closePS(ps);
        }

        return info;
    }
}
