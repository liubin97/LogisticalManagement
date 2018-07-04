package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.neuedu.model.po.OrderInfor;
import com.neuedu.model.po.SettleListInfor;
import com.neuedu.model.po.SettleSubstationInfor;
import com.neuedu.model.po.SettleSupplyInfor;
import com.neuedu.model.po.Settlement;
import com.neuedu.model.service.FinanceService;
import com.neuedu.utils.DBUtil;

/**
 * Servlet implementation class SubstationServlet
 */
public class SubstationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubstationServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action.equals("unsettled")){
			String pagenum = request.getParameter("pageNum");
			int pageNum = 1;
			String name = "";
			String date = "";
			String itemCode = "";
			String itemName = "";
			String pageSize = "";
			if(pagenum != null&&!"".equals(pagenum)){
				//点解页码进行查询
				pageNum = Integer.parseInt(pagenum);
				name = (String)request.getSession().getAttribute("name");
				date = (String)request.getSession().getAttribute("dateInq");
				itemCode = (String)request.getSession().getAttribute("itemCodeInq");
				itemName = (String)request.getSession().getAttribute("itemNameInq");
				pageSize = (String)request.getSession().getAttribute("pageSize");
				
			}
			else{//点击按钮进行查询
				name = request.getParameter("name");
				date = request.getParameter("dateInq");
				itemCode = request.getParameter("itemCodeInq");
				itemName = request.getParameter("itemNameInq");
				pageSize = request.getParameter("pageSize");
			}
			//检测商品编号是否符合规则，不符合则置位-1
			if(itemCode!=null&&!"".equals(itemCode)){	
				if(!DBUtil.testNumber(itemCode)){
					itemCode = "-1";
				}
			}
			
			Settlement settlement = new Settlement(name,date,itemCode,itemName);
			//查询分页
			List<OrderInfor> orderList = FinanceService.getInstance().getOrderInfor(settlement,Integer.parseInt(pageSize),pageNum);
			//查询页数
			int pagecount = FinanceService.getInstance().selectPageCountForSubUnsettle(settlement,Integer.parseInt(pageSize));
			request.setAttribute("resultList", orderList);
			request.setAttribute("pagecount", pagecount);
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("dateInq",date);
			request.getSession().setAttribute("itemCodeInq", itemCode);
			request.getSession().setAttribute("itemNameInq",itemName);
			request.getSession().setAttribute("pageSize", pageSize);
			request.getRequestDispatcher("settlement-with-sub-station-unsettled.jsp").forward(request, response);
		}
		else if(action.equals("information")){
			String[] taskIds = request.getParameterValues("chk");
			String[] subName = request.getParameterValues("subName");
			String[] type = request.getParameterValues("type");
			String[] itemCode = request.getParameterValues("itemCode");
			String[] itemName = request.getParameterValues("itemName");
			String[] price = request.getParameterValues("price");
			String[] quantity = request.getParameterValues("quantity");
			String[] amount = request.getParameterValues("amount");
			List<OrderInfor> orderList = new ArrayList<OrderInfor>();
			if(taskIds!=null){
				for(int i = 0;i<taskIds.length;i++){
					OrderInfor temp = new OrderInfor(Integer.parseInt(taskIds[i]),subName[i],Integer.parseInt(type[i]),itemCode[i],itemName[i],Double.parseDouble(price[i]),Integer.parseInt(quantity[i]),Double.parseDouble(amount[i]));
					orderList.add(temp);
				}
			}
			
			List<SettleSubstationInfor> infor = FinanceService.getInstance().SubstationUnsettledList(orderList);
			List<Integer> ids = new ArrayList<Integer>();
			for(int i = 0;i<taskIds.length;i++){
				ids.add(Integer.parseInt(taskIds[i]));
			}
			request.setAttribute("ids", ids);
			request.setAttribute("resultList", infor);
			request.getRequestDispatcher("settle-list.jsp").forward(request, response);
		}
		else if(action.equals("confirm")){
			String[] ids = request.getParameterValues("id");
			int value = FinanceService.getInstance().confirmSubstation(ids);
			if(value==1){
				String[] subName = request.getParameterValues("subName");
				String[] itemCode = request.getParameterValues("itemCode");
				String[] itemName = request.getParameterValues("itemName");
				String[] price = request.getParameterValues("price");
				String[] quantityOut = request.getParameterValues("quantityOut");
				String[] amountIn = request.getParameterValues("amountIn");
				String[] quantityIn = request.getParameterValues("quantityIn");
				String[] amountOut = request.getParameterValues("amountOut");
				List<SettleSubstationInfor> listInfor = new ArrayList<SettleSubstationInfor>();
				for(int i =0;i<subName.length;i++){
					SettleSubstationInfor infor = new SettleSubstationInfor(new ArrayList<Integer>(),subName[i],itemCode[i],itemName[i],
							Double.parseDouble(price[i]),Integer.parseInt(quantityOut[i]),Double.parseDouble(amountIn[i]),
							Integer.parseInt(quantityIn[i]),Double.parseDouble(amountOut[i]),"");
					listInfor.add(infor);
				}
				FinanceService.getInstance().noteSubSettleInfor(listInfor);
			}
			request.setAttribute("value", value);
			request.getRequestDispatcher("finance-index.jsp").forward(request, response);
		}
		else if(action.equals("settled")){
			String pagenum = request.getParameter("pageNum");
			int pageNum = 1;
			String name = "";
			String date = "";
			String itemCode = "";
			String itemName = "";
			String pageSize = "";
			if(pagenum != null&&!"".equals(pagenum)){
				//点解页码进行查询
				pageNum = Integer.parseInt(pagenum);
				name = (String)request.getSession().getAttribute("name");
				date = (String)request.getSession().getAttribute("dateInq");
				itemCode = (String)request.getSession().getAttribute("itemCodeInq");
				itemName = (String)request.getSession().getAttribute("itemNameInq");
				pageSize = (String)request.getSession().getAttribute("pageSize");
			}
			else{//点击按钮进行查询
				name = request.getParameter("name");
				date = request.getParameter("dateInq");
				itemCode = request.getParameter("itemCodeInq");
				itemName = request.getParameter("itemNameInq");
				pageSize = request.getParameter("pageSize");
			}
			//检测商品编号是否符合规则，不符合则置位-1
			if(itemCode!=null&&!"".equals(itemCode)){	
				if(!DBUtil.testNumber(itemCode)){
					itemCode = "-1";
				}
			}
			Settlement settlement = new Settlement(name,date,itemCode,itemName);
			//查询分页
			List<SettleSubstationInfor> infor = FinanceService.getInstance().inquireSubstationsettled(settlement,Integer.parseInt(pageSize),pageNum);
			//查询页数
			int pagecount = FinanceService.getInstance().selectPageCountForSub(settlement,Integer.parseInt(pageSize));
			request.setAttribute("resultList", infor);
			request.setAttribute("pagecount", pagecount);
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("dateInq",date);
			request.getSession().setAttribute("itemCodeInq", itemCode);
			request.getSession().setAttribute("itemNameInq",itemName);
			request.getSession().setAttribute("pageSize", pageSize);
			
			request.setAttribute("resultList", infor);
			request.getRequestDispatcher("settlement-with-sub-station-settled.jsp").forward(request, response);
		}
		else if("moreInfor".equals(action)){
			int pageNum = 1;
			String pageSize = request.getParameter("pageSize");
			String option = request.getParameter("option");
			String name = (String)request.getSession().getAttribute("name");
			String date = (String)request.getSession().getAttribute("dateInq");
			String itemCode = (String)request.getSession().getAttribute("itemCodeInq");
			String itemName = (String)request.getSession().getAttribute("itemNameInq");
			Settlement settlement = new Settlement(name,date,itemCode,itemName);
			//检测商品编号是否符合规则，不符合则置位-1
			if(itemCode!=null&&!"".equals(itemCode)){	
				if(!DBUtil.testNumber(itemCode)){
					itemCode = "-1";
				}
			}
			int pagecount = 1;
			if(option.equals("unsettled")){
				//查询分页
				List<OrderInfor> orderList = FinanceService.getInstance().getOrderInfor(settlement,Integer.parseInt(pageSize),pageNum);
				//查询页数
				pagecount = FinanceService.getInstance().selectPageCountForSubUnsettle(settlement,Integer.parseInt(pageSize));
				request.setAttribute("resultList", orderList);
			}else{
				//查询分页
				List<SettleSubstationInfor> infor = FinanceService.getInstance().inquireSubstationsettled(settlement,Integer.parseInt(pageSize),pageNum);
				//查询页数
				pagecount = FinanceService.getInstance().selectPageCountForSub(settlement,Integer.parseInt(pageSize));
				request.setAttribute("resultList", infor);
			}
			
			request.setAttribute("pagecount", pagecount);
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("dateInq",date);
			request.getSession().setAttribute("itemCodeInq", itemCode);
			request.getSession().setAttribute("itemNameInq",itemName);
			request.getSession().setAttribute("pageSize", pageSize);
			request.getRequestDispatcher("settlement-with-sub-station-"+option+".jsp").forward(request, response);
		}
		else if("initialize_unsettle".equals(action)){

			List<String> substationList = FinanceService.getInstance().getSubstation(); 
			List<String> dateList = FinanceService.getInstance().getDateForSubUnsettle();
			JsonArray jsonArray = new JsonArray( );//记录分站
			JsonArray jsonArray2 = new JsonArray( );//记录时间段
			for(int i = 0;i<substationList.size();i++){
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("substation", substationList.get(i));
				jsonArray.add(jsonObject);
			}
			for(int i = 0;i<dateList.size();i++){
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("date", dateList.get(i));
				jsonArray2.add(jsonObject);
			}
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("substation", jsonArray);
			jsonObject.add("date", jsonArray2);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject);
			pw.flush();
			pw.close();
		}
		else if("initialize_settled".equals(action)){
			List<String> substationList = FinanceService.getInstance().getSubstation(); 
			List<String> dateList = FinanceService.getInstance().getDateForSubsettled();
			JsonArray jsonArray = new JsonArray( );//记录分站
			JsonArray jsonArray2 = new JsonArray( );//记录时间段
			for(int i = 0;i<substationList.size();i++){
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("substation", substationList.get(i));
				jsonArray.add(jsonObject);
			}
			for(int i = 0;i<dateList.size();i++){
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("date", dateList.get(i));
				jsonArray2.add(jsonObject);
			}
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("substation", jsonArray);
			jsonObject.add("date", jsonArray2);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject);
			pw.flush();
			pw.close();
		}
	}

}
