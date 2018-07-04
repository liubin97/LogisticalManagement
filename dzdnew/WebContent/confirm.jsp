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
<script src="assets/js/confirm.js"></script>

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
									<h1>与供应商结算清单</h1>
								</div>


							</div>
							<div class="widget-body  am-fr">
								<form class="am-form am-form-inline"
									action="suppliersServlet?action=confirm" method="post"
									role="form">

									<!--<div class="row">-->
									<!--<div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">-->
									<!--<input type="text" class="am-form-field " placeholder="请输入标题文字" >-->
									<!--<span class="am-input-group-btn">-->
									<!--<button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="submit"></button>-->
									<!--</span>-->
									<!--</div>-->

									<!--</div>-->

									<c:forEach items="${idIn }" var="id">
										<input type="hidden" value="${id }" name="idIn">
									</c:forEach>
									<c:forEach items="${idOut }" var="id">
										<input type="hidden" value="${id }" name="idOut">
									</c:forEach>

									<div class="row">
										<div class="am-u-lg-12" style="margin-top: 50px">
											<table width="100%"
												class="am-table am-table-bordered am-table-radius am-table-striped "
												id="example-r">
												<thead>
													<tr>
														<th>供应商</th>
														<th>商品编号</th>
														<th>商品名称</th>
														<th>单价</th>
														<th>进货数量</th>
														<th>退货数量</th>
														<th>结算数量</th>
														<th>结算金额</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${resultList }" var="infor">
														<tr class="gradeX">
															<td>${infor.supplyName }<input type="hidden"
																value="${infor.supplyName }" name="supplyName"></td>
															<td>${infor.itemCode }<input type="hidden"
																value="${infor.itemCode }" name="itemCode"></td>
															<td>${infor.itemName }<input type="hidden"
																value="${infor.itemName }" name="itemName"></td>
															<td>${infor.price }<input type="hidden"
																value="${infor.price }" name="price"></td>
															<td>${infor.quantityIn }<input type="hidden"
																value="${infor.quantityIn }" name="quantityIn"></td>
															<td>${infor.quantityOut }<input type="hidden"
																value="${infor.quantityOut }" name="quantityOut"></td>
															<td>${infor.quantityIn-infor.quantityOut }</td>
															<td>${(infor.quantityIn-infor.quantityOut)*infor.price }<input
																type="hidden"
																value="${(infor.quantityIn-infor.quantityOut)*infor.price }"
																class="amount"></td>
														</tr>
													</c:forEach>

													<!-- more data -->
												</tbody>
											</table>

										</div>


										<div class="am-fr"></div>
										<div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
											<div class="am-form-group tpl-table-list-select"></div>
										</div>

										<div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
											<div class="am-form-group tpl-table-list-select">合计金额：
											</div>
											<div
												class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
												<input type="text" class="am-form-field " id="all-mount"
													placeholder="0" Readonly>
											</div>
										</div>
										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<a class="button" href="###"><button type="submit"
														class="am-btn am-btn-success">确认结算</button></a>
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

</body>
</html>