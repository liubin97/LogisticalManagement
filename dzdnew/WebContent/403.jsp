<%--
  Created by IntelliJ IDEA.
  User: xiedong
  Date: 2018/6/28
  Time: 上午11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin 403</title>
    <meta name="description" content="这是一个404页面">
    <meta name="keywords" content="404">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="assets/css/admin.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar am-topbar-inverse admin-header">

</header>

<div class="am-cf admin-main">

    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <div class="admin-content-body">


            <hr>

            <div class="am-g" style="margin-top: 200px">
                <div class="am-u-sm-12">
                    <h2 class="am-text-center am-text-xxxl am-margin-top-lg">403. Forbidden</h2>
                    <p class="am-text-center">你还未登录</p>
                    <pre class="page-404">
          .----.
       _.'__    `.
   .--($)($$)---/#\
 .' @          /###\
 :         ,   #####
  `-..__.-' _.-\###/
        `;_:    `"'
      .'"""""`.
     /,  ya ,\\
    //  403!  \\
    `-._______.-'
    ___`. | .'___
   (______|______)
        </pre>
                </div>
            </div>
        </div>

    </div>
    <!-- content end -->

</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/js/jquery.min.js"></script>
<script>

    //跳转页面到登录
    function jumpurl(){
        location.href="http://localhost:8080/Login.jsp";
    }
    //4s跳转页面
    setTimeout('jumpurl()',4000);




</script>>
<!--<![endif]-->
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
</body>
</html>