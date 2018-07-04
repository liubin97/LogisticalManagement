<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
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
<script type="text/javascript" src="js/controlCenter.js"></script>



</head>


<body data-type="widgets">
	<script src="assets/js/theme.js"></script>
	<div class="am-g tpl-g">
		<!-- 头部 -->
		<header> <!-- logo -->
		<div class="am-fl tpl-header-logo">
			<a href="javascript:;"><img src="assets/img/logo.png" alt=""></a>
		</div>
		<!-- 右侧内容 -->
		<div class="tpl-header-fluid">
			<!-- 侧边切换 -->
			<div class="am-fl tpl-header-switch-button am-icon-list">
				<span> </span>
			</div>

			<!-- 其它功能-->
			<div class="am-fr tpl-header-navbar">
				<ul>
					<!-- 欢迎语 -->
					<li class="am-text-sm tpl-header-navbar-welcome"><a
						href="javascript:;">欢迎你, <span>Amaze UI</span>
					</a></li>

					<!-- 退出 -->
					<li class="am-text-sm"><a href="javascript:;"> <span
							class="am-icon-sign-out"></span> 退出
					</a></li>
				</ul>
			</div>
		</div>

		</header>
		<!-- 风格切换 -->
		<div class="tpl-skiner">
			<div class="tpl-skiner-toggle am-icon-cog"></div>
			<div class="tpl-skiner-content">
				<div class="tpl-skiner-content-title">选择主题</div>
				<div class="tpl-skiner-content-bar">
					<span class="skiner-color skiner-white" data-color="theme-white"></span>
					<span class="skiner-color skiner-black" data-color="theme-black"></span>
				</div>
			</div>
		</div>
		<!-- 侧边导航栏 -->
		<div class="left-sidebar">


			<!-- 菜单 -->
			<li class="sidebar-nav-heading">调度中心</li>
			<ul class="sidebar-nav">

				<li class="sidebar-nav-heading">Page<span
					class="sidebar-nav-heading-info"> 常用页面</span></li>
				<li class="sidebar-nav-link"><a href="javascript:;"
					class="sidebar-nav-sub-title"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 调度管理 <span
						class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico sidebar-nav-sub-ico-rotate"></span>
				</a>
					<ul class="sidebar-nav sidebar-nav-sub" style="display: block">
						<li class="sidebar-nav-link"><a
							href="controlCenter.jsp">
								<span class="am-icon-angle-right sidebar-nav-link-logo"></span>
								订单调度
						</a></li>

						<li class="sidebar-nav-link"><a
							href="searchMission.jsp">
								<span class="am-icon-angle-right sidebar-nav-link-logo"></span>
								任务单查询
						</a></li>
					</ul></li>
			</ul>
		</div>


		<!-- 内容区域 -->
		<div class="tpl-content-wrapper">
			<div class="row-content am-cf">
				<div class="row">
					<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
						<div class="widget am-cf">
							<div class="widget-head am-cf">
								<div class="widget-title  am-cf">
									<h1>订单调度</h1>
								</div>

							</div>
							<div class="widget-body  am-fr">
								<form
									class="am-form am-form-inline tpl-form-border-form tpl-form-border-br"
									action="searchorderservlet" method="post">

									<div class="row">


										<div class="am-u-lg-12 am-u-lg-centered">
											<div class="am-u-lg-3">
												<input type="text" class="am-form-field " placeholder="完成时间"
													data-am-datepicker="" readonly="" name="finishDate">
											</div>
											<label class=" am-form-label am-u-lg-2">订单类型：</label>
											<div class="am-form-group am-u-lg-4">
												<div class="am-u-sm-9">
													<select name="orderType" data-am-selected>
														<option value="0">全部</option>
														<option value="1">订货</option>
														<option value="3">退货</option>
													</select>
												</div>
											</div>
<!-- 
											<label class="am-form-label">任务状态：</label>
											<div class="am-form-group">
												<div class="am-u-sm-9">
													<select name="orderStatement" data-am-selected>
														<option value="0">全部</option>
														<option value="2">可调度</option>
														<option value="1">缺货中</option>
													</select>
												</div>
											</div> -->

											<div class="am-form-group am-u-lg-3">
												<button type="submit" class="am-btn am-btn-primary" style="width:80%">
													<em class="fa fa-search">查询</em>
												</button>
											</div>
										</div>
									</div>
									</form>

									<form id="ditributeForm" class="am-form am-form-inline tpl-form-border-form tpl-form-border-br" action="distributeservlet" method="post">
									<div class="row">
										<div class="am-u-sm-12">
											<table width="100%"
												class="am-table am-table-compact am-table-striped tpl-table-black "
												id="example-r">
												<thead>
													<tr>
														<th>订单编号</th>
														<th>订单类别</th>
<!-- 														<th>订单状态</th>
 -->														<th>要求完成日期</th>
														<th>操作</th>
														<th width="75px">批量管理</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${orderResultList.size()==0}">
													<div>
														<label>对不起！没有查询到您想要查询的信息！</label>
													</div>
													</c:if>
													<c:forEach items="${orderResultList}" var="order">
														<tr>
															<td>${order.order_id}</td>
															<td>
															<c:if test="${order.type==1}">
															订货单
															</c:if> 
															<c:if test="${order.type==3}">
															退货单
															</c:if>
															</td>
<%-- 															<td>${order.status}</td>
 --%>															<td>${order.finish_date}</td>
															<td>
																<div class="tpl-table-black-operation">
																	<a href="javascript:;" type="button" class="am-icon-pencil"
																		onclick="distributeOneByhand(${order.order_id})">人工调度
																	</a> <a href="javascript:;" class=""
																		onclick="distributeOneAuto(${order.order_id})"> <i
																		class="am-icon-pencil"
																		></i>自动调度
																	</a>
																</div>
															</td>
															<td><input type="checkbox" value="${order.order_id}"
																name="chk" id="chk"></td>
														</tr>

													</c:forEach>







													<!-- 												
													<tr class="gradeX">
													<td>c002333</td>
													<td>购买</td>
													<td>未到货</td>
													<td>2018-06-20</td>
													<td>
														<div class="tpl-table-black-operation">
															<a type="button" class="am-icon-pencil"
																data-am-modal="{target: '#my-prompt'}">人工调度</a> <a
																href="javascript:;" class=""
																data-am-modal="{target: '#manageresult'}"> <i
																class="am-icon-pencil"></i> 自动调度
															</a>
														</div>
													</td>
													<td><input type="checkbox" style="margin-left: 50%">
													</td>
												</tr> -->


												</tbody>
											</table>
										</div>

										<div class="tpl-table-black-operation"
											style="margin-left: 80%">
											<a href="javascript:;" class="am-icon-pencil"
												data-am-modal="{target: '#tempAll'}">批量手动调度</a> 
												<a href="javascript:;" class=""
												onclick="distributeAllAuto()"> <i
												class="am-icon-pencil"></i>批量自动调度
											</a>
										</div>



										<ul class="am-pagination tpl-pagination"
											style="margin-left: 80%">

											<c:if test="${pageNum-1==0}">
												<li class="am-disabled"><a href="#">«</a></li>
											</c:if>
											<c:if test="${pageNum-1!=0}">
												<li><a href="searchorderservlet?pageNum=${pageNum-1}">«</a></li>
											</c:if>

											<c:forEach begin="1" end="${countPage}" var="p">


												<c:if test="${p==pageNum}">
													<li class="am-active"><a href="#">${p}</a></li>
												</c:if>
												<c:if test="${p!=pageNum}">
													<li><a href="searchorderservlet?pageNum=${p}">${p}</a></li>
												</c:if>

											</c:forEach>
											<c:if test="${pageNum==countPage}">
												<li class="am-disabled"><a href="#">»</a></li>
											</c:if>
											<c:if test="${pageNum!=countPage}">
												<li><a href="searchorderservlet?pageNum=${pageNum+1}">»</a></li>
											</c:if>
										</ul>
										<!--    <li class="am-disabled"><a href="#">«</a></li>
                                            <li class="am-active"><a href="#">1</a></li>
                                            <li><a href="#">2</a></li>
                                            <li><a href="#">3</a></li>
                                            <li><a href="#">4</a></li>
                                            <li><a href="#">5</a></li>
                                            <li><a href="#">»</a></li> -->
										<%-- 										<div style="margin-left: 80%">
											&lt;&lt;
											<c:forEach begin="1" end="${pagecount}" var="p">
												<c:if test="${p==pageNum}">
												${p}
											</c:if>
												<c:if test="${p!=pageNum}">
													<a href="searchOrderServlet?pageNum=${p}">${p}</a>
												</c:if>
											&nbsp;&nbsp;
										</c:forEach>
											&gt;&gt;

										</div> --%>

									</div>
									<input type="hidden" name="substationIdAllByhand" id="substationIdAllByhand" value="">
									<input type="hidden" name="action" id="action" value="">
									</form>
									
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="am-modal am-modal-alert" tabindex="-1" id="manageresult">
			<div class="am-modal-dialog">
				<div class="am-modal-hd">自动调度</div>
				<!-- 修改 -->
				<div class="am-modal-hd" id="finalSubstationId"></div>
				<div class="am-modal-footer">
					<span class="am-modal-btn" onclick="jump1(${pageNum})" >确定</span>
				</div>
			</div>
		</div>
		
		
				<div class="am-modal am-modal-alert" tabindex="-1" id="manageresultAll">
			<div class="am-modal-dialog">
				<div class="am-modal-hd">自动调度</div>
				<div class="am-modal-hd">系统经过计算...已成功将目标分配</div>
				<div class="am-modal-footer">
					<span class="am-modal-btn" onclick="jump1(${pageNum})" >确定</span>
				</div>
			</div>
		</div>
		


		<div class="am-modal am-modal-prompt" tabindex="-1" id="tempOne">
			<div class="am-modal-dialog">
				<div class="am-modal-hd">选择您想要调度的方向</div>
				<form>
					<div class="am-modal-bd">
						选择你想要的分站 
						<select name="substationSelect" id="substationSelect" data-am-selected="{btnSize: 'sm'}">
						</select>
					</div>
					<div class="am-modal-footer">
						<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span
							class="am-modal-btn" data-am-modal-confirm onclick="distributeByhandSubmit()"
							>提交</span>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div class="am-modal am-modal-prompt" tabindex="-1" id="tempAll">
			<div class="am-modal-dialog">
				<div class="am-modal-hd">选择您想要调度的方向</div>
				<form>
					<div class="am-modal-bd">
						选择你想要的分站 
						<select name="substationSelectAll" id="substationSelectAll" data-am-selected="{btnSize: 'sm'}">
							
						</select>
					</div>
					<div class="am-modal-footer">
						<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span
							class="am-modal-btn" data-am-modal-confirm onclick="distributeAllByhand()"
							>提交</span>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	

	<form id="distributeByhand" type="hidden" action="distributeservlet">
		<input type="hidden" name="orderIdOneByhand" value=""> 
		<input type="hidden" name="substationIdOneByhand" value="">
		<input type="hidden" name = "action" value = "byhandOne">
	</form>
	
		<form id="distributeAuto" type="hidden" action="distributeservlet">
		<input type="hidden" name="orderIdOneAuto" value=""> 
		<input type="hidden" name = "action" value = "autoOne">
	</form>



	<script src="assets/js/amazeui.min.js"></script>
	<script src="assets/js/amazeui.datatables.min.js"></script>
	<script src="assets/js/dataTables.responsive.min.js"></script>
	<script src="assets/js/app.js"></script>

</body>
</html>
