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
<div class="container" id="page19">
    <div class="row">
        <p class="title"><a class="cancel">取消</a>找回密码</p>

        <form action="" style="margin:0 15px;">
            <div class="form-group title-wrapper">
                <label class="sr-only" for="">标题</label>
                <input type="text" maxlength="30"  class="form-control" id="" placeholder="请输入手机号">
            </div>
            <div class="form-group title-wrapper clearfix">
                <label class="sr-only" for="">标题</label>
                <input type="text" maxlength="30"  class="form-control testcode" id="" placeholder="请输入短信验证码">
                <a href="" type="button" class="btn btn-default col-xs-5 col-xs-offset-1">获取验证码</a>
            </div>
            <div class="form-group title-wrapper">
                <label class="sr-only" for="">标题</label>
                <input type="text" maxlength="30"  class="form-control" id="" placeholder="请输入新密码">

            </div>

            <button type="submit" class="btn btn-default col-xs-10 col-xs-offset-1">提交</button>

        </form>
    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
</body>
</html>