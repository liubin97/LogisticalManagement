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
<title>任务单管理</title>
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
<link rel="stylesheet" href="asset/css/print.css">
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/print.js"></script>


<!--  <script type="text/javascript">   
//    window.onload=function(){
// 	   alert(adsdasdasd);
// 	   document.getElementById("1")。removeAttribute("selected");
// 	   document.getElementById("2")。removeAttribute("selected");
//     		if(${tasktype}==1){
//     			document.getElementById("1").selected=true;
//     		}
//     		if(${tasktype}==2){
//     			document.getElementById("2").selected=true;
//     		}
//    	}
  </script>   -->

<script type="text/javascript">
	function allocate(tasklist_id){
// 		alert(tasklist_id);
		var form = document.getElementById("allocatequerylist");
		form.tasklist_id.value=tasklist_id;
		
		form.submit();
	}
</script>

<script type="text/javascript">
	function receipt(tasklist_id){
		var form = document.getElementById("receiptform");
		form.tasklist_id.value=tasklist_id;
		
		form.submit();
	}
</script>

<script type="text/javascript">
	function querybypagenum(pageNum){
		var form = document.getElementById("queryform");
		form.pageNum=pageNum;
		form.submit();
	}
</script>



</head>
<body>

<form id="allocatequerylist" action="taskListAllocateServlet">
	<input type="hidden" name="beginTime" value="${beginTime}">
	<input type="hidden" name = "taskState" value="${taskState }">
	<input type="hidden" name = "taskType" value="${taskType }">
	<input type="hidden" name = "tasklist_id" value="">
	<input type="hidden" name = "pageNum" value="${pageNum}">
	<input type="hidden" name = "substation_id" value="${substation_id}">
	
<%-- 	<input type="hidden" name = "pagecount" value="${pagecount}"> --%>

</form>

<form id="receiptform" action="recepitServlet">
	<input type="hidden" name="beginTime" value="${beginTime}">
	<input type="hidden" name = "taskState" value="${taskState }">
	<input type="hidden" name = "taskType" value="${taskType }">
	<input type="hidden" name = "tasklist_id" value="">
	<input type="hidden" name = "pageNum" value="${pageNum}">
	<input type="hidden" name = "substation_id" value="${substation_id}">

</form>

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
									<h1>任务单管理</h1>
								</div>


							</div>
							<div class="widget-body  am-fr">
							
								<form id="queryform" class="am-form am-form-inline tpl-form-border-form tpl-form-border-br" action="taskListQueryServlet" method="post" role="form" data-am-validator>

									<div class="row">
									
										
										<div class="am-u-lg-12 am-u-lg-centered">
											<div class="am-u-lg-2">
											<input type="text" value="${beginTime}" class="am-form-field " placeholder="任务单时间" data-am-datepicker="" readonly="" name="beginTime"  required>
											</div>
											
											<label class=" am-form-label" >任务状态</label>
											<div class="am-form-group">		
                                        		<div class="am-u-sm-9">
                                           			<select name="taskState" data-am-selected >
 												 		<c:if test="${taskState==1}">
 												 			<option value="1" selected>未分配</option>
 												 		</c:if>
 												 		<c:if test="${taskState==2}">
 												 			<option value="2" selected>配送中</option>
 												 		</c:if>
 												 		<c:if test="${taskState==3}">
 												 			<option value="3" selected>已完成</option>
 												 		</c:if>
 												 		<option value="1">未分配</option>
  					                           			<option value="2">配送中</option>
  					                           			<option value="3">已完成</option>
													</select>
                                       			</div>
                                    		</div>
											
											<label class="am-form-label" >任务类型</label>
											<div class="am-form-group">
                                        		   <div class="am-u-sm-9">
                                           			<select name="taskType" data-am-selected >
                                           				<c:if test="${taskType==1}">
 												 			<option value="1" selected>送货</option>
 												 		</c:if>
 												 		<c:if test="${taskType==3}">
 												 			<option value="3" selected>退货</option>
 												 		</c:if>
 												 		<option value="1">送货</option>
  					                           			<option value="3">退货</option>
  					               
													</select>
                                       			</div>
                                    		</div>

											<div class="am-form-group">
												<button type="submit" class="am-btn am-btn-primary">
													<em class="fa fa-search">提交</em>
												</button>
											</div>
										</div>
									</div>
									
									<input type="hidden" name="pageNum" value="">
									
								</form>

								<div class="row">
									<div class="am-u-lg-12" style="margin-top: 50px">
										<table width="100%"
											class="am-table am-table-bordered am-table-radius am-table-striped "
											id="example-r">
											<thead>
												<tr>
													<th>任务单号</th>
													<th>执行任务分站</th>
													<th>任务状态</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
										
											<c:forEach items = "${resultlist}" var="tasklist">
											
												<tr class="gradeX">
													<td>${tasklist.tasklist_id}</td>
													<td>${tasklist.substation_id}</td>
													<td>
														<c:if test="${tasklist.taskState==1}">
															未分配
														</c:if>
														<c:if test="${tasklist.taskState==2}">
														        已分配
														</c:if>
														<c:if test="${tasklist.taskState==3}">
															已完成
														</c:if>
													</td>
													<td>
													
														<div class="tpl-table-black-operation">
														
														 
															<c:if test="${tasklist.taskState==1}">
																<a onclick="allocate(${tasklist.tasklist_id})" style="cursor:pointer"> 
																	<i class="am-icon-pencil"></i> 分配
																</a> 
															</c:if>
															<c:if test="${tasklist.taskState==2}">
																<a href ="javascript:return false;" onclick="return false;" style="cursor:pointer" > 
																	<i class="am-icon-pencil"></i> 分配
																</a> 
															</c:if>
															<c:if test="${tasklist.taskState==3}">
																<a href ="javascript:return false;" onclick="return false;" style="cursor:pointer"> 
																	<i class="am-icon-pencil"></i> 分配
																</a> 
															</c:if>
															
															<c:if test="${tasklist.taskState==1}">
																<a href ="javascript:return false;" onclick="return false;" style="cursor:pointer" > 
																	<i class="am-icon-pencil"></i> 录入回执
																</a> 
															</c:if>
															<c:if test="${tasklist.taskState==2}">
																<c:if test="${tasklist.taskType==1}">
																<a onclick="receipt(${tasklist.tasklist_id})" style="cursor:pointer" > 
																	<i class="am-icon-pencil"></i> 录入回执
																</a> 
																</c:if>
															</c:if>
															<c:if test="${tasklist.taskState==3}">
																<a href ="javascript:return false;" onclick="return false;" style="cursor:pointer" > 
																	<i class="am-icon-pencil"></i> 录入回执
																</a> 
															</c:if>
															
															<a href="#" class="am-icon-pencil"
																data-am-modal="{target: '#${tasklist.tasklist_id}', closeViaDimmer: 0, width: 400, height:500}">
																查看详情
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
<!-- 												<li class="am-disabled"><a href="#">«</a></li> -->

											<c:forEach var="i" begin="1" end="${pagecount}" step="1">
												<c:if test="${pageNum==i}">
													<li class="active"><a onclick="querybypagenum(this.innerHTML)" style="cursor:pointer">${i}</a></li>
												</c:if>
												<c:if test="${pageNum!=i}">
													<li><a onclick="querybypagenum(this.innerHTML)" style="cursor:pointer">${i}</a></li>
												</c:if>
											</c:forEach>

<!-- 												<li class="am-active"><a href="#">1</a></li> -->
<!-- 												<li><a href="#">2</a></li> -->
<!-- 												<li><a href="#">3</a></li> -->
<!-- 												<li><a href="#">4</a></li> -->
<!-- 												<li><a href="#">5</a></li> -->
<!-- 												<li><a href="#">»</a></li> -->

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
	
	<c:forEach items = "${resultlist}" var="tasklist">
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="${tasklist.tasklist_id}">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">
				任务单详情 <a href="javascript: void(0)" class="am-close am-close-spin"
					data-am-modal-close>&times;</a>
			</div>
			
			<div class="am-modal-bd">
			<!--startprint-->
				<table align="center">
					<tbody>
						<tr>
							<td>
								<p>任务单号：</p>
							</td>
							<td>
								<p>${tasklist.tasklist_id}</p>
							</td>
						</tr>
						
						<tr>
							<td>
								<p>订单号：</p>
							</td>
							<td>
								<p>${tasklist.order_id}</p>
							</td>
						</tr>
						
						<tr>
							<td>
								<p>分站：</p>
							</td>
							<td>
								<p>${tasklist.substation_id}</p>
							</td>
						</tr>
						
						<tr>
							<td>
								<p>创建日期：</p>
							</td>
							<td>
								<p>${tasklist.creatDate}</p>
							</td>
						</tr>
						
						
						
						<tr>
							<td>
								<p>任务类型：</p>
							</td>
							<td>
								<c:if test="${tasklist.taskType==1}">
									<p>送货</p>
								</c:if>
								<c:if test="${tasklist.taskType==3}">
									<p>退货</p>
								</c:if>
							</td>
						</tr>
						
						<tr>
							<td>
								<p>客户名：</p>
							</td>
							<td>
								<p>${tasklist.clientName}</p>
							</td>
						</tr>
						
						<tr>
							<td>
								<p>客户地址：</p>
							</td>
							<td>
								<p>${tasklist.clientAddress}</p>
							</td>
						</tr>
						
						<tr>
							<td>
								<p>客户电话：</p>
							</td>
							<td>
								<p>${tasklist.clientTel}</p>
							</td>
						</tr>
						
						<tr>
							<td>
								<p>是否需要发票：</p>
							</td>
							<td>
								<c:if test="${tasklist.is_invoice==2}">
								<p>是</p>
								</c:if>
								<c:if test="${tasklist.is_invoice==1}">
								<p>否</p>
								</c:if>
							</td>
						</tr>
					</tbody>
					
				</table>
				<!--endprint-->
				<button onclick="doPrint()">打印</button>
			</div>
			
			
		</div>
	</div>
	</c:forEach>
	
	<script src="assets/js/amazeui.min.js"></script>
	<script src="assets/js/amazeui.datatables.min.js"></script>
	<script src="assets/js/dataTables.responsive.min.js"></script>
	<script src="assets/js/app.js"></script>
</body>
</html>