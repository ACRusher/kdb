    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>宽带帮</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <%@ include file="head.jsp"%>
</head>
<body>
<div class="container" id="page18">
    <div class="row">
        <p class="title"><span class="">返回</span>登录<span class="">注册</span></p>

        <form class="form-inline sousuo">

                <div class="input-group">
                    <span class="input-group-btn img_tip">
                        <img src="images/person_head.png" alt=""/>
                    </span>
                        <input name = "phone" type="text" class="form-control" value="${phone}" placeholder="请输入手机号">
                </div><!-- /input-group -->
                <input type="hidden" name = "redirect" value="${redirect}" class="form-control">
                <div class="input-group">
                        <span class="input-group-btn img_tip">
                            <img src="images/password.png" alt=""/>
                        </span>
                    <input type="text" name="pwd" class="form-control" value="${pwd}" placeholder="密码">
                </div>
            <button type="submit" class="btn btn-default col-xs-10 col-xs-offset-1">登录/注册</button>
            <p class="retrieve_pass">找回密码</p>
            <% if(request.getAttribute("message")!=null){ %>
                <p class="alert">${message}</p>
            <% } %>

        </form>
    </div>
    <div class="row other_login">
        <p>第三方合作账号登录</p>
        <div class="login_list">
            <a class="col-xs-4">
                <img src="images/weixin.png" alt=""/>
                <p>微信登录</p>
            </a>
            <a class="col-xs-4">
                <img src="images/baidu.png" alt=""/>
                <p>贴吧登录</p>
            </a>
            <a class="col-xs-4">
                <img src="images/sina.png" alt=""/>
                <p>微博登录</p>
            </a>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/common.js"></script>
</body>
</html>