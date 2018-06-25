package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.SubWarehouseInInfo;
import com.neuedu.model.service.SubWarehouseService;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class SubWarehouseServlet
 */
public class SubWarehouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubWarehouseServlet() {
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
		System.out.println("进入SubWarehouseServlet");
		//设定编码格式
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//查找任务单
		if("searchTaskIn".equals(action)){
			doGetTaskIn(request, response);
		} else if("submitTaskIn".equals(action)) {//提交入库单
			try {
				doTransferIn(request,response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void doGetTaskIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String task_id = request.getParameter("taskid");
		JSONObject json = null;
		json = SubWarehouseService.getInstance().getTaskListIn(Integer.parseInt(task_id));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();	
		pw.print(json);
		pw.close();
	}

	private void doTransferIn(HttpServletRequest request, HttpServletResponse response) throws ParseException, SQLException, ServletException, IOException {
		String task_id = request.getParameter("taskid");
		//String actualnum = request.getParameter("acnum");
		String indate = request.getParameter("indate");
		String note = request.getParameter("note");
		SubWarehouseInInfo swin = new SubWarehouseInInfo();
		swin.setTask_list_id(Integer.parseInt(task_id));
		swin.setNote(note);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		swin.setOperate_date(sdf.parse(indate));
		SubWarehouseService.getInstance().insertInInfo(swin);
		request.getRequestDispatcher("Substation warehouse transfer  in.jsp").forward(request, response);
	}

}
