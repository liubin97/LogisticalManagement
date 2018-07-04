package com.neuedu.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Order;
import com.neuedu.model.po.TaskList_Information;
import com.neuedu.model.service.OrderService;
import com.neuedu.model.service.TaskListService;
import com.neuedu.utils.DateUtil;


public class searchTasklistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public searchTasklistServlet() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String pagenum = request.getParameter("pageNum");
		int pageNum = 1;
		
		String taskTypeStr="";
		String tasklistIdStr="";
		String taskStatusStr ="";
		String finishDateStr ="";
		String substationIdStr="";
		
		String clientName ="";
		String telNumber ="";

		int substationId = 0;
		int taskType = 0;
		int tasklistId = 0;
		int taskStatus = 0;
		Date finishDate = null;

		
		if(pagenum != null && !"".equals(pagenum)){
			pageNum = Integer.parseInt(pagenum);
			taskTypeStr = (String) request.getSession().getAttribute("taskType");
			tasklistIdStr = (String) request.getSession().getAttribute("tasklistId");
			finishDateStr = (String) request.getSession().getAttribute("finishDate");
			taskStatusStr = (String) request.getSession().getAttribute("taskStatus");
			substationIdStr = (String) request.getSession().getAttribute("substationId");
			clientName = (String) request.getSession().getAttribute("clientName");
			telNumber = (String) request.getSession().getAttribute("telNumber");
		}else{
			taskTypeStr = request.getParameter("taskType");
			tasklistIdStr = request.getParameter("tasklistId");
			finishDateStr = request.getParameter("finishDate");
			taskStatusStr = request.getParameter("taskStatus");
			substationIdStr = request.getParameter("substationId");
			clientName = request.getParameter("clientName");
			telNumber = request.getParameter("telNumber");
		}
		
		if(taskTypeStr != null && !"".equals(taskTypeStr)){
			taskType = Integer.parseInt(taskTypeStr);
		}if(substationIdStr != null && !"".equals(substationIdStr)){
			substationId = Integer.parseInt(substationIdStr);
		}if(tasklistIdStr != null && !"".equals(tasklistIdStr)){
			tasklistId = Integer.parseInt(tasklistIdStr);
		}if(taskStatusStr != null && !"".equals(taskStatusStr)){
			taskStatus = Integer.parseInt(taskStatusStr);
		}if(finishDateStr != null && !"".equals(finishDateStr)){
			finishDate = DateUtil.strToDate(finishDateStr);
		}
		
		List<TaskList_Information> list = TaskListService.getInstance().selectTasklist(taskType, taskStatus, substationId, tasklistId, clientName, telNumber, finishDate,
				pageNum);
		int countPage = TaskListService.getInstance().countPage(taskType, taskStatus, substationId, tasklistId, clientName, telNumber, finishDate);
				
				
		request.setAttribute("tasklistResultList", list);
		request.setAttribute("countPage", countPage);
		request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("taskType", taskTypeStr);
		request.getSession().setAttribute("tasklistId", tasklistIdStr);
		request.getSession().setAttribute("finishDate", finishDateStr);
		request.getSession().setAttribute("taskStatus", taskStatusStr);
		request.getSession().setAttribute("substationId", substationIdStr);
		request.getSession().setAttribute("clientName", clientName);
		request.getSession().setAttribute("telNumber", telNumber);
//		System.out.println(taskTypeStr);
//		System.out.println(tasklistIdStr);

		try {
			request.getRequestDispatcher("searchMission.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}


