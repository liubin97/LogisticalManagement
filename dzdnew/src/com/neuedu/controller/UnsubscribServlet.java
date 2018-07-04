package com.neuedu.controller;

import com.google.gson.Gson;
import com.neuedu.model.po.user.Order;
import com.neuedu.model.po.Unsubscribe;
import com.neuedu.model.po.User;
import com.neuedu.model.service.user.OrderService;
import com.neuedu.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UnsubscribServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //设置编码方式
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");//获取标识

        //查询客户
        if("search".equals(action)){

            //采用Session来保存查询条件

            System.out.println("进入退订客户查询");
            String pagenum = request.getParameter("pageNum");
            int pageNum = 1;
            String name ="";
            String tel = "";
            String idcard= "";
            String pageSize  = "";
            if(pagenum != null && !"".equals(pagenum)){
                //点击页码查询
                System.out.println("------------退订用户查询By页码-----------------");
                pageNum = Integer.parseInt(pagenum);
                System.out.println("Pagenum:"+pagenum);
                name = (String) request.getSession().getAttribute("name");
                System.out.println("name："+name);
                tel = (String) request.getSession().getAttribute("tel");
                System.out.println("tel："+tel);
                idcard = (String) request.getSession().getAttribute("id_card");
                System.out.println("id_card："+idcard);
                pageSize = (String) request.getSession().getAttribute("pageSize");
                System.out.println("pagesize："+pageSize);
                System.out.println("----------------------------------------------");
            }else{
                System.out.println("------------退订用户查询By条件-----------------");
                //点击查询按钮进行查询
                name = (String) request.getParameter("name");
                System.out.println("name："+name);
                tel = (String) request.getParameter("tel");
                System.out.println("tel："+tel);
                idcard = (String) request.getParameter("id_card");
                System.out.println("id_card："+idcard);
                pageSize = (String) request.getParameter("pageSize");
                System.out.println("pagesize："+pageSize);
                System.out.println("--------------------------------------------");

            }

            //获取用户id
            List<Integer> uids = OrderService.getInstance().getUserId(name,tel,idcard);
            System.out.println("uids:"+uids.toString());

            // 分页查询
            List<Order> orderlist = OrderService.getInstance().selectUserOreder(uids,Integer.parseInt(pageSize),pageNum,1);
            System.out.println("orderlist"+orderlist.toString());
            //页数查询
            int pagecount = OrderService.getInstance().selectPageCount(uids,Integer.parseInt(pageSize),pageNum,1);
            System.out.println("退订用户查询页数为"+pagecount);

            //数据回传
            request.setAttribute("orderlist",orderlist);
            request.setAttribute("pagecount",pagecount);
            request.getSession().setAttribute("pageNum", pageNum);
            request.getSession().setAttribute("name", name);
            request.getSession().setAttribute("tel", tel);
            request.getSession().setAttribute("id_card", idcard);
            request.getSession().setAttribute("pageSize", pageSize);

            request.getRequestDispatcher("Order_Refund.jsp").forward(request, response);

        }

        //获取订单详情
        if("Info".equals(action)){

            String flag = request.getParameter("flag");
            String info="";//保存信息
            Gson gson = new Gson();
            if("order".equals(flag)){

                System.out.println("进入获取订单");
                String order_id = request.getParameter("orderid");
                //获取用户信息
                Order o = OrderService.getInstance().getOrder(Integer.parseInt(order_id));
                //转为json
                info = gson.toJson(o);
                System.out.println(info);
            }
            if("user".equals(flag)){

                System.out.println("进入获取用户");
                String user_id = request.getParameter("userId");
                System.out.println("当前userid："+user_id);
                User u = UserService.getInstance().getUserById(Integer.parseInt(user_id));
                //转为jsonu
                info = gson.toJson(u);
                System.out.println(info);

            }

            //把消息发送到前端
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.print(info);
            out.close();

        }

        //退订
        if("subscribe".equals(action)){

            System.out.println("进入退订");
            String order_id = request.getParameter("orderid");
            System.out.println("orderid:"+order_id);
            //获取用户信息
            Order o = OrderService.getInstance().getOrder(Integer.parseInt(order_id));
            System.out.println("退订："+o.toString());
            //把用户信息传回去
            request.setAttribute("o",o);
            //请求转发
            request.getRequestDispatcher("Order_Refund2.jsp").forward(request,response);


        }

        //生成退订订单
        if("createSub".equals(action)){

            System.out.println("进入生成退订订单");
            //获取数据
            int order_id = Integer.parseInt(request.getParameter("order_id"));
            int amount = Integer.parseInt(request.getParameter("amount"));
            int prod_id = Integer.parseInt(request.getParameter("prod_id"));
            System.out.println("aomunt:"+amount);
            System.out.println("prod_id:"+prod_id);
            //有可能时间会出异常
            Date f_date = null;
            Date c_date = null;
            Date d = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                f_date = format.parse(request.getParameter("finish_date"));
                c_date = format.parse(request.getParameter("create_date"));
                d = format.parse(request.getParameter("refund_time"));//退货时间
            } catch (ParseException e) {
                e.printStackTrace();
            }

            java.sql.Date finish_date = new java.sql.Date(f_date.getTime());
            java.sql.Date create_date = new java.sql.Date(c_date.getTime());
            java.sql.Date refund_time = new java.sql.Date(d.getTime());

            String reason = request.getParameter("reason");//退货原因
            int operator = (int) request.getSession().getAttribute("manager_id");//操作员id
            System.out.println("opratorid:"+operator);
            java.sql.Date operatorDate = new java.sql.Date(new Date().getTime());


            //数据封装
            Unsubscribe us = new Unsubscribe();
            us.setOrder_id(order_id);
            us.setProd_id(prod_id);
            us.setAmount(amount);
            us.setCreate_date(create_date);
            us.setFinish_date(finish_date);
            us.setRefund_time(refund_time);
            us.setReason(reason);
            us.setOperator(operator);
            us.setOperatorDate(operatorDate);

            OrderService.getInstance().CreateUnsubscribe(us);

            //返回界面
            int pageNum = (Integer) request.getSession().getAttribute("pageNum");
            response.sendRedirect(request.getContextPath()+"/UnsubscribServlet?action=search&pageNum="+pageNum);










        }
    }
}
