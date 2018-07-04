<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
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
                                <div class="widget-title  am-cf"><h1>商品详情</h1></div>


                            </div>
                            <div class="widget-body  am-fr">
		
								
								<form class="am-form tpl-form-border-form tpl-form-border-br" name="" action="" method="post" >
						
							 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >商品代码</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="" class="am-form-field" value="${resultProduct.product_id}" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >商品名称</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="" class="am-form-field" value="${resultProduct.product_name}" disabled="disabled">
                                        </div>
                                    </div>
							 		<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >选择一级标题</label>
                                        <div class="am-u-sm-9">
                                           <select data-am-selected disabled="disabled">
 												 
  												<option value="${onetitlename}" selected>${onetitlename}</option>
  					                           
											</select>
                                        </div>
										
										 <label class="am-u-sm-3 am-form-label" >选择二级标题</label>
                                        <div class="am-u-sm-9">
                                           <select data-am-selected disabled="disabled">
 												 
  												<option value="${twotitlename}" selected>${twotitlename}</option>
  					                           
											</select>
                                        </div>
                                    </div>
									
									 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >计量单位</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="" class="am-form-field" value="${resultProduct.measurement}" disabled="disabled">
                                        </div>
                                    </div>
									 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >原价</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="" class="am-form-field" value="${resultProduct.original_price}" disabled="disabled">
                                        </div>
                                    </div>
									 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >商品折扣</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="" class="am-form-field" value="${resultProduct.discount}" disabled="disabled">
                                        </div>
                                    </div>
                                    
									 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >成本价</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="" class="am-form-field" value="${resultProduct.cost_price}" disabled="disabled" > 
                                        </div>
                                    </div>
                                    
									 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >型号</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="" class="am-form-field" value="${resultProduct.type}" disabled="disabled">
                                        </div>
                                    </div>
                                     <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >供应商</label>
                                        <div class="am-u-sm-9">
                                            <select data-am-selected disabled="disabled">
 												 <option value="${resultProduct.supplier_id}" selected >${suppliername}</option>
  												
  					                           
											</select>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >厂商</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="" class="am-form-field" value="${resultProduct.vendor}" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >保质期限</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="" class="am-form-field" value="${resultProduct.quality_period}" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >可否退货</label>
                                        <div class="am-u-sm-9">
                                            <select data-am-selected  disabled="disabled">
                                            	<c:if test="${resultProduct.is_return==1}">
                                            	<option value="yes" selected >可以</option>
                                            	</c:if>
 												 
 												 <c:if test="${resultProduct.is_return==0}">
 												 <option value="no"  selected>不可以</option>
 												 </c:if>
  												
  					                           
											</select>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >可否换货</label>
                                        <div class="am-u-sm-9">
                                            <select data-am-selected  disabled="disabled">
 												 <c:if test="${resultProduct.is_exchange==1}">
                                            	<option value="yes" selected >可以</option>
                                            	</c:if>
 												 
 												 <c:if test="${resultProduct.is_exchange==0}">
 												 <option value="no"  selected>不可以</option>
 												 </c:if>
  					                           
											</select>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >备注</label>
                                        <div class="am-u-sm-9">
                                            <textarea class="" rows="5" id=""  disabled="disabled">${resultProduct.notes}</textarea>
                                        </div>
                                    </div>
							  <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                        	<a class="button" href="manageProductServlet?pageNum=${pageNum}&action=searchProduct" >
                                            
                                            	<button type="button" class="am-btn am-btn-success"  >返回</button>	
                                            				  
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