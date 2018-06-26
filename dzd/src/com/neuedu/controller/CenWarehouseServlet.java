package com.neuedu.controller;

import com.neuedu.model.po.CenWarehouseInInfo;
import com.neuedu.model.po.PurchaseSupplier;
import com.neuedu.model.service.CenWarehouseService;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        } else if("submitPs".equals(action)) {//�ύ������ⵥ
			try {
				doPurchaseInInfo(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("searchTaskDate".equals(action)) {//�������ڲ�ѯ����
			try {
				doGetTaskListByDate(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("submitWhIN".equals(action)) {
			try {
				doWarehouseOutInfo(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("searchReturnIn".equals(action)) {
			doGetReturnInInfo(request, response);
		}
	}
	//��ѯ���������Ϣ
	private void doGetPurchaseInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String psid = request.getParameter("psid");
		JSONObject json = null;
		try {
			json = CenWarehouseService.getInstance().getPurchaseInfo(Integer.parseInt(psid));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();	
		pw.print(json);
		pw.close();
    }
	//���빺�������Ϣ
	private void doPurchaseInInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
		String psid = request.getParameter("psid");
		String actualnum = request.getParameter("acnum");
		String indate = request.getParameter("indate");
		String note = request.getParameter("note");
		CenWarehouseInInfo cwininfo = new CenWarehouseInInfo();
		cwininfo.setPs_id(Integer.parseInt(psid));
		cwininfo.setActual_num(Integer.parseInt(actualnum));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cwininfo.setDate(sdf.parse(indate));
		cwininfo.setNote(note);
		cwininfo.setOperate_date(new Date());
		CenWarehouseService.getInstance().insertInWarehouseInfo(cwininfo);
		
		request.getRequestDispatcher("Central warehouse purchases.jsp").forward(request, response);
	}
	//��ѯ��������
	private void doGetTaskListByDate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException, ServletException {

		String date = null;
		//System.out.println(date+"1");
		String pagenum = request.getParameter("pageNum");
		int pageNum = 1;
		if(pagenum!=null && !"".equals(pagenum)){
			//���ҳ���ѯ		
			date = (String) request.getSession().getAttribute("date");
			pageNum = Integer.parseInt(pagenum);
		}else{
			//���ҳ�水ť��ѯ
			date = request.getParameter("search");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int pageCount = CenWarehouseService.getInstance().getTaskListPageCount(new java.sql.Date(sdf.parse(date).getTime()));
		JSONArray json = CenWarehouseService.getInstance().getTaskListByDate(new java.sql.Date(sdf.parse(date).getTime()),pageNum);
		request.setAttribute("resultList", json);
		request.getSession().setAttribute("date", date);
		request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("pagecount", pageCount);
		request.getRequestDispatcher("Central warehouse transfer out.jsp").forward(request, response);
	}
	//������ⵥ
	private void doWarehouseOutInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		String[] ids = request.getParameterValues("chk");
		int[] idss = new int[ids.length];
		for(int i  = 0;i<ids.length;i++){
			idss[i] = Integer.parseInt(ids[i]);
		}
		CenWarehouseService.getInstance().insertOutWarehouseInfo(idss);
		int pageNum = (Integer)request.getSession().getAttribute("pageNum");
		
		response.sendRedirect(request.getContextPath()+"/cenWarehouseServlet?action=searchTaskDate&pageNum="+pageNum);
	}
	//��ѯ�˻��������
	private void doGetReturnInInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String task_id = request.getParameter("taskid");
		JSONObject json = null;
		
        response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();	
		pw.print(json);
		pw.close();
	}
	
}
