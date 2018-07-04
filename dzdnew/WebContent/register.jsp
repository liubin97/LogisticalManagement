<%--
  Created by IntelliJ IDEA.
  User: xiedong
  Date: 2018/6/19
  Time: 下午3:18
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
        <title>登记新用户</title>
        <meta name="keywords" content="index">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="renderer" content="webkit">
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <!--font-Awesome -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" >
        <link rel="icon" type="image/png" href="assets/i/favicon.png">
        <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
        <!--font-Awesome -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" >

        <link rel="stylesheet" href="assets/css/amazeui.min.css" />
        <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css" />
        <link rel="stylesheet" href="assets/css/app.css">

        <script src="assets/js/echarts.min.js"></script>
        <script src="js/jquery-1.9.1.js"></script>
        <script src="js/validate.js"></script>



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
                            <a href="register.jsp" class="sub-active">
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



                            </div>
                            <div class="widget-body  am-fr">
                                <div class="row">
                                    <div class="am-u-lg-8 am-u-lg-offset-2">
                                        <form id="form11" class="am-form am-form-horizontal " action="UserManageServlet?action=register" method="post"  id="form-with-tooltip">
                                            <div class="am-panel am-panel-primary">
                                                <div class="am-panel-hd"><h1 class="am-panel-title" style="text-align: center">添加新用户</h1></div>
                                                <div class="am-panel-bd">


                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1 am-form-label"><span style="color:red;">*</span>姓名</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8 ">
                                                            <input type="text" name="name"  placeholder="姓名" required>
                                                        </div>
                                                    </div>


                                                    <div id="theid" class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1 am-form-label"><span style="color:red;">*</span>身份证号</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" id="id_card" class="am-form-field" name="id_card" placeholder="身份证号" required>

                                                        </div>
                                                    </div>

                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1 am-form-label">工作单位</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" name="organization" placeholder="工作单位">
                                                        </div>
                                                    </div>


                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1 am-form-label">座机</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" name="landline_tel" placeholder="座机">
                                                        </div>
                                                    </div>

                                                    <div class="am-form-group">
                                                        <label for="doc-vld-pwd-1-0" class="am-u-lg-2 am-u-lg-offset-1  am-form-label"><span style="color:red;">*</span>移动电话</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" id="doc-vld-pwd-1-0" name="tel" placeholder="输入手机号"  required />
                                                            <%--<label id="tel_msg"></label>--%>
                                                        </div>
                                                    </div>


                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1  am-form-label"><span style="color:red;">*</span>联系地址</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" name="address" placeholder="联系地址" required>
                                                        </div>
                                                    </div>

                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1  am-form-label">邮编</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="text" name="zip_code"  placeholder="邮编">
                                                        </div>
                                                    </div>


                                                    <div class="am-form-group">
                                                        <label  class="am-u-lg-2 am-u-lg-offset-1  am-form-label">E-mail</label>
                                                        <div class="am-fr"></div>
                                                        <div class="am-u-lg-8">
                                                            <input type="email" name="email"  placeholder="E-mail">
                                                        </div>
                                                    </div>





                                                    <div class="am-form-group">
                                                        <div class="am-u-lg-8 am-u-lg-offset-4">
                                                            <button type="button" onclick="checkForm()" class="am-btn am-btn-primary" style="width: 300px">提交</button>
                                                        </div>
                                                    </div>

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





    <script src="assets/js/amazeui.min.js"></script>
    <script>

        /*
        * flag
        * 1- 身份证
        * 2- 电话号码
        * */
        //身份证
        $("#id_card").change(function () {
            var id_card = $("#id_card").val();
            console.log("id_card:",id_card);
            var id_card_ret = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
            //alert("进入id_card");
            if(id_card_ret.test(id_card)){
                $.ajax({
                    type:"POST",
                    dataType:"text",
                    url:'UserManageServlet?action=Validate&flag=2&id_card='+id_card,
                    success:function(data){
                        if(data == "true"){

                            //  alert("Ajax-身份证号已占用！");
                            Attention("Ajax-身份证号已占用！");
                            console.log("data:",data);


                            //am-form-error am-form-icon am-form-feedback
                            // $("#theid").addClass("am-form-error am-form-icon am-form-feedback");
                            // var str =" <span class='am-icon-times'></span>"
                            // $("#id_card").append(str);
                        }
                        if(data == "false"){
                            console.log("data:",data);
                            console.log("身份证OK")
                        }
                    }
                });

            }else{
                Attention("Ajax--身份证格式错误");

               // alert('Ajax--身份证格式错误');
            }



        });

        //电话号码
        $("#doc-vld-pwd-1-0").change(function () {
            var tel = $("#doc-vld-pwd-1-0").val();
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
            var isSubmit2 = 0;
            var tel = $("#doc-vld-pwd-1-0").val();
            var id_card = $("#id_card").val();

            var id_card_ret = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
            var tel_ret =/^1((3|5|8){1}\d{1}|70)\d{8}$/;
            if(tel_ret.test(tel)){
               console.log("手机号码OK")
                isSubmit1 = 1;
            }else{
                Attention('submit--手机号码格式错误');
               // alert('submit--手机号码格式错误');
            }

            if(id_card_ret.test(id_card)){
                console.log("身份证OK")
                isSubmit2 = 1;
            }else{
                Attention('submit--身份证格式错误');
                //alert('submit--身份证格式错误');
            }

            if(isSubmit1 == 1&&isSubmit2 ==1){
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
    <script src="assets/js/amazeui.datatables.min.js"></script>
    <script src="assets/js/dataTables.responsive.min.js"></script>
    <script src="assets/js/app.js"></script>

    </body>

    </html>
</c:if>
