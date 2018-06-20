package com.neuedu.controller;

import com.neuedu.model.po.PurchaseSupplier;
import com.neuedu.model.service.CenWarehouseService;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CenWarehouseServlet
 */
public class CenWarehouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CenWarehouseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����CenWarehouseServlet");
		//�趨�����ʽ
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//���ҹ�����
		if("searchPs".equals(action)){
            
            doGetPurchaseInfo(request,response);
        }
		//�ύ������ⵥ
		else if("submitPs".equals(action)) {
	
			request.getRequestDispatcher("Central warehouse purchases.jsp").forward(request, response);
		}
	}

	private void doGetPurchaseInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String psid = request.getParameter("psid");
		JSONObject ps = CenWarehouseService.getInstance().getPurchaseInfo(Integer.parseInt(psid));
        response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();	
		pw.print(ps);
		pw.close();
    }

}
