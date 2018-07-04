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
    <script src="assets/js1/purchase1.js"></script>
    <script src="assets/css/print.css"></script>
    <script src="assets/js1/print.js"></script>
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
                                <div class="widget-title  am-cf"><h1>订单缺货进货安排</h1></div>


                            </div>
                            <div class="widget-body  am-fr">
								<form action="purchaseReturnServlet?action=searchstock" method="post" class="am-form tpl-form-border-form tpl-form-border-br" >	
									
        
                               
                               	 <div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <input type="text" class="am-form-field " placeholder="商品名称" name="proname1">
                                        <span class="am-input-group-btn">
            <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search"  type="submit"></button>
          </span>
                                    </div>
                                </div>
								</form>
								
							
								
							  <div class="am-u-sm-12">
                                    <table width="100%" style="table-layout:auto;" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                                        <thead>
                                            <tr>
												
                                                <th>一级分类</th>
                                                <th>二级分类</th>
                                                <th>商品代码</th>
												<th>商品名称</th>
												<th>订单编号</th>
												<th>缺货数量</th>
												<th>计量单位</th>
												<th>进货</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        
                                                                                                                     
               <c:forEach items="${resultList}" var="stock" >
				<tr>
					<td>${stock.onetitle_name}</td>
					<td>${stock.twotitle_name}</td>
					<td>${stock.prod_id}</td>
					<td>${stock.prod_name}</td>		
					<td>${stock.order_id}</td>	
					<td>${stock.outofstock}</td>	
					<td>${stock.measurement}</td>	
					<td>
				  <input type="number" id="pnum${stock.prod_id}" name="pnum"  placeholder="数量"  style="width=50px" required>
                     <input type="text" id="do_date${stock.prod_id}" name="do_date" placeholder="时间" data-am-datepicker="" readonly="">
                            <i class="am-icon-trash" onclick="show(${stock.prod_id},${stock.order_id})">进货</i> 
					
					
					</td>
				</tr>
			</c:forEach>
                                        
                                        
                                        
                                           <!--  <tr class="gradeX">
												
                                               <td>吃的</td>
                                                <td>方便面</td>
												<td>00000001</td>
                                                <td>红烧牛肉面</td>
												<td>2000</td>
                                                <td>500</td>
                                                <td>5000</td>
												<td>1000</td>
                                                <td>g</td>
                                                <td>
													
                                              <form action="" class="am-form tpl-form-border-form tpl-form-border-br" data-am-validator>	
                                               
                                                    <input type="text" name=""  placeholder="数量" style="width=50px">
                                                       <input type="text"  placeholder="时间" data-am-datepicker="" readonly="">
                                                            <i class="am-icon-trash" id="doc-confirm-toggle">进货</i> 
                                                     
                                                      
                                                   
                                                       
													</form>
                                                  
                                                </td>
                                            </tr> --> 
                                          
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
											<li><a href="purchaseReturnServlet?action=searchstock&pageno=${pageNum-1}">«</a></li>
										</c:if>
   
                                        <c:forEach begin="1" end="${pagecount}" var="p"  >
                                      
                                        
										<c:if test="${p==pageNum}">
											<li class="am-active"><a href="#">${p}</a></li>
										</c:if>
										<c:if test="${p!=pageNum}">
											<li><a href="purchaseReturnServlet?action=searchstock&pageno=${p}"  >${p}</a></li>      
										</c:if>
										
										</c:forEach>
										 <c:if test="${pageNum==pagecount}">
											<li class="am-disabled"><a href="#">»</a></li>
										</c:if>
                                        <c:if test="${pageNum!=pagecount}">
											<li><a href="purchaseReturnServlet?action=searchstock&pageno=${pageNum+1}">»</a></li>
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
    
<input type="hidden" id="hpnum"  value="">
<input type="hidden" id="hsupid"  value="">
<input type="hidden" id="hprodid" value="">
<input type="hidden" id="hdate"  value="">
<input type="hidden" id="hprice"  value="">
<input type="hidden" id="hsumprice"  value="">
<input type="hidden" id="horderid"  value="">

 <div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
  <div class="am-modal-dialog">
  <!--startprint-->
    <div class="am-modal-hd">确认单</div>
    <div class="am-modal-bd">
    <table align="center">
    <tr>
    <tr><td><span id="ps_id"></span></td></tr>
    <tr><td><span id="prod_id"></span></td></tr>
      <tr><td><span id="prod_name"></span></td></tr>
      <tr><td><span id="pnum"></span></td></tr>
      <tr><td><span id="order_id"></span></td></tr>
      <tr><td><span id="price"></span></td></tr>
      <tr><td><span id="sum_price"></span></td></tr>
      <tr><td><span id="do_date"></span></td></tr>
      </table>
    </div>
    <!--endprint-->
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
      <span class="am-modal-btn" onclick="doandprint()">打印购货单</span>
    </div>
  </div>
</div>
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/amazeui.datatables.min.js"></script>
    <script src="assets/js/dataTables.responsive.min.js"></script>
    <script src="assets/js/app.js"></script>
	<script>
	
	$(function() {
  $('#doc-modal-list').find('.am-icon-close').add('#doc-confirm-toggle').
    on('click', function() {
      $('#my-confirm').modal({
        relatedTarget: this,
        onConfirm: function(options) {
         
          alert("打印");
        },
        // closeOnConfirm: false,
        onCancel: function() {
          alert('算求，不弄了');
        }
      });
    });
});
	</script>
</body>
</html>