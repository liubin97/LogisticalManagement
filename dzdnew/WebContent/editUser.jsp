<%--
  Created by IntelliJ IDEA.
  User: xiedong
  Date: 2018/6/21
  Time: 下午1:12
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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>修改用户信息</title>
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
            <li class="sidebar-nav-heading">用户中心</li>

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
                    <i class="fa fa-clock-o"></i> 操作员工作量查询
                    <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico sidebar-nav-sub-ico-rotate"></span>
                </a>
                <ul class="sidebar-nav sidebar-nav-sub" style="display: block;">
                    <li class="sidebar-nav-link">
                        <a href="Order_Search.jsp" >
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 订单查询
                        </a>
                    </li>

                    <li class="sidebar-nav-link">
                        <a href="Order.jsp" >
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
                    <i class="am-icon-table sidebar-nav-link-logo"></i> 操作员工作量查询
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

                            <h1>修改用户信息</h1>

                        </div>
                        <div class="widget-body  am-fr">

                            <div class="row">
                                <div class="am-u-lg-8 am-u-lg-offset-2">

                                        <div class="am-panel am-panel-primary">
                                            <div class="am-panel-hd"><h1 class="am-panel-title" style="text-align: center">修改用户信息</h1></div>
                                            <div class="am-panel-bd">
                                                <form id="form11" class="am-form am-form-horizontal " action="UserManageServlet?action=edit&flag=change" method="post">
                                                    <div class="am-form-group">

                                                        <label  class="am-u-lg-2 am-u-lg-offset-1 am-form-label">用户编号</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8 ">
                                                            <input type="text" name="user_id" id="user_id" value="${user.user_id}"   readonly>
                                                        </div>

                                                    </div>

                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1 am-form-label">姓名</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8 ">
                                                            <input type="text" name="name" id="name" value="${user.name}" >
                                                        </div>
                                                    </div>

                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1 am-form-label">身份证号</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" name="id_card" id="id_card" value="${user.id_card_num}" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1 am-form-label">工作单位</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" name="organization" id="organization" value="${user.organization}" >
                                                        </div>
                                                    </div>

                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1 am-form-label">座机</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" name="landline_tel" id="landline_tel" value="${user.landline_tel}" >
                                                        </div>
                                                    </div>

                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1  am-form-label">移动电话</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" name="tel" id="phone" value="${user.tel}" >
                                                        </div>
                                                    </div>

                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1  am-form-label">联系地址</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" name="address" id="address" value="${user.address}" >
                                                        </div>
                                                    </div>

                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1  am-form-label">邮编</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" name="zip_code" id="zip_code" value="${user.zip_code}" >
                                                        </div>
                                                    </div>

                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1  am-form-label">E-mail</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="email" name="email" id="email" value="${user.email}" >
                                                        </div>
                                                    </div>

                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1  am-form-label">用户状态</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" name="status" id="status" value="${user.status}" readonly>
                                                        </div>
                                                    </div>


                                                    <div class="am-form-group">
                                                        <div class="am-u-lg-8 am-u-lg-offset-4">
                                                             <button type="button" class="am-btn am-btn-primary" onclick="checkForm()" style="width: 300px">提交</button>
                                                        </div>
                                                    </div>
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
    </div>
</div>




    <%--模态弹窗--%>
<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">提示</div>
        <div class="am-modal-bd" id="message">

        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn">确定</span>
        </div>
    </div>
</div>

<script >

    //form1
</script>
<script>

    /*
    * flag
    * 1- 身份证
    * 2- 电话号码
    * */


    //电话号码
    $("#phone").change(function () {
        var tel = $("#phone").val();
        console.log("tel:",tel);
        var tel_ret =/^1((3|5|8){1}\d{1}|70)\d{8}$/;
        if(tel_ret.test(tel)){
            //alert("进入id_card");
            $.ajax({
                type:"POST",
                dataType:"text",
                url:'UserManageServlet?action=Validate&flag=1&tel='+tel,
                success:function(data){
                    if(data == "true"){
                        Attention('Ajax--电话号码已占用！');
                        //alert("Ajax--电话号码已占用！");
                        console.log("data:",data);

                    }
                    if(data == "false"){
                        console.log("data:",data);
                    }
                }
            });
        }
        else {
            Attention('Ajax--电话号格式错误');
            // alert("Ajax--电话号格式错误");
        }



    });

    function checkForm() {
        //doc-vld-pwd-1-0
        //id_card
        var isSubmit1 = 0;
        var tel = $("#phone").val();
        var tel_ret =/^1((3|5|8){1}\d{1}|70)\d{8}$/;
        if(tel_ret.test(tel)){
            console.log("手机号码OK")
            isSubmit1 = 1;
        }else{
            Attention('submit--手机号码格式错误');
            // alert('submit--手机号码格式错误');
        }

        if(isSubmit1 == 1){
            // 提交订单
            $("#form11").submit();
        }

        //   form11

    }


    function Attention(msg){
        //  alert("Ajax-身份证号已占用！");
        var $tab = $("#message");//找到数据插入点
        $tab.empty();//每次给清空一次
        //  var msg = "Ajax-身份证号已占用！,请重新输入";
        $tab.append(msg);
        $('#my-alert').modal('toggle');

    }

</script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/amazeui.datatables.min.js"></script>
<script src="assets/js/dataTables.responsive.min.js"></script>
<script src="assets/js/app.js"></script>

</body>
</html>
</c:if>