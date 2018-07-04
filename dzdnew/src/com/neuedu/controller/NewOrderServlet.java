package com.neuedu.controller;

import com.neuedu.model.po.user.Order;
import com.neuedu.model.po.user.Product;
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

public class NewOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码方式
        request.setCharacterEncoding("utf-8");
        System.out.println("成功进入NewOrderServlet");
        String action = request.getParameter("action");//获取标识
        String flag = request.getParameter("flag");//获取标识
        String title_id = request.getParameter("title");//获取标识;

        //获取产品目录
        if("order".equals(action)){
            System.out.println("成功进入order");
            String pro_title = "";
            //一级目录
            if("one".equals(flag)){
                pro_title = OrderService.getInstance().getOnetitle();
                System.out.println(pro_title);
            }
            //二级目录
            if("two".equals(flag)){
                pro_title = OrderService.getInstance().getTwotitle(Integer.parseInt(title_id));
                System.out.println(pro_title);

            }

            if("product".equals(flag)){
                pro_title = OrderService.getInstance().getProductList(Integer.parseInt(title_id));
                System.out.println(pro_title);

            }

            //把消息发送到前端
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.print(pro_title);
            out.close();

        }

        //获取订单信息
       if("orderInfo".equals(action)) {

            String user_id = request.getParameter("userid");//获取用户id
            String prod_id = request.getParameter("prodid");//获取产品id
            String amount = request.getParameter("amount");//获取订购数量


           //获取用户信息
           User u = UserService.getInstance().getUserById(Integer.parseInt(user_id));
           System.out.println(u.toString());
           //把用户信息传回去

           request.setAttribute("u",u);


           //获取产品信息
           Product p = OrderService.getInstance().getProduct(Integer.parseInt(prod_id));
           System.out.println(p.toString());
           //传回去值
           request.setAttribute("prodlist",p);
           request.setAttribute("amount",amount);

           //跳转页面
           request.getRequestDispatcher("addOrder.jsp").forward(request,response);


       }

       //下订单
        if("creatorder".equals(action)){

            System.out.println("进入创建订单");
            //获取数据
            int client_id = Integer.parseInt(request.getParameter("user_id"));
            int prod_id =  Integer.parseInt(request.getParameter("prodct_id"));
            int amount =  Integer.parseInt(request.getParameter("pro_amount"));
            String unit = request.getParameter("pro_unit");
            double price = Double.parseDouble(request.getParameter("price"));
            double discount = Double.parseDouble(request.getParameter("discount"));
            double sum_money = (double)amount * price * discount;

            //有可能时间会出异常
            Date f_date = null;
            Date c_date = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                f_date = format.parse(request.getParameter("finish_date"));
                c_date = format.parse(request.getParameter("create_date"));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            java.sql.Date finish_date = new java.sql.Date(f_date.getTime());
            java.sql.Date create_date = new java.sql.Date(c_date.getTime());

            int type =  Integer.parseInt(request.getParameter("type"));
            String deliver_addr = request.getParameter("address");
            String consignee = request.getParameter("consignee");
            String consignee_tel = request.getParameter("consignee_tel");
            String consignee_zip_cod = request.getParameter("zip_code");
            int invoice = Integer.parseInt(request.getParameter("invoice"));

            int operator = (int) request.getSession().getAttribute("manager_id");//Integer.parseInt((String) request.getSession().getAttribute("manager_id"));
            System.out.println("opratorid:"+operator);
            java.sql.Date operatorDate = new java.sql.Date(new Date().getTime());
            int status;  //订单状态
            if(OrderService.getInstance().isLack(prod_id,amount)){
                status = 2;//可分配
            }
            else {
                status = 1;  //缺货

            }


            //数据封装
            Order order = new Order();

            order.setClient_id(client_id);
            order.setStatus(status);
            order.setProd_id(prod_id);
            order.setAmount(amount);
            order.setUnit(unit);
            order.setPrice(price);
            order.setDiscount(discount);
            order.setSum_monney(sum_money);
            order.setFinish_date(finish_date);
            order.setCreate_date(create_date);
            order.setType(type);
            order.setDeliver_addr(deliver_addr);
            order.setConsignee(consignee);
            order.setConsignee_tel(consignee_tel);
            order.setConsignee_zip_cod(consignee_zip_cod);
            order.setInvoice(invoice);
            order.setOperator(operator);
            order.setOperatorDate(operatorDate);

            System.out.println(order.toString());
            //把数据给后台
            int keyid = OrderService.getInstance().CreateOrder(order);
            // System.out.println("keyid:"+keyid);
            //修改库存
            if(keyid>=0 &&status == 2){

                OrderService.getInstance().ChangeStock(prod_id,amount);
                System.out.println("已经改变库存");
            }
            if(keyid>=0 && status == 1 ){

                OrderService.getInstance().createOutOfStock(prod_id,amount,keyid);
                System.out.println("缺货表创建成功");

            }

            //跳转页面
            request.getRequestDispatcher("Order.jsp").forward(request,response);


        }


    }



}
