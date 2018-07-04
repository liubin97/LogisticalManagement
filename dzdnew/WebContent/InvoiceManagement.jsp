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
<title>发票查询领取</title>
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
									<h1>发票</h1>
								</div>

							</div>
							<div class="widget-body  am-fr">
								<form class="am-form am-form-inline" action="invoiceManagementServlet" method="post"
									role="form">
									<div class="row">
										<div class="am-u-lg-10 am-u-lg-centered">
											
											<label class=" am-form-label" >任务单号</label>
											
											<div class="am-form-group">
												<input type="text" name="querytasklist_id"
													class="am-form-field" value="${tasklist_id}" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" required>
											</div>

											<div class="am-form-group">
												<button type="submit" class="am-btn am-btn-primary">
													<em class="fa fa-search">提交</em>
												</button>
											</div>
										</div>
									</div>
								</form>
								<c:forEach items = "${list}" var="inv">
								<form class="am-form tpl-form-border-form tpl-form-border-br" name="" action="" method="post" >
						
							 		<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >任务单号：</label>
                                        <div class="am-u-sm-9">
                                            <input name="tasklist_id" type="text" class="am-form-field" placeholder="${tasklist_id}" disabled="disabled">
                                        </div>
                                    </div>
								
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >分站：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="substation_id" class="am-form-field" placeholder="${inv.substation}" disabled="disabled">
                                        </div>
                                    </div>
									
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >发票号：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="invoice_id" class="am-form-field" placeholder="${inv.invoice_id}" disabled="disabled">
                                        </div>
                                    </div>
									
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >发票状态：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="invoice_status" class="am-form-field" placeholder="${inv.invoiceStatus}" disabled="disabled">
                                        </div>
                                    </div>
									
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >发票金额：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="invoice_price" class="am-form-field" placeholder="${inv.invoice_price}" disabled="disabled">
                                        </div>
                                    </div>
									
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >领用日期：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="use_date" class="am-form-field" placeholder="${inv.useDate}" disabled="disabled">
                                        </div>
                                    </div>
									
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >作废日期：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="invalid_date" class="am-form-field" placeholder="${inv.invaliddDate}" disabled="disabled">
                                        </div>
                                    </div>
							 	
							  		
						
								</form>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>


	<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">提示</div>
			<div class="am-modal-bd">确定删除？</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn">确定</span> <span class="am-modal-btn">取消</span>
			</div>
		</div>
	</div>




	<script src="assets/js/amazeui.min.js"></script>
	<script src="assets/js/amazeui.datatables.min.js"></script>
	<script src="assets/js/dataTables.responsive.min.js"></script>
	<script src="assets/js/app.js"></script>
</body>
</html>