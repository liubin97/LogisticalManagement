<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>中心库房调拨出库</title>
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
    <script src="assets/js/submit.js"></script>
</head>

<body data-type="widgets">
<script src="assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
    <header>
        <!-- logo -->
        <div class="am-fl tpl-header-logo">
            <a href="javascript:;"><img src="assets/img/logo.png" alt=""></a>
        </div>
        <!-- 右侧内容 -->
        <div class="tpl-header-fluid">
            <!-- 侧边切换 -->
            <div class="am-fl tpl-header-switch-button am-icon-list">
                    <span>

                </span>
            </div>

            <!-- 其它功能-->
            <div class="am-fr tpl-header-navbar">
                <ul>
                    <!-- 欢迎语 -->
                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a href="javascript:;">欢迎使用</a>
                    </li>
                    <!-- 退出 -->
                    <li class="am-text-sm">
                        <a href="javascript:;">
                            <span class="am-icon-sign-out"></span> 退出
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </header>
    <!-- 风格切换 -->
    <div class="tpl-skiner">
        <div class="tpl-skiner-toggle am-icon-cog">
        </div>
        <div class="tpl-skiner-content">
            <div class="tpl-skiner-content-title">
                选择主题
            </div>
            <div class="tpl-skiner-content-bar">
                <span class="skiner-color skiner-white" data-color="theme-white"></span>
                <span class="skiner-color skiner-black" data-color="theme-black"></span>
            </div>
        </div>
    </div>
    <!-- 侧边导航栏 -->
    <div class="left-sidebar">
        <!-- 菜单 -->
        <li class="sidebar-nav-heading">Storage Room Management </li>
        <ul class="sidebar-nav">
            <li class="sidebar-nav-link">
                <a href="javascript:;" class="sidebar-nav-sub-title">
                    <i class="am-icon-table sidebar-nav-link-logo"></i> 中心库房管理
                    <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico sidebar-nav-sub-ico-rotate"></span>
                </a>
                <ul class="sidebar-nav sidebar-nav-sub" style="display:block">
                    <li class="sidebar-nav-link">
                        <a href="Central warehouse purchases.jsp">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 中心库房购货入库
                        </a>
                    </li>

                    <li class="sidebar-nav-link">
                        <a href="Central%20warehouse%20transfer%20out.jsp"  class="sub-active">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 中心库房调拨出库
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="Print out receipt.jsp">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 中心库房打印出库单
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="Print%20out%20distribution.jsp" >
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 中心库房打印分发单
                        </a>
                    </li>
                </ul>
            </li>

            <li class="sidebar-nav-link">


                <a href="javascript:;" class="sidebar-nav-sub-title">
                    <i class="am-icon-table sidebar-nav-link-logo"></i> 退货管理
                    <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico sidebar-nav-sub-ico-rotate"></span>
                </a>
                <ul class="sidebar-nav sidebar-nav-sub" style="display:block">
                    <li class="sidebar-nav-link">
                        <a href="Central%20return%20in.jsp">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 中心库房退货入库
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="Central%20return%20out.jsp">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 中心库房退货出库
                        </a>
                    </li>
                </ul>
            </li>

        </ul>
    </div>

    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <div class="row-content am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title  am-cf"><h1>中心库房调拨出库</h1></div>
                        </div>

                        <!--查询表单-->
                        <div class="widget-body  am-fr">
                            <form class="am-form" action="cenWarehouseServlet?action=searchTaskDate" method="post" data-am-validator>
                                <div class="am-form-group">
                                    <div class="am-u-sm-6 am-u-lg-centered">
                                        <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                            <input type="text" name="search" id="search" class="am-form-field" placeholder="请选择日期查询任务单" data-am-datepicker readonly required >
                                            <span id="warn"></span>
                                            <span class="am-input-group-btn">
                                            <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="submit"></button>
                                            </span>
                                        </div>
                                    </div>

                                </div>
                            </form>

                            <form action="cenWarehouseServlet?action=submitWhOut" method="post">
                            <div class="am-u-sm-12">
                                    <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                                        <thead>
                                        <tr>
                                            <th>选择操作</th>
                                            <th>商品信息</th>
                                            <th>分站名称</th>
                                            <th>出库数量</th>
                                            <th>时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${resultList}" var="item">
                                        <tr>
                                            <td>
                                                <input name="chk" type="checkbox"  value="${item.task_list_id}">
                                            </td>
                                            <td name="productname">${item.product_name}</td>
                                            <td name="substation">${item.substation}</td>
                                            <td name="productnum">${item.product_num}</td>
                                            <td name="finishdate">${item.finish_date}</td>
                                        </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                            </div>
                                <div class="am-u-sm-12">
                                    <button type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success" id="doc-confirm-toggle" onclick="selectCol()">
                                        调拨出库
                                    </button>
                                </div>
                            </form>

                            <div class="am-u-lg-12 am-cf">
                                <div class="am-fr">
                                    <ul class="am-pagination tpl-pagination">
                                        <li><a href="cenWarehouseServlet?action=searchTaskDate&pageNum=${(pageNum<=1)?pageNum:(pageNum-1)}">«</a></li>
                                        <c:forEach begin="1" end="${pagecount}" var="p">
	                                        <c:if test="${p==pageNum}">
	                                            <li class="am-active"><a>${p}</a></li>
	                                        </c:if>
	                                        <c:if test="${p!=pageNum}">
	                                            <a href="cenWarehouseServlet?action=searchTaskDate&pageNum=${p}">${p}</a>
	                                        </c:if>
                                   	 	</c:forEach>
                                        <li><a href="cenWarehouseServlet?action=searchTaskDate&pageNum=${(pageNum>=pagecount)?pagecount:(pageNum+1)}">»</a></li>
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
<button type="button" class="am-btn am-btn-warning">
    Confirm
</button>

<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
    <div class="am-modal-dialog">
        <div class="am-modal-hd"><h3>调拨出库</h3></div>
        <div class="am-modal-bd">
                <p>你选择的调拨信息如下，是否要调拨出库?</p>
            <table class="am-table">
                <thead>
                <tr>
                    <th>分站库房</th>
                    <th>商品名称</th>
                    <th>出库数量</th>
                </tr>
                </thead>
                <tbody align="left" id="tbody">

                </tbody>
            </table>

        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>出库</span>
        </div>
    </div>
</div>
<script>

</script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/amazeui.datatables.min.js"></script>
<script src="assets/js/dataTables.responsive.min.js"></script>
<script src="assets/js/app.js"></script>

</body>

</html>