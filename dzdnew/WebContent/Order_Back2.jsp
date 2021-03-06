<%--
  Created by IntelliJ IDEA.
  User: xiedong
  Date: 2018/6/22
  Time: 下午3:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<c:if  test="${manager_id == null}" >

    <c:redirect url="403.jsp"/>
</c:if>
<c:if  test="${manager_id != null}" >
    <!DOCTYPE html>
    <html>
    <head>
            <%--<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>--%>
            <%--<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>--%>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>退货</title>
        <meta name="keywords" content="index">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="renderer" content="webkit">
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <link rel="icon" type="image/png" href="assets/i/favicon.png">
        <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
        <!--font-Awesome -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" >

        <link rel="stylesheet" href="assets/css/amazeui.min.css" />
        <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css" />
        <link rel="stylesheet" href="assets/css/app.css">

        <script src="assets/js/echarts.min.js"></script>
        <script src="js/jquery-1.9.1.js"></script>
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
                            <a href="javascript:;">欢迎你, <span>${manager_name}</span> </a>
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
            <ul class="sidebar-nav">
                <li class="sidebar-nav-heading">客户中心</li>

                <!--客户管理-->
                <li class="sidebar-nav-link">
                    <a href="javascript:;" class="sidebar-nav-sub-title">
                        <i class="fa fa-users"></i> 客户管理
                        <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico sidebar-nav-sub-ico-rotate"></span>
                    </a>
                    <ul class="sidebar-nav sidebar-nav-sub" style="display: block;">
                        <li class="sidebar-nav-link">
                            <a href="selectUser.jsp">
                                <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 客户查询
                            </a>
                        </li>

                        <li class="sidebar-nav-link">
                            <a href="register.jsp" >
                                <span class="am-icon-angle-right sidebar-nav-link-logo"></span>新客户登记
                            </a>
                        </li>

                    </ul>
                </li>

                <!--订单管理-->
                <li class="sidebar-nav-link">
                    <a href="javascript:;" class="sidebar-nav-sub-title">
                        <i class="fa fa-newspaper-o"></i> 订单管理
                        <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico sidebar-nav-sub-ico-rotate"></span>
                    </a>
                    <ul class="sidebar-nav sidebar-nav-sub" style="display: block;">
                        <li class="sidebar-nav-link">
                            <a href="Order_Search.jsp" >
                                <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 订单查询
                            </a>
                        </li>

                        <li class="sidebar-nav-link">
                            <a href="Order.jsp">
                                <span class="am-icon-angle-right sidebar-nav-link-logo"></span>新订
                            </a>
                        </li>
                        <li class="sidebar-nav-link">
                            <a href="Order_Refund.jsp" >
                                <span class="am-icon-angle-right sidebar-nav-link-logo"></span>退订
                            </a>
                        </li>

                        <li class="sidebar-nav-link">
                            <a href="Order_Back.jsp" class="sub-active">
                                <span class="am-icon-angle-right sidebar-nav-link-logo"></span>退货
                            </a>
                        </li>

                    </ul>
                </li>

                <!--操作员工作量查询-->
                <li class="sidebar-nav-link">
                    <a href="Operator_Time_Search.jsp">
                        <i class="fa fa-clock-o"></i> 操作员工作量查询
                    </a>
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

                                <h1>退货</h1>

                            </div>
                            <div class="widget-body  am-fr">
                                <div class="row">

                                    <div class="am-u-lg-8 am-u-lg-offset-2">
                                        <div class="am-panel am-panel-primary">
                                            <div class="am-panel-hd">
                                                <h3 class="am-panel-title" style="text-align: center">退货</h3>
                                            </div>


                                            <form  action="ReturnOrderServlet?action=createRuturn" method="post" >
                                                <table class="am-table am-table-bordered am-table-radius am-table-striped">
                                                    <tbody>
                                                    <tr>
                                                        <td>订单号</td>
                                                        <td>${o.order_id}<input type="hidden" name="order_id" value="${o.order_id}">
                                                            <input type="hidden" name="prod_id" value="${o.prod_id}">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>订单类型</td>
                                                        <td>新订</td>
                                                    </tr>

                                                    <tr>
                                                        <td>订单状态</td>
                                                        <td>

                                                            <c:choose>
                                                                <c:when test="${o.status ==4}">
                                                                   完成
                                                                </c:when>
                                                                <c:when test="${o.status==2}">
                                                                    可调度
                                                                </c:when>
                                                            </c:choose>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>订单生成日期</td>
                                                        <td>${o.create_date}<input type="hidden" name="create_date" value="${o.create_date}"></td>
                                                    </tr>
                                                    <tr>
                                                        <td>要求完成日期</td>
                                                        <td>${o.finish_date}<input type="hidden" name="finish_date" value="${o.finish_date}"></td>

                                                    </tr>

                                                    <tr>
                                                        <td>收货人</td>
                                                        <td>${o.consignee}</td>

                                                    </tr>
                                                    <tr>
                                                        <td>收货人电话</td>
                                                        <td>${o.consignee_tel}</td>

                                                    </tr>


                                                    <tr>
                                                        <td>退货数量</td>
                                                        <td><input type="number" id="return_amount" name="amount" value="${o.amount}" required></td>

                                                    </tr>

                                                    <tr>
                                                        <td>退货原因</td>
                                                        <td><input type="text" name="reason" required></td>

                                                    </tr>
                                                    <tr>
                                                        <td>退货日期</td>
                                                        <td><input type="text" class="am-form-field " id="refund_time" name="refund_time" readonly>
                                                        </td>

                                                    </tr>

                                                    </tbody>
                                                </table>

                                                <button  type="submit" class=" am-btn am-btn-primary" style="width: 300px;margin-left: 35%;margin-bottom: 2%;" >提交</button>


                                            </form>

                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>

            $(function() {

                $(document).ready(function () {

                    //获取当前的时间
                    var now = new Date();
                    var time = now.getFullYear() + "-" +((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate();
                    $("#refund_time").val(time);
                    console.log("yyyy-mm-dd:"+time);
                });
            });

            function gettData(){
                var date = $("#date").val();
                console.log("当前获取的时间是"+date);
            }

            <%--//判断输入数量是否正确--%>
            <%--function checkAmount(){--%>

                <%--var total = ${o.amount};//获取总数量--%>
                <%--if($("#return_amount").val() < 0){--%>
                    <%--alert("输入数量不能为负");--%>
                <%--}--%>
                <%--if($("#return_amount").val() > total){--%>
                    <%--alert("输入数量有误");--%>
                <%--}--%>

            <%--}--%>
        </script>

        <script src="assets/js/amazeui.min.js"></script>
        <script src="assets/js/amazeui.datatables.min.js"></script>
        <script src="assets/js/dataTables.responsive.min.js"></script>
        <script src="assets/js/app.js"></script>

    </body>
    </html>

</c:if>

