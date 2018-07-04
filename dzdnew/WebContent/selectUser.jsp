<%--
  Created by IntelliJ IDEA.
  User: xiedong
  Date: 2018/6/20
  Time: 下午8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<c:if  test="${manager_id == null}" >

    <c:redirect url="403.jsp"/>
</c:if>
<c:if  test="${manager_id != null}" >

<!DOCTYPE html>
<html lang="zh">

<head>
    <%--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>--%>
    <%--<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>--%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>客户查询</title>
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
                        <a href="exit.jsp" target="_top">
                            <span class="am-icon-sign-out"></span> 退出
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </header>
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
                        <a href="selectUser.jsp"  class="sub-active" >
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 客户查询
                        </a>
                    </li>

                    <li class="sidebar-nav-link">
                        <a href="register.jsp">
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
                        <a href="Order.jsp ">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>新订
                        </a>
                    </li>

                    <li class="sidebar-nav-link">
                        <a href="Order_Refund.jsp">
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
                            <div class="widget-title  am-cf"><h1>客户查询</h1></div>


                        </div>
                        <div class="widget-body  am-fr">

                            <div class="row">
                                <div class="am-u-lg-10 am-u-lg-offset-2">

                                    <form class="am-form am-form-inline" action="UserManageServlet?action=search" method="post" role="form">
                                        <div class="am-form-group">
                                            <input type="text" name="name" class="am-form-field" placeholder="姓名">
                                        </div>

                                        <div class="am-form-group">
                                            <input type="text"  name="tel" class="am-form-field" placeholder="电话号码">
                                        </div>

                                        <div class="am-form-group">
                                            <input type="text"  name="id_card" class="am-form-field" placeholder="身份证号码">
                                        </div>

                                        <div class="am-form-group">
                                            <select name="pageSize" style="width:110px;" >
                                                <option value="5">每页显示数</option>
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="5" >5</option>
                                            </select>
                                        </div>

                                        <div class="am-form-group">
                                            <button type="submit" class="am-btn am-btn-primary"><em class="fa fa-search"></em></button>
                                        </div>
                                    </form>

                                </div>

                            </div>

                        <%--查询显示界面--%>

                            <div class="row" >

                                <div class="am-u-lg-12" style="margin-top: 50px">

                                        <c:if test="${pagecount>0}">
                                        <table width="100%" class="am-table am-table-bordered am-table-radius am-table-striped ">
                                            <thead>
                                            <tr>
                                                <th>客户编号</th>
                                                <th>姓名</th>
                                                <th>身份证号</th>
                                                <th>工作单位</th>
                                                <th>座机</th>
                                                <th>移动电话</th>
                                                <th>联系地址</th>
                                                <th>邮编</th>
                                                <th>Email</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <!--
                                            forEach:循环遍历
                                            items属性：进行循环的数据（EL表达式）
                                            var属性：当前遍历道德元素的别名
                                             -->
                                            <c:forEach items="${resultList}" var="user">
                                                <tr>
                                                    <td>${user.user_id}</td>
                                                    <td>${user.name}</td>
                                                    <td>${user.id_card_num}</td>
                                                    <td>${user.organization}</td>
                                                    <td>${user.landline_tel}</td>
                                                    <td>${user.tel}</td>
                                                    <td>${user.address}</td>
                                                    <td>${user.zip_code}</td>
                                                    <td>${user.email}</td>
                                                    <td>
                                                        <div class="tpl-table-black-operation">
                                                            <a href="UserManageServlet?action=edit&userId=${user.user_id}">
                                                                <i class="am-icon-pencil"></i> 编辑
                                                            </a>
                                                            <a href="UserManageServlet?action=delete&userId=${user.user_id}" class="tpl-table-black-operation-del">
                                                                <i class="am-icon-trash"></i> 删除
                                                            </a>
                                                        </div>
                                                    </td>
                                            </c:forEach>

                                            </tbody>
                                        </table>
                                        </c:if>

                                </div>

                                <div class="am-u-lg-12 am-cf">

                                    <div class="am-fr">
                                        <ul class="am-pagination tpl-pagination">
                                            <c:forEach begin="1" end="${pagecount}" var="p"  >
                                                <c:if test="${p==1 }">
                                                    <li class="am-disabled"><a href="#">«</a></li>
                                                </c:if>

                                                <c:if test="${p==pageNum}">
                                                    <li class="am-active"><a href="#">${p}</a></li>
                                                </c:if>

                                                <c:if test="${p!=pageNum}">
                                                    <li><a href="UserManageServlet?action=search&pageNum=${p}">${p}</a></li>
                                                </c:if>

                                                <c:if test="${p==pagecount}">
                                                    <li><a href="#">»</a></li>
                                                </c:if>
                                            </c:forEach>
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

</div>


<script src="assets/js/amazeui.min.js"></script>

<script src="assets/js/amazeui.datatables.min.js"></script>
<script src="assets/js/dataTables.responsive.min.js"></script>
<script src="assets/js/app.js"></script>

</body>

</html>
</c:if>