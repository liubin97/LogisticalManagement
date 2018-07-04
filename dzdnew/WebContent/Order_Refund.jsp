<%--
  Created by IntelliJ IDEA.
  User: xiedong
  Date: 2018/6/22
  Time: 下午3:05
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
        <title>退订订单</title>
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
                            <a href="Order_Refund.jsp" class="sub-active">
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

                                <h1>退订</h1>

                            </div>
                            <div class="widget-body  am-fr">

                                <form class="am-form am-form-inline" action="UnsubscribServlet?action=search" method="post" role="form">
                                    <div class="row">
                                        <div class="am-u-lg-10 am-u-lg-offset-2">

                                            <div class="am-form-group">
                                                <!--<label for="name" class="am-form-label">姓名</label>-->
                                                <input type="text" id="name" name="name" class="am-form-field" placeholder="姓名">
                                            </div>

                                            <div class="am-form-group">
                                                <!--<label for="tel" class="am-form-label">电话号码</label>-->
                                                <input type="text" id="tel" name="tel" class="am-form-field" placeholder="电话号码">
                                            </div>

                                            <div class="am-form-group">
                                                <!--<label for="idcard" class="am-form-label">身份证号码</label>-->
                                                <input type="text" id="idcard" name="id_card" class="am-form-field" placeholder="身份证号码">
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
                                        </div>
                                    </div>
                                </form>

                                <div class="row" >
                                    <div class="am-u-lg-12" style="margin-top: 50px">

                                        <c:if test="${pagecount>0}">
                                            <table class="am-table am-table-striped am-table-hover am-table-bordered am-table-centered" >
                                                <thead>
                                                <tr>

                                                    <th>订单号</th>
                                                    <th>订单状态</th>
                                                    <th>订单类型</th>
                                                    <th>订单生成日期</th>
                                                    <th>要求完成日期</th>
                                                    <th>客户姓名</th>
                                                    <th>客户详情</th>
                                                    <th>订单详情</th>
                                                    <th></th>

                                                </tr>
                                                </thead>
                                                <tbody>

                                                <c:forEach items="${orderlist}" var="order">
                                                    <tr>
                                                        <td>${order.order_id}</td>
                                                        <td>
                                                            <c:if test="${order.order_id==2}">
                                                               可分配
                                                            </c:if>
                                                        </td>
                                                        <td>新定</td>
                                                        <td>${order.create_date}</td>
                                                        <td>${order.finish_date}</td>
                                                        <td>${order.consignee}</td>
                                                        <td>
                                                            <button type="button" class="am-btn am-btn-primary" onclick="getUserPop(${order.client_id})"><i class="fa fa-user"></i></button>
                                                        </td>

                                                        <td>
                                                            <button type="button" class="am-btn am-btn-success" onclick="getOrderPop(${order.order_id})" ><i class="fa fa-newspaper-o"></i></button>
                                                        </td>
                                                        <td>
                                                            <a href="UnsubscribServlet?action=subscribe&orderid=${order.order_id}"><button type="button" class="am-btn am-btn-danger">退订</button></a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>

                                                <!-- more data -->
                                                </tbody>
                                            </table>
                                        </c:if>

                                    </div>
                                </div>

                                <div class="am-u-lg-12 am-cf">

                                    <div class="am-fr">

                                        <ul class="am-pagination tpl-pagination">
                                            <ul class="am-pagination tpl-pagination">
                                                <c:forEach begin="1" end="${pagecount}" var="p"  >
                                                    <c:if test="${p==1 }">
                                                        <li class="am-disabled"><a href="#">«</a></li>
                                                    </c:if>

                                                    <c:if test="${p==pageNum}">
                                                        <li class="am-active"><a href="#">${p}</a></li>
                                                    </c:if>

                                                    <c:if test="${p!=pageNum}">
                                                        <li><a href="UnsubscribServlet?action=search&pageNum=${p}">${p}</a></li>
                                                    </c:if>

                                                    <c:if test="${p==pagecount}">
                                                        <li><a href="#">»</a></li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
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
    </div>
    </div>
    </div>

        <%--订单信息模态弹窗--%>
    <div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
        <div class="am-modal-dialog">
            <div class="am-modal-hd">订单详情
                <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
            </div>
            <div class="am-modal-bd">
                <table class="am-table am-table-bordered am-table-radius am-table-striped">
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>

        <%--用户信息模态弹窗--%>
    <div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-2">
        <div class="am-modal-dialog">
            <div class="am-modal-hd">用户详情
                <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
            </div>
            <div class="am-modal-bd">
                <table class="am-table am-table-bordered am-table-radius am-table-striped">
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script>
        //订单信息pop
        function getOrderPop(orderid){
            var order_id = orderid; //保存订单id
            $.ajax({
                type:"POST",
                dataType:"json",
                url:"UnsubscribServlet?action=Info&flag=order&orderid="+orderid,
                success:function(data){

                    var $tab = $("#doc-modal-1 tbody");//找到数据插入点
                    //遍历product数据集
                    $tab.empty();//每次的ajax后吧tbody给清空一次
                    //打印订单信息
                    var str = data.discount;
                    alert(str);
                    var trhtml ="<tr><td>订单号</td>";
                    trhtml += "<td>"+data.order_id+"</td></tr>";
                    trhtml += "<tr><td>商品名称</td>";
                    trhtml += "<td>"+data.prod_name+"</td></tr>";
                    trhtml += "<tr><td>订购数量</td>";
                    trhtml += "<td>"+data.amount+"</td></tr>";
                    trhtml += "<tr><td>计量单位</td>";
                    trhtml += "<td>"+data.unit+"</td></tr>";
                    trhtml += "<tr><td>单价</td>";
                    trhtml += "<td>"+data.price+"</td></tr>";
                    trhtml += "<tr><td>折扣</td>";
                    // trhtml += "<td>"+data.discount+"</td></tr>";
                    trhtml += "<td>"+data.discount+"</td></tr>";
                    trhtml += "<tr><td>订单总金额</td>";
                    trhtml += "<td>"+data.sum_monney+"</td></tr>";
                    trhtml += "<tr><td>要求完成日期</td>";
                    trhtml += "<td>"+data.finish_date+"</td></tr>";
                    trhtml += "<tr><td>订单生成日期</td>";
                    trhtml += "<td>"+data.create_date+"</td></tr>";
                    trhtml += "<tr><td>订单类型</td>";
                    trhtml += "<td>"+getType(data.type)+"</td></tr>";
                    trhtml += "<tr><td>订单状态</td>";
                    trhtml += "<td>"+getStatus(data.status)+"</td></tr>";
                    trhtml += "<tr><td>送货地址</td>";
                    trhtml += "<td>"+data.deliver_addr+"</td></tr>";
                    trhtml += "<tr><td>收货人</td>";
                    trhtml += "<td>"+data.consignee+"</td></tr>";
                    trhtml += "<tr><td>收货人电话</td>";
                    trhtml += "<td>"+data.consignee_tel+"</td></tr>";
                    trhtml += "<tr><td>收货人邮编</td>";
                    trhtml += "<td>"+data.consignee_zip_cod+"</td></tr>";
                    trhtml += "<tr><td>是否要发票</td>";
                    trhtml += "<td>"+(data.invoice==1?"否":"是")+"</td></tr>";

                    //添加进html
                    $tab.append(trhtml);
                    //弹出弹窗
                    $('#doc-modal-1').modal('toggle');

                },
                error:function(){
                    alert("获取失败！");
                }

            });

        }
        //获取订单type类型
        function getType(type) {
            var x;
            switch(type) {
                case 1:
                    x="新订";
                    break;
                case 2:
                    x="退订";
                    break;
                case 3:
                    x="退货";
                    break;
            }
            return x;
        }

        //获取订单状态
        function getStatus(status) {
            var x;
            switch(status) {

                case 1:x="缺货";
                    break;
                case 2:x="可调度";
                    break;
                case 3:x="已调度";
                    break;
                case 4:x="完成";
                    break;
                case 5:x="失败";
                    break;
            }
            return x;
        }

        //获取用户信息
        function getUserPop(Userid){

            var user_id = Userid; //保存用户id
            $.ajax({
                type:"POST",
                dataType:"json",
                url:"UnsubscribServlet?action=Info&flag=user&userId="+user_id,
                success:function(data){

                    var $tab = $("#doc-modal-2 tbody");//找到数据插入点
                    //遍历product数据集
                    $tab.empty();//每次的ajax后吧tbody给清空一次
                    //打印订单信息

                    var trhtml ="<tr><td>客户编号</td>";
                    trhtml += "<td>"+data.user_id+"</td></tr>";
                    trhtml += "<tr><td>客户姓名</td>";
                    trhtml += "<td>"+data.name+"</td></tr>";
                    trhtml += "<tr><td>身份证编号</td>";
                    trhtml += "<td>"+data.id_card_num+"</td></tr>";
                    trhtml += "<tr><td>工作单位</td>";
                    trhtml += "<td>"+data.organization+"</td></tr>";
                    trhtml += "<tr><td>座机</td>";
                    trhtml += "<td>"+data.landline_tel+"</td></tr>";
                    trhtml += "<tr><td>移动电话</td>";
                    trhtml += "<td>"+data.tel+"</td></tr>";
                    trhtml += "<tr><td>联系地址</td>";
                    trhtml += "<td>"+data.address+"</td></tr>";
                    trhtml += "<tr><td>邮编</td>";
                    trhtml += "<td>"+data.zip_code+"</td></tr>";
                    trhtml += "<tr><td>E-mail</td>";
                    trhtml += "<td>"+data.email+"</td></tr>";


                    //添加进html
                    $tab.append(trhtml);
                    //弹出弹窗
                    $('#doc-modal-2').modal('toggle');

                },
                error:function(){
                    alert("获取失败！");
                }

            });


        }



    </script>
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/amazeui.datatables.min.js"></script>
    <script src="assets/js/dataTables.responsive.min.js"></script>
    <script src="assets/js/app.js"></script>

    </body>
    </html>
</c:if>

