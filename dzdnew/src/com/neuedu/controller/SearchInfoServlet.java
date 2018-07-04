package com.neuedu.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.HotProduct;
import com.neuedu.model.po.Implementation;
import com.neuedu.model.po.InOutList;
import com.neuedu.model.po.ResNumInfo;
import com.neuedu.model.po.Satisfaction;
import com.neuedu.model.po.Warehouse;
import com.neuedu.model.service.InfoService;
import com.neuedu.model.service.WhService;

/**
 * Servlet implementation class SearchInfoServlet
 */
public class SearchInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		System.out.println(action);
		if("searchresnum".equals(action)){
			doSearchResNum(request, response);
		}else if("searchinout".equals(action)){
		
				try {
					doSearchInOut(request,response);
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		
		}else if("hotproduct".equals(action)){
			doSearchHotProduct(request,response);
		}else if("implement".equals(action)){
			doSearchImplement(request,response);
		}else if("satisfaction".equals(action)){
			doSearchSatisfaction(request,response);
		}
	
		
		
	}

	private void doSearchSatisfaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		// TODO 自动生成的方法存根
				String pagenum = request.getParameter("pageno");
				int pageNum = 1;
				String startdate ="";
				String enddate="";
				if(pagenum != null && !"".equals(pagenum)){
				
					pageNum = Integer.parseInt(pagenum);
					startdate = (String) request.getSession().getAttribute("starttime");
					enddate = (String) request.getSession().getAttribute("endtime");

					
				}else{
					startdate = (String) request.getParameter("starttime");
					enddate = (String) request.getParameter("endtime");
			
					
					
				}

				
				List<Satisfaction> list =  InfoService.getInstance().searchSatisfaction(startdate, enddate, pageNum);
				int pagecount =  InfoService.getInstance().calPagecountSf(startdate, enddate);

				request.setAttribute("resultList", list);
				request.setAttribute("pagecount", pagecount);
				request.getSession().setAttribute("pageNum", pageNum);
				request.getSession().setAttribute("starttime", startdate);
				request.getSession().setAttribute("endtime", enddate);
				request.getRequestDispatcher("/satisfaction.jsp").forward(request, response);
				
		
		
	}

	private void doSearchImplement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		String startdate ="";
		String enddate="";

		startdate = (String) request.getParameter("starttime");
		enddate = (String) request.getParameter("endtime");			
		
		List<Implementation> list =  InfoService.getInstance().searchImplement(startdate, enddate);
		

		request.setAttribute("resultList", list);
		request.getRequestDispatcher("/Implementation.jsp").forward(request, response);
		
		
	}

	private void doSearchHotProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根

		String startdate ="";
		String enddate="";
		String start,end;
		start = (String) request.getParameter("starttime");
		end = (String) request.getParameter("endtime");	
		if("".equals(start)||"".equals(end)||start==null||end==null){
			startdate="1970-01-01";
			enddate="2050-01-01";
		}else{
			startdate=start;
			enddate=end;
		}
			
		
		List<HotProduct> list =  InfoService.getInstance().searchHotProduct(startdate, enddate);
		

		request.setAttribute("resultList", list);
		request.getRequestDispatcher("/hotProduct.jsp").forward(request, response);
		
		
	
	}

	private void doSearchInOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成的方法存根
		String pagenum = request.getParameter("pageno");
		int pageNum = 1;
		String startdate ="";
		String enddate="";
		String proid="";
		String type="";
		if(pagenum != null && !"".equals(pagenum)){
		
			pageNum = Integer.parseInt(pagenum);
			startdate = (String) request.getSession().getAttribute("starttime");
			enddate = (String) request.getSession().getAttribute("endtime");
			proid = (String) request.getSession().getAttribute("proid");
			type = (String) request.getSession().getAttribute("inouttype");
			
		}else{
			startdate = (String) request.getParameter("starttime");
			enddate = (String) request.getParameter("endtime");
			proid = (String) request.getParameter("proid");
			type = (String) request.getParameter("inouttype");
			
			
			
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		 Date bt=sdf.parse(startdate); 
		  Date et=sdf.parse(enddate); 
		if(bt.before(et)){
			System.out.println(type);
			
			List<InOutList> list =  InfoService.getInstance().searchInOut(startdate, enddate, Integer.parseInt(proid), type, pageNum);
			int pagecount =  InfoService.getInstance().calPagecountInOut(startdate, enddate, Integer.parseInt(proid), type);

			request.setAttribute("resultList", list);
			request.setAttribute("pagecount", pagecount);
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("starttime", startdate);
			request.getSession().setAttribute("endtime", enddate);
			request.getSession().setAttribute("proid", proid);
			request.getSession().setAttribute("inouttype", type);
		}else{
			System.out.println("adsllja");
			request.getSession().setAttribute("inoutmsg", "日期起始终止不对");
		}
		
		request.getRequestDispatcher("/searchInOut.jsp").forward(request, response);
		
		
		
		
	}

	private void doSearchResNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		
		String pagenum = request.getParameter("pageno");
		int pageNum = 1;
		String proname ="";
		if(pagenum != null && !"".equals(pagenum)){
		
			pageNum = Integer.parseInt(pagenum);
			proname = (String) request.getSession().getAttribute("proname");
			System.out.println(proname);
		}else{
			proname=(String) request.getParameter("proname");
			System.out.println(proname);
			
		}
	
		List<ResNumInfo> list =  InfoService.getInstance().searchResNum(proname, pageNum);
		int pagecount =  InfoService.getInstance().calPagecountRes(proname);

		request.setAttribute("resultList", list);
		request.setAttribute("pagecount", pagecount);
		request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("proname", proname);
		request.getRequestDispatcher("/searchInventory.jsp").forward(request, response);
		
		
	}

}
