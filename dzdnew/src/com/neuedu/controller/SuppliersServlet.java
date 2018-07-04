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
import com.neuedu.model.po.SettleListInfor;
import com.neuedu.model.po.SettleSupplyInfor;
import com.neuedu.model.po.Settlement;
import com.neuedu.model.service.FinanceService;
import com.neuedu.utils.DBUtil;

/**
 * Servlet implementation class SuppliersServlet
 */
public class SuppliersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuppliersServlet() {
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
		String action = request.getParameter("action");
		if("unsettled".equals(action)){//未结算信息
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
			List<SettleSupplyInfor> infor = FinanceService.getInstance().inquireSuppliersUnsettled(settlement,Integer.parseInt(pageSize),pageNum);
			//查询页数
			int pagecount = FinanceService.getInstance().selectPageCountForSupUnsettle(settlement,Integer.parseInt(pageSize));
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("resultList", infor);
			
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("dateInq",date);
			request.getSession().setAttribute("itemCodeInq", itemCode);
			request.getSession().setAttribute("itemNameInq",itemName);
			request.getSession().setAttribute("pageSize", pageSize);
			request.getRequestDispatcher("balance-with-suppliers-unsettled.jsp").forward(request, response);
		}
		else if("moreInfor".equals(action)){//获取更多信息（每页显示的行数增加）
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
			if(option.equals("unsettled")){//未结算页面发送的请求
				//查询分页
				List<SettleSupplyInfor> infor = FinanceService.getInstance().inquireSuppliersUnsettled(settlement,Integer.parseInt(pageSize),pageNum);
				//查询页数
				pagecount = FinanceService.getInstance().selectPageCountForSupUnsettle(settlement,Integer.parseInt(pageSize));
				request.setAttribute("resultList", infor);
			}else if(option.equals("settled")){//已结算信息页面发送的请求
				//查询分页
				List<SettleListInfor> infor = FinanceService.getInstance().inquireSuppliersSettled(settlement,Integer.parseInt(pageSize),pageNum);
				//查询页数
				pagecount = FinanceService.getInstance().selectPageCountForSup(settlement,Integer.parseInt(pageSize));
				request.setAttribute("resultList", infor);
			}
			
			request.setAttribute("pagecount", pagecount);
			request.getSession().setAttribute("pageNum", 1);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("dateInq",date);
			request.getSession().setAttribute("itemCodeInq", itemCode);
			request.getSession().setAttribute("itemNameInq",itemName);
			request.getSession().setAttribute("pageSize", pageSize);
			request.getRequestDispatcher("balance-with-suppliers-"+option+".jsp").forward(request, response);
		}
		else if("settled".equals(action)){//已结算信息
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
			List<SettleListInfor> infor = FinanceService.getInstance().inquireSuppliersSettled(settlement,Integer.parseInt(pageSize),pageNum);
			//查询页数
			int pagecount = FinanceService.getInstance().selectPageCountForSup(settlement,Integer.parseInt(pageSize));
			request.setAttribute("resultList", infor);
			request.setAttribute("pagecount", pagecount);
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("dateInq",date);
			request.getSession().setAttribute("itemCodeInq", itemCode);
			request.getSession().setAttribute("itemNameInq",itemName);
			request.getSession().setAttribute("pageSize", pageSize);
			request.getRequestDispatcher("balance-with-suppliers-settled.jsp").forward(request, response);
		}
		else if("information".equals(action)){//统计未结算的进货和退货信息
			String[] ids = request.getParameterValues("chk");
			String[] supplyName = request.getParameterValues("supplyName");
			String[] kind = request.getParameterValues("kind");
			String[] itemCode = request.getParameterValues("itemCode");
			String[] itemName = request.getParameterValues("itemName");
			String[] price = request.getParameterValues("price");
			String[] quantity = request.getParameterValues("quantity");
			String[] date = request.getParameterValues("date");
			List<SettleSupplyInfor> settleList = new ArrayList<SettleSupplyInfor>();
			for(int i = 0;i<ids.length;i++){
				SettleSupplyInfor temp = new SettleSupplyInfor(Integer.parseInt(ids[i]),supplyName[i],kind[i],itemCode[i],itemName[i],Double.parseDouble(price[i]),Integer.parseInt(quantity[i]),date[i]);
				settleList.add(temp);
			}
			List<SettleListInfor> listInfor = FinanceService.getInstance().settlementInfor(settleList);
			//记录进货id以供更改状态
			List<Integer> idIn = new ArrayList<Integer>();
			for(int i =0;i<listInfor.size();i++){
				for(int j = 0;j<listInfor.get(i).getIdIn().size();j++){
					idIn.add(listInfor.get(i).getIdIn().get(j));
				}
			}
			//记录退货id以供更改状态
			List<Integer> idOut = new ArrayList<Integer>();
			for(int i =0;i<listInfor.size();i++){
				for(int j = 0;j<listInfor.get(i).getIdOut().size();j++){
					idOut.add(listInfor.get(i).getIdOut().get(j));
				}
			}
			request.setAttribute("idIn", idIn);
			request.setAttribute("idOut", idOut);
			request.setAttribute("resultList", listInfor);


			request.getRequestDispatcher("confirm.jsp").forward(request, response);
		}
		else if("confirm".equals(action)){//确认结算
			String[] idIn = request.getParameterValues("idIn");
			String[] idOut = request.getParameterValues("idOut");
			String[] supplyName = request.getParameterValues("supplyName");
			String[] itemCode = request.getParameterValues("itemCode");
			String[] itemName = request.getParameterValues("itemName");
			String[] price = request.getParameterValues("price");
			String[] quantityIn = request.getParameterValues("quantityIn");
			String[] quantityOut = request.getParameterValues("quantityOut");
			//更改状态
			int value = FinanceService.getInstance().confirmSuppliers(idIn, idOut);
			if(value==1){
				List<SettleListInfor> listInfor = new ArrayList<SettleListInfor>();
				//提取结算信息
				for(int i =0;i<supplyName.length;i++){
					SettleListInfor infor = new SettleListInfor(null,null,
							supplyName[i],itemCode[i],itemName[i],
							Double.parseDouble(price[i]),Integer.parseInt(quantityIn[i]),Integer.parseInt(quantityOut[i]),
							Integer.parseInt(quantityIn[i])-Integer.parseInt(quantityOut[i]));
					
					listInfor.add(infor);
				}
				//记录结算信息
				FinanceService.getInstance().noteSupplySettleInfor(listInfor);
			}
			request.setAttribute("value", value);
			request.getRequestDispatcher("finance-index.jsp").forward(request, response);
		}
		//未结算页面初始化供应商与时间段
		else if("initialize_unsettle".equals(action)){
			response.setCharacterEncoding("utf-8");
			//获取供应商名称
			List<String> supplierList = FinanceService.getInstance().getSupplier(); 
			//获取时间段
			List<String> dateList = FinanceService.getInstance().getDateForSupUnsettle();
			JsonArray jsonArray = new JsonArray( );//记录供应商
			JsonArray jsonArray2 = new JsonArray( );//记录时间段
			//json数据记录供应商名称
			for(int i = 0;i<supplierList.size();i++){
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("supplierName", supplierList.get(i));
				jsonArray.add(jsonObject);
			}
			//json数组记录时间段
			for(int i = 0;i<dateList.size();i++){
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("date", dateList.get(i));
				jsonArray2.add(jsonObject);
			}
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("supplier", jsonArray);//记录json供应商数组
			jsonObject.add("date", jsonArray2);//记录json时间段数组
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject);//发送json对象
			pw.flush();
			pw.close();
		}
		//已结算页面初始化供应商与时间段
		else if("initialize_settled".equals(action)){
			response.setCharacterEncoding("utf-8");
			//获取供应商名称
			List<String> supplierList = FinanceService.getInstance().getSupplier(); 
			//获取时间段
			List<String> dateList = FinanceService.getInstance().getDateForSupSettled();
			JsonArray jsonArray = new JsonArray( );
			JsonArray jsonArray2 = new JsonArray( );
			//json数据记录供应商名称
			for(int i = 0;i<supplierList.size();i++){
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("supplierName", supplierList.get(i));
				jsonArray.add(jsonObject);
			}
			//json数组记录时间段
			for(int i = 0;i<dateList.size();i++){
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("date", dateList.get(i));
				jsonArray2.add(jsonObject);
			}
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("supplier", jsonArray);//记录供应商
			jsonObject.add("date", jsonArray2);//记录时间段
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject);
			pw.flush();
			pw.close();
		}
	}

}
