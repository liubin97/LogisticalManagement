package com.neuedu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Invoice_sub;
import com.neuedu.model.service.InvoiceManageService;

/**
 * Servlet implementation class InvoiceManagementServlet
 */
public class InvoiceManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceManagementServlet() {
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
		
		if(request.getParameter("action")==null||"".equals((String)request.getParameter("action"))) {
			int tasklist_id = Integer.parseInt((String)request.getParameter("querytasklist_id"));
			Invoice_sub invoice = InvoiceManageService.getInstance().getInvoice(tasklist_id);
			List<Invoice_sub> list = new ArrayList<Invoice_sub>();
			
			if(invoice!=null) {
				list.add(invoice);
			}
			request.setAttribute("list", list);
			request.setAttribute("tasklist_id", tasklist_id);
			
			request.getRequestDispatcher("InvoiceManagement.jsp").forward(request, response);
		}else {
			
		}
		
	}

}
