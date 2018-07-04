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
    <script src="assets/js1/return.js"></script>
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
                                <div class="widget-title  am-cf"><h1>退货安排</h1></div>


                            </div>
                            <div class="widget-body  am-fr">
								
									
                                  
							<form action="purchaseReturnServlet?action=searchreturn" method="post" class="am-form tpl-form-border-form tpl-form-border-br" data-am-validator>	
										<div class="am-u-lg-2">
								 <input type="text" name="starttime" class="am-form-field " placeholder="进货起始时间" data-am-datepicker="" readonly="" required>
											
								</div>
								
									<div class="am-u-lg-2">
								 <input type="text" name="endtime" class="am-form-field" placeholder="进货终止时间" data-am-datepicker="" readonly="" required>
										
									</div>	
							
								
									<div class="am-u-lg-2">
										
                                   <input type="number" name="prod_id" placeholder="商品编号" required>
                                
								</div>
								<div class="am-u-lg-2">
										
                                   <input type="number" name="supplier_id" placeholder="供应商id" required>
                                
								</div>
								
									
					 <button class="am-btn  am-btn-default am-btn-success am-icon-search" type="submit"></button>
							
                             
								</form>
								
							
								
							  <div class="am-u-sm-12">
                                    <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                                        <thead>
                                            <tr>
												
												<th>供应商</th>
                                                <th>商品编号</th>
                                                <th>商品名称</th>
                                                <th>商品单价</th>
												<th>退货数量</th>
												<th>退货入库时间</th>
												<th>退货</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           
                <c:forEach items="${resultList}" var="rsr"  varStatus="abc">
				<tr id="abc${abc.count}">
					<td>${rsr.supplier_id}</td>
					<td>${rsr.prod_id}</td>
					<td>${rsr.product_name}</td>
					<td>${rsr.price}</td>
					<td>${rsr.rs_num}</td>		
					<td>${rsr.date}</td>	
				
					<td>
				 
                    
                            <i class="am-icon-trash" onclick="tuihuo(${abc.count},${rsr.supplier_id},${rsr.prod_id},'${rsr.product_name}',${rsr.price},${rsr.rs_num},${rsr.criid})">退货</i> 
					
					
					</td>
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
											<li><a href="purchaseReturnServlet?action=searchreturn&pageno=${pageNum-1}">«</a></li>
										</c:if>
   
                                        <c:forEach begin="1" end="${pagecount}" var="p"  >
                                      
                                        
										<c:if test="${p==pageNum}">
											<li class="am-active"><a href="#">${p}</a></li>
										</c:if>
										<c:if test="${p!=pageNum}">
											<li><a href="purchaseReturnServlet?action=searchreturn&pageno=${p}"  >${p}</a></li>      
										</c:if>
										
										</c:forEach>
										 <c:if test="${pageNum==pagecount}">
											<li class="am-disabled"><a href="#">»</a></li>
										</c:if>
                                        <c:if test="${pageNum!=pagecount}">
											<li><a href="purchaseReturnServlet?action=searchreturn&pageno=${pageNum+1}">»</a></li>
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
 
	<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">Amaze UI</div>
    <div class="am-modal-bd">
      确定退货方便面50件？
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
      <span class="am-modal-btn" data-am-modal-confirm>打印退货单</span>
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
          var $link = $(this.relatedTarget).prev('a');
          var msg = $link.length ? '你要删除的链接 ID 为 ' + $link.data('id') :
            '确定了，但不知道要整哪样';
          alert(msg);
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