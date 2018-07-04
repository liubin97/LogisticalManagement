package com.neuedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.PaymentResult;
import com.neuedu.model.service.PaymentService;

/**
 * Servlet implementation class PaymentQueryServlet
 */
public class PaymentQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentQueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String pagenum = request.getParameter("pageNum");
		int pageNum = 1;
		int substation_id;
		String begintime="";
		String endtime="";
	
		substation_id = (int) request.getSession().getAttribute("substation_id");
		System.out.println(substation_id);
		//根据pageNum的值是否为空判断是通过按钮查询还是页码查询
		if(pagenum != null && !"".equals(pagenum)){
			pageNum = Integer.parseInt(pagenum);
			begintime=(String)request.getParameter("begintime");
			endtime=(String)request.getParameter("endtime");
		}else {
			begintime=(String)request.getParameter("begintime");
			endtime=(String)request.getParameter("endtime");
		}
		
		//查询结果
		List<PaymentResult> list = PaymentService.getInstance().getPayment(begintime, endtime, substation_id,pageNum);
		int pagecount = PaymentService.getInstance().selectPageCount(begintime, endtime, substation_id);
		
		request.setAttribute("paylist", list);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("substation_id", substation_id);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("begintime", begintime);
		request.setAttribute("endtime", endtime);
		
		
		//请求转发，同时带着查询条件
		request.getRequestDispatcher("PaymentInquery.jsp").forward(request, response);
	}

}
