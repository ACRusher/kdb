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
<div class="container" id="page12">
    <div class="row">
        <p class="title"><a href="javascript:history.back();"><img src="./images/back.png" class="back" /></a>曝真相</p>

        <p class="cont">已接受你的曝光请求，我们需要更有说服力的数据，请在连接wifi的环境下测试网速</p>
        <!--<button type="submit" class="btn btn-default col-xs-10 col-xs-offset-1">我要安装</button>-->
        <a href="1_2" style="" class="btn btn-default col-xs-10 col-xs-offset-1">网速测评</a>


    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<script type="text/javascript">
    $('#menu1 li').click(function(e){
        var val = $(this).text();
        $('#topic').html(val + ' <span class="caret"></span>');
    });
</script>

</body>
</html>