package com.neuedu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.lang.Integer;

import com.neuedu.model.service.OrderService;
import com.neuedu.utils.*;
import com.neuedu.model.po.*;

import java.sql.Date;



public class searchOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchOrderServlet() {
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
		System.out.println("123");
		String pagenum = request.getParameter("pageNum");
		String orderTypeStr="";
		String orderStatementStr="";
		String finishDateStr ="";
		int orderType = 0;
		int orderStatement = 0;
		Date finishDate = null;
		int pageNum = 1;
		
		if(pagenum != null && !"".equals(pagenum)){
			pageNum = Integer.parseInt(pagenum);
			orderTypeStr = (String) request.getSession().getAttribute("orderType");
			orderStatementStr = (String) request.getSession().getAttribute("orderStatement");
			finishDateStr = (String) request.getSession().getAttribute("finishDate");
		}else{
			orderTypeStr = request.getParameter("orderType");
			orderStatementStr = request.getParameter("orderStatement");
			finishDateStr = request.getParameter("finishDate");
		}
		
		if(orderTypeStr != null && !"".equals(orderTypeStr)){
			orderType = Integer.parseInt(orderTypeStr);
		}if(orderStatementStr != null && !"".equals(orderStatementStr)){
			orderStatement = Integer.parseInt(orderStatementStr);
		}if(finishDateStr != null && !"".equals(finishDateStr)){
			finishDate = DateUtil.strToDate(finishDateStr);
		}
		
		List<Order> list = OrderService.getInstance().selectOrder(orderType, orderStatement, finishDate, pageNum);
		int countPage = OrderService.getInstance().countPage(orderType, orderStatement, finishDate);
		
		request.setAttribute("orderResultList", list);
		request.setAttribute("countPage", countPage);
		request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("orderTypeStr", orderTypeStr);
		request.getSession().setAttribute("orderStatementStr", orderStatementStr);
		request.getSession().setAttribute("finishDateStr", finishDateStr);

		try {
			request.getRequestDispatcher("controlCenter.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


}