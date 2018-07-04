package com.neuedu.model.dao;

import com.neuedu.model.po.OperatorInfo;

import java.util.List;

public interface OperatorDao {

    //获取操作员数据
    public String getOperator();

    //获取操作员新订操作量
    public List<OperatorInfo> getNewInfo(java.sql.Date begin,java.sql.Date end,int operator,String name);
    //获取操作员退订操作量
    public List<OperatorInfo> getReturnInfo(java.sql.Date begin,java.sql.Date end,int operator,String name);
    //获取操作员退货操作量
    public List<OperatorInfo> getUnsubscribeInfo(java.sql.Date begin,java.sql.Date end,int operator,String name);
}
