package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.neuedu.model.po.Product;
import com.neuedu.model.service.ManageProductService;
import com.neuedu.model.service.ManageTitleService;

import net.sf.json.JSONArray;


public class ManageProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ManageProductServlet() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入管理商品Servlet");
		request.setCharacterEncoding("utf-8");	
		String action = request.getParameter("action");
		if("addProduct".equals(action)) {//添加商品
			addProduct(request,response);
		}else if("searchProduct".equals(action)) {//查询商品
			searchProduct(request,response);
		}else if("productDetail".equals(action)) {//商品详细
			productDetail(request,response);
		}else if("supplierList".equals(action)) {//列出供应商
			supplierList(request,response);
		}else if("deleteProduct".equals(action)) {//删除商品
			deleteProduct(request,response);
		}else if("modifyProduct".equals(action)) {//查询要修改的商品并跳转到修改界面
			modifyProduct(request,response);
		}else if("updateProduct".equals(action)) {//更新商品
			updateProduct(request,response);
		}else if("validateProduct".equals(action)) {//校验商品是否重名
			validateProduct(request,response);
		}
	}

	

	private void validateProduct(HttpServletRequest request, HttpServletResponse response) {//校验商品是否重名
		String product_name = request.getParameter("product_name");//获取商品名
		String flag = "";
		PrintWriter pw;
		System.out.println("进入校验商品是否重名");
		List<Product> list = ManageProductService.getInstance().selectProductByName(product_name);//根据该商品名查询
		if(list!=null && !list.isEmpty()) {
			flag = "false";//若list不为空则说明重名，返回false
		}else {
			flag = "true";
		}
		response.setContentType("text/html;charset=utf-8");		
		try {
			pw = response.getWriter();
			pw.print(flag);//返回flag到AJAX
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) {//更新商品
		Date quality_period = null; //默认保质期为空
		int product_id = Integer.parseInt(request.getParameter("product_id"));//获取商品ID
		String product_name = request.getParameter("product_name");//获取商品名
		int twotitle_id = Integer.parseInt( request.getParameter("twotitle_id"));//获取二级分类ID
		String measurement = request.getParameter("measurement");//获取单位
		String type = request.getParameter("type");//获取类型
		String vendor = request.getParameter("vendor");//获取厂商
		String notes = request.getParameter("notes");//获取备注
		String operator = (String) request.getSession().getAttribute("admin");//获取当前用户名
		float original_price = Float.parseFloat(request.getParameter("original_price"));//获取原价
		float cost_price = Float.parseFloat(request.getParameter("cost_price"));//获取成本价
		float discount = Float.parseFloat(request.getParameter("discount"));//获取折扣
		int supplier_id = Integer.parseInt(request.getParameter("supplier_id"));//获取供应商ID
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String qualityPeriod = request.getParameter("quality_period");//获取保质期（String）
		if(qualityPeriod!=null&&!"".equals(qualityPeriod)) {
			try {
				 quality_period = new java.sql.Date(sdf.parse(qualityPeriod).getTime());//若保质期不为空，就将其转化为sql类型DATE
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int is_return = Integer.parseInt(request.getParameter("is_return"));//获取是否可以退货
		int is_exchange = Integer.parseInt(request.getParameter("is_exchange"));//获取是否可以换货
		int status = 0;//默认为0
		Date operatordate = new Date(new java.util.Date().getTime());//将系统时间util类型转化为sql类型
		Product product = new Product();//封装
		product.setProduct_name(product_name);
		product.setCost_price(cost_price);
		product.setDiscount(discount);
		product.setIs_exchange(is_exchange);
		product.setIs_return(is_return);
		product.setMeasurement(measurement);
		product.setNotes(notes);
		product.setOperator(operator);
		product.setOperatordate(operatordate);
		product.setOriginal_price(original_price);
		product.setQuality_period(quality_period);
		product.setStatus(status);
		product.setSupplier_id(supplier_id);
		product.setTwotitle_id(twotitle_id);
		product.setType(type);
		product.setVendor(vendor);
		product.setProduct_id(product_id);
		
		ManageProductService.getInstance().updateProduct(product);//更新商品
		try {
			response.sendRedirect(request.getContextPath()+"/manageProduct.jsp");//跳转到管理商品页面
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void modifyProduct(HttpServletRequest request, HttpServletResponse response) {//查询修改商品信息并跳转到修改商品界面
		int product_id = Integer.parseInt(request.getParameter("product_id"));//获取要修改商品的ID
		Product product = ManageProductService.getInstance().selectProductById(product_id);//查询该商品
		int onetitle_id = ManageTitleService.getInstance().selectOneIdByTwoId(product.getTwotitle_id());//查询该商品的一级分类ID
		request.setAttribute("resultProduct", product);//返回该商品信息
		request.setAttribute("onetitle_id", onetitle_id);//返回该商品的一级分类ID
		try {
			request.getRequestDispatcher("modifyProduct.jsp").forward(request, response);//跳转到修改商品界面
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {//删除商品
		int product_id = Integer.parseInt(request.getParameter("product_id"));//获取要删除商品的ID
		ManageProductService.getInstance().deleteProduct(product_id);//删除商品
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");//获取删除商品的那一页页码
		try {
			response.sendRedirect(request.getContextPath()+"/manageProductServlet?pageNum="+pageNum+"&action=searchProduct");//返回重新查询该页
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void supplierList(HttpServletRequest request, HttpServletResponse response) {// 列出供应商
		JSONArray supplierlist  = ManageProductService.getInstance().supplierList();//查询出所有供应商，返回ID和NAME的JSON数组
		PrintWriter pw;
		response.setContentType("text/html;charset=utf-8");
		try {
			pw = response.getWriter();
			pw.write(supplierlist.toString());//返回JSON数组到AJAX
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void productDetail(HttpServletRequest request, HttpServletResponse response) {// 商品详细
		int product_id = Integer.parseInt(request.getParameter("product_id"));//获取要详细显示的商品ID
		Product product = ManageProductService.getInstance().selectProductById(product_id);//查询该商品
		String twotitlename = ManageTitleService.getInstance().selectTwoNameById(product.getTwotitle_id());//查询该商品对应的二级分类名称
		String onetitlename = ManageTitleService.getInstance().selectOneNameByTwoId(product.getTwotitle_id());//查询该商品对应的一级分类名称
		String suppliername = ManageProductService.getInstance().selectSupplierNameById(product.getSupplier_id());//查询该商品对应的供应商名称
		request.setAttribute("resultProduct", product);//返回数据
		request.setAttribute("twotitlename", twotitlename);
		request.setAttribute("onetitlename", onetitlename);
		request.setAttribute("suppliername", suppliername);
		try {
			request.getRequestDispatcher("productDetail.jsp").forward(request, response);//跳转到商品详细界面
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void searchProduct(HttpServletRequest request, HttpServletResponse response) {//查询商品
		String pagenum = request.getParameter("pageNum");//获取页码
		String selectType = "";//获取查询类型
		String onetitle_name = "";
		String twotitle_name = "";
		String product_name = "";
		int product_id = 0;//默认商品ID为0
		String productId = "";
		int pageSize = 5;//默认单页最多显示5条
		int pageNum = 1;//默认第一页
		if(pagenum != null && !"".equals(pagenum)) {
			pageNum = Integer.parseInt(pagenum);//点击页码查询
			onetitle_name = (String) request.getSession().getAttribute("selectText");//从Session中获取数据
			twotitle_name = (String) request.getSession().getAttribute("selectText");
			if("productCode".equals(selectType)) {
				productId = (String) request.getSession().getAttribute("selectText");
				if(productId!=null&&!"".equals(productId)) {//如果输入的ID不为空
					product_id = Integer.parseInt(productId);
				}//否则默认为0
			}
			product_name = (String) request.getSession().getAttribute("selectText");
			selectType = (String) request.getSession().getAttribute("selectType");
		}else {
			//点击查询框查询的
			selectType = request.getParameter("selectType");//从request中获取数据
			onetitle_name = request.getParameter("selectText");
			twotitle_name = request.getParameter("selectText");
			if("productCode".equals(selectType)) {
				productId =  request.getParameter("selectText");
				if(productId!=null&&!"".equals(productId)) {
					product_id = Integer.parseInt(productId);
				}
			}
			product_name = request.getParameter("selectText");
		}
		
		if("oneTitle".equals(selectType)) {//根据一级分类查询
			List<String> oneTitleName = new ArrayList<String>();//商品对应一级分类名称数组
			List<String> twoTitleName = new ArrayList<String>();//商品对应二级分类名称数组
			List<Product> list = ManageProductService.getInstance().selectProductByOne(onetitle_name,pageNum,pageSize);//根据一级分类查询商品
			int pagecount = ManageProductService.getInstance().selectProductByOneCount(onetitle_name,pageSize);//查询总页数
			int size = list.size();
			for(int i = 0; i<size ; i++) {
				String twotitlename = ManageTitleService.getInstance().selectTwoNameById(list.get(i).getTwotitle_id());//根据list查询商品对应的二级分类名称
				String onetitlename = ManageTitleService.getInstance().selectOneNameByTwoId(list.get(i).getTwotitle_id());//根据list查询商品对应的一级分类名称
				twoTitleName.add(twotitlename);
				oneTitleName.add(onetitlename);
			}
			request.setAttribute("resultList", list);//返回list
			request.setAttribute("pagecount", pagecount);//返回总页数
			request.setAttribute("twoTitleName", twoTitleName);//返回商品对应二级分类名称数组
			request.setAttribute("oneTitleName", oneTitleName);//返回商品对应一级分类名称数组
			request.getSession().setAttribute("selectType", selectType);//将查询类型放在Session中
			request.getSession().setAttribute("selectText", product_name);//将查询内容放在Session中
			request.getSession().setAttribute("pageNum", pageNum);//将当前页码放在Session中
			try {
				request.getRequestDispatcher("manageProduct.jsp").forward(request, response);//跳转到管理商品页面
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if("twoTitle".equals(selectType)) {//根据二级分类查询商品
			List<String> oneTitleName = new ArrayList<String>();//商品对应一级分类名称数组
			List<String> twoTitleName = new ArrayList<String>();//商品对应二级分类名称数组
			List<Product> list = ManageProductService.getInstance().selectProductByTwo(twotitle_name,pageNum,pageSize);
			int pagecount = ManageProductService.getInstance().selectProductByTwoCount(twotitle_name,pageSize);
			int size = list.size();
			for(int i = 0; i<size ; i++) {
				String twotitlename = ManageTitleService.getInstance().selectTwoNameById(list.get(i).getTwotitle_id());
				String onetitlename = ManageTitleService.getInstance().selectOneNameByTwoId(list.get(i).getTwotitle_id());
				twoTitleName.add(twotitlename);
				oneTitleName.add(onetitlename);
			}
			request.setAttribute("resultList", list);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("twoTitleName", twoTitleName);
			request.setAttribute("oneTitleName", oneTitleName);
			request.getSession().setAttribute("selectType", selectType);
			request.getSession().setAttribute("selectText", product_name);
			request.getSession().setAttribute("pageNum", pageNum);
			try {
				request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if("productName".equals(selectType)) {//根据商品名查询
			List<String> twoTitleName = new ArrayList<String>();
			List<String> oneTitleName = new ArrayList<String>();
			List<Product> list = ManageProductService.getInstance().selectProductByName(product_name,pageNum,pageSize);
			int pagecount = ManageProductService.getInstance().selectProductByNameCount(product_name,pageSize);
			int size = list.size();
			for(int i = 0; i<size ; i++) {
				String twotitlename = ManageTitleService.getInstance().selectTwoNameById(list.get(i).getTwotitle_id());
				String onetitlename = ManageTitleService.getInstance().selectOneNameByTwoId(list.get(i).getTwotitle_id());
				twoTitleName.add(twotitlename);
				oneTitleName.add(onetitlename);
			}
			request.setAttribute("resultList", list);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("twoTitleName", twoTitleName);
			request.setAttribute("oneTitleName", oneTitleName);
			request.getSession().setAttribute("selectType", selectType);
			request.getSession().setAttribute("selectText", product_name);
			request.getSession().setAttribute("pageNum", pageNum);
			try {
				request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if("productCode".equals(selectType)) {//根据商品ID查询
			List<String> twoTitleName = new ArrayList<String>();
			List<String> oneTitleName = new ArrayList<String>();
			List<Product> list = ManageProductService.getInstance().selectProductById(product_id,pageNum,pageSize);
			int pagecount = ManageProductService.getInstance().selectProductByIdCount(product_id,pageSize);
			int size = list.size();
			for(int i = 0; i<size ; i++) {
				String twotitlename = ManageTitleService.getInstance().selectTwoNameById(list.get(i).getTwotitle_id());
				String onetitlename = ManageTitleService.getInstance().selectOneNameByTwoId(list.get(i).getTwotitle_id());
				twoTitleName.add(twotitlename);
				oneTitleName.add(onetitlename);
			}
			request.setAttribute("twoTitleName", twoTitleName);
			request.setAttribute("oneTitleName", oneTitleName);
			request.setAttribute("resultList", list);
			request.setAttribute("pagecount", pagecount);
			request.getSession().setAttribute("selectType", selectType);
			request.getSession().setAttribute("selectText", product_name);
			request.getSession().setAttribute("pageNum", pageNum);
			try {
				request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response) {//添加商品
		Date quality_period = null;//默认保质期为空
		String product_name = request.getParameter("product_name");
		int twotitle_id = Integer.parseInt( request.getParameter("twotitle_id"));
		String measurement = request.getParameter("measurement");
		String type = request.getParameter("type");
		String vendor = request.getParameter("vendor");
		String notes = request.getParameter("notes");
		String operator = (String) request.getSession().getAttribute("admin");
		float original_price = Float.parseFloat(request.getParameter("original_price"));
		float cost_price = Float.parseFloat(request.getParameter("cost_price"));
		float discount = Float.parseFloat(request.getParameter("discount"));
		int supplier_id = Integer.parseInt(request.getParameter("supplier_id"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String qualityPeriod = request.getParameter("quality_period");
		if(qualityPeriod!=null&&!"".equals(qualityPeriod)) {
			try {
				 quality_period = new java.sql.Date(sdf.parse(qualityPeriod).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int is_return = Integer.parseInt(request.getParameter("is_return"));
		int is_exchange = Integer.parseInt(request.getParameter("is_exchange"));
		int status = 0;
		Date operatordate = new Date(new java.util.Date().getTime());
		Product product = new Product();
		product.setProduct_name(product_name);
		product.setCost_price(cost_price);
		product.setDiscount(discount);
		product.setIs_exchange(is_exchange);
		product.setIs_return(is_return);
		product.setMeasurement(measurement);
		product.setNotes(notes);
		product.setOperator(operator);
		product.setOperatordate(operatordate);
		product.setOriginal_price(original_price);
		product.setQuality_period(quality_period);
		product.setStatus(status);
		product.setSupplier_id(supplier_id);
		product.setTwotitle_id(twotitle_id);
		product.setType(type);
		product.setVendor(vendor);
		ManageProductService.getInstance().addProduct(product);
		try {
			response.sendRedirect(request.getContextPath()+"/manageProduct.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
