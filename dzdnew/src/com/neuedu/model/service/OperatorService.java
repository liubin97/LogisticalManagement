package com.neuedu.model.service;

import com.neuedu.model.dao.OperatorDao;
import com.neuedu.model.dao.OperatorDaoImp;
import com.neuedu.model.po.OperatorInfo;
import com.neuedu.utils.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class OperatorService {

    private OperatorService(){

    }

    private static OperatorService service = new OperatorService();

    //单例模式
    public static OperatorService getInstance(){
        return service;
    }

    //获取操作员数据
    public String getOperator() {

        Connection conn = DBUtil.getConn();
        OperatorDao dao = new OperatorDaoImp(conn);
        return dao.getOperator();
    }

    //获取操作员新订操作量
    public List<OperatorInfo> getNewInfo(Date begin, Date end, int operator, String name) {


        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        OperatorDao dao = new OperatorDaoImp(conn);
        return dao.getNewInfo(begin,end,operator,name);
    }

    //获取操作员退订操作量
    public List<OperatorInfo> getReturnInfo(Date begin, Date end, int operator, String name) {

        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        OperatorDao dao = new OperatorDaoImp(conn);
        return dao.getReturnInfo(begin,end,operator,name);
    }

    //获取操作员退货操作量
    public List<OperatorInfo> getUnsubscribeInfo(Date begin, Date end, int operator, String name) {

        //获取数据库的连接
        Connection conn = DBUtil.getConn();
        OperatorDao dao = new OperatorDaoImp(conn);
        return dao.getUnsubscribeInfo(begin,end,operator,name);
    }


}
