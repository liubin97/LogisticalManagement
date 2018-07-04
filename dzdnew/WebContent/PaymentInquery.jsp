<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
	<%@include file="frame.html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>缴款查询</title>
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

<script type="text/javascript">
	function querybypagenum(pageNum){
		var form = document.getElementById("queryform");
		form.pageNum=pageNum;
		form.submit();
	}
</script>

</head>
<body>
	<script src="assets/js/theme.js"></script>
	<div class="am-g tpl-g">

		<!-- 内容区域 -->
		<div class="tpl-content-wrapper">
			<div class="row-content am-cf">
				<div class="row">
					<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
						<div class="widget am-cf">
							<div class="widget-head am-cf">
								<div class="widget-title  am-cf">
									<h1>缴款查询</h1>
								</div>


							</div>
							<div class="widget-body  am-fr">
								<form action="paymentQueryServlet" method="post" class="am-form tpl-form-border-form tpl-form-border-br" data-am-validator >
									
									
									<div class="am-u-lg-2">
										<input type="text" name="begintime" class="am-form-field " placeholder="起始时间" value="${begintime}"
											data-am-datepicker="" readonly="" required>
									</div>
									
								
									<div class="am-u-lg-2">	
										<input type="text" name="endtime" class="am-form-field" placeholder="终止时间" value="${endtime}"
											data-am-datepicker="" readonly="" required>
									</div>
									
									<input type="hidden" name="substation_id" value="${substation_id}">
									<input type="hidden" name="pageNum" value="">
									
									<button class="am-btn  am-btn-default am-btn-success am-icon-search"
										type="submit"></button>
										
								</form>

								<div class="row">
									<div class="am-u-lg-12" style="margin-top: 50px">
										<table width="100%"t
											class="am-table am-table-bordered am-table-radius am-table-striped "
											id="example-r">
											
											<thead>
												<tr>
													<th>商品编号</th>
													<th>商品名</th>
													<th>总数量</th>
													<th>总金额</th>
													<th>分站</th>
												</tr>
											</thead>
											
											<tbody>
												<c:forEach items="${paylist}" var="list">
													<tr class="gradeX">
														<td>${list.product_name}</td>
														<td>${list.prod_id}</td>
														<td>${list.amount}</td>
														<td>${list.summoney}</td>
														<td>${substation_id}</td>
													</tr>
												</c:forEach>
											</tbody>
											
										</table>
									</div>

									<div class="am-u-lg-12 am-cf">
										<div class="am-fr">
											<ul class="am-pagination tpl-pagination">
<!-- 												<li class="am-disabled"><a href="#">«</a></li> -->

											<c:forEach var="i" begin="1" end="${pagecount}" step="1">
												<c:if test="${pageNum==i}">
													<li class="active"><a onclick="querybypagenum(this.innerHTML)" style="cursor:pointer">${i}</a></li>
												</c:if>
												<c:if test="${pageNum!=i}">
													<li><a onclick="querybypagenum(this.innerHTML)" style="cursor:pointer">${i}</a></li>
												</c:if>
											</c:forEach>

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
	</div>

	<script src="assets/js/amazeui.min.js"></script>
	<script src="assets/js/amazeui.datatables.min.js"></script>
	<script src="assets/js/dataTables.responsive.min.js"></script>
	<script src="assets/js/app.js"></script>
</body>
</html>