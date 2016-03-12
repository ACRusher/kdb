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
<div class="container" id="page21">
    <div class="row">
        <p class="title">&nbsp;</p>
        <div class="wrapper clearfix">
            <div class="col-xs-6">
                <h4>性能测评</h4>
                <ul>
                    <li><span class="">PING</span>42ms</li>
                    <li><span class="img_tip2">下载</span>24.5M/S</li>
                    <li><span class="img_tip3">上传</span>1.2M/S</li>
                </ul>

            </div>
            <div class="col-xs-6">
                <h4>性能测评</h4>
                <ul>
                    <li><span class="">PING</span>42ms</li>
                    <li><span class="img_tip2">下载</span>2.5M/S</li>
                    <li><span class="img_tip3">上传</span>1.2M/S</li>
                </ul>

            </div>
        </div>




        <form action="">


            <div class="form-group clearfix">
                <label class="col-xs-5 control-label" style="padding-top: 5px;text-align: right;">选择宽带大小</label>
                <div class="col-xs-6 p0">
                    <div class="dropdown" id="">
                        <a id="topic" style="margin-top: 0;width: 100%;" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                            50兆
                            <span class="caret"></span>
                        </a>
                        <ul id="menu1" class="dropdown-menu" style="width: 100%;" aria-labelledby="topic">
                            <li><a href="#">话题1</a></li>
                            <li><a href="#">话题2</a></li>
                            <li><a href="#">话题3</a></li>
                            <li><a href="#">话题4</a></li>
                        </ul>
                    </div>
                </div>

            </div>
            <div class="outer clearfix">
                <div class="col-xs-12">
                    <img class="img-circle" style="width: 45px;height: 45px;" src="images/person.jpg" alt=""/>
                    <p class="col-xs-8" style="">您的宽带缩水大概</p>
                    <p class="col-xs-8" style="">30%</p>
                </div>
            </div>

            <button type="submit" class="btn btn-default col-xs-10 col-xs-offset-1">请看真相</button>

        </form>
    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
</body>
</html>