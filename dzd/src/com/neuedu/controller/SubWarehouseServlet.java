package com.neuedu.controller;

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

import com.neuedu.model.po.RecvGoodsInfo;
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
		System.out.println("����SubWarehouseServlet");
		//�趨�����ʽ
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//��������
		if("searchTaskIn".equals(action)){
			doGetTaskIn(request, response);
		} else if("submitTaskIn".equals(action)) {//�ύ������ⵥ
			try {
				doTransferIn(request,response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("searchTaskOut".equals(action)) {//��ѯ�����
			doGetTaskOut(request, response);
		} else if("submitTaskOut".equals(action)) {//�ύ������Ϣ
			try {
				doRecvGoods(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//��ѯ�������
	private void doGetTaskIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String task_id = request.getParameter("taskid");
		JSONObject json = null;
		json = SubWarehouseService.getInstance().getTaskListIn(Integer.parseInt(task_id));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();	
		pw.print(json);
		pw.close();
	}
	//������������Ϣ
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
	//��ѯ��������
	private void doGetTaskOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String task_id = request.getParameter("taskid");
		JSONObject json = null;
		json = SubWarehouseService.getInstance().getTaskListOut(Integer.parseInt(task_id));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();	
		pw.print(json);
		pw.close();
	}
	//���������Ϣ
	private void doRecvGoods(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
		String task_id = request.getParameter("taskid");
		String outdate = request.getParameter("outdate");
		String note = request.getParameter("note");
		String recv_person = request.getParameter("recvname");	
		RecvGoodsInfo rin = new RecvGoodsInfo();
		rin.setTask_list_id(Integer.parseInt(task_id));
		rin.setNote(note);
		rin.setRecv_person(recv_person);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		rin.setRecv_date(sdf.parse(outdate));
		rin.setOperate_date(new Date());
		SubWarehouseService.getInstance().insertRecvGoodsInfo(rin);
		request.getRequestDispatcher("picking.jsp").forward(request, response);
	}

}
