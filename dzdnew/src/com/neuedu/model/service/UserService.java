package com.neuedu.model.service;


import com.neuedu.model.dao.UserDao;
import com.neuedu.model.dao.UserDaoImp;
import com.neuedu.model.po.User;
import com.neuedu.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserService(){

    }

    private static UserService service = new UserService();

    public static UserService getInstance(){
        return service;
    }

    //测试数据库
    public boolean testConnect(){
        Connection conn = DBUtil.getConn();
        if(conn!=null){
            System.out.println("连接数据库成功！！");
            return true;
        }
        return false;
    }

    //注册用户
    public void register(User user) throws ClassNotFoundException, SQLException {
        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        //开启事务
        DBUtil.beginTransaction(conn);
        //增删改要开启事务，在出异常时进行回滚，否则提交
        try{
            UserDao dao = new UserDaoImp(conn);
            dao.register(user);
            //提交
            DBUtil.commit(conn);
        }
        catch (Exception e) {
            // TODO: handle exception
            DBUtil.rollback(conn);
        }
        finally{
            DBUtil.closeConn(conn);
        }
    }

    //分页查询
    public List<User> selectUserByPage(String name, String tel, String id_card, int pageSize, int pageNum) {
        Connection conn = DBUtil.getConn();
        UserDao dao = new UserDaoImp(conn);
        return dao.selectUserByPage(name, tel,id_card,pageSize,pageNum);
    }

    //查询页面的总数
    public int selectPageCount(String name, String tel, String id_card, int pageSize) {
        Connection conn = DBUtil.getConn();
        UserDao dao = new UserDaoImp(conn);
        return dao.selectPageCount(name,tel,id_card,pageSize);
    }

    //删除用户
    public void deleteUser(int id) {
        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        //开启事务
        DBUtil.beginTransaction(conn);
        //增删改要开启事务，在出异常时进行回滚，否则提交
        try{
            UserDao dao = new UserDaoImp(conn);
            dao.deleteUser(id);
            //提交
            DBUtil.commit(conn);
        }
        catch (Exception e) {
            // TODO: handle exception
            DBUtil.rollback(conn);
        }
        finally{
            DBUtil.closeConn(conn);
        }
    }

    //获取需要修改信息的用户
    public User getUserById(int id) {
        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        UserDao dao = new UserDaoImp(conn);
        return dao.getUserById(id);
    }

    //更新用户数据
    public void updateUser(User u){
        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        //开启事务
        DBUtil.beginTransaction(conn);
        try {
            UserDao dao = new UserDaoImp(conn);
            dao.updateUser(u);
            //提交
            DBUtil.commit(conn);
        } catch (Exception e) {
            //回滚
            DBUtil.rollback(conn);
        }finally{
            DBUtil.closeConn(conn);
        }

    }


    //是否可删除
    public boolean hasOrder(int id) {
        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        UserDao dao = new UserDaoImp(conn);
        return dao.hasOrder(id);
    }

    //判断电话号码和身份证的合法性
    public boolean IsValidate(String tel,String id_card,int flag){

        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        UserDao dao = new UserDaoImp(conn);
        return dao.IsValidate(tel,id_card,flag);

    }

}
