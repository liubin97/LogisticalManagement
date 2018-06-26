package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.RecvGoodsInfo;
import com.neuedu.model.po.ReturnRegisterInfo;
import com.neuedu.model.po.SubWarehouseInInfo;
import com.neuedu.model.service.SubWarehouseService;

import net.sf.json.JSONArray;
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
		} else if("searchReturnRegister".equals(action)) {//��ѯ�˻��Ǽ���Ϣ
			doGetReturnRegisterInfo(request, response);
		} else if("submitReturnRegister".equals(action)) {//�����˻��Ǽ���Ϣ
			try {
				doRetrunRegister(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("searchSubReturnOut".equals(action)) {//��ѯ�˻�������Ϣ
			try {
				doGetReturnOut(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("submitReturnOut".equals(action)) {
			try {
				doReturnOut(request, response);
			} catch (SQLException e) {
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
	//��ѯ�˻��Ǽ�����
	private void doGetReturnRegisterInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String task_id = request.getParameter("taskid");
		JSONObject json = SubWarehouseService.getInstance().getReturnInTaskList(Integer.parseInt(task_id));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();	
		pw.print(json);
		pw.close();
	}
	//�����˻��Ǽ���Ϣ
	private void doRetrunRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String task_id = request.getParameter("taskid");
		String actual_num = request.getParameter("acnum");
		ReturnRegisterInfo rin = new ReturnRegisterInfo();
		rin.setTask_id(Integer.parseInt(task_id));
		rin.setActual_num(Integer.parseInt(actual_num));
		rin.setOperate_date(new Date());
		SubWarehouseService.getInstance().insertReturnRegisterInfo(rin);
		request.getRequestDispatcher("Return register.jsp").forward(request, response);
	}
	//��ѯ�˻���������
	private void doGetReturnOut(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
		String start_date = null;
		String end_date = null;
		String pagenum = request.getParameter("pageNum");
		int pageNum = 1;
		if(pagenum!=null && !"".equals(pagenum)){
			//���ҳ���ѯ
			System.out.println(pagenum);
			start_date = (String) request.getSession().getAttribute("starttime");
			end_date = (String) request.getSession().getAttribute("endtime");
			pageNum = Integer.parseInt(pagenum);
		}else{
			//���ҳ�水ť��ѯ
			start_date = request.getParameter("starttime");
			end_date = request.getParameter("endtime");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sdate = new java.sql.Date(sdf.parse(start_date).getTime());
		java.sql.Date edate = new java.sql.Date(sdf.parse(end_date).getTime());
		JSONArray json = SubWarehouseService.getInstance().getReturnOutTaskList(sdate,edate,pageNum);
		int pageCount = SubWarehouseService.getInstance().getReturnOutPage(sdate,edate);
		request.setAttribute("resultList", json);
		request.getSession().setAttribute("starttime", start_date);
		request.getSession().setAttribute("endtime", end_date);
		request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("pagecount", pageCount);
		request.getRequestDispatcher("Substation return out.jsp").forward(request, response);
	}
	//�����˻�������Ϣ
	private void doReturnOut(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		String[] ids = request.getParameterValues("chk");
		int[] idss = new int[ids.length];
		for(int i  = 0;i<ids.length;i++){
			idss[i] = Integer.parseInt(ids[i]);
		}
		SubWarehouseService.getInstance().insertReturnOutInfo(idss);
		int pageNum = (Integer)request.getSession().getAttribute("pageNum");
		
		response.sendRedirect(request.getContextPath()+"/subWarehouseServlet?action=searchSubReturnOut&pageNum="+pageNum);
	}
}
