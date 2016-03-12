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
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <%@ include file="head.jsp"%>
</head>
<body>
<div class="container" id="page24">
    <div class="row">
        <div class="person-info">
            <img class="img-circle" src="images/person.jpg" alt=""/>
        <!--
            <p style="margin-top: 15px;"><span>ace</span>张旭</p>
        -->
        <p style="margin-top: 15px;" id="online">
            <a href='/18'>登录/注册</a>
        </p>

        </div>

        <div class="media">
            <div class="media-left">
                <a href="#">
                    <img class="img-circle"style="width: 48px;height:48px;" src="images/person.jpg" alt=""/>                                </a>
            </div>
            <a id="my_msg" href="/18?redirect=/24" class="media-body">
                <span class="">我的消息</span>
            </a>
        </div>
        <div class="bac_3"></div>
        <div class="row navbar-fixed-bottom" id="nav_list">
            <ul class="nav nav-pills nav-justified">
                <li role="presentation" class="active"><a href="/home" class="col-xs-3">
                    <img src="images/home.png" alt=""/>
                    <div>首页</div></a></li>
                <li role="presentation"><a href="2" class="col-xs-3">
                    <img src="images/help.png" alt=""/>
                    <div>宽带求助</div></a></li>
                <li role="presentation"><a href="25" class="col-xs-3">
                    <img src="images/hao.png" alt=""/>
                    <div>哪家好</div></a></li>
                <li role="presentation"><a href="24" class="col-xs-3">
                    <img src="images/me_cur.png" alt=""/>
                    <div>我的</div></a></li>
            </ul>
        </div>
    </div>


</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/common.js"></script>
<script type='text/javascript'>
    // 通过cookie判断用户登录
    $(document).ready(function(){
        if($('#my_msg').attr('href') != '/17'){
            var obj = parseCookie();
            console.log('cookie = ' + JSON.stringify(obj));
            if(obj && obj.phone){
                $('#online').html('<span>'+obj.phone+'</span>');
                $('#my_msg').attr('href', '/17');
            }
        }

    });
</script>
</body>
</html>
