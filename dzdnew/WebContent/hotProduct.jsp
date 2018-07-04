<!DOCTYPE HTML>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
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
       
        <!-- 内容区域 -->
        <div class="tpl-content-wrapper">
            <div class="row-content am-cf">
                <div class="row">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title  am-cf"><h1>TOP5畅销商品</h1></div>


                            </div>
							 <div class="widget-body  am-fr">
							<form action="searchInfoServlet?action=hotproduct" method="post" class="am-form tpl-form-border-form tpl-form-border-br" data-am-validator>	
										<div class="am-u-lg-2">
								 <input type="text" class="am-form-field " name="starttime" placeholder="起始时间" data-am-datepicker="" readonly="" required>
											
								</div>
								
									<div class="am-u-lg-2">
								 <input type="text" class="am-form-field" name="endtime" placeholder="终止时间" data-am-datepicker="" readonly="" required>
										
									</div>	
							
								
								 <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="submit"></button>
							
								
							
									 </form>
							
                             <div class="am-u-sm-12">
                                    <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                                        <thead>
                                            <tr>
                                                <th>商品编号</th>
                                                <th>商品名称</th>
                                                <th>一级分类</th>
												<th>二级分类</th>
												<th>原价</th>
												<th>成本</th>
												<th>折扣</th>
												<th>供应商名称</th>
												<th>销量</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                                                                                                         
               <c:forEach items="${resultList}" var="hp" >
				<tr>
					<td>${hp.product_id}</td>
					<td>${hp.product_name}</td>
					<td>${hp.onetitle}</td>
					<td>${hp.twotitle}</td>		
					<td>${hp.original_price}</td>	
					<td>${hp.cost_price}</td>		
					<td>${hp.discount}</td>	
					<td>${hp.supplier_name}</td>	
					<td>${hp.sellnum}</td>
				</tr>
			</c:forEach>
								<!-- more data-->
								                </tbody>
                                    </table>
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