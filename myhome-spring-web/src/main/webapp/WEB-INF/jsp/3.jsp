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
<div class="container" id="topic-page">
    <div class="row">
        <p class="title"><a href="javascript:history.back();"><img src="./images/back.png" class="back" /></a>发表话题<span class="tip">提问</span></p>

        <form action="#" method="post">
            <div class="dropdown" id="topic-wrapper">
                <a id="topic" href="#" class="dropdown-toggle"  data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                    话题
                    <span class="caret"></span>
                </a>
                <ul id="menu1" class="dropdown-menu" aria-labelledby="topic">
                    <li data-index = "0"><a href="#">故障解答</a></li>
                    <li data-index = "1"><a href="#" >安装技术</a></li>
                    <li data-index = "2"><a href="#">宽带业务</a></li>
                </ul>
            </div>
            <input type="hidden"  name = "type" id="curIndex" value="${curIndex}" />
            <div class="form-group title-wrapper">
                <label class="sr-only" for="title">标题</label>
                <input name="title" type="text" maxlength="30" class="form-control" id="title" placeholder="标题（可选）">
            </div>

            <div class="form-group comment-wrapper">
                <label class="sr-only" for="comment">内容</label>
                <textarea rows="10" name="content" maxlength="500" class="form-control" id="comment" placeholder="请填写内容"></textarea>
            </div>
            <div class="" style="text-align: left">${message}</div>
            <button type="submit" style="background-color: #4bc0e3;color: #fff;" class="btn btn-default col-xs-8 col-xs-offset-2">我要提问</button>

        </form>


    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/common.js"></script>
<script type="text/javascript">

    $(document).ready(function(){
        var queries = parseUrl();
        if(queries && queries.curIndex){
            var index = parseInt(queries.curIndex);
            $("#curIndex").val(index);
            var cont = $('#menu1 li').eq(index).find('a').text();
            $('#topic').html(cont+'<span class="caret"></span>')
        }
    });
        $('#menu1 li').click(function(e){
            var index = $(this).attr('data-index');
            $("#curIndex").val(index);

        });
    $('#menu1 li').click(function(e){
        var val = $(this).text();
        $('#topic').html(val + ' <span class="caret"></span>');
    });
</script>

</body>
</html>