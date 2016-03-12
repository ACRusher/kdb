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
    <style type="text/css">
    .sug_list{
    text-align: left;
    width: 81%;
    position: absolute;
    margin-top: -10px;
    }
    .sug_list ul{
    border: 1px solid #ccc;
    background-color: #fff;
    z-index: 9999;
    position: relative;
    }
    .sug_list .region{
    font-size: 12px;
    padding-left: 5px;
    color: #999;
    }
    .sug_list li div{
    border-bottom: 1px solid #ccc;
    padding-left: 10px;
    padding-top: 5px;
    padding-bottom: 5px;
    }
    </style>
</head>
<body>
<div class="container" id="page1_2">
    <div class="row">
        <p class="title"><a href="javascript:history.back();"><img src="./images/back.png" class="back" /></a>宽带帮
            <a href="1" class="right_tip">关闭</a></p>
        <form action="">


            <div class="form-group clearfix">
                <div class="col-xs-10 col-xs-offset-1 speed_select">
                    <div class="form-group">
                        <label class="sr-only" for="keyword">Email address</label>
                        <input type="text" class="form-control" id="keyword" placeholder="请输入小区名称查询">
                    </div>
                    <%--<div class="dropdown" id="">--%>
                        <%--<a id="topic" style="margin-top: 0;width: 100%;" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">--%>
                            <%--选择宽带大小--%>
                            <%--<span class="caret"></span>--%>
                        <%--</a>--%>
                        <%--<ul id="menu1" class="dropdown-menu" style="width: 100%;" aria-labelledby="topic">--%>
                            <%--<li><a href="#">20M</a></li>--%>
                            <%--<li><a href="#">30M</a></li>--%>
                            <%--<li><a href="#">50M</a></li>--%>
                            <%--<li><a href="#">100M</a></li>--%>
                        <%--</ul>--%>
                    <%--</div>--%>
                    <a id="searchRes" href="javascript:;"><img src="images/position.png" alt=""/></a>
                    <div class="sug_list" style="display:none;">
                        <ul id='listUL' style="overflow-y: scroll;height:219px;"></ul>
                    </div>
                </div>

            </div>
            <div class="test_info clearfix">
                <div class="col-xs-3">
                    <p>PING</p>
                    <img src="images/cesu_left1.png" alt=""/>
                </div>
                <div class="col-xs-3">
                    <p>下载</p>
                    <img src="images/cesu_left1.png" alt=""/>
                </div>
                <div class="col-xs-3" >
                    <p>上传</p>
                    <img src="images/cesu_left1.png" alt=""/>
                </div>
                <div class="col-xs-3">
                    <p>缩水</p>
                    <img src="images/cesu_left1.png" alt=""/>
                </div>
            </div>
            <div class="biao_pan">
                <img src="images/zhi_zhen.png" alt="" style=""/>
            </div>

            <div class="jindu">
                <img src="images/down.png" alt=""/>
                <div class="pro"></div>
                <img src="images/up.png" alt=""/>
            </div>
            <h4 style="text-align: center;margin-bottom: 4em;">中国电信北京分工司</h4>

            <div class="row navbar-fixed-bottom" id="nav_list">
                <ul class="nav nav-pills nav-justified">
                    <li role="presentation" class="active"><a href="#" class="col-xs-6">
                        重新测试</a></li>
                    <li role="presentation"><a href="13" class="col-xs-6">
                        我要点评</a></li>
                </ul>
            </div>
        </form>
    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<script type="text/javascript">
    <%--$('#menu1 li').click(function(e){--%>
        <%--var val = $(this).text();--%>
        <%--$('#topic').html(val + ' <span class="caret"></span>');--%>
    <%--});--%>

    var placeSuggestiionUrl = 'http://api.map.baidu.com/place/v2/suggestion?region=131&output=json&ak=rB7LzGxKX9N2EpxQpcj2iuaf&callback=?&query=';

    // keyword显示提示信息
    var searchRes = null;
    var timer = null;
    $('#keyword').keyup(function(e){
        if(timer == null){
            var that = this;
            timer = setTimeout(function(){
            var v = $('#keyword').val().trim();

                // 发送ajax
                $.ajax({
                type : 'GET',
                url : placeSuggestiionUrl+encodeURIComponent(v),
                dataType : "jsonp",
                jsonp : 'callback',
                success : function(r){
                            //console.log('r = ' + JSON.stringify(r));
                            if(r.message == 'ok'){
                            var innerHtml = '';
                            var res = r.result; r = null;
                            searchRes = res;
                            for(var i = 0; i < res.length; i++){
                            var item = '<li>'+
                            '<div>'+res[i].name+'<span class="region">'+res[i].city+res[i].district+'</span></div>'+
                            '</li>';
                            innerHtml += item;
                            }
                            $('#listUL').html(innerHtml);
                            //$('#listUL li').mouseenter(liHover);
                            $('#listUL li').click(liClick);

                            $('.sug_list').slideDown();
                        }
                    }
                });
        timer = null;
        }, 1500);
    }
    });

    function liClick(e){
        var i = $(this).index();
        var item = searchRes[i];
        console.log('item = ' + JSON.stringify(item));
        $('#keyword').val(item.name +' '+item.city +' '+item.district);
        $('#searchBd').val(encodeURIComponent(item));
        $('.sug_list').hide();
        // 发送ajax
    }
</script>
</body>
</html>