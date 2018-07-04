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
<script src="assets/js/settlement-with-sub-station-settled.js"></script>
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
									<h1>与分站已结算情况</h1>
								</div>


							</div>
							<div class="widget-body  am-fr">
								<form class="am-form am-form-inline"
									action="substationServlet?action=settled" method="post"
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
											<select data-am-selected="{btnSize: 'sm'}" name="name" class="substation">
												<option value="0">分站</option>
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
									
									<%--每页行数 --%>
									<input type="hidden" value="1" name="pageSize">
									
									<div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
										<div
											class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
											<input type="text" class="am-form-field " name="itemCodeInq"
												placeholder="商品编号">

										</div>
									</div>
									<div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
										<div
											class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
											<input type="text" class="am-form-field " name="itemNameInq"
												placeholder="商品名称"> <span class="am-input-group-btn">
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
												<th>分站</th>
												<th>商品编号</th>
												<th>商品名称</th>
												<th>单价</th>
												<th>送货数量</th>
												<th>收款额</th>
												<th>退货数量</th>
												<th>退款额</th>
												<th>结算日期</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${resultList }" var="infor">
												<tr class="gradeX">
													<td>${infor.subName}</td>
													<td>${infor.itemCode}</td>
													<td>${infor.itemName}</td>
													<td>${infor.price}</td>
													<td>${infor.quantityOut}</td>
													<td>${infor.amountIn}</td>
													<td>${infor.quantityIn}</td>
													<td>${infor.amountOut}</td>
													<td>${infor.date }</td>
												</tr>
											</c:forEach>

											<!-- more data -->
										</tbody>
									</table>


									<div class="am-fr">
									<ul class="am-pagination tpl-pagination">
										<li><a class="button" href="substationServlet?action=moreInfor&pageSize=${pageSize+5}&option=settled" >获取更多</a></li>
										<li><a class="button" href="substationServlet?action=moreInfor&pageSize=${pagecount*pageSize}&option=settled" >显示全部</a></li>
                                        <c:if test="${pageNum-1==0}">
											<li class="am-disabled"><a href="#">«</a></li>
										</c:if>
                                        <c:if test="${pageNum-1!=0}">
											<li><a href="substationServlet?action=settled&pageNum=${pageNum-1}">«</a></li>
										</c:if>
   
                                        <c:forEach begin="1" end="${pagecount}" var="p"  >
										<c:if test="${p==pageNum}">
											<li class="am-active"><a href="#">${p}</a></li>
										</c:if>
										<c:if test="${p!=pageNum}">
											<li><a href="substationServlet?action=settled&pageNum=${p}"  >${p}</a></li>      
										</c:if>
										</c:forEach>
										
										 <c:if test="${pageNum==pagecount}">
											<li class="am-disabled"><a href="#">»</a></li>
										</c:if>
                                        <c:if test="${pageNum!=pagecount}">
											<li><a href="substationServlet?action=settled&pageNum=${pageNum+1}">»</a></li>
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