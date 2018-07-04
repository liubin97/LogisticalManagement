package com.neuedu.controller;

import com.google.gson.Gson;
import com.neuedu.model.po.OperatorInfo;
import com.neuedu.model.service.OperatorService;

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

public class SelectOperatorServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码方式
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");//获取标识

        //查询工作量
        if("operator".equals(action)){

            String begin = request.getParameter("begin");
            String end = request.getParameter("end");
            int type =  Integer.parseInt(request.getParameter("type"));//默认全部
            int operator_id =  Integer.parseInt(request.getParameter("operator"));//必填选项
            String prod_name = request.getParameter("prod_name");
            String operator_name =  request.getParameter("operator_name");

            //有可能时间会出异常
            Date begin_date = null;//保存时间临时变量
            Date end_date = null;//保存时间临时变量
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            //打印条件便于查看
            System.out.println("------------操作员工作量查询条件-----------------");
            System.out.println("begin:"+begin);
            System.out.println("end:"+end);
            System.out.println("type:"+type);
            System.out.println("operator_id:"+operator_id);
            System.out.println("prod_name:"+prod_name);
            System.out.println("operator_name:"+operator_name);
            System.out.println("--------------------------------------------");

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
            Gson gson = new Gson();
            //只有新订
            if(type == 1){
             List<OperatorInfo> newInfo = OperatorService.getInstance().getNewInfo(Begin,End,operator_id,prod_name);
                System.out.println("---------------------新订---------------------");
                System.out.println("newInfo:"+gson.toJson(newInfo));
                System.out.println("------------------------------------------------");

                //返回数据
                request.setAttribute("newInfo",newInfo);
                request.setAttribute("operator_name",operator_name);
                request.setAttribute("operator_id",operator_id);
                request.setAttribute("flag",5);

                //请求转发
                request.getRequestDispatcher("Operator_Time_Search.jsp").forward(request, response);

            }
            //只有退订
            if(type == 2){

                List<OperatorInfo> unsubInfo = OperatorService.getInstance().getUnsubscribeInfo(Begin,End,operator_id,prod_name);
                System.out.println("---------------------退订---------------------");
                System.out.println("unsubInfo:"+gson.toJson(unsubInfo));
                System.out.println("------------------------------------------------");

                //返回数据
                request.setAttribute("unsubInfo",unsubInfo);
                request.setAttribute("operator_name",operator_name);
                request.setAttribute("operator_id",operator_id);
                request.setAttribute("flag",6);

                //请求转发
                request.getRequestDispatcher("Operator_Time_Search.jsp").forward(request, response);
            }
            //只有退货
            if(type == 3){
                List<OperatorInfo> reInfo = OperatorService.getInstance().getReturnInfo(Begin,End,operator_id,prod_name);
                System.out.println("---------------------退货---------------------");
                System.out.println("reInfo:"+gson.toJson(reInfo));
                System.out.println("------------------------------------------------");

                //返回数据
                request.setAttribute("reInfo",reInfo);
                request.setAttribute("operator_name",operator_name);
                request.setAttribute("operator_id",operator_id);
                request.setAttribute("flag",7);

                //请求转发
                request.getRequestDispatcher("Operator_Time_Search.jsp").forward(request, response);
            }
            //全部
            if(type == 0) {
                List<OperatorInfo> newInfo = OperatorService.getInstance().getNewInfo(Begin,End,operator_id,prod_name);
                List<OperatorInfo> unsubInfo = OperatorService.getInstance().getUnsubscribeInfo(Begin,End,operator_id,prod_name);
                List<OperatorInfo> reInfo = OperatorService.getInstance().getReturnInfo(Begin,End,operator_id,prod_name);
                System.out.println("---------------------全部---------------------");
                System.out.println("newInfo:"+gson.toJson(newInfo));
                System.out.println("unsubInfo:"+gson.toJson(unsubInfo));
                System.out.println("reInfo:"+gson.toJson(reInfo));
                System.out.println("------------------------------------------------");

                //返回数据
                request.setAttribute("newInfo",newInfo);
                request.setAttribute("unsubInfo",unsubInfo);
                request.setAttribute("reInfo",reInfo);
                request.setAttribute("operator_name",operator_name);
                request.setAttribute("operator_id",operator_id);
                request.setAttribute("flag",8);

                //请求转发
                request.getRequestDispatcher("Operator_Time_Search.jsp").forward(request, response);



            }


        }

        //查询操作员信息以json返回
        if("getOperator".equals(action)){

            String operator = OperatorService.getInstance().getOperator();
            System.out.println("-----------operator数据-----------");
            System.out.println(operator);
            System.out.println("----------------------------------");
            //把消息发送到前端
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.print(operator);
            out.close();

        }

}
}
