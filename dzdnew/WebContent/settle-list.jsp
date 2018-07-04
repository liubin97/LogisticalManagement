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
<script src="assets/js/settle-list.js"></script>

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
									<h1>与分站结算清单</h1>
								</div>


							</div>

							<form action="substationServlet?action=confirm" method="post" id="confirm">
								<div class="row">
									<div class="am-u-lg-12" style="margin-top: 50px">
										<c:forEach items="${ids}" var="id">
											<input type="hidden" value="${id}" name="id">
										</c:forEach>
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
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${resultList }" var="infor">
													
													<tr class="gradeX">
														<td>${infor.subName}
														<input type="hidden" value="${infor.subName}"
															name="subName"> <input type="hidden"
															value="${infor.itemCode}" name="itemCode"> <input
															type="hidden" value="${infor.itemName}" name="itemName">
															<input type="hidden" value="${infor.price}"
															name="price"> <input type="hidden"
															value="${infor.quantityOut}" name="quantityOut"> <input
															type="hidden" value="${infor.amountIn}" name="amountIn">
															<input type="hidden" value="${infor.quantityIn}"
															name="quantityIn"> <input type="hidden"
															value="${infor.amountOut}" name="amountOut">
														</td>
														<td>${infor.itemCode}</td>
														<td>${infor.itemName}</td>
														<td>${infor.price}</td>
														<td>${infor.quantityOut}</td>
														<td>${infor.amountIn}</td>
														<td>${infor.quantityIn}</td>
														<td>${infor.amountOut}</td>
													</tr>
												</c:forEach>

												<!-- more data -->
											</tbody>
										</table>

										<div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
											<div
												class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
												实收小计：</div>
											<div
												class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
												<input type="text" class="am-form-field " id="inAll"
													placeholder="0" Readonly>

											</div>
										</div>
										<div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
											<div
												class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
												退款小计：</div>
											<div
												class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
												<input type="text" class="am-form-field " id="outAll"
													placeholder="0" Readonly>

											</div>
										</div>
										<div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
											<div
												class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
												应缴额：</div>
											<div
												class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
												<input type="text" class="am-form-field " id="allAmount"
													placeholder="0" Readonly>

											</div>
										</div>

										<div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
											<div
												class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
												请确认：</div>
											<div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
												<a class="button" href="###"><button type="submit"
														class="am-btn am-btn-success">确认结算</button></a>
											</div>
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