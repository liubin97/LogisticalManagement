<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
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
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" >
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <script src="assets/js/echarts.min.js"></script>
    <link rel="stylesheet" href="assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="js/EditTwoTitle.js" ></script>
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
                                <div class="widget-title  am-cf"><h1>修改二级商品标题</h1></div>


                            </div>
                            <div class="widget-body  am-fr">
							
								
                        	<form class="am-form tpl-form-border-form tpl-form-border-br" name="updateTwoTitle" action="manageTitleServlet?action=updateTwoTitle" method="post" >
							 <input type="hidden" name="twotitle_id" class="am-form-field" value="${resultTwoTitle[0].twotitle_id}" >
							 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >二级商品标题</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="twotitle_name" id="twotitle_name" class="am-form-field" value="${resultTwoTitle[0].twotitle_name}" >
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >描述信息</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="twotitle_describe" class="am-form-field" value="${resultTwoTitle[0].twotitle_describe}">
                                        </div>
                                    </div>
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >选择一级标题</label>
                                        	<input type="hidden" id="select_value"  value="${resultTwoTitle[0].onetitle_id}"/>
                                        <div class="am-u-sm-9">
                                           <select data-am-selected name="oneTitleList" id="oneTitleList">
 												 
											</select>
                                        </div>
                                    </div>
							 	<script >
								//$("#oneTitleList").find("option[value='"+${resultTwoTitle[0].onetitle_id}+"']").attr("selected","selected");
							 	</script>
							  <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <a class="button" href="#" ><button type="submit" id="submitTwoTitle" class="am-btn am-btn-success" data-am-modal="{target: '#my-alert'}" >保存</button></a>
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
 <div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">二级商品标题</div>
    <div class="am-modal-bd">
      添加成功！
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn">确定</span>
    </div>
  </div>
</div>
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/amazeui.datatables.min.js"></script>
    <script src="assets/js/dataTables.responsive.min.js"></script>
    <script src="assets/js/app.js"></script>

</body>

</html>