package com.neuedu.model.dao;


import com.neuedu.model.po.User;
import com.neuedu.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao {

    Connection conn;//数据库连接实例

    public UserDaoImp(Connection conn){
        this.conn =  conn;
    }

    //注册
    @Override
    public void register(User user) throws SQLException{

         //sql语句
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into user(name, id_card_num, organization, landline_tel, tel, address, zip_cod, email, status) values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getId_card_num());
            ps.setString(3, user.getOrganization());
            ps.setString(4, user.getLandline_tel());
            ps.setString(5, user.getTel());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getZip_code());
            ps.setString(8, user.getEmail());
            ps.setInt(9, 1);
            ps.executeUpdate();
            System.out.println("数据插入成功");
        }
        catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        finally{
            DBUtil.closePS(ps);
        }
    }

    //分页查询
    @Override
    public List<User> selectUserByPage(String name, String tel, String id_card, int pageSize, int pageNum) {

        List<User> list = new ArrayList<User>();
        StringBuffer sbf = new StringBuffer("");
        sbf.append("  select *  from  user where 1=1  ");
        if(name != null && !"".equals(name)){
            sbf.append(" and name=? ");
        }

        if(tel != null && !"".equals(tel)){
            sbf.append(" and tel=? ");
        }

        if(id_card != null && !"".equals(id_card)){
            sbf.append(" and id_card_num=? ");
        }
        //只显示没有被删除的用户
        sbf.append(" and status=1 ");
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(sbf.toString()+" limit "+(pageNum-1)*pageSize+" , "+ pageSize);
            int index = 1;
            if(name != null && !"".equals(name)){
                ps.setString(index,name);
                index++;
            }
            if(tel != null && !"".equals(tel)){
               ps.setString(index,tel);
               index++;
            }
            if(id_card != null && !"".equals(id_card)){
                ps.setString(index,id_card);
            }

             //执行查询
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setName(rs.getString("name"));
                u.setId_card_num(rs.getString("id_card_num"));
                u.setOrganization(rs.getString("organization"));
                u.setLandline_tel(rs.getString("landline_tel"));
                u.setTel(rs.getString("tel"));
                u.setAddress(rs.getString("address"));
                u.setZip_code(rs.getString("zip_cod"));
                u.setEmail(rs.getString("email"));
                u.setUser_id(rs.getInt("user_id"));
                list.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DBUtil.closePS(ps);
        }
        return list;
    }

    //页数查询
    @Override
    public int selectPageCount(String name, String tel, String id_card, int pageSize) {

        int count = 0;
        PreparedStatement ps = null;
        StringBuffer sbf = new StringBuffer("");
        sbf.append("  select count(*) cc  from  user where 1=1 ");

        if(name != null && !"".equals(name)){
            sbf.append(" and name=? ");
        }

        if(tel != null && !"".equals(tel)){
            sbf.append(" and tel=? ");
        }

        if(id_card != null && !"".equals(id_card)){
            sbf.append(" and id_card_num=? ");
        }

        try {
             ps = conn.prepareStatement(sbf.toString());
            int index=1;

            if(name != null && !"".equals(name)){
                ps.setString(index,name);
                index++;
            }
            if(tel != null && !"".equals(tel)){
                ps.setString(index,tel);
                index++;
            }
            if(id_card != null && !"".equals(id_card)){
                ps.setString(index,id_card);
            }

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                count = rs.getInt("cc");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DBUtil.closePS(ps);
        }

        return count%pageSize==0? count/pageSize : count/pageSize+1;
    }

    //删除用户
    @Override
    public void deleteUser(int id) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" "+" update user set status=2 where user_id= "+id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBUtil.closePS(ps);
        }


    }

    //获取需要修改信息的用户
    @Override
    public User getUserById(int id) {

        User u = new User();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" "+" select * from user where user_id=?  ");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            //获取数据
            if(rs.next()){

                u.setName(rs.getString("name"));
                u.setId_card_num(rs.getString("id_card_num"));
                u.setOrganization(rs.getString("organization"));
                u.setLandline_tel(rs.getString("landline_tel"));
                u.setTel(rs.getString("tel"));
                u.setAddress(rs.getString("address"));
                u.setZip_code(rs.getString("zip_cod"));
                u.setEmail(rs.getString("email"));
                u.setStatus(rs.getInt("status"));
                u.setUser_id(rs.getInt("user_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    //更新用户数据
    @Override
    public void updateUser(User u) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" "+" update user set name=?,organization=?,landline_tel=?,tel=?,address=?,zip_cod=?,email=? where user_id=? ");
            ps.setString(1,u.getName());
            ps.setString(2,u.getOrganization());
            ps.setString(3,u.getLandline_tel());
            ps.setString(4,u.getTel());
            ps.setString(5,u.getAddress());
            ps.setString(6,u.getZip_code());
            ps.setString(7,u.getEmail());
            ps.setInt(8,u.getUser_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DBUtil.closePS(ps);
        }

    }

    //是否可删除
    @Override
    public boolean hasOrder(int id) {


        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" select * from `order` where client_id="+id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    //判断电话号码和身份证的合法性
    @Override
    public boolean IsValidate(String tel, String id_card, int flag) {

        StringBuffer sbf = new StringBuffer("");
        PreparedStatement ps = null;

        //电话号码验证
        if(flag == 1){
            sbf.append("  select tel  from  user where 1=1  ");
            sbf.append("  and tel = ?  ");
            System.out.println(sbf.toString());
        }
        //身份证验证
        if(flag == 2){
            sbf.append("  select id_card_num  from  user where 1=1  ");
            sbf.append("  and id_card_num = ?  ");
            System.out.println(sbf.toString());
        }

        try {
            ps = conn.prepareStatement(sbf.toString());
            int index=1;
            if(flag == 1){
                ps.setString(index,tel);
            }
            if(flag == 2){
                ps.setString(index,id_card);

                }
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                //能找到数据
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //无数据
        return false;
    }
}

