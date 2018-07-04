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
                                <div class="widget-title  am-cf"><h1>库存量查询</h1></div>


                            </div>
                            <div class="widget-body  am-fr">

						<form action="searchInfoServlet?action=searchresnum"  method="post" >
                                <div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <input type="text" class="am-form-field " name="proname" placeholder="商品名称">
                                        <span class="am-input-group-btn">
            <button type="submit" class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="button"></button>
          </span>
                                    </div>
                                </div>
						</form>
                                <div class="am-u-sm-12">
                                    <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                                        <thead>
                                            <tr>
                                                <th>库房名称</th>
                                                <th>商品名称</th>
                                                <th>总库存量</th>
												<th>退回数量</th>
												<th>可分配量</th>
												<th>已分配量</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                                                      
               <c:forEach items="${resultList}" var="res" >
				<tr>
					<td>${res.whname}</td>
					<td>${res.proname}</td>
					<td>${res.res_num}</td>
					<td>${res.return_num}</td>		
					<td>${res.can_distribute}</td>	
					<td>${res.has_distribute}</td>		
					
				</tr>
			</c:forEach>
                                        
                                        
                                            <!--  <tr class="gradeX">
                                                <td>库房1</td>
                                                <td>红烧牛肉面</td>
                                                <td>1000</td>
												<td>200</td>
												<td>800</td>
												<td>300</td>
                               
                                            </tr>-->
								<!-- more data-->
								                </tbody>
                                    </table>
                                </div>
                                <div class="am-u-lg-12 am-cf">

                                    <div class="am-fr">
                                         <ul class="am-pagination tpl-pagination">
                                        
                                        
                                        
                                        <c:if test="${pageNum-1==0}">
											<li class="am-disabled"><a href="#">«</a></li>
										</c:if>
                                        <c:if test="${pageNum-1!=0}">
											<li><a href="searchInfoServlet?action=searchresnum&pageno=${pageNum-1}">«</a></li>
										</c:if>
   
                                        <c:forEach begin="1" end="${pagecount}" var="p"  >
                                      
                                        
										<c:if test="${p==pageNum}">
											<li class="am-active"><a href="#">${p}</a></li>
										</c:if>
										<c:if test="${p!=pageNum}">
											<li><a href="searchInfoServlet?action=searchresnum&pageno=${p}"  >${p}</a></li>      
										</c:if>
										
										</c:forEach>
										 <c:if test="${pageNum==pagecount}">
											<li class="am-disabled"><a href="#">»</a></li>
										</c:if>
                                        <c:if test="${pageNum!=pagecount}">
											<li><a href="searchInfoServlet?action=searchresnum&pageno=${pageNum+1}">»</a></li>
										</c:if>
										     
                                         <!--    <li class="am-disabled"><a href="#">«</a></li>
                                            <li class="am-active"><a href="#">1</a></li>
                                            <li><a href="#">2</a></li>
                                            <li><a href="#">3</a></li>
                                            <li><a href="#">4</a></li>
                                            <li><a href="#">5</a></li>
                                            <li><a href="#">»</a></li> -->
                                        </ul>
                                    </div>
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