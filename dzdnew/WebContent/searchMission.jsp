<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
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

</head>

<body data-type="widgets">
	<script src="assets/js/theme.js"></script>
	<div class="am-g tpl-g">
		<!-- 头部 -->
		<header>
			<!-- logo -->
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
									<h1>任务单查询</h1>
								</div>


							</div>
							<div class="widget-body  am-fr">
								<form class="am-form am-form-inline"
									action="searchtasklistservlet" method="post" role="form">
									<div class="row">
										<div class="am-u-lg-12 am-u-lg-centered">
											<div class="am-u-lg-3">
												<div>
													<label class=" am-form-label" style="width: 125px">任务单号:</label>
													<div class="am-form-group">
														<input type="text" id="tel" name="tasklistId"
															class="am-form-field" placeholder="任务单号"
															style="width: 200px"
															onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');">
													</div>
												</div>
												<div>
													<label class=" am-form-label" style="width: 125px">执行任务分站ID:</label>
													<div class="am-form-group">
														<input type="text" id="name" name="substationId"
															class="am-form-field" placeholder="执行任务分站ID"
															style="width: 200px"
															onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');">
													</div>
												</div>

											</div>


											<div class="am-u-lg-3">
												<div>
													<label class=" am-form-label" style="width: 125px">任务类型:</label>
													<div class="am-form-group" style="width: 200px">
														<div class="am-u-sm-9">
															<select name="taskType" data-am-selected>
																<option value="0">全部</option>
																<option value="1">新订</option>
																<option value="3">退货</option>
															</select>
														</div>
													</div>
												</div>
												<div>
													<label class=" am-form-label" style="width: 125px">客户姓名:</label>
													<div class="am-form-group">
														<input type="text" id="tel" name="clientName"
															class="am-form-field" placeholder="客户姓名"
															style="width: 200px">
													</div>
												</div>
											</div>


											<div class="am-u-lg-3">
												<div>
													<label class=" am-form-label" style="width: 125px">任务状态:</label>
													<div class="am-form-group" style="width: 200px">
														<div class="am-u-sm-9">
															<select name="taskStatus" data-am-selected>
																<option value="0">全部</option>
																<option value="1">已调度</option>
																<option value="2">已分配</option>
																<option value="3">派送中</option>
																<option value="4">派送中</option>
															</select>
														</div>
													</div>
												</div>
												<div>
													<label class=" am-form-label" style="width: 125px">联系电话:</label>
													<div class="am-form-group">
														<input type="text" id="tel" name="telNumber"
															class="am-form-field" placeholder="联系电话"
															style="width: 200px"
															onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');">
													</div>
												</div>
											</div>

											<div class="am-u-lg-3">
												<div>
													<label class=" am-form-label" style="width: 125px">完成时间:</label>
													<div class="am-u-lg" style="">
														<input type="text" class="am-form-field "
															placeholder="完成时间" data-am-datepicker=""
															name="finishDate" style="width: 200px">
													</div>
												</div>
												<div>
													<div class="am-form-group" style="margin-top: 30px">
														<button type="submit" class="am-btn am-btn-primary"
															style="width: 200px">
															<em class="fa fa-search">查询</em>
														</button>
													</div>
												</div>
											</div>

										</div>
									</div>
								</form>


								<!-- 											<label class=" am-form-label">客户姓名:</label>
											<div class="am-form-group">
												<input type="text" id="tel" name="clientName"
													class="am-form-field" placeholder="客户姓名"
													style="width: 200px">
											</div> -->

								<!-- <div>
												<div class="am-u-lg-2" style="margin-left: 0">
													<input type="text" class="am-form-field "
														placeholder="完成时间" data-am-datepicker="" readonly=""
														name="finishDate" style="margin-left: 0px">
												</div>

												<div class="am-form-group">
													<button type="submit" class="am-btn am-btn-primary"
														style="width: 200px; margin-left: 80%">
														<em class="fa fa-search">提交</em>
													</button>
												</div>
											</div> -->


								<div class="am-u-sm-12">
									<table width="100%"
										class="am-table am-table-compact am-table-striped tpl-table-black "
										id="example-r">
										<thead>
											<tr>
												<th>任务单号码</th>
												<th>任务类型</th>
												<th>任务状态</th>
												<th>要求完成日期</th>
												<th>执行任务分站</th>
												<th>客户姓名</th>
												<th>联系电话</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${tasklistResultList.size()==0}">
												<div>
													<label>对不起！没有查询到您想要查询的信息！</label>
												</div>
											</c:if>
											<c:forEach items="${tasklistResultList}" var="tasklist">
												<tr>
													<td>${tasklist.tasklistId}</td>
													<td>
													<c:if test="${tasklist.taskType==1}">
															订货单
															</c:if> 
															<c:if test="${tasklist.taskType==3}">
															退货单
															</c:if>
													</td>
													<td>
													<c:if test="${tasklist.taskStatus==1||tasklist.taskStatus==7}">
													已调度
													</c:if> 
													<c:if test="${tasklist.taskStatus==4||tasklist.taskStatus==8}">
													已分配
													</c:if> 
													<c:if test="${tasklist.taskStatus==5}">
													派送中
													</c:if> 
													<c:if test="${tasklist.taskStatus==6||tasklist.taskStatus==11}">
													已完成
													</c:if> 
													
													</td>
													<td>${tasklist.finishDate}</td>
													<td>${tasklist.substationId}</td>
													<td>${tasklist.clientName}</td>
													<td>${tasklist.clientTel}</td>
												</tr>

											</c:forEach>

										</tbody>
									</table>
								</div>


								<div class="am-u-sm-12 am-u-lg" style="text-align: center;">
									<ul class="am-pagination tpl-pagination"
										style="margin-left: 50%">

										<c:if test="${pageNum-1==0}">
											<li class="am-disabled"><a href="#">«</a></li>
										</c:if>
										<c:if test="${pageNum-1!=0}">
											<li><a href="searchtasklistservlet?pageNum=${pageNum-1}">«</a></li>
										</c:if>

										<c:forEach begin="1" end="${countPage}" var="p">

											<c:if test="${p==pageNum}">
												<li class="am-active"><a href="#">${p}</a></li>
											</c:if>
											<c:if test="${p!=pageNum}">
												<li><a href="searchtasklistservlet?pageNum=${p}">${p}</a></li>
											</c:if>

										</c:forEach>
										<c:if test="${pageNum==countPage}">
											<li class="am-disabled"><a href="#">»</a></li>
										</c:if>
										<c:if test="${pageNum!=countPage}">
											<li><a href="searchtasklistservlet?pageNum=${pageNum+1}">»</a></li>
										</c:if>
									</ul>
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