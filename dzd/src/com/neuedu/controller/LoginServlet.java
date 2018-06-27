package com.neuedu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Manager;
import com.neuedu.model.service.LoginService;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		int flag=0;
		int manager_type=0;
		String admin=request.getParameter("admin");
		String password=request.getParameter("password");
		if("".equals(admin)||"".equals(password)){
			request.getSession().setAttribute("loginmsg", "请全部填写！");
	
		}else{
			Manager m=LoginService.getInstance().validateLogin(admin, password);
			if(m==null){
				request.getSession().setAttribute("loginmsg", "账号密码错误！");
				
			}else{
				flag=1;
				request.getSession().setAttribute("admin", m.getAdmin());
				request.getSession().setAttribute("manager_id", m.getManager_id());
				request.getSession().setAttribute("manager_name", m.getName());
				request.getSession().setAttribute("telephone", m.getTelephone());
				request.getSession().setAttribute("type", m.getType());
				manager_type=m.getType();
				if(manager_type==4||manager_type==5){
					request.getSession().setAttribute("substation_id", m.getSub_id());
				}		
				
			}
			
		}
		String goal=null;
		if(flag==0){
			goal="Login.jsp";
		}else{
			switch(manager_type){
			case 1:
				goal="manageindex.jsp";
				break;
			case 2:
				goal="controlcenter.jsp";
				break;
			case 3:
				goal="addonetitle.jsp";
				break;
			case 4:
				goal="Central warehouse purchases.jsp";
				break;
				
			case 5:
				goal="Substation warehouse transfer  in.jsp";
				break;
			case 6:
				goal="finance-index.jsp";
				break;		
				
			}
			
		}
		
		response.sendRedirect(goal); 
		

		
		
	}

}
