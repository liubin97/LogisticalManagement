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
    <title>回执录入</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
	<!--font-Awesome -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" >
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <script src="assets/js/echarts.min.js"></script>
    <link rel="stylesheet" href="assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="assets/js/jquery.min.js"></script>
    
    <script type="text/javascript">
    	function entryreceipt(){
    		
    		var form = document.getElementById("submitForm");
    		
    		form.clientsatis.value=document.getElementById("sss").value;
    		form.note.value=document.getElementById("notenote").value;
    		alert("提交成功");
    		form.submit();
    	}
    </script>
    
</head>
<body>

<form id="submitForm"  action="recepitServlet">
	<input type="hidden" name="action" value="1">
	
	<input type="hidden" name="tasklist_id" value="${tasklist_id}">
	<input type="hidden" name="beginTime" value="${beginTime}">
	<input type="hidden" name = "taskState" value="${taskState }">
	<input type="hidden" name = "taskType" value="${taskType }">
	<input type="hidden" name = "pageNum" value="${pageNum}">
	<input type="hidden" name = "substation_id" value="${substation_id}">
	
	<input type="hidden" name="clientsatis" value="">
	<input type="hidden" name="note" value="">
	
	
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
                                <div class="widget-title  am-cf"><h1>回执录入</h1></div>


                            </div>
                            <div class="widget-body  am-fr">

                        	<form class="am-form tpl-form-border-form tpl-form-border-br" name="" action="" method="post" >
									
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >任务单号</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="" class="am-form-field" placeholder="${tasklist_id}" disabled="disabled">
                                        </div>
                                    </div>
								
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >客户满意度</label>
                                        <div class="am-u-sm-9">
                                           <select data-am-selected  id="sss">	 
  												<option value="1">满意</option>
  					                       		<option value="2">一般</option>
  				                               	<option value="3">不满意</option>  				                             
											</select>
                                        </div>
                                    </div>
								
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >备注</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" id="notenote" class="am-form-field" placeholder="备注">
                                        </div>
                                    </div>
									
							  		<div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <button onclick="entryreceipt()" type="button" class="am-btn am-btn-success" >保存</button>                                          
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