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
    <%String inoutmsg=(String)request.getSession().getAttribute("inoutmsg");
    request.getSession().removeAttribute("inoutmsg");%>
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
                                <div class="widget-title  am-cf"><h1>查询出入单</h1></div>


                            </div>
                            <div class="widget-body  am-fr">
							
							<form action="searchInfoServlet?action=searchinout" method="post" class="am-form tpl-form-border-form tpl-form-border-br" data-am-validator>	
										<div class="am-u-lg-2">
								 <input type="text" class="am-form-field " name="starttime" placeholder="起始时间" data-am-datepicker="" readonly="" required>
											
								</div>
								
									<div class="am-u-lg-2">
								 <input type="text" class="am-form-field" name="endtime" placeholder="终止时间" data-am-datepicker="" readonly="" required>
										
									</div>	
							
								
									<div class="am-u-lg-2">
										
                                   <input type="number" name="proid" placeholder="商品id" required>
                                
								</div>
									<div class="am-u-lg-2">
										
                                   <select data-am-selected name="inouttype">

  												<option value="入库" selected>入库</option>
  					                       
  				                             <option value="出库" >出库</option>
											</select>
                                
								</div>
								<div class="am-u-lg-2">
									
					 <button class="am-btn  am-btn-default am-btn-success am-icon-search" type="submit"></button>
					 </div>
							
									 </form>
                      
                                </div>

                                <div class="am-u-sm-12">
                                    <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                                        <thead>
                                            <tr>
                                                <th>类型</th>
                                                <th>单号</th>
                                                <th>仓库名</th>
												<th>商品代码</th>
												<th>商品名称</th>
												<th>单价</th>
												<th>出入库数量</th>
												<th>金额</th>
												<th>出入库日期</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                                                                          
               <c:forEach items="${resultList}" var="inout" >
				<tr>
					<td>${inout.type}</td>
					<td>${inout.listid}</td>
					<td>${inout.wh_name}</td>
					<td>${inout.product_id}</td>		
					<td>${inout.product_name}</td>	
					<td>${inout.price}</td>		
					<td>${inout.amount}</td>	
					<td>${inout.allprice}</td>	
					<td>${inout.date}</td>
				</tr>
			</c:forEach>
                                         
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
											<li><a href="searchInfoServlet?action=searchinout&pageno=${pageNum-1}">«</a></li>
										</c:if>
   
                                        <c:forEach begin="1" end="${pagecount}" var="p"  >
                                      
                                        
										<c:if test="${p==pageNum}">
											<li class="am-active"><a href="#">${p}</a></li>
										</c:if>
										<c:if test="${p!=pageNum}">
											<li><a href="searchInfoServlet?action=searchinout&pageno=${p}"  >${p}</a></li>      
										</c:if>
										
										</c:forEach>
										 <c:if test="${pageNum==pagecount}">
											<li class="am-disabled"><a href="#">»</a></li>
										</c:if>
                                        <c:if test="${pageNum!=pagecount}">
											<li><a href="searchInfoServlet?action=searchinout&pageno=${pageNum+1}">»</a></li>
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
 
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/amazeui.datatables.min.js"></script>
    <script src="assets/js/dataTables.responsive.min.js"></script>
    <script src="assets/js/app.js"></script>
    <script type="text/javascript">
	    window.onload=function(){
        var inoutmsg="<%=inoutmsg%>";
        if(inoutmsg!="null"){
            alert(inoutmsg);
        }
    }
	    </script>
</body>
</html>