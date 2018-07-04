<%--
  Created by IntelliJ IDEA.
  User: xiedong
  Date: 2018/6/26
  Time: 下午8:19
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
    <%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>--%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>操作员工作量查询</title>
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
    <!--jQuery多级联动插件-->
    <script src="cxSelect/js/jquery.cxselect.js"></script>

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
                        <a href="Order_Back.jsp">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>退货
                        </a>
                    </li>

                </ul>
            </li>

            <!--操作员工作量查询-->
            <li class="sidebar-nav-link">
                <a href="Operator_Time_Search.jsp" class="sub-active">
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

                            <h1>操作员工作量查询</h1>

                        </div>
                        <div class="widget-body  am-fr">

                            <div class="row">
                                <div class="am-u-lg-10 am-u-lg-offset-2">
                                    <form action="SelectOperatorServlet?action=operator" method="post" class="am-form tpl-form-border-form tpl-form-border-br am-form am-form-inline" data-am-validator>

                                        <div class="am-form-group">
                                            <input type="text" name="begin" class="am-form-field " placeholder="起始时间" data-am-datepicker="" readonly="">

                                        </div>

                                        <div class="am-form-group">
                                            <input type="text" name="end" class="am-form-field" placeholder="终止时间" data-am-datepicker="" readonly="" >

                                        </div>


                                        <div class="am-form-group">
                                            <select id="doc-select-1" name="type" style="width: 80px;" >
                                                <option value="0">订单类型</option>
                                                <option value="0">全部</option>
                                                <option value="1">新订</option>
                                                <option value="2">退订</option>
                                                <option value="3">退货</option>
                                            </select>
                                        </div>


                                        <div class="am-form-group" id="op" style="width: 100px">
                                            <select  class="operator" id="operator" name="operator" required="required"></select>
                                        </div>


                                        <div class="am-form-group">

                                            <input type="text" name="prod_name" placeholder="商品名称">

                                        </div>
                                            <input type="hidden" name="operator_name" id="operator_name">

                                        <div class="am-form-group">
                                            <button type="submit" class="am-btn am-btn-primary"><em class="fa fa-search"></em></button>
                                        </div>


                                    </form>
                                </div>
                            </div>

                            <%--接收数据--%>
                            <div class="row" >
                                <div class="am-u-lg-8 am-u-lg-offset-2" style="margin-top: 50px">

                                    <c:if test="${flag == 5||flag==6||flag==7||flag== 8}">
                                      <%--保存总利润--%>
                                        <c:set var="total_profit" value="0"/>
                                        <div class="am-panel am-panel-primary">
                                            <div class="am-panel-hd">

                                                <h3 class="am-panel-title" style="text-align: center">姓名：${operator_name} &nbsp;&nbsp;&nbsp; ID：${operator_id}</h3>

                                            </div>


                                            <c:if test="${flag == 5||flag== 8}">
                                                <c:set var="Orderprofit" value="0"/>
                                                <h5 style="text-align: center">商品新订情况</h5>

                                                <table class="am-table am-table-bordered am-table-radius am-table-striped">
                                                    <thead>
                                                    <tr>
                                                        <th>商品名</th>
                                                        <th>笔数</th>
                                                        <th>数量</th>
                                                        <th>金额</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${newInfo}" var="n">
                                                    <tr>
                                                        <td>${n.prod_name}</td>
                                                        <td>${n.count_num}</td>
                                                        <td>${n.amount}</td>
                                                        <td>￥${n.sum_money}</td>
                                                        <c:set var="Orderprofit" value="${Orderprofit+n.sum_money}"/>

                                                    </tr>
                                                    </c:forEach>
                                                    <tr>

                                                        <td colspan="4" style="text-align: center"><strong> 总金额：</strong> ￥${Orderprofit}</td>
                                                    </tr>
                                                    </tbody>
                                                </table>

                                            </c:if>


                                            <c:if test="${flag == 6||flag== 8}">
                                                <c:set var="Reprofit" value="0"/>
                                                <h5 style="text-align: center">退货情况</h5>
                                                <table class="am-table am-table-bordered am-table-radius am-table-striped">
                                                    <thead>
                                                    <tr>
                                                        <th>商品名</th>
                                                        <th>笔数</th>
                                                        <th>数量</th>
                                                        <th>金额</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${reInfo}" var="re">
                                                    <tr>
                                                        <td>${re.prod_name}</td>
                                                        <td>${re.count_num}</td>
                                                        <td>${re.amount}</td>
                                                        <td>￥${re.sum_money}</td>
                                                        <c:set var="Reprofit" value="${re.sum_money+Reprofit}" />

                                                    </tr>
                                                    </c:forEach>
                                                    <tr>
                                                        <td colspan="4" style="text-align: center"><strong> 总金额：</strong> ￥${Reprofit}</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </c:if>


                                            <c:if test="${flag == 7||flag== 8}">
                                                <c:set var="Unprofit" value="0"/>
                                                <h5 style="text-align: center">退订情况</h5>
                                                <table class="am-table am-table-bordered am-table-radius am-table-striped">
                                                    <thead>
                                                    <tr>
                                                        <th>商品名</th>
                                                        <th>笔数</th>
                                                        <th>数量</th>
                                                        <th>金额</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${unsubInfo}" var="uns">
                                                    <tr>
                                                        <td>${uns.prod_name}</td>
                                                        <td>${uns.count_num}</td>
                                                        <td>${uns.amount}</td>
                                                        <td>￥${uns.sum_money}</td>
                                                        <c:set var="Unprofit" value="${uns.sum_money+Unprofit}"/>
                                                    </tr>
                                                    </c:forEach>
                                                    <tr>
                                                        <td colspan="4" style="text-align: center"><strong> 总金额：</strong> ￥${Unprofit}</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </c:if>


                                        <c:if test="${flag == 5||flag==6||flag==7||flag== 8}">
                                            <c:set var="total_profit" value="${Orderprofit-Unprofit-Reprofit}"/>
                                            <h5 style="text-align: center">收入</h5>
                                            <table class="am-table am-table-centered am-table-bordered am-table-radius">
                                                <thead>
                                                <tr>
                                                    <th>类型</th>
                                                    <th>利润</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:if test="${flag == 5|| flag == 8}"  >
                                                    <tr>
                                                        <td class="am-primary">新订</td>
                                                        <td class="am-primary">￥${Orderprofit}</td>
                                                    </tr>
                                                </c:if>

                                                <c:if test="${flag == 6|| flag == 8}"  >
                                                    <tr>
                                                        <td class="am-active">退订</td>
                                                        <td class="am-active">￥${Unprofit}</td>

                                                    </tr>
                                                </c:if>

                                                <c:if test="${flag == 7|| flag == 8}"  >
                                                    <tr>
                                                        <td class="am-success">退货</td>
                                                        <td class="am-success" >￥${Reprofit}</td>

                                                    </tr>
                                                </c:if>
                                                    <tr>
                                                        <td style="color: #525e62;text-align: center" colspan="2" ><strong>总利润:</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>￥${total_profit}</strong></td>

                                                    </tr>

                                                </c:if>


                                                </tbody>
                                            </table>

                                            <div style="margin-bottom: 50px"></div>

                                        </c:if>


                                        </div>


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

    $(function(){

        $(document).ready(function () {
            //获取管理员列表
            $("#op").cxSelect({
                selects: ['operator'],
                jsonName: 'n',
                jsonValue: 'v',
                required: true,
                emptyStyle: "none",
                url:'SelectOperatorServlet?action=getOperator'
        });


        });

    });

    //监听事件

        $("#operator").change(function(){
         $("#operator_name").val($("#operator option:selected").text());
            // alert($("#operator option:selected").text());
           console.log( $("#operator_name").val());

        });





</script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/amazeui.datatables.min.js"></script>
<script src="assets/js/dataTables.responsive.min.js"></script>
<script src="assets/js/app.js"></script>

</body>
</html>
</c:if>