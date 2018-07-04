package com.neuedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Receipt;
import com.neuedu.model.po.TaskList_allInformation;
import com.neuedu.model.po.delivery_staff;
import com.neuedu.model.service.ReceiptService;
import com.neuedu.model.service.TaskListService;

/**
 * Servlet implementation class RecepitServlet
 */
public class RecepitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecepitServlet() {
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
			//从任务单查询的页面跳转到本servlet
			int pageNum ;
			int substation_id;
			String begintime = "";
			String taskstate = "";
			String tasktype  = "";
			int tasklist_id;
			
			//将任务单查询页面的查询条件获取
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			substation_id = Integer.parseInt(request.getParameter("substation_id"));
			begintime = request.getParameter("beginTime");
			taskstate = request.getParameter("taskState");
			tasktype = request.getParameter("taskType");
			tasklist_id= Integer.parseInt(request.getParameter("tasklist_id"));
			
			//将任务单的查询条件放回request中
			request.setAttribute("beginTime", begintime);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("taskState", taskstate);
			request.setAttribute("taskType", tasktype);
			request.setAttribute("substation_id", substation_id);
			request.setAttribute("tasklist_id", tasklist_id);
			
			//将请求转发到回执的页面中
			request.getRequestDispatcher("RecepitInquery.jsp").forward(request,response);
			
		}else {
			
			//点击回执录入的提交按钮进入本servlet
			int pageNum ;
			int substation_id;
			String begintime = "";
			String taskstate = "";
			String tasktype  = "";
			int tasklist_id;
			
			tasklist_id = Integer.parseInt(request.getParameter("tasklist_id"));
			String clientSatis="null";
			if(Integer.parseInt((String)request.getParameter("clientsatis"))==1){
				clientSatis="满意";
			}else if(Integer.parseInt((String)request.getParameter("clientsatis"))==2){
				clientSatis="一般";
			}else if(Integer.parseInt((String)request.getParameter("clientsatis"))==3){
				clientSatis="不满意";
			}
			String note = request.getParameter("note");
		
//			System.out.println(clientSatis);
			
			
			//重新获取上个页面的查询条件并将查询条件放回request中
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			substation_id = Integer.parseInt(request.getParameter("substation_id"));
			begintime = request.getParameter("beginTime");
			taskstate = request.getParameter("taskState");
			tasktype = request.getParameter("taskType");
			
			request.setAttribute("beginTime", begintime);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("taskState", taskstate);
			request.setAttribute("taskType", tasktype);
			request.setAttribute("substation_id", substation_id);
			request.setAttribute("tasklist_id", tasklist_id);
			
			//用户输入的回执单
			Receipt rec = new Receipt();
			rec.setReceiptnote(note);
			rec.setTasklist_id(tasklist_id);
			rec.setClientSatis(clientSatis);
			
//			System.out.println(rec.getClientSatis());
			
			ReceiptService.getInstance().EntryReceipt(rec);
			
			
			//回执提交之后将请求转发回任务单查询的servlet中
			request.getRequestDispatcher("/taskListQueryServlet").forward(request, response);
		}
	}

}
