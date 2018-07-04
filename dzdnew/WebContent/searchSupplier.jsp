<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
<!DOCTYPE html>
<html lang="cn">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
	<!--font-Awesome -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" >
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <script src="assets/js/echarts.min.js"></script>
    <link rel="stylesheet" href="assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="js/searchSupplier.js" ></script>
</head>

<body data-type="widgets">
    <script src="assets/js/theme.js"></script>
    <div class="am-g tpl-g">
        <!-- 头部 -->
             <%@include file="leftandtop.html"%>

        <!-- 内容区域 -->
        <div class="tpl-content-wrapper">
            <div class="row-content am-cf">
                <div class="row">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title  am-cf"><h1>管理供应商</h1></div>


                            </div>
                            <div class="widget-body  am-fr">

                                <div class="am-u-sm-12 am-scrollable-horizontal">
                                    <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black" id="example-r">
                                        <thead>
											<th>
												<form id="queryForm" action="supplierManagementServlet?action=query" method="post">
													<td><input type=text name="supplierNameQuery" id="supplierNameQuery"></td>
													<td><input type=text name="supplierAddressQuery" id="supplierAddressQuery"></td>
													<td><input type=text name="contactNameQuery" id="contactNameQuery"></td>
													<td><input type=text name="contactPhoneQuery" id="contactPhoneQuery"></td>
													<td><input type=text name="bankOfRegistrationQuery" id="bankOfRegistrationQuery"></td>
													<td><input type=text name="bankAccountQuery" id="bankAccountQuery"></td>
													<td><input type=text name="faxNoQuery" id="faxNoQuery"></td>
													<td><input type=text name="zipCodeQuery" id="zipCodeQuery"></td>
													<td><input type=text name="legalPersonQuery" id="legalPersonQuery"></td>
													<td><input type=text name="statusQuery" id="statusQuery"></td>
													<td><input type=hidden name="pageSizeSelector" id="pageSizeSelector" ><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</p></td>
													<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<span class="am-input-group-btn" style="display: inline-block"><button type="submit"   class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search"></button></span>&nbsp&nbsp&nbsp&nbsp</td>
												</form>
												
											</th>
                                        </thead>
                                            
                                            
											<tbody>
											<tr class="gradeX">
                                            	<td><input type="checkbox" id="allcheck"></td>
                                                <td>供应商名称</td>
                                                <td>地址</td>
                                                <td>联系人</td>
												<td>联系电话</td>
												<td>开户行</td>
												<td>银行账号</td>
												<td>传真</td>
												<td>邮编</td>
												<td>法人</td>
												<td>状态【0可用，1不可用】</td>
												<td>备注</td>
												<td>操作</td>
                                            </tr>
													<c:forEach items="${list}" var="s">
														<tr class="gradeX">
															<td>
																<form class="formChange" action="supplierManagementServlet?action=changeMulti&infoStatus=${s.status}" method="POST">
																	<input type="checkbox" class="chkbox"
																	value="${s.supplierName}" name="sNameForSubmit">
																</form>
															</td>	
															<td>${s.supplierName}</td>
															<td>${s.supplierAddress}</td>
															<td>${s.contactName}</td>
															<td>${s.contactPhone}</td>
															<td>${s.bankOfRegistration}</td>
															<td>${s.bankAccount}</td>
															<td>${s.faxNo}</td>
															<td>${s.zipCode}</td>
															<td>${s.legalPerson}</td>
															<td>${s.status}</td>
															<td>${s.remark}</td>
															<td>
																	<div class="tpl-table-black-operation">
				                                                        <a href="supplierManagementServlet?sNameToBeModified=${s.supplierName}&action=modify">
				                                                            <i class="am-icon-pencil"></i> 编辑
				                                                        </a>
				                                                        <a href="supplierManagementServlet?action=changeStat&infoStatus=${s.status}&sNameOfShifting=${s.supplierName}" class="tpl-table-black-operation-del">
				                                                            <i class="am-icon-trash"></i> 更改可用性
				                                                        </a>
				                                                    </div>
															</td>
															

														</tr>
													</c:forEach>

													<!-- more data -->
												</tbody>
                                    </table>
                                </div>
                                <div class="am-u-lg-12 am-cf">

                                    <div class="am-fr">
                                        <ul class="am-pagination tpl-pagination">
                                        	
												<c:forEach begin="1" end="${pageSum}" var="p"  >
													<c:if test="${p==pageNum}">${p}</c:if>
													<c:if test="${p!=pageNum}"><a href="supplierManagementServlet?action=query&pageNum=${p}">${p}</a></c:if>
													&nbsp;&nbsp;
												</c:forEach>
											
											
											<br>
											<li><a class="button" href="###" onclick="postCheck()">批量更改状态</a></li>
                                        </ul>
                                        
                                        	<select data-am-selected="{btnSize: 'sm'}" name="pageSizeSelectorDisplay" id="pageSizeSelectorDisplay">
												<option value='' disabled selected style='display:none;'>选择每页显示条目数</option>
												<option value="5">显示5条</option>
												<option value="10">显示10条</option>
											</select>
                                    </div>
                                </div>
								 
                        
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/amazeui.datatables.min.js"></script>
    <script src="assets/js/dataTables.responsive.min.js"></script>
    <script src="assets/js/app.js"></script>

</body>

<script type="text/javascript">//上传批量修改
	function postCheck(){

	var flag = false;
	$(".chkbox").each(function(){
		if($(this).is(':checked')){
			flag = true;
		}else{
			$(this).siblings().attr("name","none");
		}
	})
	if(flag){
		//期缴请求
		$(".formChange").submit();
	}
	else{
		//提示
		alert("请至少选择一个条目更改");
	}
	
}
</script>


<script type="text/javascript">//pageSizeSelector get
	$("#pageSizeSelectorDisplay").bind("change",function(){
		var a = $("#pageSizeSelectorDisplay").val();
		$("#pageSizeSelector").val(a);
	});

</script>

<script type="text/javascript">
$("#allcheck").click(function(){
		if($("#allcheck").is(':checked')){
			$(".chkbox").prop('checked',true);
		}
		else{
			$(".chkbox").prop('checked',false);
		}
	});
	$(".chkbox").click(function(){
		var allchk = false;
		$(".chkbox").each(function(){
			if(!$(this).is(':checked')){
				allchk = true;
			}
		})
		if(allchk){
			$("#allcheck").prop('checked',false);
		}
		else{
			$("#allcheck").prop('checked',true);
		}
	});
</script>


</html>