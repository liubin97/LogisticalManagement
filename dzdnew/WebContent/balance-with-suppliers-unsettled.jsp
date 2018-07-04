<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
<script src="assets/js/balance-with-suppliers-unsettled.js"></script>

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
									<h1>与供应商结算</h1>
								</div>


							</div>
							<div class="widget-body  am-fr">
							<!-- 初始化供应商和时间段 -->

								<form class="am-form am-form-inline"
									action="suppliersServlet?action=unsettled" id="form" method="post"
									role="form">
									<%--每页显示行数 --%>
									<input type="hidden" value="1" name="pageSize">
									<div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
										<div class="am-form-group tpl-table-list-select">
											<select data-am-selected="{btnSize: 'sm'}" name="name" class="supplier">
												<option value="0">供应商</option>
											</select>
										</div>
									</div>
									 
									<div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
										<div class="am-form-group tpl-table-list-select">
											<select data-am-selected="{btnSize: 'sm'}" name="dateInq" class="date">
												<option value="0">时间段</option>

											</select>
										</div>
									</div>

									<div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
										<div
											class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
											<input name="itemCodeInq" type="text" class="am-form-field "
												placeholder="商品编号">

										</div>
									</div>
									<div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
										<div
											class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
											<input name="itemNameInq" type="text" class="am-form-field "
												placeholder="商品名称"> <span class="am-input-group-btn">
												<button
													class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search"
													type="submit"></button>
											</span>
										</div>
									</div>


									<div class="row">
										<div class="am-u-lg-12" style="margin-top: 50px">
											<table width="100%"
												class="am-table am-table-bordered am-table-radius am-table-striped "
												id="example-r">
												<thead>
													<tr>
														<th><input type="checkbox" id="allcheck">&nbsp;全选</th>
														<th>供应商</th>
														<th>类别</th>
														<th>商品编号</th>
														<th>商品名称</th>
														<th>单价</th>
														<th>数量</th>
														<th>日期</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${resultList}" var="infor">
														<tr class="gradeX">
															<td><input type="checkbox" class="subchk"
																value="${infor.id }" name="chk">
																<input type="hidden"
																value="${infor.supplyName }" name="supplyName">
																<input type="hidden"
																value="${infor.kind }" name="kind">
																<input type="hidden"
																value="${infor.itemCode }" name="itemCode">
																<input type="hidden"
																value="${infor.itemName }" name="itemName">
																<input type="hidden"
																value="${infor.price }" name="price">
																<input type="hidden"
																value="${infor.quantity }" name="quantity">
																<input type="hidden"
																value="${infor.date }" name="date"></td>
															<td>${infor.supplyName }</td>
															<td>${infor.kind }</td>
															<td>${infor.itemCode }</td>
															<td>${infor.itemName }</td>
															<td>${infor.price }</td>
															<td>${infor.quantity }</td>
															<td>${infor.date }</td>
														</tr>
													</c:forEach>

													<!-- more data -->
												</tbody>
											</table>
							<div class="am-fr">
									<ul class="am-pagination tpl-pagination">
										<li><a class="button" href="suppliersServlet?action=moreInfor&pageSize=${pageSize+5}&option=unsettled" >获取更多</a></li>
										<li><a class="button" href="suppliersServlet?action=moreInfor&pageSize=${pagecount*pageSize}&option=unsettled" >显示全部</a></li>
										<li><a class="button" href="###" onclick="getInfor()">结算</a></li>
                                        <c:if test="${pageNum-1==0}">
											<li class="am-disabled"><a href="#">«</a></li>
										</c:if>
                                        <c:if test="${pageNum-1!=0}">
											<li><a href="suppliersServlet?action=unsettled&pageNum=${pageNum-1}">«</a></li>
										</c:if>
   
                                        <c:forEach begin="1" end="${pagecount}" var="p"  >
										<c:if test="${p==pageNum}">
											<li class="am-active"><a href="#">${p}</a></li>
										</c:if>
										<c:if test="${p!=pageNum}">
											<li><a href="suppliersServlet?action=unsettled&pageNum=${p}"  >${p}</a></li>      
										</c:if>
										</c:forEach>
										
										 <c:if test="${pageNum==pagecount}">
											<li class="am-disabled"><a href="#">»</a></li>
										</c:if>
                                        <c:if test="${pageNum!=pagecount}">
											<li><a href="suppliersServlet?action=unsettled&pageNum=${pageNum+1}">»</a></li>
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
								</form>
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
</html>