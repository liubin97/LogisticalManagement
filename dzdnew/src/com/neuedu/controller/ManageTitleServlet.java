package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.OneTitle;
import com.neuedu.model.po.TwoTitle;
import com.neuedu.model.service.ManageTitleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ManageTitleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ManageTitleServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入分类servlet");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if("addone".equals(action)) {
			doNewOneTitle(request,response);//添加一级分类
		}else if("addtwo".equals(action)) {
			doNewTwoTitle(request,response);//添加二级分类
		}else if("oneTitleList".equals(action)) {
			selectOneTitle(request,response);//列出一级分类
		}else if("twoTitleList".equals(action)){//列出二级分类
			selectTwoTitle(request,response);
		}else if("selectTitle".equals(action)) {
			doSearchTitle(request,response);//查询分类
		}else if("editOneTitle".equals(action)) {//查询要修改的一级分类并跳转页面
			editOneTitle(request,response);
		}else if("editTwoTitle".equals(action)) {//查询要修改的二级分类并跳转页面
			editTwoTitle(request,response);
		}else if("updateTwoTitle".equals(action)) {//更新二级标题
			updateTwoTitle(request,response);
		}else if("updateOneTitle".equals(action)) {//更新一级标题
			updateOneTitle(request,response);
		}else if("validateOneTitle".equals(action)) {//验证一级标题是否重名
			validateOneTitle(request,response);
		}else if("validateTwoTitle".equals(action)) {//验证二级标题是否重名
			validateTwoTitle(request,response);
		}else if("deleteOneTitle".equals(action)) {//删除一级标题
			deleteOneTitle(request,response);
		}else if("deleteTwoTitle".equals(action)) {//删除二级标题
			deleteTwoTitle(request,response);
		}
	}
	private void selectTwoTitle(HttpServletRequest request, HttpServletResponse response) {
		
				System.out.println("列出二级分类");
				int onetitle_id = Integer.parseInt(request.getParameter("oneTitle_id"));//获取要列出的二级分类的一级分类ID
				List<TwoTitle> list = ManageTitleService.getInstance().selectTwoTitle(onetitle_id);	//获取要列出的二级分类			
				JSONArray twotitles = new JSONArray();//创建JSON数组
				for(TwoTitle twotitle : list ) {//将二级分类ID和NAME放入JSON，在存入数组中
					
					JSONObject jo = new JSONObject();
					jo.put("twotitle_name", twotitle.getTwotitle_name());
					jo.put("twotitle_id", twotitle.getTwotitle_id());
					twotitles.add(jo);
				}
				
				PrintWriter pw;
				response.setContentType("text/html;charset=utf-8");//设置编码格式
				try {
					pw = response.getWriter();
					pw.write(twotitles.toString());//将JSON数组返回AJAX
					pw.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
	}

	private void deleteTwoTitle(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("删除二级分类");
		int twotitle_id = Integer.parseInt(request.getParameter("twotitle_id"));//获取要删除的二级分类的ID
		ManageTitleService.getInstance().deleteTwoTitle(twotitle_id);//删除二级分类
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");//获取当前查询页数
		try {
			response.sendRedirect(request.getContextPath()+"/manageTitleServlet?pageNum="+pageNum+"&action=selectTitle");//跳转继续查询当前页
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private void deleteOneTitle(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("删除一级分类");
		int onetitle_id = Integer.parseInt(request.getParameter("onetitle_id"));//获取要删除的一级分类的ID
		ManageTitleService.getInstance().deleteOneTitle(onetitle_id);//删除一级分类
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");//获取当前查询页数
		try {
			response.sendRedirect(request.getContextPath()+"/manageTitleServlet?pageNum="+pageNum+"&action=selectTitle");//跳转继续查询当前页
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private void validateTwoTitle(HttpServletRequest request, HttpServletResponse response) {
		
		String flag = "";//设置要返回的flag	
		PrintWriter pw;
		System.out.println("校验二级分类重名");
		String twotitle_name = request.getParameter("twoTitle_name");//获取要校验的二级分类名称
		List<TwoTitle> list = ManageTitleService.getInstance().selectTwoTitle(twotitle_name);//根据该名称查询LIST
		if(list!=null && !list.isEmpty()) {
			flag = "false";//list不为空说明重名，返回false
		}else {
			flag = "true";
		}
		response.setContentType("text/html;charset=utf-8");
		
		try {
			pw = response.getWriter();
			pw.print(flag);//返回flag
			pw.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private void validateOneTitle(HttpServletRequest request, HttpServletResponse response) {
		
		String flag = "";
		PrintWriter pw;
		System.out.println("校验一级标题重名֤");
		String onetitle_name = request.getParameter("oneTitle_name");//获取要校验的一级分类名称
		List<OneTitle> list = ManageTitleService.getInstance().selectOneTitle(onetitle_name);//根据该名称去查询，返回list
		if(list!=null && !list.isEmpty()) {
			flag = "false";//若list不为空则说明重名，返回false
		}else {
			flag = "true";
		}
		response.setContentType("text/html;charset=utf-8");
		
		try {
			pw = response.getWriter();
			pw.print(flag);//向AJAX返回flag
			pw.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	private void updateOneTitle(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("更新一级分类");
		Date day=new Date();//获取当前系统时间
		String onetitle_name = request.getParameter("onetitle_name");//获取一级分类名称
		String onetitle_describe = request.getParameter("onetitle_describe");//获取一级分类描述
		int onetitle_id = Integer.parseInt(request.getParameter("onetitle_id"));//获取一级分类ID
		OneTitle oneTitle = new OneTitle();//封装一级分类
		oneTitle.setOnetitle_id(onetitle_id);
		oneTitle.setOnetitle_name(onetitle_name);
		oneTitle.setOnetitle_describe(onetitle_describe);
		oneTitle.setStatus(0);//默认为0
		oneTitle.setOperator((String)request.getSession().getAttribute("admin"));//获取当前登陆的用户
		oneTitle.setOperatoedate(day);
		ManageTitleService.getInstance().updateOneTitle(oneTitle);//更新一级分类
		try {
			response.sendRedirect(request.getContextPath()+"/manageTitle.jsp");//跳转到管理分类界面
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private void updateTwoTitle(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("更新二级分类");
		Date day=new Date();//获取当前系统时间 
		String twotitle_name = request.getParameter("twotitle_name");//获取二级分类名称
		String twotitle_describe = request.getParameter("twotitle_describe");//获取二级分类描述
		int onetitle_id = Integer.parseInt(request.getParameter("oneTitleList"));//获取更新的一级分类ID
		int twotitle_id = Integer.parseInt(request.getParameter("twotitle_id"));//获取二级分类ID
		TwoTitle twoTitle = new TwoTitle();//封装
		twoTitle.setTwotitle_name(twotitle_name);
		twoTitle.setTwotitle_describe(twotitle_describe);
		twoTitle.setTwotitle_id(twotitle_id);
		twoTitle.setOnetitle_id(onetitle_id);
		twoTitle.setStatus(0);
		twoTitle.setOperator((String)request.getSession().getAttribute("admin"));//获取当前登陆的用户
		twoTitle.setOperatoedate(day);
		ManageTitleService.getInstance().updateTwoTitle(twoTitle);//更新二级分类
		try {
			response.sendRedirect(request.getContextPath()+"/manageTitle.jsp");//跳转到分类管理界面
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private void editTwoTitle(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("跳转到编辑二级分类页面");
		int twotitle_id = Integer.parseInt(request.getParameter("twotitle_id"));//获取要编辑的二级分类ID
		List<TwoTitle> list = ManageTitleService.getInstance().selectTwoTitleById(twotitle_id);//获取该二级分类
		request.setAttribute("resultTwoTitle", list);//返回list
		
		try {
			request.getRequestDispatcher("modifyTitle.jsp").forward(request, response);//跳转到二级分类编辑界面
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private void editOneTitle(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("跳转到一级分类修改界面");
		int onetitle_id = Integer.parseInt(request.getParameter("onetitle_id"));//获取要编辑的一级分类ID
		List<OneTitle> list = ManageTitleService.getInstance().selectOneTitleById(onetitle_id);//查询该一级分类
		request.setAttribute("resultOneTitle", list);//返回该一级分类
		
		try {
			request.getRequestDispatcher("modifyOneTitle.jsp").forward(request, response);//跳转到一级分类编辑界面
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doSearchTitle(HttpServletRequest request, HttpServletResponse response) {
		String pagenum = request.getParameter("pageNum");//获取当前页码
		String selectType = "";//查询的类型（一级还是二级）
		String onetitle_name = "";
		String twotitle_name = "";
		int pageSize = 5;//默认单页最多显示5条
		int pageNum = 1;//默认为第一页
		if(pagenum != null && !"".equals(pagenum)) {
			pageNum = Integer.parseInt(pagenum);//页码不为空，说明是点页码查询的
			onetitle_name = (String) request.getSession().getAttribute("selectText");//从Session中获取查询内容
			twotitle_name = (String) request.getSession().getAttribute("selectText");//从Session中获取查询内容
			selectType = (String) request.getSession().getAttribute("selectType");//从Session中获取查询类型
		}else {
			//页码为空。说明是点查询查询的
			selectType = request.getParameter("selectType");//从request中获取相关信息
			onetitle_name = request.getParameter("selectText");
			twotitle_name = request.getParameter("selectText");
		}
		if("selectOneTitle".equals(selectType)) {//查询一级分类
			List<String> isOneDelete = new ArrayList<String>();//是否可以删除该一级分类的数组
			List<OneTitle> list = ManageTitleService.getInstance().selectOneTitle(onetitle_name,pageNum,pageSize);//查询某页某名称的一级分类
			int pagecount =  ManageTitleService.getInstance().selectOnePage(onetitle_name,pageSize);//查询总页数
			int size = list.size();
			for(int i = 0; i<size ; i++) {
				String isdelete = ManageTitleService.getInstance().oneTitleIsDelete(list.get(i).getOnetitle_id());//根据查询回来的list，遍历判断这些一级分类下是否有下属分类
				isOneDelete.add(isdelete);//添加到数组中
			}
			request.setAttribute("isOneDelete", isOneDelete);//返回是否可以删除数组
			request.setAttribute("resultList", list);//返回查询的一节分类LIST
			request.setAttribute("pagecount", pagecount);//返回查询的总页数
			request.getSession().setAttribute("pageNum", pageNum);//将当前页数放在Session中
			request.getSession().setAttribute("selectText", onetitle_name);//将查询内容放在Session中
			request.getSession().setAttribute("selectType", selectType);//将查询类型放在Session中
			try {
				request.getRequestDispatcher("manageTitle.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if("selectTwoTitle".equals(selectType)){//查询二级分类
			List<String> oneNameList = new ArrayList<String>();//二级分类对应的一级分类的名称
			List<String> isTwoDelete = new ArrayList<String>();//二级分类对应的是否可以删除的数组
			List<TwoTitle> list = ManageTitleService.getInstance().selectTwoTitle(twotitle_name,pageNum,pageSize);//查询某名某页的二级分类
			int pagecount =  ManageTitleService.getInstance().selectTwoPage(twotitle_name,pageSize);//查询总页数
			int size = list.size();
			for(int i = 0; i<size ; i++) {
				String oneTitle_name = ManageTitleService.getInstance().selectOneNameById(list.get(i).getOnetitle_id());//根据返回的list查询对应的一级分类名称
				oneNameList.add(oneTitle_name);
				String isdelete = ManageTitleService.getInstance().twoTitleIsDelete(list.get(i).getTwotitle_id());//根据返回的list查询对应的二级分类是否有下属商品
				isTwoDelete.add(isdelete);
			}
			request.setAttribute("isTwoDelete", isTwoDelete);//返回是否可删除的数组
			request.setAttribute("oneNameList", oneNameList);//返回一级分类名称数组
			request.setAttribute("resultList", list);//返回查询出来的二级分类数组
			request.setAttribute("pagecount", pagecount);//返回总页数
			request.getSession().setAttribute("pageNum", pageNum);//将当前页数放在Session中
			request.getSession().setAttribute("selectText", twotitle_name);//将查询内容放在Session中
			request.getSession().setAttribute("selectType", selectType);//将查询类型放在Session中
			
			try {
				request.getRequestDispatcher("manageTitle.jsp").forward(request, response);//返回管理分类界面
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void selectOneTitle(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("列出一级分类");
		String onetitle_name = request.getParameter("oneTitle_name");
		List<OneTitle> list = ManageTitleService.getInstance().selectOneTitle(onetitle_name);
		JSONArray onetitles = new JSONArray();//创建JSON数组
		for(OneTitle onetitle : list ) {//根据list添加JSON到数组中
			JSONObject jo = new JSONObject();
			jo.put("onetitle_name", onetitle.getOnetitle_name());
			jo.put("onetitle_id", onetitle.getOnetitle_id());
			onetitles.add(jo);
		}
		
		PrintWriter pw;
		response.setContentType("text/html;charset=utf-8");
		try {
			pw = response.getWriter();
			pw.write(onetitles.toString());//返回该数组
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	

	private void doNewTwoTitle(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("添加二级分类");
		Date day=new Date();//获取系统当前时间
		String twotitle_name = request.getParameter("twotitle_name");//获取二级分类名称
		String twotitle_describe = request.getParameter("twotitle_describe");//获取二级分类描述
		int onetitle_id = Integer.parseInt(request.getParameter("oneTitleList"));//获取二级分类ID
		TwoTitle twoTitle = new TwoTitle();//封装
		twoTitle.setTwotitle_name(twotitle_name);
		twoTitle.setTwotitle_describe(twotitle_describe);
		twoTitle.setOnetitle_id(onetitle_id);
		twoTitle.setStatus(0);//默认为0
		twoTitle.setOperator((String)request.getSession().getAttribute("admin"));//获取当前用户
		twoTitle.setOperatoedate(day);
		ManageTitleService.getInstance().newTwoTitle(twoTitle);//添加二级分类
		try {
			response.sendRedirect(request.getContextPath()+"/manageTitle.jsp");//跳转到管理分类界面
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doNewOneTitle(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("添加一级分类");
		Date day=new Date();    //获取系统当前时间
		String onetitle_name = request.getParameter("oneTitle_name");//获取一级分类名称
		String onetitle_describe = request.getParameter("oneTitle_describe");//获取一级分类描述
		OneTitle oneTitle = new OneTitle();//封装
		oneTitle.setOnetitle_name(onetitle_name);
		oneTitle.setOnetitle_describe(onetitle_describe);
		oneTitle.setStatus(0);
		oneTitle.setOperator((String)request.getSession().getAttribute("admin"));//获取当前用户
		oneTitle.setOperatoedate(day);
		ManageTitleService.getInstance().newOneTitle(oneTitle);//添加一级分类
		try {
			response.sendRedirect(request.getContextPath()+"/manageTitle.jsp");//跳转到管理分类界面
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
