package com.neuedu.controller;

import com.neuedu.model.po.user.Order;
import com.neuedu.model.service.user.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SelectOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码方式
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");//获取标识

        //查询
        if("search".equals(action)){

            System.out.println("进入订单查询");

            //采用Session来保存查询条件
            String pagenum = request.getParameter("pageNum");
            int pageNum = 1;
            String name ="";
            String tel = "";
            String order_id = "";
            String begin = "";//起始时间java.sql.Date
            String end = "";//结束时间java.sql.Date
            String type = "";//订单类型
            String status = "";//订单状态

            //有可能时间会出异常
            Date begin_date = null;//保存时间临时变量
            Date end_date = null;//保存时间临时变量
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            String pageSize  = "";
            if(pagenum != null && !"".equals(pagenum)){
                //点击页码查询
                System.out.println("------------订单查询By页码-----------------");
                pageNum = Integer.parseInt(pagenum);
                System.out.println("Pagenum:"+pageNum);
                name = (String) request.getSession().getAttribute("name");
                System.out.println("name："+name);
                tel = (String) request.getSession().getAttribute("tel");
                System.out.println("tel："+tel);
                begin = (String) request.getSession().getAttribute("begin");
                System.out.println("begin:"+begin);
                end = (String) request.getSession().getAttribute("end");
                System.out.println("end:"+end);
                order_id = (String) request.getSession().getAttribute("order_id");
                System.out.println("Order_id:"+order_id);
                type = (String) request.getSession().getAttribute("type");
                System.out.println("type:"+type);
                status = (String) request.getSession().getAttribute("status");
                System.out.println("status:"+status);
                pageSize = (String) request.getSession().getAttribute("pageSize");
                System.out.println("pagesize："+pageSize);
                System.out.println("----------------------------------------------");
            }else{
                System.out.println("------------订单查询By条件-----------------");
                //点击查询按钮进行查询
                name = (String) request.getParameter("name");
                System.out.println("name："+name);
                tel = (String) request.getParameter("tel");
                System.out.println("tel："+tel);
                begin = (String) request.getParameter("begin");
                System.out.println("begin:"+begin);
                end = (String) request.getParameter("end");
                System.out.println("end:"+end);
                order_id = (String) request.getParameter("order_id");
                System.out.println("Order_id:"+order_id);
                type = (String) request.getParameter("type");
                System.out.println("type:"+type);
                status = (String) request.getParameter("status");
                System.out.println("status:"+status);
                pageSize = (String) request.getParameter("pageSize");
                System.out.println("pagesize："+pageSize);
                System.out.println("--------------------------------------------");

            }
            int tempOrder_id = 0;//初始化order_id
            if(order_id != null && !"".equals(order_id)){
                tempOrder_id = Integer.parseInt(order_id);
            }
            int tempType = 0;//初始化type
            if(type != null && !"".equals(type)){
                tempType = Integer.parseInt(type);
            }
            int tempStatus = 0;//初始化Status
            if(status != null && !"".equals(status)){
                tempStatus = Integer.parseInt(status);
            }

            //提前判断日期是否为空并初始化
            java.sql.Date Begin = null;
            if(begin != null && !"".equals(begin)){
                try {
                      begin_date = format.parse(begin);
                      Begin = new java.sql.Date(begin_date.getTime());

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            java.sql.Date End = null;
            if(end != null && !"".equals(end)){
                try {
                    end_date = format.parse(end);
                    End = new java.sql.Date(end_date.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            System.out.println("----------------订单查询数据测试（时间、订单号、状态、类型）---------------------");
            System.out.println("orderid:"+tempOrder_id);
            System.out.println("Type:"+tempType);
            System.out.println("Status:"+status);
            System.out.println("Begin:"+Begin);
            System.out.println("End:"+End);
            System.out.println("-----------------------------------------------------------------------");

            // 分页查询
            List<Order> orderlist = OrderService.getInstance().selectOrder(Begin,End,tempOrder_id,tempType,tempStatus,name,tel,Integer.parseInt(pageSize),pageNum);
            System.out.println("orderlist"+orderlist.toString());
            System.out.println("order数量"+orderlist.size());

            //页数查询
            int pagecount = OrderService.getInstance().selectOrderCount(Begin,End,tempOrder_id,tempType,tempStatus,name,tel,Integer.parseInt(pageSize));
            System.out.println("当前订单查询的页数为："+pagecount);


            //数据回传
            request.setAttribute("orderlist",orderlist);
            request.setAttribute("pagecount",pagecount);
            request.getSession().setAttribute("pageNum", pageNum);
            request.getSession().setAttribute("name", name);
            request.getSession().setAttribute("tel", tel);
            request.getSession().setAttribute("begin", begin);
            request.getSession().setAttribute("end", end);
            request.getSession().setAttribute("order_id", order_id);
            request.getSession().setAttribute("type", type);
            request.getSession().setAttribute("status", status);
            request.getSession().setAttribute("pageSize", pageSize);

            //请求转发
            request.getRequestDispatcher("Order_Search.jsp").forward(request, response);
        }

    }

}
