<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
<!DOCTYPE html>

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
	<script type="text/javascript" src="js/manageProduct.js"></script>
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
                                <div class="widget-title  am-cf"><h1>商品查询与管理</h1></div>


                            </div>
                            
							<form class="am-form tpl-form-border-form tpl-form-border-br" name="selectTitle" action="manageProductServlet?action=searchProduct" method="post" >
          					<div class="widget-body  am-fr">
          					<div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
                                    <div class="am-form-group tpl-table-list-select">
             							<select data-am-selected="{btnSize: 'sm'}" name="selectType" id="selectType">
											<option value="oneTitle">一级分类</option>
											<option value="twoTitle">二级分类</option>
											<option value="productName">商品名称</option>
											<option value="productCode">商品代码</option>
										</select>
									</div>
								</div>
                                <div class="am-u-sm-12 am-u-md-12 am-u-lg-3 ">
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <input type="text" class="am-form-field " name="selectText" id="selectText" >
                                        <span class="am-input-group-btn">
					            <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="submit"></button>
					          </span>
                                    </div>
                                </div>
							</form>
                                <div class="am-u-sm-12">
                                    <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                                        <thead>
                                            <tr>
                                                <th>商品编号</th>
                                                <th>商品名称</th>
                                                <th>一级分类</th>
												<th>二级分类</th>
												<th>计量单位</th>
												<th>原价</th>
												<th>商品折扣</th>
												<th>...</th>
												
                                            </tr>
                                        </thead>
                                        <tbody>
                                       		<c:forEach items="${resultList}" var="result" varStatus="loop">
                                       		 <tr class="gradeX">
                                       		 <td>${result.product_id}</td>
                                                <td>${result.product_name}</td>
                                                <td>${oneTitleName[loop.count-1]}</td>
												<td>${twoTitleName[loop.count-1]}</td>
                                                <td>${result.measurement}</td>
                                                <td>${result.original_price}</td>
												 <td>${result.discount}</td>
                                                <td>...</td>
                                                <td>
                                                    <div class="tpl-table-black-operation">
                                                    	 <a href="manageProductServlet?action=productDetail&product_id=${result.product_id}">
                                                            <i class="am-icon-table"></i> 详情
                                                        </a>
                                                        <a href="manageProductServlet?action=modifyProduct&product_id=${result.product_id}">
                                                            <i class="am-icon-pencil"></i> 编辑
                                                        </a>
                                                        <a href="manageProductServlet?action=deleteProduct&product_id=${result.product_id}" class="tpl-table-black-operation-del">
                                                            <i class="am-icon-trash"></i> 删除
                                                        </a>
                                                    </div>
                                                </td>
                                            </tr>
                                       		</c:forEach>
                                       		</tbody>
                                       		</table>
                                       		</div>
                                       		
                                       		<div class="am-u-lg-12 am-cf">
<!-- 动态页码 -->
                                    			<div class="am-fr">
			                                        <ul class="am-pagination tpl-pagination">
				                                        <li class="am-disabled"><a href="#">«</a></li>
				                                        <c:forEach begin="1" end="${pagecount}" var="p"  >
															<c:if test="${p==pageNum}">
															<li class="am-active"><a href="#">${p}</a></li>
															</c:if>
															<c:if test="${p!=pageNum}">
															<li><a href="manageProductServlet?pageNum=${p}&action=searchProduct"  >${p}</a></li>
															</c:if>
															</c:forEach>
				                                            <li><a href="#">»</a></li>
			                                        </ul>
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