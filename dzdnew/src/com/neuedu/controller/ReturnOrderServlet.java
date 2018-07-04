package com.neuedu.controller;

import com.neuedu.model.po.user.Order;
import com.neuedu.model.po.Returns;
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

public class ReturnOrderServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //设置编码方式
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");//获取标识

        if("search".equals(action)){

            //采用Session来保存查询条件

            System.out.println("进入退货查询");
            String pagenum = request.getParameter("pageNum");
            int pageNum = 1;
            String name ="";
            String tel = "";
            String idcard= "";
            String pageSize  = "";
            if(pagenum != null && !"".equals(pagenum)){
                //点击页码查询
                pageNum = Integer.parseInt(pagenum);
                name = (String) request.getSession().getAttribute("name");
                tel = (String) request.getSession().getAttribute("tel");
                idcard = (String) request.getSession().getAttribute("id_card");
                pageSize = (String) request.getSession().getAttribute("pageSize");
            }else{
                //点击查询按钮进行查询
                name = (String) request.getParameter("name");
                tel = (String) request.getParameter("tel");
                idcard = (String) request.getParameter("id_card");
                pageSize = (String) request.getParameter("pageSize");
            }


            //获取用户id
            List<Integer> uids = OrderService.getInstance().getUserId(name,tel,idcard);
            System.out.println("uids:"+uids.toString());

            // 分页查询
            List<Order> orderlist = OrderService.getInstance().selectUserOreder(uids,Integer.parseInt(pageSize),pageNum,2);
            System.out.println("orderlist"+orderlist.toString());
            //页数查询
            int pagecount = OrderService.getInstance().selectPageCount(uids,Integer.parseInt(pageSize),pageNum,2);
            System.out.println("页数为"+pagecount);

            //数据回传
            request.setAttribute("orderlist",orderlist);
            request.setAttribute("pagecount",pagecount);
            request.getSession().setAttribute("pageNum", pageNum);
            request.getSession().setAttribute("name", name);
            request.getSession().setAttribute("tel", tel);
            request.getSession().setAttribute("id_card", idcard);
            request.getSession().setAttribute("pageSize", pageSize);

            request.getRequestDispatcher("Order_Back.jsp").forward(request, response);
        }

        //退货
        if("return".equals(action)){

            System.out.println("进入退货");
            String order_id = request.getParameter("orderid");
            System.out.println("orderid:"+order_id);
            //获取用户信息
            Order o = OrderService.getInstance().getOrder(Integer.parseInt(order_id));
            System.out.println(o.toString());
            //把用户信息传回去
            request.setAttribute("o",o);
            //请求转发
            request.getRequestDispatcher("Order_Back2.jsp").forward(request,response);

        }

        //创建退货单
        if("createRuturn".equals(action)){

            System.out.println("创建退货单");
            System.out.println("进入生成退订订单");
            //获取数据
            int order_id = Integer.parseInt(request.getParameter("order_id"));
            int amount = Integer.parseInt(request.getParameter("amount"));
            int prod_id = Integer.parseInt(request.getParameter("prod_id"));

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
            java.sql.Date rerutn_time = new java.sql.Date(d.getTime());

            String reason = request.getParameter("reason");//退货原因
            int operator = (int) request.getSession().getAttribute("manager_id");//操作员id
            System.out.println("opratorid:"+operator);
            java.sql.Date operatorDate = new java.sql.Date(new Date().getTime());

            //数据封装
            Returns r = new Returns();
            r.setOrder_id(order_id);
            r.setAmount(amount);
            r.setProd_id(prod_id);
            r.setFinish_date(finish_date);
            r.setCreate_date(create_date);
            r.setReturn_time(rerutn_time);
            r.setReason(reason);
            r.setOperator(operator);
            r.setOperatorDate(operatorDate);

            //插入
            OrderService.getInstance().CreateReturn(r);

            //返回界面
            int pageNum = (Integer) request.getSession().getAttribute("pageNum");
            response.sendRedirect(request.getContextPath()+"/ReturnOrderServlet?action=search&pageNum="+pageNum);




        }

    }
}
