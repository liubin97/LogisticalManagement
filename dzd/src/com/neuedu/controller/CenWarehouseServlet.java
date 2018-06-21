package com.neuedu.controller;

import com.neuedu.model.po.CenWarehouseInInfo;
import com.neuedu.model.po.PurchaseSupplier;
import com.neuedu.model.service.CenWarehouseService;

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
		System.out.println("进入CenWarehouseServlet");
		//设定编码格式
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//查找购货单
		if("searchPs".equals(action)){
            doGetPurchaseInfo(request,response);
        }
		//提交购货入库单
		else if("submitPs".equals(action)) {
			try {
				doPurchaseInInfo(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

}
