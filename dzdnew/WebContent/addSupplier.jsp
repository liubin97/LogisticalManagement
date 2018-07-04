<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
<!DOCTYPE html>
<html lang="cn">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
    <meta name="description" content="è¿æ¯ä¸ä¸ª index é¡µé¢">
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
	<script type="text/javascript" src="js/addSupplier.js" ></script>
</head>

<body data-type="widgets">
    <script src="assets/js/theme.js"></script>
    <div class="am-g tpl-g">
        <!-- 头部 -->
         <%@include file="leftandtop.html"%>
        <!-- 内容区域 -->
        <div class="tpl-content-wrapper">
            <div class="row-content am-cf">
                <div class="row">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title  am-cf"><h1>新增供应商</h1></div>


                            </div>
                            <div class="widget-body  am-fr">

								
						<form class="am-form tpl-form-border-form tpl-form-border-br" name="newSupplierEntry" action="supplierManagementServlet?action=register" method="post" >
						
							 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >供应商名称</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="supplierNameEntry" id="supplierNameEntry" class="am-form-field" placeholder="供应商名称" >
                                            <span id="span1"></span>
                                            <span id="span2"></span>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >地址</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="supplierAddressEntry" id="supplierAddressEntry" class="am-form-field" placeholder="地址">
                                            <span id="span3"></span>
                                        </div>
                                    </div>
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >联系人</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="contactNameEntry" id="contactNameEntry" class="am-form-field" placeholder="联系人">
                                        	<span id="span4"></span>
                                        </div>
                                    </div>
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >联系电话</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="contactPhoneEntry" id="contactPhoneEntry" class="am-form-field" placeholder="联系电话">
                                        	<span id="span5"></span>
                                        </div>
                                    </div>
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >开户行</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="bankOfRegistrationEntry" id="bankOfRegistrationEntry" class="am-form-field" placeholder="开户行">
                                        	<span id="span6"></span>
                                        </div>
                                    </div>
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >银行账户</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="bankAccountEntry" id="bankAccountEntry" class="am-form-field" placeholder="银行账户">
                                        	<span id="span7"></span>
                                        </div>
                                    </div>
									<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >传真</label>
                                        <div class="am-u-sm-9">
                                       		<input type="text" name="faxNoEntry" id="faxNoEntry" class="am-form-field" placeholder="传真">
                                       		<span id="span8"></span>
                                       	</div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >邮编</label>
                                        <div class="am-u-sm-9">
                                       		<input type="text" name="zipCodeEntry" id="zipCodeEntry" class="am-form-field" placeholder="邮编">
                                       		<span id="span9"></span>
                                       	</div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >法人</label>
                                        <div class="am-u-sm-9">
                                       		<input type="text" name="legalPersonEntry" id="legalPersonEntry" class="am-form-field" placeholder="法人">
                                       		<span id="span10"></span>
                                       	</div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >备注</label>
                                        <div class="am-u-sm-9">
                                       		<input type="text" name="remarkEntry" id="remarkEntry" class="am-form-field" placeholder="备注">
                                       		<span id="span11"></span>
                                       	</div>
                                    </div>
							 
							  		<div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <a class="button" href="###" ><button type="submit" class="am-btn am-btn-primary">保存</button></a>
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