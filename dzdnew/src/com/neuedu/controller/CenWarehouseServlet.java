package com.neuedu.controller;

import com.neuedu.model.po.CenReturnInInfo;
import com.neuedu.model.po.CenReturnOutInfo;
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
		System.out.println("Enter into CenWarehouseServlet");
		//设定编码格式
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//查询购货单
		if("searchPs".equals(action)){
            doGetPurchaseInfo(request,response);
        } else if("submitPs".equals(action)) {//插入购货入库信息
			try {
				doPurchaseInInfo(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("searchTaskDate".equals(action)) {//查询出库单
			try {
				doGetTaskListByDate(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("submitWhOut".equals(action)) {//提交出库信息
			try {
				doWarehouseOutInfo(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("searchReturnIn".equals(action)) {//查询退货入库信息
			try {
				doGetReturnInInfo(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("submitReturnIn".equals(action)) {
			try {
				doReturnIn(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("searchReturnOut".equals(action)) {//查询退货出库信息
			try {
				doGetReturnOutInfo(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("sbumitReturnOut".equals(action)) {//提交退货出库信息
			try {
				doReturnOut(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		} else if("getSubstation".equals(action)) {//查询库房信息
			try {
				doGetSubstation(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if("searchDistribution".equals(action)) {//查询分发单
			try {
				doGetDistribution(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("searchPrintDis".equals(action)) {
			try {
				doGetPrintDis(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//查询购货单
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
	//插入购货入库信息
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
	//查询要调拨任务信息
	private void doGetTaskListByDate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException, ServletException {
		String date = null;
		//System.out.println(date+"1");
		String pagenum = request.getParameter("pageNum");
		int pageNum = 1;
		if(pagenum!=null && !"".equals(pagenum)){
			//点击页码查询	
			date = (String) request.getSession().getAttribute("date");
			pageNum = Integer.parseInt(pagenum);
		}else{
			//点击页面查询
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
	//插入调拨出库信息
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
	//查询退货入库信息
	private void doGetReturnInInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, SQLException {
		String task_id = request.getParameter("taskid");
		JSONObject json = CenWarehouseService.getInstance().getReturnInInfo(Integer.parseInt(task_id));
        response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();	
		pw.print(json);
		pw.close();
	}
	//插入退货入库信息
	private void doReturnIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String task_id = request.getParameter("taskid");
		String actual_num = request.getParameter("acnum");
		CenReturnInInfo crin = new CenReturnInInfo();
		crin.setTask_list_id(Integer.parseInt(task_id));
		crin.setReturn_date(new Date());
		crin.setActual_num(Integer.parseInt(actual_num));
		CenWarehouseService.getInstance().insertReturnInInfo(crin);
		request.getRequestDispatcher("Central return in.jsp").forward(request, response);
	}
	//查询退货出库信息
	private void doGetReturnOutInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, SQLException {
		String rsid = request.getParameter("rsid");
		JSONObject json = CenWarehouseService.getInstance().getReturnOutInfo(Integer.parseInt(rsid));
        response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();	
		pw.print(json);
		pw.close();
	}
	//插入退货出库信息
	private void doReturnOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String rsid = request.getParameter("rsid");
		String actual_num = request.getParameter("acnum");
		CenReturnOutInfo croi = new CenReturnOutInfo();
		croi.setRs_id(Integer.parseInt(rsid));
		croi.setActual_num(Integer.parseInt(actual_num));
		croi.setOperate_date(new Date());
		CenWarehouseService.getInstance().insertReturnOutInfo(croi);
		request.getRequestDispatcher("Central return out.jsp").forward(request, response);
	}
	//查询分站库房信息
	private void doGetSubstation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		JSONArray json = CenWarehouseService.getInstance().getSubstationInfo();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();	
		pw.print(json);
		pw.close();
		
	}
	//查询分发单
	private void doGetDistribution(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, ParseException, ServletException, IOException {
		String sub_id = null;
		String date = null;
		String product_name = null;	
		String pagenum = request.getParameter("pageNum");
		int pageNum = 1;
		if(pagenum!=null && !"".equals(pagenum)){
			//点击页码查询	
			date = (String) request.getSession().getAttribute("outdate");
			sub_id = (String) request.getSession().getAttribute("substation");
			product_name = (String) request.getSession().getAttribute("productname");
			pageNum = Integer.parseInt(pagenum);
		}else{
			//点击页面查询
			sub_id = request.getParameter("substation");
			date = request.getParameter("outdate");
			product_name = request.getParameter("productname");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int pageCount = CenWarehouseService.getInstance().getDistributionPageCount(Integer.parseInt(sub_id), new java.sql.Date(sdf.parse(date).getTime()), product_name);
		JSONArray json = CenWarehouseService.getInstance().getDistribution(Integer.parseInt(sub_id), new java.sql.Date(sdf.parse(date).getTime()), product_name, pageNum);
		
		request.setAttribute("resultList", json);
		request.getSession().setAttribute("outdate", date);
		request.getSession().setAttribute("substation", sub_id);
		request.getSession().setAttribute("productname", product_name);
		request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("pagecount", pageCount);
		request.getRequestDispatcher("Print out distribution.jsp").forward(request, response);
	}
	//查询要打印的分发单信息
	private void doGetPrintDis(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, IOException {
		String task_id = request.getParameter("taskid");
		JSONObject json = CenWarehouseService.getInstance().getPrintDis(Integer.parseInt(task_id));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();	
		pw.print(json);
		pw.close();
	}
}
