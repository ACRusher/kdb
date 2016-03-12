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
<div class="container" id="page8">
    <div class="row">
        <p class="title">发表话题<a href="1" class="right_tip">关闭</a></p>
        <div class="media" style="margin: 15px 15px 0 15px;">
            <div class="media-left">
                <a href="#">
                    <img class="img-circle"style="width: 45px;height:45px;" src="images/person.jpg" alt=""/>                                </a>
            </div>
            <div class="media-body">
                <h4 class="media-heading" style="font-size: 16px;line-height: 45px;">请详细描述您的真实经历</h4>
            </div>
        </div>

        <form action="" style="margin:0 15px;">
            <div class="form-group title-wrapper">
                <label class="sr-only" for="title">标题</label>
                <input type="text" maxlength="30"  class="form-control" id="title" placeholder="简述商家行为或个人遭遇">
            </div>

            <div class="form-group comment-wrapper">
                <label class="sr-only" for="title">内容</label>
                <textarea rows="6"  maxlength="500" class="form-control" id="comment" placeholder="请填写内容"></textarea>
            </div>

            <div class="media" style="">
                <div class="media-left">
                    <a href="#">
                        <img class="img-circle"style="width: 45px;height:45px;" src="images/person.jpg" alt=""/>                                </a>
                </div>
                <div class="media-body">
                    <h4 class="media-heading" style="font-size: 16px;line-height: 45px;">请提交您的真实信息</h4>
                </div>
            </div>

            <div class="form-group title-wrapper">
                <label class="sr-only" for="title">标题</label>
                <input type="text" maxlength="30"  class="form-control" id="title" placeholder="真实姓名">
            </div>
            <div class="form-group title-wrapper">
                <label class="sr-only" for="title">标题</label>
                <input type="text" maxlength="30"  class="form-control" id="title" placeholder="身份证">
            </div>
            <div class="form-group title-wrapper">
                <label class="sr-only" for="title">标题</label>
                <input type="text" maxlength="30"  class="form-control" id="title" placeholder="手机号码">
            </div>
            <div class="form-group title-wrapper">
                <label class="sr-only" for="title">标题</label>
                <input type="text" maxlength="30"  class="form-control" id="title" placeholder="短信验证码">
            </div>
            <!--<button type="submit" class="btn btn-default col-xs-10 col-xs-offset-1">提交</button>-->
            <a href="12" class="btn btn-default col-xs-10 col-xs-offset-1">提交</a>

        </form>
    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
</body>
</html>