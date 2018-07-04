package com.neuedu.controller;

import com.google.gson.Gson;
import com.neuedu.model.po.User;
import com.neuedu.model.service.UserService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class UserManageServlet extends javax.servlet.http.HttpServlet {


    protected void doGet (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        doPost(request,response);
    }


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        //设置编码方式
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");//获取标识

        //标识为注册
        if("register".equals(action)){
            System.out.println("进入注册");
            //获取数据
            String name = request.getParameter("name");
            String id_card = request.getParameter("id_card");
            String organization = request.getParameter("organization");
            String landline_tel = request.getParameter("landline_tel");
            String tel = request.getParameter("tel");
            String address = request.getParameter("address");
            String zip_code = request.getParameter("zip_code");
            String email = request.getParameter("email");

            //数据封装
            User user = new User();
            user.setName(name);
            user.setId_card_num(id_card);
            user.setOrganization(organization);
            user.setLandline_tel(landline_tel);
            user.setTel(tel);
            user.setAddress(address);
            user.setZip_code(zip_code);
            user.setEmail(email);

            try {
                UserService.getInstance().register(user);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect(request.getContextPath()+"/register.jsp");

        }
        //删除用户
        else if("delete".equals(action)){

            System.out.println("进入删除");
            //获取要删除的用户id
            String user_id = request.getParameter("userId");
            if(UserService.getInstance().hasOrder(Integer.parseInt(user_id)) == false){
                UserService.getInstance().deleteUser(Integer.parseInt(user_id));
            }




            //返回删除操作前的页面
            int pageNum = (Integer) request.getSession().getAttribute("pageNum");
            response.sendRedirect(request.getContextPath()+"/UserManageServlet?action=search&pageNum="+pageNum);


        }
        //更新用户数据
        else if("edit".equals(action)){

            System.out.println("进入修改数据");
            String flag = request.getParameter("flag");

            //修改用户信息
            if("change".equals(flag)){
                System.out.println("修改用户信息");
                String user_id = request.getParameter("user_id");
                String name = request.getParameter("name");
                String id_card = request.getParameter("id_card");
                String organization = request.getParameter("organization");
                String landline_tel = request.getParameter("landline_tel");
                String tel = request.getParameter("tel");
                String address = request.getParameter("address");
                String zip_code = request.getParameter("zip_code");
                String email = request.getParameter("email");
                String status = request.getParameter("status");

                //数据封装
                User user = new User();
                user.setUser_id(Integer.parseInt(user_id));
                user.setName(name);
                user.setId_card_num(id_card);
                user.setOrganization(organization);
                user.setLandline_tel(landline_tel);
                user.setTel(tel);
                user.setAddress(address);
                user.setZip_code(zip_code);
                user.setEmail(email);
                user.setStatus(Integer.parseInt(status));
                UserService.getInstance().updateUser(user);

                Gson gson = new Gson();
                String u = gson.toJson(user);
                System.out.println(u);
                int pageNum = (Integer) request.getSession().getAttribute("pageNum");
                response.sendRedirect(request.getContextPath()+"/UserManageServlet?action=search&pageNum="+pageNum);

            }
            //返回用户信息
            else {
                String user_id = request.getParameter("userId");
                System.out.println("用户id为："+user_id);
                User u = UserService.getInstance().getUserById(Integer.parseInt(user_id));

                //把用户信息传回去
                request.setAttribute("user",u);

                request.getRequestDispatcher("editUser.jsp").forward(request,response);
            }



            //返回修改操作前的页面
        }

        //查询
        else if("search".equals(action)) {
            //采用Session来保存查询条件

            System.out.println("进入查询");
            String from = request.getParameter("from");
            String pagenum = request.getParameter("pageNum");
            int pageNum = 1;
            String name ="";
            String tel = "";
            String id_card= "";
            String pageSize  = "";
            if(pagenum != null && !"".equals(pagenum)){
                //点击页码查询
                pageNum = Integer.parseInt(pagenum);
                name = (String) request.getSession().getAttribute("name");
                tel = (String) request.getSession().getAttribute("tel");
                id_card = (String) request.getSession().getAttribute("id_card");
                pageSize = (String) request.getSession().getAttribute("pageSize");
            }else{
                //点击查询按钮进行查询
                name = (String) request.getParameter("name");
                tel = (String) request.getParameter("tel");
                id_card = (String) request.getParameter("id_card");
                pageSize = (String) request.getParameter("pageSize");
            }
        // 分页查询
           List<User> list = UserService.getInstance().selectUserByPage(name, tel,id_card,Integer.parseInt(pageSize),pageNum);

        //页数查询
            int pagecount = UserService.getInstance().selectPageCount(name, tel,id_card,Integer.parseInt(pageSize));
            System.out.println("页数为"+pagecount);

            request.setAttribute("resultList", list);
            request.setAttribute("pagecount", pagecount);
            request.getSession().setAttribute("pageNum", pageNum);
            request.getSession().setAttribute("name", name);
            request.getSession().setAttribute("tel", tel);
            request.getSession().setAttribute("id_card", id_card);
            request.getSession().setAttribute("pageSize", pageSize);
            //跳转

            if("order".equals(from)){
                request.getRequestDispatcher("Order.jsp").forward(request, response);
            }
            else {
                request.getRequestDispatcher("selectUser.jsp").forward(request, response);
            }



        }

        //验证
       if("Validate".equals(action)){

            /*  flag意义：
             *  1-电话号码验证
             *  2-身份证验证
             */
           System.out.println("进入信息验证");
           int flag = Integer.parseInt(request.getParameter("flag"));
           String tel = request.getParameter("tel");
           String id_card_num = request.getParameter("id_card");

           System.out.println("当前flag为："+flag);
           //
           if(flag == 1){
               System.out.println("电话号码验证");
               boolean validate = UserService.getInstance().IsValidate(tel,id_card_num,1);
               System.out.println("validate:"+validate);
               //把消息发送到前端
               response.setCharacterEncoding("UTF-8");
               response.setContentType("text;charset=UTF-8");
               PrintWriter out=response.getWriter();
               out.print(validate);
               out.close();
           }
           if(flag == 2){
               System.out.println("身份证验证");
               boolean validate =  UserService.getInstance().IsValidate(tel,id_card_num,2);
               System.out.println("validate:"+validate);
               //把消息发送到前端
               response.setCharacterEncoding("UTF-8");
               response.setContentType("text;charset=UTF-8");
               PrintWriter out=response.getWriter();
               out.print(validate);
               out.close();

           }


        }

    }
}
