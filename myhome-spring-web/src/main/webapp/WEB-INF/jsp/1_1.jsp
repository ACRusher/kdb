<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body>
<div class="container">
    <div class="row">
        <img class="big_title" src="images/big_title.jpg"  alt=""/>
    </div>
    <div class="row m15">
        <div class="col-xs-3 service">
            <div style="">
                <img src="images/wenhao.png" style="" alt=""/>
                <p>宽带求助</p>
            </div>
        </div>
        <div class="col-xs-3 service">
            <div>
                <img src="images/wifi.png" alt="" style=""/>
                <p>宽带哪家好</p>
            </div>

        </div>
    </div>

    <div class="row">
        <div id="my-carousel" class="carousel slide" data-interval="3000" data-pause="hover" data-wrap = "true" data-ride="carousel">

            <!--indicators-->
            <ol class="carousel-indicators">
                <li data-target="#my-carousel" data-slide-to="0" class="active"></li>
                <li data-target="#my-carousel" data-slide-to="1" ></li>
                <li data-target="#my-carousel" data-slide-to="2" ></li>
            </ol>

            <!--wrapper for slides-->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img class="big_title" src="images/huodong_1.png" style="height: 130px;" alt=""/>

                    <div class="carousel-caption">
                        <!--<h3>this is title</h3>
                        <p>this is content</p>-->
                    </div>
                </div>
                <div class="item">
                    <img class="big_title" src="images/huodong_1.png" style="height: 130px;" alt=""/>
                    <div class="carousel-caption">
                        <!--<h3>this is title</h3>
                        <p>this is content</p>-->
                    </div>
                </div>
                <div class="item">
                    <img class="big_title" src="images/huodong_1.png" style="height: 130px;" alt=""/>
                    <div class="carousel-caption">
                        <!--<h3>this is title</h3>
                        <p>this is content</p>-->
                    </div>
                </div>

            </div>

        </div>
        <!---->
    </div>

    <div class="row" style="margin: 0 -10px;margin-bottom: 60px;">
        <div class="col-xs-6 service_1">
            <div style="">
                <img src="images/bao.png" alt="" style="width: 45px;"/>
                <span>宽带曝光</span>
            </div>
        </div>
        <div class="col-xs-6 service_1">
            <div style="line-height: 33px;background-color: #9e85c6;">
                <img src="images/kuandai.png" alt="" style="width: 45px;"/>
                <span>宽带安装</span>
            </div>
        </div>
    </div>

    <div class="row navbar-fixed-bottom" id="nav_list">
        <ul class="nav nav-pills nav-justified">
            <li role="presentation" class="active"><a href="#" class="col-xs-3">
                <img src="images/bottom_1.png" alt=""/>
                <div>首页</div></a></li>
            <li role="presentation"><a href="#" class="col-xs-3">
                <img src="images/me.png" alt=""/>
                <div>宽带求助</div></a></li>
            <li role="presentation"><a href="#" class="col-xs-3">
                <img src="images/me.png" alt=""/>
                <div>哪家好</div></a></li>
            <li role="presentation"><a href="#" class="col-xs-3">
                <img src="images/me.png" alt=""/>
                <div>我的</div></a></li>
        </ul>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
</body>
</html>