package com.neuedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.TaskList_allInformation;
import com.neuedu.model.po.delivery_staff;
import com.neuedu.model.service.TaskListService;

/**
 * Servlet implementation class TaskListAllocateServlet
 */
public class TaskListAllocateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskListAllocateServlet() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		if(request.getParameter("action")==null||"".equals((String)request.getParameter("action"))) {
			//从任务单查询页面跳转到本servlet
			int pageNum ;
			int substation_id;
			String begintime = "";
			String taskstate = "";
			String tasktype  = "";
			int tasklist_id;
			
			//获取上个页面的查询条件并将其放入request中
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			substation_id = Integer.parseInt(request.getParameter("substation_id"));
			begintime = request.getParameter("beginTime");
			taskstate = request.getParameter("taskState");
			tasktype = request.getParameter("taskType");
			tasklist_id= Integer.parseInt(request.getParameter("tasklist_id"));
			
			TaskList_allInformation tasklist;
			tasklist = TaskListService.getInstance().getTaskListById(tasklist_id);
			List<delivery_staff> deliverlist = TaskListService.getInstance().getAlldeliver();
			
			request.setAttribute("taskList", tasklist);
			request.setAttribute("deliveryList", deliverlist);
			request.setAttribute("beginTime", begintime);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("taskState", taskstate);
			request.setAttribute("taskType", tasktype);
			request.setAttribute("substation_id", substation_id);
			
			
			//将请求转发到任务分配的页面中
			request.getRequestDispatcher("AssigementAllocate.jsp").forward(request,response);
		}else {
			//通过任务分配的页面按钮提交任务分配进入本页面
			
			int pageNum ;
			int substation_id;
			String begintime = "";
			String taskstate = "";
			String tasktype  = "";
			int tasklist_id;
			//获取查询条件并放入request中
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			substation_id = Integer.parseInt(request.getParameter("substation_id"));
			begintime = request.getParameter("beginTime");
			taskstate = request.getParameter("taskState");
			tasktype = request.getParameter("taskType");
			tasklist_id= Integer.parseInt(request.getParameter("tasklist_id"));
			
			request.setAttribute("beginTime", begintime);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("taskState", taskstate);
			request.setAttribute("taskType", tasktype);
			request.setAttribute("substation_id", substation_id);
			
			
			
			int delivery_staff_id = Integer.parseInt(request.getParameter("deliver_id"));
			int taskType = Integer.parseInt(request.getParameter("taskType"));
			TaskListService.getInstance().AllocateDeliver(taskType,tasklist_id,delivery_staff_id);
			
			//分配完之后将请求转发回任务单查询页面
			request.getRequestDispatcher("/taskListQueryServlet").forward(request, response);
			
		}
		//response.sendRedirect("AssigementAllocate.jsp");
		
	}

}
