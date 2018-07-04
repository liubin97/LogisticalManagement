package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Warehouse;
import com.neuedu.model.service.WhService;

/**
 * Servlet implementation class ManageWhServlet
 */
public class ManageWhServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageWhServlet() {
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
		if("addwh".equals(action)){
			doAddWh(request, response);
		}else if("deletewh".equals(action)){
			doDeleteWh(request,response);
		}else if("modifywh".equals(action)){
			doModifyWh(request,response);
		}else if("searchwh".equals(action)){
			doSearchWh(request,response);		
		}
	
	}
	
	private void doSearchWh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		
		System.out.println("sousuo");
		
		String pagenum = request.getParameter("pageno");
		int pageNum = 1;
		String whname ="";
		if(pagenum != null && !"".equals(pagenum)){
		
			pageNum = Integer.parseInt(pagenum);
			whname = (String) request.getSession().getAttribute("searchwhmsg");
			System.out.println(whname);
		}else{
			whname=(String) request.getParameter("searchwhmsg");
			System.out.println(whname);
			
		}
	
		List<Warehouse> list =  WhService.getInstance().searchWarehouse(whname,pageNum);
	
		int pagecount =  WhService.getInstance().calPagecount(whname);

		request.setAttribute("resultList", list);
		request.setAttribute("pagecount", pagecount);
		request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("searchwhmsg", whname);
		request.getRequestDispatcher("/manageWarehouse.jsp").forward(request, response);
		
		
		
	}

	private void doModifyWh(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO 自动生成的方法存根
		
		int flag=0;
		String wh_id=request.getParameter("whid");
		String wh_name=request.getParameter("whname");
		String wh_addr=request.getParameter("whaddr");
		String wh_admin=request.getParameter("whadmin");
		String wh_level=(String)request.getParameter("selectlevel");
		System.out.println(wh_level);
		String operator=(String) request.getSession().getAttribute("manager_name");
		if("".equals(wh_name)||"".equals(wh_addr)||"".equals(wh_admin)){
			request.getSession().setAttribute("modifywhmsg", "请全部填写！");
		}else{
			System.out.println(WhService.getInstance().haveCenterWarehouse());
			if("0".equals(wh_level)&&WhService.getInstance().haveCenterWarehouse()==true){
				request.getSession().setAttribute("modifywhmsg", "已经有中心库房了");
			}else{
				
				flag=1;
				Warehouse wh=new Warehouse();
				wh.setWh_id(Integer.parseInt(wh_id));
				wh.setWh_name(wh_name);
				wh.setWh_addr(wh_addr);
				wh.setWh_admin(wh_admin);
				wh.setWh_level(Integer.parseInt(wh_level));
				wh.setStatus(0);
				wh.setOperator(operator);
				Date   now   =   new   Date();    
				SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy-MM-dd ");//可以方便地修改日期格式    
				String   operator_date   =   dateFormat.format(   now   );    
				wh.setOperator_date(operator_date);
				WhService.getInstance().modifyWarehouse(wh);
				request.getSession().setAttribute("modifywhmsg", "修改成功!");
			}
			
			
		}
		response.sendRedirect("editWarehouse.jsp?wh_id="+wh_id);
		
		
	}

	private void doDeleteWh(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO 自动生成的方法存根
		
		int whid=Integer.parseInt(request.getParameter("wh_id"));
		boolean flag=false;
		String operator=(String) request.getSession().getAttribute("manager_name");
		PrintWriter pw=response.getWriter();
		if(WhService.getInstance().haveProductIn(whid)==false){
			WhService.getInstance().deleteWarehouse(whid,operator);
			response.setContentType("text/html");
			flag=true;
			System.out.println("删除成功");
			pw.print(flag);
			pw.close();
		}else{
			System.out.println("有商品");
			pw.print(flag);
			pw.close();
		}
		
		
		
	}

	private void doAddWh(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int flag=0;
		String wh_name=request.getParameter("whname");
		String wh_addr=request.getParameter("whaddr");
		String wh_admin=request.getParameter("whadmin");
		String wh_level=request.getParameter("selectlevel");
		String operator=(String) request.getSession().getAttribute("manager_name");
		System.out.println();
		if("".equals(wh_name)||"".equals(wh_addr)||"".equals(wh_admin)){
			request.getSession().setAttribute("addwhmsg", "请全部填写！");
		}else{
			flag=1;
			if("0".equals(wh_level)&&WhService.getInstance().haveCenterWarehouse()==true){
				request.getSession().setAttribute("addwhmsg", "已经有中心库房了");
			}else{
				
				
				Warehouse wh=new Warehouse();
				wh.setWh_name(wh_name);
				wh.setWh_addr(wh_addr);
				wh.setWh_admin(wh_admin);
				wh.setWh_level(Integer.parseInt(wh_level));
				wh.setStatus(0);
				wh.setOperator(operator);
				Date   now   =   new   Date();    
				SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy-MM-dd ");//可以方便地修改日期格式    
				String   operator_date   =   dateFormat.format(   now   );    
				wh.setOperator_date(operator_date);
				WhService.getInstance().addWarehouse(wh);
				request.getSession().setAttribute("addwhmsg", "新建成功!");
			}
			
		}
		
		response.sendRedirect("addWarehouse.jsp");
	}
	
	

}
