package com.neuedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.TaskList_allInformation;
import com.neuedu.model.po.TaskList_allInformation;
import com.neuedu.model.service.TaskListService;

/**
 * Servlet implementation class TaskListQuery
 */
public class TaskListQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TaskListQueryServlet() {
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
		
		String pagenum = request.getParameter("pageNum");
		int pageNum = 1;
		int substation_id;
		String begintime = "";
		String taskstate = "";
		String tasktype  = "";
		substation_id = (int) request.getSession().getAttribute("substation_id");
		//System.out.println(substation_id);
		//根据pageNum的值判断是用按钮查询还是页码查询
		if(pagenum != null && !"".equals(pagenum)){
			pageNum = Integer.parseInt(pagenum);
			begintime = (String) request.getParameter("beginTime");
			taskstate = (String) request.getParameter("taskState");
			tasktype = (String) request.getParameter("taskType");
		}else{
			begintime = (String) request.getParameter("beginTime");
			taskstate = (String) request.getParameter("taskState");
			tasktype = (String) request.getParameter("taskType");
		}
		
		//查询结果
		List<TaskList_allInformation> list =  TaskListService.getInstance().queryTaskList(substation_id,begintime,taskstate,tasktype,pageNum);
		
		//查询总页数
		int pagecount =  TaskListService.getInstance().selectPageCount(substation_id,begintime,taskstate,tasktype);
		
//		for(int i=0;i<list.size();i++) {
//			System.out.println(list.get(i).getTaskState());
//			System.out.println(list.get(i).getTaskType());
//		}
		
		
		//将查询条件返回至页面
		request.setAttribute("resultlist", list);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("beginTime", begintime);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("taskState", taskstate);
		request.setAttribute("taskType", tasktype);
		request.setAttribute("substation_id", substation_id);
		
		//请求转发
		request.getRequestDispatcher("AssigementInquery.jsp").forward(request, response);
		
		
	}

}
