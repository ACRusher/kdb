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
<div class="container" id="page13">
    <div class="row">
        <p class="title">北京电信（海淀区）<a href="#" class="commit">提交</a></p>
        <p class="wrapper" id="all_score">
            总体<span class="star" style="margin:-3px 0 0 8px;"><span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span></span>
        </p>
        <ul id="detail_score" class="service_score" style="display: none;">
            <li class="clearfix">
                <!--★★★-->
                <div class="col-xs-2" style="padding-right: 0;">网速</div>
                <div class="col-xs-8 star"><span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span></div>
                <div class="col-xs-2" style="padding-left: 0;">很好</div>
            </li>
            <li class="clearfix">
                <div class="col-xs-2" style="padding-right: 0;">服务</div>
                <div class="col-xs-8 star"><span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span></div>
                <div class="col-xs-2" style="padding-left: 0;">很好</div>
            </li>
            <li class="clearfix">
                <div class="col-xs-2" style="padding-right: 0;">稳定</div>
                <div class="col-xs-8 star"><span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span></div>
                <div class="col-xs-2" style="padding-left: 0;">很好</div>
            </li>
        </ul>


        <div class="panel">
            <div class="panel-heading">点赞</div>
            <div class="panel-body" id="like_zan">
                <div class="col-xs-4 cur">
                    <a href="#" role="button" class="btn btn-default">看视屏不错</a>
                </div>
                <div class="col-xs-4">
                    <a href="#" role="button" class="btn btn-default">玩游戏不错</a>
                </div>
                <div class="col-xs-4">
                    <a href="#" role="button" class="btn btn-default">下载bt不错</a>
                </div>
                <div class="col-xs-4">
                    <a href="#" role="button" class="btn btn-default">下载app不错</a>
                </div>
                <div class="col-xs-4">
                    <a href="#" role="button" class="btn btn-default">上传速度不错</a>
                </div>
            </div>
        </div>

        <div class="panel">
            <div class="panel-heading">吐槽</div>
            <div class="panel-body" id="hide_zan">
                <div class="col-xs-4">
                    <a href="#" role="button" class="btn btn-default">看视屏不错</a>
                </div>
                <div class="col-xs-4 cur">
                    <a href="#" role="button" class="btn btn-default">玩游戏不错</a>
                </div>
                <div class="col-xs-4">
                    <a href="#" role="button" class="btn btn-default">下载bt不错</a>
                </div>
                <div class="col-xs-4">
                    <a href="#" role="button" class="btn btn-default">下载app不错</a>
                </div>
                <div class="col-xs-4">
                    <a href="#" role="button" class="btn btn-default">上传速度不错</a>
                </div>
            </div>
        </div>

        <form action="" style="margin:0 15px;">
            <div class="form-group title-wrapper">
                <label class="sr-only" for="">标题</label>
                <input type="text" maxlength="30"  class="form-control" id="" placeholder="还想说点什么">
            </div>
            <!--提交表单打开-->
            <!--<button type="submit" class="btn btn-default col-xs-10 col-xs-offset-1">提交</button>-->
            <a href="./27" style="background-color: #fe8400;color: #ffffff;margin-bottom: 20px;" class="btn btn-default col-xs-10 col-xs-offset-1">提交</a>
        </form>
    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/common.js"></script>
<script type="text/javascript">

    $('#all_score span').on('click', function(e){
        var index = $(this).index();
        var children = $(this).parent().children();
        for(var i = 0; i < children.length; i++){
            if(i <= index){
                children.eq(i).text('★');
            }else{
                children.eq(i).text('☆');
            }
        }
        if($('#detail_score').css('display') == 'none'){
            $('#detail_score').slideDown(300);
        }

        return false;
    });

    $('#detail_score .star').each(function(i, item){
        $(item).find('span').on('click', function(e){
            var index = $(this).index();
            var children = $(this).parent().children();
            for(var i = 0; i < children.length; i++){
                if(i <= index){
                    children.eq(i).text('★');
                }else{
                    children.eq(i).text('☆');
                }
            }
        });
    });

    switchTabs([$('#like_zan > div'), $('#hide_zan > div')]);

</script>
</body>
</html>