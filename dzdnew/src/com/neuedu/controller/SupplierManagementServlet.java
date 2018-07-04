package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.neuedu.model.po.Supplier;
import com.neuedu.model.service.SupplierService;

/**
 * Servlet implementation class SupplierManagementServlet
 */
@WebServlet("/SupplierManagementServlet")
public class SupplierManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierManagementServlet() {
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
		String infoStatus = request.getParameter("infoStatus");
		
		//ע��//�ǹ���ҳ�棬�����ô���ˢ��
		if("register".equals(action)) {
			//��ʼ����װ��������
			String supplierName = "";
			String supplierAddress = "";
			String contactName = "";
			String contactPhone = "";
			String bankOfRegistration = "";
			String bankAccount = "";
			String faxNo = "";
			String zipCode = "";
			String legalPerson = "";
			String remark = "";
			//��ȡ����
			supplierName = request.getParameter("supplierNameEntry");
			supplierAddress = request.getParameter("supplierAddressEntry");
			contactName = request.getParameter("contactNameEntry");
			contactPhone = request.getParameter("contactPhoneEntry");
			bankOfRegistration = request.getParameter("bankOfRegistrationEntry");
			bankAccount = request.getParameter("bankAccountEntry");
			faxNo = request.getParameter("faxNoEntry");
			zipCode = request.getParameter("zipCodeEntry");
			legalPerson = request.getParameter("legalPersonEntry");
			remark = request.getParameter("remarkEntry");
			//��װ����
			Supplier s = new Supplier();
			s.setSupplierName(supplierName);
			s.setSupplierAddress(supplierAddress);
			s.setContactName(contactName);
			s.setContactPhone(contactPhone);
			s.setBankOfRegistration(bankOfRegistration);
			s.setBankAccount(bankAccount);
			s.setFaxNo(faxNo);
			s.setZipCode(zipCode);
			s.setLegalPerson(legalPerson);
			s.setRemark(remark);
			s.setStatus(0);
			//����ģ�Ͳ������б���
			try {
				SupplierService.getInstance().register(s);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//���ع������
			response.sendRedirect("searchSupplier.jsp");//��ӹ���ҳ��url
		}
		//��ѯ
		if("query".equals(action)) {
			String pagenum = request.getParameter("pageNum");
			int pageNum = 1;
			String supplierName = "";
			String supplierAddress = "";
			String contactName = "";
			String contactPhone = "";
			String bankOfRegistration = "";
			String bankAccount = "";
			String faxNo = "";
			String zipCode = "";
			String legalPerson = "";
			String status = "";
			
			int pageSize = 5;
			
			
			if(pagenum != null && !"".equals(pagenum)){
				//���ҳ����в�ѯ
				pageNum = Integer.parseInt(pagenum);
				supplierName = (String) request.getSession().getAttribute("supplierNameQuery");
				supplierAddress = (String) request.getSession().getAttribute("supplierAddressQuery");
				contactName = (String) request.getSession().getAttribute("contactNameQuery");
				contactPhone = (String) request.getSession().getAttribute("contactPhoneQuery");
				bankOfRegistration = (String) request.getSession().getAttribute("bankOfRegistrationQuery");
				bankAccount = (String) request.getSession().getAttribute("bankAccountQuery");
				faxNo = (String) request.getSession().getAttribute("faxNoQuery");
				zipCode = (String) request.getSession().getAttribute("zipCodeQuery");
				legalPerson = (String) request.getSession().getAttribute("legalPersonQuery");
				status = (String) request.getSession().getAttribute("statusQuery");
				
					if(request.getSession().getAttribute("pageSizeSelector")!=null && !"".equals(request.getSession().getAttribute("pageSizeSelector"))) {
						pageSize = (int) request.getSession().getAttribute("pageSizeSelector");
					}
				
				}else{
					//�����ѯ��ť���в�ѯ
					supplierName = request.getParameter("supplierNameQuery");
					supplierAddress = request.getParameter("supplierAddressQuery");
					contactName = request.getParameter("contactNameQuery");
					contactPhone = request.getParameter("contactPhoneQuery");
					bankOfRegistration = request.getParameter("bankOfRegistrationQuery");
					bankAccount = request.getParameter("bankAccountQuery");
					faxNo = request.getParameter("faxNoQuery");
					zipCode = request.getParameter("zipCodeQuery");
					legalPerson = request.getParameter("legalPersonQuery");
					status = request.getParameter("statusQuery");
					
					if(request.getParameter("pageSizeSelector")!=null && !"".equals(request.getParameter("pageSizeSelector"))) {
						pageSize = Integer.parseInt(request.getParameter("pageSizeSelector"));
					}

				}
			
			
			int pageSum = SupplierService.getInstance().pageSum(supplierName, supplierAddress, contactName, contactPhone, bankOfRegistration, bankAccount, faxNo, zipCode, legalPerson, status, pageSize);
			List<Supplier> fetchedList = SupplierService.getInstance().querySupplier(supplierName, supplierAddress, contactName, contactPhone, bankOfRegistration, bankAccount, faxNo, zipCode, legalPerson, status, pageSize, pageNum);
			
			
			//�б��ѻ�ȡ�������ʾ������jspд���˴�д��λظ���ҳ��
			request.setAttribute("list", fetchedList);
			request.setAttribute("pageSum", pageSum);
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("supplierNameQuery", supplierName);
			request.getSession().setAttribute("supplierAddressQuery", supplierAddress);
			request.getSession().setAttribute("contactNameQuery", contactName);
			request.getSession().setAttribute("contactPhoneQuery", contactPhone);
			request.getSession().setAttribute("bankOfRegistrationQuery", bankOfRegistration);
			request.getSession().setAttribute("bankAccountQuery", bankAccount);
			request.getSession().setAttribute("faxNoQuery", faxNo);
			request.getSession().setAttribute("zipCodeQuery", zipCode);
			request.getSession().setAttribute("legalPersonQuery", legalPerson);
			request.getSession().setAttribute("statusQuery", status);
			
			
			request.getSession().setAttribute("pageSizeSelector", pageSize);
			
			
			
			request.getRequestDispatcher("searchSupplier.jsp").forward(request, response);//��ӹ���ҳ��url
			
		}

		
		
		
		//�޸�Ԥ��
		if("modify".equals(action)) {
			//��ȡ���޸ĵ�supplier
			String sName = request.getParameter("sNameToBeModified");//Ҫ�޸ĵ�sup��sup_name�ı�ǩ��
			Supplier s = SupplierService.getInstance().getSupplierBySupplierName(sName);
			//�ϴ�ԭ����Ϣ
			request.getSession().setAttribute("supplierNameToBeModified", s.getSupplierName());
			request.getSession().setAttribute("supplierAddressToBeModified", s.getSupplierAddress());
			request.getSession().setAttribute("contactNameToBeModified", s.getContactName());
			request.getSession().setAttribute("contactPhoneToBeModified", s.getContactPhone());
			request.getSession().setAttribute("bankOfRegistrationToBeModified", s.getBankOfRegistration());
			request.getSession().setAttribute("bankAccountModifyToBeModified", s.getBankAccount());
			request.getSession().setAttribute("faxNoToBeModified", s.getFaxNo());
			request.getSession().setAttribute("zipCodeToBeModified", s.getZipCode());
			request.getSession().setAttribute("legalPersonToBeModified", s.getLegalPerson());
			request.getSession().setAttribute("remarkToBeModified", s.getRemark());
			request.getSession().setAttribute("statusToBeModified", s.getStatus());
			
			request.getRequestDispatcher("editSupplier.jsp").forward(request, response);//��ӱ༭ҳ��url
			

		}
		
		//�����޸�
		if("saveModification".equals(action)) {
			
			String a = (String) request.getSession().getAttribute("supplierNameToBeModified");
			
			String sName = request.getParameter("sNameModify");
			String sAddress = request.getParameter("sAddressModify");
			String cName = request.getParameter("cNameModify");
			String cPhone = request.getParameter("cPhoneModify");
			String bOR = request.getParameter("bORModify");
			String bA = request.getParameter("bAModify");
			String fN = request.getParameter("fNModify");
			String zC = request.getParameter("zCModify");
			String lP = request.getParameter("lPModify");
			String remark = request.getParameter("remarkModify");
			String status = request.getParameter("statusModify");
					
			Supplier s = new Supplier();
			s.setSupplierName(sName);
			s.setSupplierAddress(sAddress);
			s.setContactName(cName);
			s.setContactPhone(cPhone);
			s.setBankOfRegistration(bOR);
			s.setBankAccount(bA);
			s.setFaxNo(fN);
			s.setZipCode(zC);
			s.setLegalPerson(lP);
			s.setRemark(remark);
			s.setStatus(Integer.parseInt(status));
			
			
			SupplierService.getInstance().modifySupplier(a, s);
			
			
			response.sendRedirect("searchSupplier.jsp");//��ӹ���ҳ��url
		}
		
		
		
		//ɾ��
		if("changeStat".equals(action) && Integer.parseInt(infoStatus)==0) {
			String sName = request.getParameter("sNameOfShifting");//Ҫɾ����sup_name�ı�ǩ��
			SupplierService.getInstance().deleteSupplier(sName);
			response.sendRedirect("searchSupplier.jsp");//��ӹ���ҳ��url
		}
		
		//�ָ�����
		if("changeStat".equals(action) && Integer.parseInt(infoStatus)==1) {
			//��ȡ���޸ĵ�supplier
			String sName = request.getParameter("sNameOfShifting");//Ҫ�޸ĵ�sup��sup_name�ı�ǩ��
			//����״̬
			SupplierService.getInstance().restoreSupplier(sName);
			response.sendRedirect("searchSupplier.jsp");//��ӹ���ҳ��url
		}
		

		//��������״̬
		if("changeMulti".equals(action)) {
			if(Integer.parseInt(infoStatus)==0) {
				String[] sNames0 = request.getParameterValues("sNameForSubmit");//Ҫɾ����checked sup_name�ı�ǩ��
				SupplierService.getInstance().deleteSupplier(sNames0);
			}
			if(Integer.parseInt(infoStatus)==1) {
				String[] sNames1 = request.getParameterValues("sNameForSubmit");//Ҫ�޸ĵ�sup��sup_name�ı�ǩ��
				//����״̬
				SupplierService.getInstance().restoreSupplier(sNames1);
			}
			response.sendRedirect("searchSupplier.jsp");//��ӹ���ҳ��url
		}
		
		
		/*
		//��ʾĬ��  ҳ��һ�򿪾ͼ���  js д  onLoad
		if("showdefault".equals(action)) {

		}
		*/
		
		
		//����
		if("checkSupplierName".equals(action)) {
			String sName = request.getParameter("supplierNameEntry");
			boolean flag = SupplierService.getInstance().validateSupplierName(sName);
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(flag);
			pw.close();
		}
		
		
		//��֤���������Ϣ��supplierName�Ϸ��ԣ�contactPhone���ʱ࣬�����˺�  �Ϸ���
		if("validateSupplierName".equals(action)) {
			//supplierName�Ϸ���
			String sName = request.getParameter("supplierNameEntry");			
			Pattern p = Pattern.compile("^(?!_)(?!.*?_$)[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$");
			Matcher m = p.matcher(sName);
			boolean isSNameFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isSNameFormal);
			pw.close();
		}
		
		
		if("validateSupplierAddress".equals(action)) {
			//supplierAddress�Ϸ���
			String sAddress = request.getParameter("supplierAddressEntry");
			Pattern p = Pattern.compile("^(?!.*?_$)(?!.*?\\s$)[a-zA-Z0-9\u4e00-\u9fa5]{1}[a-zA-Z0-9\u4e00-\u9fa5_ ]{0,38}.");
			Matcher m = p.matcher(sAddress);
			boolean isSAddressFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isSAddressFormal);
			pw.close();
		}
		
		if("validateContactName".equals(action)) {
			//contactName�Ϸ���
			String cName = request.getParameter("contactNameEntry");
			Pattern p = Pattern.compile("^[a-zA-Z0-9\u4e00-\u9fa5]{1,30}$");
			Matcher m = p.matcher(cName);
			boolean isCNameFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isCNameFormal);
			pw.close();
		}
		
		if("validateContactPhone".equals(action)) {
			//contactPhone�Ϸ���
			String cPhone = request.getParameter("contactPhoneEntry");
			Pattern p = Pattern.compile("^1[0-9]{10}$");
			Matcher m = p.matcher(cPhone);
			boolean isCNameFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isCNameFormal);
			pw.close();
		}
		
		if("validateBankOfRegistration".equals(action)) {
			//bankOfRegistration�Ϸ���
			String bOR = request.getParameter("bankOfRegistrationEntry");
			Pattern p = Pattern.compile("^[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$");
			Matcher m = p.matcher(bOR);
			boolean isBORFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isBORFormal);
			pw.close();
		}
		
		if("validateBankAccount".equals(action)) {
			//bankAccount�Ϸ���
			String bA = request.getParameter("bankAccountEntry");
			Pattern p = Pattern.compile("^[0-9]{19}$");
			Matcher m = p.matcher(bA);
			boolean isBAFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isBAFormal);
			pw.close();
		}
		
		if("validateFaxNo".equals(action)) {
			//faxNo�Ϸ���
			String fN = request.getParameter("faxNoEntry");
			Pattern p = Pattern.compile("^[0-9]{11,40}$");
			Matcher m = p.matcher(fN);
			boolean isfNFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isfNFormal);
			pw.close();
		}
		
		if("validateZipCode".equals(action)) {
			//zipCode�Ϸ���
			String zC = request.getParameter("zipCodeEntry");
			Pattern p = Pattern.compile("[1-9]\\d{5}");
			Matcher m = p.matcher(zC);
			boolean isZCFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isZCFormal);
			pw.close();
		}
		
		if("validateLegalPerson".equals(action)) {
			//legalPerson�ĺϷ���
			String lP = request.getParameter("legalPersonEntry");
			Pattern p = Pattern.compile("^[a-zA-Z0-9\\u4e00-\\u9fa5]{1,30}$");
			Matcher m = p.matcher(lP);
			boolean isLPFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isLPFormal);
			pw.close();
		}
		
		if("validateRemark".equals(action)) {
			//remark�ĺϷ���
			String r = request.getParameter("remarkEntry");
			Pattern p = Pattern.compile("^.{0,255}$");
			Matcher m = p.matcher(r);
			boolean isRFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isRFormal);
			pw.close();
		}
		
		
		
		
		
		
		//�޸Ľ���ĺϷ��Լ��
		//����
		if("checkSupplierNameModify".equals(action)) {
			String sName = request.getParameter("sNameModify");
			boolean flag = SupplierService.getInstance().validateSupplierName(sName);
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(flag);
			pw.close();
		}
		
		
		//��֤���������Ϣ��supplierName�Ϸ��ԣ�contactPhone���ʱ࣬�����˺�  �Ϸ���
		if("validateSupplierNameModify".equals(action)) {
			//supplierName�Ϸ���
			String sName = request.getParameter("sNameModify");			
			Pattern p = Pattern.compile("^(?!_)(?!.*?_$)[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$");
			Matcher m = p.matcher(sName);
			boolean isSNameFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isSNameFormal);
			pw.close();
		}
		
		
		if("validateSupplierAddressModify".equals(action)) {
			//supplierAddress�Ϸ���
			String sAddress = request.getParameter("sAddressModify");
			Pattern p = Pattern.compile("^(?!.*?_$)(?!.*?\\s$)[a-zA-Z0-9\u4e00-\u9fa5]{1}[a-zA-Z0-9\u4e00-\u9fa5_ ]{0,38}.");
			Matcher m = p.matcher(sAddress);
			boolean isSAddressFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isSAddressFormal);
			pw.close();
		}
		
		if("validateContactNameModify".equals(action)) {
			//contactName�Ϸ���
			String cName = request.getParameter("cNameModify");
			Pattern p = Pattern.compile("^[a-zA-Z0-9\u4e00-\u9fa5]{1,30}$");
			Matcher m = p.matcher(cName);
			boolean isCNameFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isCNameFormal);
			pw.close();
		}
		
		if("validateContactPhoneModify".equals(action)) {
			//contactPhone�Ϸ���
			String cPhone = request.getParameter("cPhoneModify");
			Pattern p = Pattern.compile("^1[0-9]{10}$");
			Matcher m = p.matcher(cPhone);
			boolean isCNameFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isCNameFormal);
			pw.close();
		}
		
		if("validateBankOfRegistrationModify".equals(action)) {
			//bankOfRegistration�Ϸ���
			String bOR = request.getParameter("bORModify");
			Pattern p = Pattern.compile("^[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$");
			Matcher m = p.matcher(bOR);
			boolean isBORFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isBORFormal);
			pw.close();
		}
		
		if("validateBankAccountModify".equals(action)) {
			//bankAccount�Ϸ���
			String bA = request.getParameter("bAModify");
			Pattern p = Pattern.compile("^[0-9]{19}$");
			Matcher m = p.matcher(bA);
			boolean isBAFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isBAFormal);
			pw.close();
		}
		
		if("validateFaxNoModify".equals(action)) {
			//faxNo�Ϸ���
			String fN = request.getParameter("fNModify");
			Pattern p = Pattern.compile("^[0-9]{11,40}$");
			Matcher m = p.matcher(fN);
			boolean isfNFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isfNFormal);
			pw.close();
		}
		
		if("validateZipCodeModify".equals(action)) {
			//zipCode�Ϸ���
			String zC = request.getParameter("zCModify");
			Pattern p = Pattern.compile("[1-9]\\d{5}");
			Matcher m = p.matcher(zC);
			boolean isZCFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isZCFormal);
			pw.close();
		}
		
		if("validateLegalPersonModify".equals(action)) {
			//legalPerson�ĺϷ���
			String lP = request.getParameter("lPModify");
			Pattern p = Pattern.compile("^[a-zA-Z0-9\\u4e00-\\u9fa5]{1,30}$");
			Matcher m = p.matcher(lP);
			boolean isLPFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isLPFormal);
			pw.close();
		}
		
		if("validateRemarkModify".equals(action)) {
			//remark�ĺϷ���
			String r = request.getParameter("remarkModify");
			Pattern p = Pattern.compile("^.{0,255}$");
			Matcher m = p.matcher(r);
			boolean isRFormal = m.matches();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(isRFormal);
			pw.close();
		}
		
		
	}

}
