package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.PurchaseMsg;
import com.neuedu.model.po.PurchaseSupplier;
import com.neuedu.model.po.ReturnSupplier;
import com.neuedu.model.po.Warehouse;
import com.neuedu.model.service.*;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class PurchaseReturnServlet
 */
public class PurchaseReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseReturnServlet() {
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
		if("searchwarn".equals(action)){
			doSearchWarn(request, response);
		}else if("searchstock".equals(action)){
			doSearchStock(request,response);
		}else if("newpswarn".equals(action)){
			doNewPSwarn(request,response);
		}else if("newpsstock".equals(action)){
			doNewPSstock(request,response);		
		}else if("acps".equals(action)){
			acPs(request, response);
		}else if("acps1".equals(action)){
			acPs1(request, response);
		}else if("searchreturn".equals(action)){
			searchReturn(request, response);
		}else if("returnsupplier".equals(action)){
			returnSupplier(request, response);
		}
		
		
	}

	private void returnSupplier(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO 自动生成的方法存根
		String supplier_id=request.getParameter("supplier_id");
		String prod_id=request.getParameter("prod_id");
		String product_name=request.getParameter("product_name");
		String rs_num=request.getParameter("rs_num");
		String price=request.getParameter("price");
		String criid=request.getParameter("criid");
		Date   now   =   new   Date();    
		SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy-MM-dd ");//可以方便地修改日期格式    
		String   date   =   dateFormat.format(   now   );  

		ReturnSupplier rsr=new ReturnSupplier();
		rsr.setCriid(Integer.parseInt(criid));
		rsr.setDate(date);
		rsr.setPrice(Float.parseFloat(price));
		rsr.setProd_id(Integer.parseInt(prod_id));
		rsr.setProduct_name(product_name);
		rsr.setRs_num(Integer.parseInt(rs_num));
		rsr.setSupplier_id(Integer.parseInt(supplier_id));
		
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		PRService.getInstance().returnSupplier(rsr);
		JSONObject json = new JSONObject();
		json.put("msg","成功");

			pw.print(json);
			pw.close();
		
	}

	private List<ReturnSupplier> searchReturn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		
		String prod_id=null;
		String starttime=null;
		String endtime=null;
		String supplier_id=null;
		
		
	
			prod_id=request.getParameter("prod_id");
			starttime=request.getParameter("starttime");
			endtime=request.getParameter("endtime");
			supplier_id=request.getParameter("supplier_id");
		
			
	
		List<ReturnSupplier> list = PRService.getInstance().searchReturn(Integer.parseInt(supplier_id), starttime, endtime,Integer.parseInt( prod_id));
	

		request.setAttribute("resultList", list);
		request.getRequestDispatcher("/return.jsp").forward(request, response);
		return list;
		
	}

	private void doSearchWarn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根

		String pagenum = request.getParameter("pageno");
		int pageNum = 1;
		String proname ="";
		if(pagenum != null && !"".equals(pagenum)){
		
			pageNum = Integer.parseInt(pagenum);
			proname = (String) request.getSession().getAttribute("proname1");
			System.out.println(proname);
		}else{
			proname=(String) request.getParameter("proname1");
			System.out.println(proname);
			
		}
	
		List<PurchaseMsg> list = PRService.getInstance().searchWarn(proname, pageNum);
	
		int pagecount =  PRService.getInstance().calPagecountWarn(proname);

		request.setAttribute("resultList", list);
		request.setAttribute("pagecount", pagecount);
		request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("proname1", proname);
		request.getRequestDispatcher("/purchasewarn.jsp").forward(request, response);
		
	}

	private void doSearchStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		String pagenum = request.getParameter("pageno");
		int pageNum = 1;
		String proname ="";
		if(pagenum != null && !"".equals(pagenum)){
		
			pageNum = Integer.parseInt(pagenum);
			proname = (String) request.getSession().getAttribute("proname1");
			System.out.println(proname);
		}else{
			proname=(String) request.getParameter("proname1");
			System.out.println(proname);
			
		}
	
		List<PurchaseMsg> list = PRService.getInstance().searchOutofstock(proname, pageNum);
	
		int pagecount =  PRService.getInstance().calPagecountOutofstock(proname);

		request.setAttribute("resultList", list);
		request.setAttribute("pagecount", pagecount);
		request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("proname1", proname);
		request.getRequestDispatcher("/purchasestock.jsp").forward(request, response);
		
	}

	private void doNewPSwarn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO 自动生成的方法存根
		
		String prod_id=request.getParameter("prod_id");
		String pnum=request.getParameter("pnum");
		String do_date=request.getParameter("do_date");
		String sum_price=request.getParameter("sum_price");
		String price=request.getParameter("price");
		String sup_id=request.getParameter("sup_id");
		System.out.println(sum_price);
		PurchaseSupplier psr=new PurchaseSupplier();
		psr.setDate(do_date);
		psr.setPrice(Float.parseFloat(price));
		psr.setProd_id(Integer.parseInt(prod_id));
		psr.setPur_num(Integer.parseInt(pnum));
		psr.setSettle_status(0);
		psr.setStatus(0);
		psr.setSum_price(Float.parseFloat(sum_price));
		psr.setSup_id(Integer.parseInt(sup_id));
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			int newid=PRService.getInstance().newPSwarn(psr);

				pw.print(newid);
				pw.close();
		
	}

	private void doNewPSstock(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO 自动生成的方法存根
		String prod_id=request.getParameter("prod_id");
		String pnum=request.getParameter("pnum");
		String do_date=request.getParameter("do_date");
		String sum_price=request.getParameter("sum_price");
		String price=request.getParameter("price");
		String sup_id=request.getParameter("sup_id");
		String order_id=request.getParameter("order_id");
		System.out.println(sum_price);
		PurchaseSupplier psr=new PurchaseSupplier();
		psr.setDate(do_date);
		psr.setPrice(Float.parseFloat(price));
		psr.setProd_id(Integer.parseInt(prod_id));
		psr.setPur_num(Integer.parseInt(pnum));
		psr.setSettle_status(0);
		psr.setStatus(0);
		psr.setSum_price(Float.parseFloat(sum_price));
		psr.setSup_id(Integer.parseInt(sup_id));
		psr.setOrder_id(Integer.parseInt(order_id));
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			int newid=PRService.getInstance().newPSstock(psr);

				pw.print(newid);
				pw.close();
		
	}
	private void acPs(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String prod_id=request.getParameter("prod_id");
		String pnum=request.getParameter("pnum");
		String do_date=request.getParameter("do_date");
		System.out.println();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			PurchaseSupplier psr=PRService.getInstance().asPs(Integer.parseInt(prod_id),Integer.parseInt(pnum), do_date);
			JSONObject json = new JSONObject();
			json.put("prod_id", psr.getProd_id());
			json.put("pur_num", psr.getPur_num());
			json.put("price", psr.getPrice());
			json.put("sum_price", psr.getSum_price());
			json.put("date", psr.getDate());
			json.put("prod_name", psr.getProd_name());
			json.put("sup_id", psr.getSup_id());
				pw.print(json);
				pw.close();
		
	}
	private void acPs1(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String prod_id=request.getParameter("prod_id");
		String pnum=request.getParameter("pnum");
		String do_date=request.getParameter("do_date");
		String order_id=request.getParameter("order_id");
		System.out.println();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			PurchaseSupplier psr=PRService.getInstance().asPs1(Integer.parseInt(prod_id),Integer.parseInt(order_id),Integer.parseInt(pnum), do_date);
			JSONObject json = new JSONObject();
			json.put("prod_id", psr.getProd_id());
			json.put("pur_num", psr.getPur_num());
			json.put("price", psr.getPrice());
			json.put("sum_price", psr.getSum_price());
			json.put("date", psr.getDate());
			json.put("prod_name", psr.getProd_name());
			json.put("sup_id", psr.getSup_id());
			json.put("order_id", psr.getOrder_id());
				pw.print(json);
				pw.close();
		
	}

}
