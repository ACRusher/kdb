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
    <script type="text/javascript" src="http://api.map.baidu.com/api?type=quick&ak=rB7LzGxKX9N2EpxQpcj2iuaf&v=1.0"></script>
    <%@ include file="head.jsp"%>
    <style type="text/css">
        #keyword{
            width:80%;
            display:inline-block;
        }
        .sug_list{
            text-align: left;
            width: 70%;
            margin: -10px auto;
            margin-bottom: 15px;
        }
        .sug_list ul{
            margin-left: 7px;
            border: 1px solid #ccc;
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
            padding-bottom: 5px
        }
    </style>
</head>
<body>
<div class="container" id="page25">
    <div class="row top"  style="margin-top: -3px;">
        <img src="images/decorate.png" class="filled" alt=""/>
        <img src="images/bac_1.png" class="filled" alt=""/>
        <p class="title"><a href="javascript:history.back();" style="color: #fff;"><img src="./images/back.png" class="back" />&nbsp;&nbsp;返回</a></p>

        <form class="form-inline sousuo" action = '/4' method='post'>
            <div class="form-group">
                <label class="sr-only" for="keyword">Email address</label>
                <input type="text" name="keyword" class="form-control" id="keyword" placeholder="请输入小区名称查询" autocomplete="false">
                <input type="hidden" name="isp" value="" id="isp">
                <a id="nextPage" href="javascript:;" class="btn btn-default">&nbsp;</a>
            </div>
            <input type="hidden" name="searchBd" class="form-control" id="searchBd" />
            <div class="sug_list" style="display:none;">
                <ul id='listUL' style="overflow: scroll;height:219px;"></ul>
            </div>
            <!--<button type="submit" class="btn btn-default">&nbsp;</button>-->

        </form>
    </div>

    <div class="row navbar-fixed-bottom" id="nav_list">
        <ul class="nav nav-pills nav-justified">
            <li role="presentation" class="active"><a href="/home" class="col-xs-3">
                <img src="images/home.png" alt=""/>
                <div>首页</div></a></li>
            <li role="presentation"><a href="2" class="col-xs-3">
                <img src="images/help.png" alt=""/>
                <div>宽带求助</div></a></li>
            <li role="presentation"><a href="25" class="col-xs-3">
                <img src="images/hao_cur.png" alt=""/>
                <div>哪家好</div></a></li>
            <li role="presentation"><a href="24" class="col-xs-3">
                <img src="images/me.png" alt=""/>
                <div>我的</div></a></li>
        </ul>
    </div>
        <div id="allmap"></div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/common.js"></script>
<script type= "text/javascript">
    // 百度地图API功

        // 获取地理位置信息
        // citycode=131代表北京
            // "lng":116.32114413239,"lat":40.058026298871}
        var pointUrl = "http://api.map.baidu.com/location/ip?ip=&ak=rB7LzGxKX9N2EpxQpcj2iuaf&coor=bd09ll&callback=?";
        var positionBaseUrl = 'http://api.map.baidu.com/geocoder/v2/?ak=rB7LzGxKX9N2EpxQpcj2iuaf&location=()&output=json&pois=1&callback=?';
        var placeSuggestiionUrl = 'http://api.map.baidu.com/place/v2/suggestion?region=131&output=json&ak=rB7LzGxKX9N2EpxQpcj2iuaf&callback=?&query=';

        /**
            只能定位到城市：先关闭该功能
            定位思路：（h5接口，不稳定，且存在限制）
            1. 服务器端返回当前用户ip
            2. 根据ip，调用百度接口，查询用户的经纬度坐标
               http://lbsyun.baidu.com/index.php?title=webapi/ip-api
            3. 根据经纬度坐标，调用百度接口，查询具体位置
               http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding
            注意：2步中url参数没有设置ip，使用访问者当前的ip（就是我们想要的）
        */
        /*$.ajax({
            type : "GET",
            url : pointUrl,
            dataType : "jsonp",
            jsonp: 'callback',
            success : function(json){
                // console.log('position = ' +JSON.stringify(json));
                var x = json.content.point.x, y = json.content.point.y;
                $.ajax({
                    type : 'GET',
                    url : positionBaseUrl.replace('()',[y, x].join(',')),
                    dataType : "jsonp",
                    jsonp : 'callback',
                    success : function(r){
                        //console.log('r = ' + JSON.stringify(r));
                        var addr = r.result.formatted_address;
                        $('#keyword').val(addr);
                    }
                });
            }
        });
        */
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
            $('#searchBd').val(JSON.stringify(item));
            $('.sousuo').submit();
        }
        function liHover(e){
            $(this).find('div').css({
                'background-color':'#ccc',
                'color':'#000'
            });
        }
        $('#nextPage').click(function(e){
            // 先调用ajax至baidu，直接向服务器提交百度返回的第一条数据
            // 发送ajax
            var v = $('#keyword').val().trim();
            if(v){
                $.ajax({
                    type : 'GET',
                    url : placeSuggestiionUrl+encodeURIComponent(v),
                    dataType : "jsonp",
                    jsonp : 'callback',
                    success : function(r){
                        //console.log('r = ' + JSON.stringify(r));
                        if(r.message == 'ok'){
                            var item = r.result[0];
                            console.log('item = ' + JSON.stringify(item));
                            $('#searchBd').val(encodeURIComponent(item));

                        }
                        $('.sousuo').submit();
                    },
                    error :function(){
                        $('.sousuo').submit();
                    }
                });
            }
        });
    $.ajax({
        type : 'GET',
        url : '/ajax/isp',
        data : {

        },
        success : function(r){
            console.log(r);
            if(r && r.success){
                $("#isp").attr("value", r.data);
            }
        }
    })
</script>

</body>
</html>