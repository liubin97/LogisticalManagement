package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.ReserveInfo;
import com.neuedu.model.po.Warehouse;
import com.neuedu.model.service.ResService;
import com.neuedu.model.service.WhService;

/**
 * Servlet implementation class ManageResServlet
 */
public class ManageResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageResServlet() {
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
		String action=request.getParameter("action");
		System.out.println(action);
		if("manageres".equals(action)){
			doManageRes(request, response);
		}else if("acWhname".equals(action)){
			doAcWhname(request,response);
		}else if("acProname".equals(action)){
			doAcProname(request,response);
		}
	}

	private void doAcProname(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO 自动生成的方法存根
		
		String pro_id=request.getParameter("pro_id");
		if("".equals(pro_id)||pro_id==null){
			
		}else{
			response.setCharacterEncoding("utf-8");
			PrintWriter pw=response.getWriter();
				String name=ResService.getInstance().acProname(Integer.parseInt(pro_id));
				response.setContentType("text/html");
				pw.print(name);
				pw.close();
		}
		
	
			
	}

	private void doAcWhname(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO 自动生成的方法存根
		String wh_id=request.getParameter("wh_id");
		if("".equals(wh_id)||wh_id==null){
			
		}else{
			response.setCharacterEncoding("utf-8");
			PrintWriter pw=response.getWriter();
				String name=ResService.getInstance().acWhname(Integer.parseInt(wh_id));
				response.setContentType("text/html");
				pw.print(name);
				pw.close();
		}
		
	}

	private void doManageRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO 自动生成的方法存根
		int flag=0;
		String pro_id=request.getParameter("proid");
		String wh_id=request.getParameter("whid");
		String warnnum=request.getParameter("warnnum");
		String maxnum=request.getParameter("maxnum");
		String operator=(String) request.getSession().getAttribute("manager_name");
		
		System.out.println();
		if("".equals(pro_id)||"".equals(wh_id)||"".equals(warnnum)||"".equals(maxnum)){
			request.getSession().setAttribute("manageresmsg", "请全部填写！");
		}else{
			
			if(isInteger(warnnum)&&isInteger(maxnum)){
				if(Integer.parseInt(warnnum)<Integer.parseInt(maxnum)){
					ReserveInfo res=new ReserveInfo();
					Date   now   =   new   Date();    
					SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy-MM-dd ");//可以方便地修改日期格式    
					String   operator_date   =   dateFormat.format(   now   ); 
					res.setWh_id(Integer.parseInt(wh_id));
					res.setPro_id(Integer.parseInt(pro_id));
					res.setWarn_num(Integer.parseInt(warnnum));
					res.setMax_num(Integer.parseInt(maxnum));
					res.setOperator(operator);
					res.setOperatordate(operator_date);
					res.setStatus(0);
					res.setRes_num(0);
					if(ResService.getInstance().haveRes(res)){
						ResService.getInstance().updateRes(res);
						request.getSession().setAttribute("manageresmsg", "修改成功！");
					}else{
						ResService.getInstance().addRes(res);
						request.getSession().setAttribute("manageresmsg", "新建成功！");
					}
					
					
				}else{
					request.getSession().setAttribute("manageresmsg", "预警要小于最大！");
				}
			}else{
				request.getSession().setAttribute("manageresmsg", "预警最大值请填写整数！");
			}
				/*
				wh.setOperator(operator);
				Date   now   =   new   Date();    
				SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy-MM-dd ");//可以方便地修改日期格式    
				String   operator_date   =   dateFormat.format(   now   );    
				wh.setOperator_date(operator_date);
				WhService.getInstance().addWarehouse(wh);
				request.getSession().setAttribute("manageresmsg", "修改成功!");*/
			
			
		}
		System.out.println("jaja");
		response.sendRedirect("manageReserve.jsp");
		
	}
	
	public static boolean isInteger(String str) {    
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
	    return pattern.matcher(str).matches();    
	  }  

}
