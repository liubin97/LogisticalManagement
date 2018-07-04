package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Warehouse;
import com.neuedu.model.service.TaskListService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class distributeServlet
 */
public class distributeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public distributeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// System.out.println("abc");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		 System.out.println(action);
		if ("autoOne".equals(action)) {
			distributeAutoOne(request, response);
		}
		if ("byhandOne".equals(action)) {
			distributeByhandOne(request, response);
		}
		if ("distribute".equals(action)) {
			distribute(request, response);
		}
		if ("distributeAllByhand".equals(action)) {
			distributeAllByhand(request, response);
		}
		if ("distributeAllAuto".equals(action)) {
			distributeAllAuto(request, response);
		}
		if ("getSubstation".equals(action)) {
			getSubstation(request, response);
		}

	}

	private void distributeByhandOne(HttpServletRequest request, HttpServletResponse response) {

		String orderIdStr = "";
		String substationIdStr = "";
		orderIdStr = request.getParameter("orderIdOneByhand");
		substationIdStr = request.getParameter("substationIdOneByhand");
		int orderId = Integer.parseInt(orderIdStr);
		int substationId = Integer.parseInt(substationIdStr);
		TaskListService.getInstance().createTasklist(orderId, substationId);
		
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		String orderType = (String) request.getSession().getAttribute("orderType");
		String finishDate = (String) request.getSession().getAttribute("finishDate");

		try {
			response.sendRedirect("searchorderservlet?pageNum="+pageNum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private void distributeAutoOne(HttpServletRequest request, HttpServletResponse response) {

		String orderIdStr = "";
		orderIdStr = request.getParameter("orderIdOneAuto");
		int orderId = Integer.parseInt(orderIdStr);
		int finalSubstationId = TaskListService.getInstance().distributeAuto(orderId);
		System.out.println("distributeServlet 中的 substationId 是" + finalSubstationId);
		// 修改
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.print(finalSubstationId);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void distribute(HttpServletRequest request, HttpServletResponse response) {
		TaskListService.getInstance().distribute();
		try {
			request.getRequestDispatcher("controlCenter.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void distributeAllByhand(HttpServletRequest request, HttpServletResponse response) {
		String substationIdStr = "";
		substationIdStr = request.getParameter("substationIdAllByhand");
		int substationId = 0;
		substationId = Integer.parseInt(substationIdStr);
		String[] ids = request.getParameterValues("chk");
		int[] orderId = new int[ids.length];
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
			orderId[i] = Integer.parseInt(ids[i]);
			TaskListService.getInstance().createTasklist(orderId[i], substationId);
		}
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		String orderType = (String) request.getSession().getAttribute("orderType");
		String finishDate = (String) request.getSession().getAttribute("finishDate");

		try {
			response.sendRedirect("searchorderservlet?pageNum="+pageNum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void distributeAllAuto(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameterValues("chk");
		int[] orderId = new int[ids.length];
		for (int i = 0; i < ids.length; i++) {
			orderId[i] = Integer.parseInt(ids[i]);
			TaskListService.getInstance().distributeAuto(orderId[i]);
		}
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		String orderType = (String) request.getSession().getAttribute("orderType");
		String finishDate = (String) request.getSession().getAttribute("finishDate");

		try {
			response.sendRedirect("searchorderservlet?pageNum="+pageNum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getSubstation(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("oooooi");
		List<Warehouse> wh = TaskListService.getInstance().getSubstation();
		JSONArray jsonarr = new JSONArray();
		JSONObject json = new JSONObject();
		for (Warehouse whtemp : wh ) {
			
			System.out.println(whtemp.getWh_id());
			json.put("id", whtemp.getWh_id());
			json.put("name", whtemp.getWh_name());
			jsonarr.add(json);
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.print(jsonarr);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
