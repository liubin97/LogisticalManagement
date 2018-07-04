<!DOCTYPE HTML>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
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
</head>
<body data-type="widgets">
<script src="assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
    <%@include file="leftandtop.html"%>
    	<%String modifywhmsg=(String)request.getSession().getAttribute("modifywhmsg");
    	String wh_id=request.getParameter("wh_id");
		request.getSession().removeAttribute("modifywhmsg");%>
    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <div class="row-content am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title  am-cf"><h1>编辑库房</h1></div>


                        </div>
                        <div class="widget-body  am-fr">
                            <form class="am-form tpl-form-border-form tpl-form-border-br"  action="manageWhServlet?action=modifywh" method="post" >



 								<div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label" >库房id</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="whid" name="whid" class="am-form-field" value="库房1" readonly style="background:#CCCCCC">
                                        
                                    </div>
                                </div>
                                
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label" >库房名称</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="whname" class="am-form-field" value="" >
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label" >库房地址</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="whaddr" class="am-form-field" value="">
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label" >库管员</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="whadmin" class="am-form-field" value="">
                                    </div>

                                </div>

                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label" >库房级别</label>
                                    <div class="am-u-sm-9">
                                        <select data-am-selected name="selectlevel">

                                            <option value="1">分站库房 </option>

                                            <option value="0" >中心库房</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="submit" class="am-btn am-btn-primary">保存</button>
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
<script type="text/javascript">
window.onload=function(){
	$("#whid").val(<%=wh_id%>);
	var modifywhmsg="<%=modifywhmsg%>";
	if(modifywhmsg!="null"){
		alert(modifywhmsg);
	}
}
</script>
</body>
</html>