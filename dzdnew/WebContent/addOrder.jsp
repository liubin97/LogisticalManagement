<%--
  Created by IntelliJ IDEA.
  User: xiedong
  Date: 2018/6/25
  Time: 下午2:18
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
    <title>增加新订单</title>
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
    <!--jQuery多级联动插件-->
    <script src="js/jquery-1.9.1.js"></script>
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
                        <a href="Order.jsp"class="sub-active">
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

                            <h1>增加新订单</h1>

                        </div>
                        <div class="widget-body  am-fr">

                            <div class="row" >
                                <div class="am-u-lg-12 am-u-sm-offset-1" >
                                    <form id="form1" class="am-form am-form-horizontal"   action="NewOrderServlet?action=creatorder" method="post" data-am-validator>
                                        <%--存放user_id--%>
                                        <input type="hidden" name="user_id" value="${u.user_id}">
                                        <input type="hidden" name="prodct_id" value="${prodlist.id}">
                                        <input type="hidden" name="pro_amount" value="${amount}">
                                        <input type="hidden" name="pro_unit" value="${prodlist.unit}">
                                        <input type="hidden" name="price" value="${prodlist.price}">
                                        <input type="hidden" name="discount" value="${prodlist.discount}">
                                        <div class="am-form-group" style="clear: right">
                                            <label class="am-u-sm-2 am-form-label">订单类型</label>
                                            <div class="am-fr"></div>
                                            <div class="am-u-sm-7">
                                                <label class="am-radio-inline">
                                                    <input type="radio"  name="type" value="1" checked> 新订
                                                </label>

                                            </div>
                                        </div>

                                        <div class="am-form-group" style="clear: right">
                                            <label class="am-u-sm-2 am-form-label">送货地址</label>
                                            <div class="am-fr"></div>
                                            <div class="am-u-sm-7">
                                                <input type="text" name="address" value="${u.address}" >
                                            </div>
                                        </div>

                                        <div class="am-form-group" style="clear: right">
                                            <label class="am-u-sm-2 am-form-label">收货人</label>
                                            <div class="am-fr"></div>
                                            <div class="am-u-sm-7">
                                                <input type="text" name="consignee" value="${u.name}">
                                            </div>
                                        </div>

                                        <div class="am-form-group" style="clear: right">
                                            <label class="am-u-sm-2 am-form-label">收货人电话</label>
                                            <div class="am-fr"></div>
                                            <div class="am-u-sm-7">
                                                <input type="text" name="consignee_tel" value="${u.tel}" >
                                            </div>
                                        </div>

                                        <div class="am-form-group" style="clear: right">
                                            <label class="am-u-sm-2 am-form-label">收货人邮编</label>
                                            <div class="am-fr"></div>
                                            <div class="am-u-sm-7">
                                                <input type="text" name="zip_code" value="${u.zip_code}">
                                            </div>
                                        </div>

                                        <div class="am-form-group" style="clear: right">
                                            <label class="am-u-sm-2 am-form-label">是否要发票</label>
                                            <div class="am-fr"></div>
                                            <div class="am-u-sm-7">
                                                <label class="am-radio-inline">
                                                    <input type="radio" value="1"  name="invoice" checked> 否
                                                </label>
                                                <label class="am-radio-inline">
                                                    <input type="radio" value="2" name="invoice"> 是
                                                </label>
                                            </div>
                                        </div>

                                        <div class="am-form-group"  >
                                            <label class="am-u-sm-2 am-form-label">要求完成日期</label>
                                            <div class="am-fr"></div>
                                            <div class="am-u-sm-7">
                                                <input type="text" id="fdate" class="am-form-field" name="finish_date" placeholder="请选择日期"  data-am-datepicker="" readonly="" required>

                                            </div>

                                        </div>

                                        <div class="am-form-group"  >
                                            <label class="am-u-sm-2 am-form-label">订单生成日期</label>
                                            <div class="am-fr"></div>
                                            <div class="am-u-sm-7">
                                                <input type="text" class="am-form-field " id="create_date" name="create_date"  readonly>

                                            </div>

                                        </div>

                                        <div class="am-form-group">
                                            <div class="am-u-lg-8 am-u-lg-offset-1"  style="margin-top: 20px">
                                                <table class="am-table am-table-bordered ">
                                                    <tbody >
                                                    <tr>
                                                        <td class="am-primary" colspan="3">
                                                            <span>订单</span>
                                                        </td>
                                                        <td class="am-primary table_boder_clear" colspan="2">用户名：<span>${prodlist.id}</span></td>
                                                        <td class="am-primary table_boder_clear"> 总价：￥<span>${prodlist.price * amount*prodlist.discount}</span></td>

                                                    </tr>
                                                    </tbody>

                                                    <tbody>
                                                    <tr>
                                                        <td >
                                                            <span>商品名：</span>
                                                            <span>${prodlist.name}</span>
                                                        </td>
                                                        <td  >单价：<span>${prodlist.price}</span></td>
                                                        <td>产品描述：<span>${prodlist.description}</span></td>
                                                        <td >数量：<span>${amount}</span></td>
                                                        <td>单位：<span>${prodlist.unit}</span></td>
                                                        <td >折扣：<span>${prodlist.discount}</span></td>

                                                    </tr>

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>


                                        <div class="am-form-group">
                                            <div class="am-u-sm-10 am-u-sm-offset-4">

                                                <button type="button"  class="am-btn am-btn-primary" onclick="checkForm()" style="width: 300px" >生成订单</button>
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


<script>

    $(function() {

        $(document).ready(function () {

            //获取当前的时间
            var now = new Date();
            var time = now.getFullYear() + "-" +((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate();
            $("#create_date").val(time);
            console.log("yyyy-mm-dd:"+time);
        });
    });

    function gettData(){
        var date = $("#date").val();
        console.log("当前获取的时间是"+date);
    }


    //检查时间
    function checkDate(){
        var time= $("#fdate").val();
        time = time.replace("-","/");//替换字符，变成标准格式
        var d2=new Date();//取今天的日期
        console.log(d2);
        var d1 = new Date(Date.parse(time));//我的时间
        console.log(d2);
        //console.log("判断结果：",d1<d2);
        if(d1<d2){
            // alert("日期输入无效请重新输入！");
            return false;
        }else {
           return true;
         }

    }


    function checkForm(){
       // alert("函数执行");
        //alert(checkDate());

        if(checkDate() == true){
            // 提交订单
              //  alert("函数提交");
             $("#form1").submit();

        }
        else {

          //  alert("日期输入无效请重新输入");
            var $tab = $("#message");//找到数据插入点
           // alert($tab);
            $tab.empty();//每次给清空一次
            var msg = "日期输入无效请重新输入";
            $tab.append(msg);
            $('#my-alert').modal('toggle');


            // alert("进入函数");
            // $tab.empty();//每次给清空一次
            // var msg = "日期输入无效请重新输入";
            // tab.append(msg);
            // //弹出弹窗
            //
            //打印订单信息

        }

    }


</script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/amazeui.datatables.min.js"></script>
<script src="assets/js/dataTables.responsive.min.js"></script>
<script src="assets/js/app.js"></script>

</body>

</html>
</c:if>
