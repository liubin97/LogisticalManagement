<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html lang="cn">

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
    <script type="text/javascript" src="js/addOneTitle.js"></script>
    
</head>
<body data-type="widgets">
    <script src="assets/js/theme.js"></script>
    <div class="am-g tpl-g">
    <%@include file="leftandtop.html"%>
    
        


        <!-- 内容区域 -->
        <div class="tpl-content-wrapper">
            <div class="row-content am-cf">
                <div class="row">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title  am-cf"><h1>添加一级商品标题</h1></div>


                            </div>
                            <div class="widget-body  am-fr">

                        	<form class="am-form tpl-form-border-form tpl-form-border-br" name="addOneTitle" action="manageTitleServlet?action=addone" method="post" >
						
							 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >一级商品标题</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="oneTitle_name" id="oneTitle_name" class="am-form-field" placeholder="一级商品标题" required="required" >
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >描述信息</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="oneTitle_describe" class="am-form-field" placeholder="描述信息" required="required" >
                                        </div>
                                    </div>
							 
							  <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <a class="button" href="#" >
                                            	<button type="submit" id="submitOneTitle" class="am-btn am-btn-success" disabled="disabled" >保存</button>
                                            </a>
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