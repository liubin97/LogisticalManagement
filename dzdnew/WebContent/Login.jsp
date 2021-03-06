<%--
  Created by IntelliJ IDEA.
  User: xiedong
  Date: 2018/6/25
  Time: 下午2:42
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="assets/js/jquery.min.js"></script>
</head>
<body data-type="login">
<script src="assets/js/theme.js"></script>
<%String loginmsg=(String)request.getSession().getAttribute("loginmsg");
    request.getSession().removeAttribute("loginmsg");%>
<div class="am-g tpl-g">
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
    <div class="tpl-login">
        <div class="tpl-login-content">
            <div class="tpl-login-logo">

            </div>

            <form class="am-form tpl-form-line-form" action="loginServlet" method="post">
                <div class="am-form-group">
                    <input type="text" class="tpl-form-input" name="admin" id="admin" placeholder="请输入账号">

                </div>

                <div class="am-form-group">
                    <input type="password" class="tpl-form-input" name="password" id="password" placeholder="请输入密码">

                </div>

                <div class="am-form-group">

                    <button type="submit" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">登录</button>

                </div>
            </form>
        </div>
    </div>
</div>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script type="text/javascript">
    window.onload=function(){
        var loginmsg="<%=loginmsg%>";
        if(loginmsg!="null"){
            alert(loginmsg);
        }
    }

</script>
</body>
</html>