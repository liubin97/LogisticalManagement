<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
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
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link rel="icon" type="image/png" href="assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<script src="assets/js/echarts.min.js"></script>
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
<link rel="stylesheet" href="assets/css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="assets/css/app.css">
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/invoice-inquiry.js"></script>
</head>

<body data-type="widgets">
		<%@include file="finance-leftandtop.html"%>



		<!-- 内容区域 -->
		<div class="tpl-content-wrapper">
			<div class="row-content am-cf">
				<div class="row">
					<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
						<div class="widget am-cf">
							<div class="widget-head am-cf">
								<div class="widget-title  am-cf">
									<h1>发票查询</h1>
								</div>


							</div>
							<div class="widget-body  am-fr">
								<form class="am-form am-form-inline"
									action="invoiceServlet?action=inquiry" method="post"
									role="form">

									<!--<div class="row">-->
									<!--<div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">-->
									<!--<input type="text" class="am-form-field " placeholder="请输入标题文字" >-->
									<!--<span class="am-input-group-btn">-->
									<!--<button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="submit"></button>-->
									<!--</span>-->
									<!--</div>-->

									<!--</div>-->
									
									

									<div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
										<div class="am-form-group tpl-table-list-select">
											<%--默认显示行数 --%>
											<input type="hidden" name="pageSize" value="1">
										</div>
									</div>
									<div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
										<div class="am-form-group tpl-table-list-select">
											<select data-am-selected="{btnSize: 'sm'}" name="type"
												class="inquiry" onChange="change()">
												<option value="发票号码">按发票号码查询</option>
												<option value="订单号">按订单号查询</option>
											</select>
										</div>
									</div>

									<div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
										<div class="am-form-group tpl-table-list-select">
											<select data-am-selected="{btnSize: 'sm'}" name="subName" class="substation">
												<option value="0">领用分站</option>

											</select>
										</div>
									</div>
								
									<div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
										<div
											class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
											<input type="text" class="am-form-field " name="number"
												id="inputInfor" placeholder="发票号码"> <span
												class="am-input-group-btn">
												<button
													class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search"
													type="submit"></button>
											</span>
										</div>
									</div>
								</form>
							</div>

							<div class="row">
								<div class="am-u-lg-12" style="margin-top: 50px">
									<table width="100%"
										class="am-table am-table-bordered am-table-radius am-table-striped "
										id="example-r">
										<thead>
											<tr>

												<th>发票号码</th>
												<th>发票金额</th>
												<th>登记日期</th>
												<th>领用日期</th>
												<th>丢失/作废日期</th>
												<th>领用分站名称</th>
												<th>发票状态</th>
												<th>订单号</th>

											</tr>
										</thead>
										<tbody>
											<c:forEach items="${invoiceList }" var="invoice">
												<tr>
													<td>${invoice.invoiceNumber }</td>
													<td>${invoice.amount }</td>
													<td>${invoice.registrationDate }</td>
													<td>${invoice.useDate }</td>
													<td>${invoice.lostInvalidatedDate }</td>
													<td>${invoice.substationName }</td>
													<td>${invoice.invoiceStatus }</td>
													<td>${invoice.orderNumber }</td>
												</tr>
											</c:forEach>
											
											<!-- more data -->
										</tbody>
									</table>


									<div class="am-fr">
									<ul class="am-pagination tpl-pagination">
									  	<li><a class="button" href="invoiceServlet?action=moreInfor&pageSize=${pageSize+5}" >获取更多</a></li>
										<li><a class="button" href="invoiceServlet?action=moreInfor&pageSize=${pagecount*pageSize}" >显示全部</a></li>
										 <c:if test="${pageNum-1==0}">
											<li class="am-disabled"><a href="#">«</a></li>
										</c:if>
                                        <c:if test="${pageNum-1!=0}">
											<li><a href="invoiceServlet?action=inquiry&pageNum=${pageNum-1}">«</a></li>
										</c:if>
   
                                        <c:forEach begin="1" end="${pagecount}" var="p"  >
										<c:if test="${p==pageNum}">
											<li class="am-active"><a href="#">${p}</a></li>
										</c:if>
										<c:if test="${p!=pageNum}">
											<li><a href="invoiceServlet?action=inquiry&pageNum=${p}"  >${p}</a></li>      
										</c:if>
										</c:forEach>
										
										 <c:if test="${pageNum==pagecount}">
											<li class="am-disabled"><a href="#">»</a></li>
										</c:if>
                                        <c:if test="${pageNum!=pagecount}">
											<li><a href="invoiceServlet?action=inquiry&pageNum=${pageNum+1}">»</a></li>
										</c:if>
										     
                                         <!--    <li class="am-disabled"><a href="#">«</a></li>
                                            <li class="am-active"><a href="#">1</a></li>
                                            <li><a href="#">2</a></li>
                                            <li><a href="#">3</a></li>
                                            <li><a href="#">4</a></li>
                                            <li><a href="#">5</a></li>
                                            <li><a href="#">»</a></li> -->
                                            
                                        </ul>
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
</html>