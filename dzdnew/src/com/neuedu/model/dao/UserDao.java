package com.neuedu.model.dao;

import com.neuedu.model.po.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    //注册用户
    public void register(User user) throws SQLException;

    //数据库分页
    public List<User> selectUserByPage(String username, String tel, String id_card, int pageSize, int pageNum);

    //页数查询
    public int  selectPageCount(String name, String tel, String id_card, int pageSize);

    //删除用户
    public void deleteUser(int id);

    //查找需要修改信息的用户
    public User getUserById(int id);

    //修改用户数据
    public void updateUser(User u);

    //是否有订单
    public boolean hasOrder(int id);

    //判断电话号码和身份证的合法性
    public boolean IsValidate(String tel,String id_card,int flag);

}
