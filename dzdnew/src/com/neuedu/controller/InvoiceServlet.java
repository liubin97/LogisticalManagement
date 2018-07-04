package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.neuedu.model.po.Invoice;
import com.neuedu.model.po.OrderInfor;
import com.neuedu.model.po.SettleSubstationInfor;
import com.neuedu.model.po.Settlement;
import com.neuedu.model.service.FinanceService;
import com.neuedu.utils.DBUtil;

/**
 * Servlet implementation class InvoiceServlet
 */
public class InvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action.equals("register")){
			String invoiceNumber = request.getParameter("invoiceNumber");
			String amount = request.getParameter("amount");
			String orderNumber = request.getParameter("orderNumber");
			Invoice invoice = new Invoice(Integer.parseInt(invoiceNumber),Double.parseDouble(amount),
					"无","无","无","无","正常",Integer.parseInt(orderNumber));
			FinanceService.getInstance().invoiceRegiser(invoice);
			String url = "invoice-register.jsp";
			request.setAttribute("url", url);
			request.setAttribute("invoiceInfor", invoice);
			request.getRequestDispatcher("invoice-infor.jsp").forward(request, response);
		}
		else if(action.equals("use")){
			String invoiceNumber = request.getParameter("invoiceNumber");
			String subName = request.getParameter("subName");
			Invoice invoice = FinanceService.getInstance().invoiceUse(Integer.parseInt(invoiceNumber),subName);
			String url = "invoicing.jsp";
			request.setAttribute("url",url);
			request.setAttribute("invoiceInfor", invoice);
			request.getRequestDispatcher("invoice-infor.jsp").forward(request, response);
		}
		else if(action.equals("invalid")){
			String invoiceNumber = request.getParameter("invoiceNumber");

			Invoice invoice = FinanceService.getInstance().invoiceInvalid(Integer.parseInt(invoiceNumber));
			String url = "lose.jsp";
			request.setAttribute("url",url);
			request.setAttribute("invoiceInfor", invoice);
			request.getRequestDispatcher("invoice-infor.jsp").forward(request, response);
		}
		else if(action.equals("inquiry")){
			String pagenum = request.getParameter("pageNum");
			int pageNum = 1;
			String pageSize = "";
			String type = "";
			String number = "";
			String subName = "";
			if(pagenum != null&&!"".equals(pagenum)){
				//点解页码进行查询
				pageNum = Integer.parseInt(pagenum);
				type = (String)request.getSession().getAttribute("type");
				number = (String)request.getSession().getAttribute("number");
				subName = (String)request.getSession().getAttribute("subName");
				pageSize = (String)request.getSession().getAttribute("pageSize");
			}
			else{//点击按钮进行查询
				type = request.getParameter("type");
				number = request.getParameter("number");
				subName = request.getParameter("subName");
				pageSize = request.getParameter("pageSize");
			}


			int num = 0;
			if(number!=null&&!"".equals(number)){	
				if(DBUtil.testNumber(number)){
					num = Integer.parseInt(number);
				}
				else{
					num = -1;
				}
				
			}

			List<Invoice> invoiceList = null;
			int pagecount = 1;
			if(type.equals("发票号码")){
				invoiceList = FinanceService.getInstance().invoiceInqByInvNum(num,subName,Integer.parseInt(pageSize),pageNum);
				pagecount = FinanceService.getInstance().selectPageCountByInvNum(num,subName,Integer.parseInt(pageSize));
			}
			else if(type.equals("订单号")){
				invoiceList = FinanceService.getInstance().invoiceInqByOrderNum(num,subName,Integer.parseInt(pageSize),pageNum);
				pagecount = FinanceService.getInstance().selectPageCountByOrderNum(num,subName,Integer.parseInt(pageSize));
			}

			request.setAttribute("pagecount", pagecount);
			request.setAttribute("invoiceList", invoiceList);
			
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("type",type);
			request.getSession().setAttribute("number",number);
			request.getSession().setAttribute("subName",subName);
			request.getSession().setAttribute("pageSize",pageSize);

			request.getRequestDispatcher("invoice-inquiry.jsp").forward(request, response);
		}
		else if("moreInfor".equals(action)){
			int pageNum = 1;
			String pageSize = request.getParameter("pageSize");
			String type = "";
			if(request.getSession().getAttribute("type")!=null){
				type = (String)request.getSession().getAttribute("type");
			}

			String number = (String)request.getSession().getAttribute("number");
			String subName = (String)request.getSession().getAttribute("subName");
			
			

			int num = 0;
			if(number!=null&&!"".equals(number)){
				if(DBUtil.testNumber(number)){
					num = Integer.parseInt(number);
				}
				else{
					num = -1;
				}
			}
			List<Invoice> invoiceList = null;
			int pagecount = 1;
			
			if(type.equals("发票号码")){
				invoiceList = FinanceService.getInstance().invoiceInqByInvNum(num,subName,Integer.parseInt(pageSize),pageNum);
				pagecount = FinanceService.getInstance().selectPageCountByInvNum(num,subName,Integer.parseInt(pageSize));
			}
			else if(type.equals("订单号")){
				invoiceList = FinanceService.getInstance().invoiceInqByOrderNum(num,subName,Integer.parseInt(pageSize),pageNum);
				pagecount = FinanceService.getInstance().selectPageCountByOrderNum(num,subName,Integer.parseInt(pageSize));
			}

			
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("invoiceList", invoiceList);
			
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("type",type);
			request.getSession().setAttribute("number",number);
			request.getSession().setAttribute("subName",subName);
			request.getSession().setAttribute("pageSize",pageSize);

			request.getRequestDispatcher("invoice-inquiry.jsp").forward(request, response);
		}
		else if(action.equals("initialize")){
			
			List<String> substationList = FinanceService.getInstance().getSubstation(); 
			JsonArray jsonArray = new JsonArray( );
			for(int i = 0;i<substationList.size();i++){
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("substation", substationList.get(i));
				jsonArray.add(jsonObject);
			}
			PrintWriter pw = response.getWriter();
			pw.print(jsonArray);
			pw.flush();
			pw.close();
		}
		else if(action.equals("validateRegister")){
			String invoiceNumber = request.getParameter("invoiceNumber");
			String amount = request.getParameter("amount");
			String orderNumber = request.getParameter("orderNumber");
			boolean flag = false;
			if(invoiceNumber!=null){
				flag = FinanceService.getInstance().registerTestInvoice(invoiceNumber);
			}
			if(amount!=null){
				flag = FinanceService.getInstance().registerTestAmount(amount);
			}
			if(orderNumber!=null){
				flag = FinanceService.getInstance().registerTestOrder(orderNumber);
			}
			PrintWriter pw = response.getWriter();
			pw.print(flag);
			pw.flush();
			pw.close();
		}
		else if(action.equals("validateUse")){
			String invoiceNumber = request.getParameter("invoiceNumber");
			boolean flag = false;
			if(invoiceNumber!=null){
				flag = FinanceService.getInstance().useTestInvoice(invoiceNumber);
			}
			PrintWriter pw = response.getWriter();
			pw.print(flag);
			pw.flush();
			pw.close();
		}else if(action.equals("validateInvalid")){
			String invoiceNumber = request.getParameter("invoiceNumber");
			boolean flag = false;
			if(invoiceNumber!=null){
				flag = FinanceService.getInstance().invalidTestInvoice(invoiceNumber);
			}
			PrintWriter pw = response.getWriter();
			pw.print(flag);
			pw.flush();
			pw.close();
		}
	}

}
