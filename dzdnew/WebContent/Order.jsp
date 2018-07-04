<%--
  Created by IntelliJ IDEA.
  User: xiedong
  Date: 2018/6/22
  Time: 下午1:09
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

                            <form  id="form1"class="am-form am-form-inline" action="UserManageServlet?action=search&from=order" method="post" role="form">
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
                                            <input type="text" id="id_card" name="id_card" class="am-form-field" placeholder="身份证号码">
                                        </div>

                                        <div class="am-form-group">
                                            <select name="pageSize" style="width:110px;">
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

                            <%--查询显示界面--%>

                            <div class="row" >

                                <div class="am-u-lg-12" style="margin-top: 50px">

                                    <c:if test="${pagecount>0}">
                                        <table class="am-table am-table-bordered am-table-radius am-table-striped am-table-centered">
                                            <thead>
                                                <tr>
                                                    <th>姓名</th>
                                                    <th>身份证号</th>
                                                    <th>工作单位</th>
                                                    <th>座机</th>
                                                    <th>移动电话</th>
                                                    <th>联系地址</th>
                                                    <th>邮编</th>
                                                    <th>Email</th>
                                                    <th></th>
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

                                                    <input type="hidden" value="${user.user_id}" id="user${user.user_id}" name="user_id">
                                                    <td>${user.name}</td>
                                                    <td>${user.id_card_num}</td>
                                                    <td>${user.organization}</td>
                                                    <td>${user.landline_tel}</td>
                                                    <td>${user.tel}</td>
                                                    <td>${user.address}</td>
                                                    <td>${user.zip_code}</td>
                                                    <td>${user.email}</td>
                                                    <td><button type="button" class="am-btn am-btn-warning" onclick="getUser(${user.user_id});" ><em class="fa fa-check">新订</em> </button></td>
                                                </tr>
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
                                                    <li><a href="UserManageServlet?action=search&pageNum=${p}&from=order">${p}</a></li>
                                                </c:if>

                                                <c:if test="${p==pagecount}">
                                                    <li><a href="#">»</a></li>
                                                </c:if>
                                            </c:forEach>
                                        </ul>
                                    </div>

                                </div>
                            </div>

                            <!--二级联动显示购物清单-->
                            <div class="row" id="pro_list">
                                <div class="am-u-lg-12">
                                        <form class="am-form am-form-inline">
                                            <fieldset >
                                                <legend>选择商品</legend>
                                                <div class="am-form-group" id="one" style="width: 150px">
                                                    <label  class=" am-form-label">一级商品分类</label>
                                                    <select  class="onetitle" id="onetitle" name="onetitle" ></select>
                                                </div>

                                                <div class="am-form-group"  id="two" style="width: 150px">
                                                    <label  class=" am-form-label">二级商品分类</label>
                                                    <select class="twotitle" id="twotitle" name="twotitle"  ></select>
                                                </div>
                                            </fieldset>
                                        </form>
                                </div>
                            </div>

                            <!---->
                            <div class="row" >
                                <div class="am-u-lg-12" style="margin-top: 50px">
                                        <table  class="am-table am-table-striped am-table-hover am-table-bordered" id="products">
                                            <caption >请选择商品</caption>
                                            <thead>
                                            <tr>
                                                <th></th>
                                                <th>商品名称</th>
                                                <th>计量单位</th>
                                                <th>单价</th>
                                                <th>折扣</th>
                                                <th>商品说明</th>
                                                <th style="width: 50px">数量</th>
                                                <th>金额</th>


                                            </tr>
                                            </thead>

                                            <tbody>

                                            <!-- more data -->
                                            </tbody>

                                        </table>

                                            <div class="am-u-lg-8 am-u-lg-offset-4" id="products_btn">
                                                <button type="button"  class="am-btn am-btn-primary" onclick="sendOrder()" style="width: 300px">确定</button>
                                            </div>

                                </div>
                            </div>
                            <div class="row"  >
                                <div class="am-u-lg-12" >
                                    <form id="form2" action="NewOrderServlet?action=orderInfo" method="post">
                                        <input type="hidden" name="userid" id="userid">
                                        <input type="hidden" name="prodid" id="prodid">
                                        <input type="hidden" name="amount" id="amount">
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

<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/amazeui.datatables.min.js"></script>
<script src="assets/js/dataTables.responsive.min.js"></script>
<script src="assets/js/app.js"></script>
<script>
    var userid;
    //隐藏产品列表
    $(function() {

        $(document).ready(function () {
            $("#pro_list").hide();//隐藏二级联动
            $('#products').hide();//隐藏产品列表
            $('#products_btn').hide();//隐藏产品列表提交button
            console.log("userID:"+userid);

        });
    });

    //显示
    function getUser(id) {
        //显示产品
        $("#pro_list").show();
        $('#products').show();
        $('#products_btn').show();

        //获取用户id
        userid = id;
        console.log("当前用户id："+userid);




    }

    //获取一级级目录
    $("#one").cxSelect({
        selects: ['onetitle'],
        jsonName: 'n',
        jsonValue: 'v',
        required: true,
        emptyStyle: "none",
        url:'NewOrderServlet?action=order&flag=one'
    });

    //获取二级目录
    $('#onetitle').change(function () {
        var title =  $("#onetitle option:selected").val();
        $("#two").cxSelect({
            selects: ['twotitle'],
            jsonName: 'n',
            jsonValue: 'v',
            required: true,
            emptyStyle: "none",
            url:'NewOrderServlet?action=order&flag=two&title='+title

            });

    });

    //获取产品目录
    $('#twotitle').change(function () {
        var titile = $("#twotitle option:selected").val();
        $.ajax({
            type:"POST",
            dataType:"json",
            url:'NewOrderServlet?action=order&flag=product&title='+titile,
            success:function(data){

                //找到tbody
                var $tab = $('#products tbody');
               //遍历product数据集
                $tab.empty();//每次的ajax后吧tbody给清空一次
                for(var i = 0;i<data.length;i++){

                    var trhtml = "<tr>";//组装字符串
                    trhtml += "<td><input type=\"radio\" name=\"chose\" value=\""+data[i].id+"\" id=\"chose"+data[i].id+"\"></td>";
                    trhtml += "<td>"+data[i].name+"</td>";
                    trhtml += "<td>"+data[i].unit+"</td>";
                    trhtml += "<td>"+data[i].price+"<input type=\"hidden\" value=\""+data[i].price+"\" id=\"price"+data[i].id+"\"></td>";
                    trhtml += "<td><input type=\"hidden\" value=\""+data[i].discount+"\" id=\"rate"+data[i].id+"\">"+data[i].discount+"</td>";
                    trhtml += "<td>"+data[i].notes+"</td>";
                    trhtml += "<td><input type=\"number\" id=\"num"+data[i].id+"\" value='0' onchange=\"Count("+data[i].id+")\"></td>";
                    trhtml += "<td id= \"total"+data[i].id+"\">"+0+ "</td>";
                    trhtml += " </tr>";
                    $tab.append(trhtml);
               }

            }
        });


        });


    /*计算总价格的价格*/
    function Count(id) {

        var quantity = $("#num"+id).val();
        var price = $("#price"+id).val();
        var rate = $("#rate"+id).val();
        var smallTotal = quantity*price*rate;
        $("#total"+id).html(smallTotal);
        console.log("价格"+smallTotal);
    }

    /*提交表单*/
    function sendOrder(){

        var prodid = $("input[name='chose']:checked").val();//获取选中的产品id
        //判断是否选择了用户
        if(userid != undefined || userid != null){

            //判断prodid是否为空
            if(prodid == undefined){
                alert("请选择产品！");
            }
            else {
                console.log("本次选中的是："+prodid);
                var amount = $("#num"+prodid).val();//获取数量

                //判断产品数量合法性
                if(amount<0){

                    alert("产品数量不合法，请重新输入！！");
                }
                else if(amount == 0){

                    alert("请输入产品数量！！");

                }
                else {
                    //设置隐藏表单value值
                    $("#userid").val(userid);
                    $("#prodid").val(prodid);
                    $("#amount").val(amount);

                    // 提交订单
                    $("#form2").submit();

                }


            }

        }
        else {
            alert("请选择用户");
        }
    }



</script>

</body>

</html>
</c:if>