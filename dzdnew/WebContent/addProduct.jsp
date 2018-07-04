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
	<script type="text/javascript" src="js/addProduct.js"></script>
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
                                <div class="widget-title  am-cf"><h1>新增商品</h1></div>


                            </div>
                            <div class="widget-body  am-fr">
		
								
								<form class="am-form tpl-form-border-form tpl-form-border-br" name="addProduct" id="addProduct" action="manageProductServlet?action=addProduct" method="post" >
						<!--  
							 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >商品代码</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="" class="am-form-field" placeholder="商品代码" >
                                        </div>
                                    </div>-->
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >商品名称</label>
                                        <div class="am-u-sm-9" id="product_name_div">
                                            <input type="text" name="product_name" id="product_name" class="am-form-field" placeholder="商品名称" required="required">
                                        </div>
                                    </div>
							 		<div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >选择一级标题</label>
                                        <div class="am-u-sm-9">
                                           <select data-am-selected id="oneTitleList" name="oneTitleList">
 												 
											</select>
                                        </div>
										
										 <label class="am-u-sm-3 am-form-label" >选择二级标题</label>
                                        <div class="am-u-sm-9">
                                           <select data-am-selected name="twotitle_id" id="twotitle_id">
 												 
											</select>
                                        </div>
                                    </div>
									
									 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >计量单位</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="measurement" id="measurement" class="am-form-field" placeholder="计量单位" required="required">
                                        </div>
                                    </div>
									 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >原价</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="original_price" id="original_price" class="am-form-field" placeholder="原价" required="required">
                                        </div>
                                    </div>
									 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >商品折扣</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="discount" id="discount" class="am-form-field" placeholder="商品折扣" required="required" >
                                        </div>
                                    </div>
                                    
									 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >成本价</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="cost_price" id="cost_price" class="am-form-field" placeholder="成本价" required="required">
                                        </div>
                                    </div>
                                    
									 <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >型号</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="type" class="am-form-field" placeholder="型号"  >
                                        </div>
                                    </div>
                                     <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >供应商</label>
                                        <div class="am-u-sm-9">
                                             <select data-am-selected id="supplier_id" name="supplier_id">
 												 
  					                           
											</select>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >厂商</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="vendor" id="vendor" class="am-form-field" placeholder="厂商" required="required">
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >保质期限</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="quality_period" class="am-form-field " placeholder="保质期限" data-am-datepicker="" readonly="readonly">
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >可否退货</label>
                                        <div class="am-u-sm-9">
                                            <select data-am-selected id="is_return" name="is_return">
 												 <option value=1 selected >可以</option>
  												<option value=0 >不可以</option>
  					                           
											</select>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >可否换货</label>
                                        <div class="am-u-sm-9">
                                            <select data-am-selected id="is_exchange" name="is_exchange">
 												 <option value=1 selected >可以</option>
  												<option value=0 >不可以</option>
  					                           
											</select>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label" >备注</label>
                                        <div class="am-u-sm-9">
                                            <textarea class="" rows="5" id="notes" name="notes" placeholder="请输入备注"></textarea>
                                        </div>
                                    </div>
							  <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <input type="button"  id="submitProduct" class="am-btn am-btn-success" value="提交">
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