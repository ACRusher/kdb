<%@ page import="com.kdb.model.VillageOperators" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.kdb.model.Operators" %>
<%@ page import="java.util.Map" %>
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
        <style type="text/css">
        .sug_list{
            text-align: left;
            width: 72%;
            margin-left: 13px;
            margin-top: -10px;
        }
        .sug_list ul{
            margin-left: 7px;
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
<div class="container" id="page4">
    <div class="row top"  style="margin-top: -3px;">
        <img src="images/decorate.png" class="filled" alt=""/>
        <img src="images/bac_1.png" class="filled" alt=""/>

        <form class="form-inline sousuo">
            <div class="form-group">
                <label class="sr-only" for="keyword">Email address</label>
                <input type="text" class="form-control" name="keyword" id="keyword" value="${keyword}" placeholder="请输入小区地址" autocomplete="false">
            </div>
            <a id="submit" href="javascript:;" class="btn btn-default">搜索</a>
            <div class="sug_list" style="display:none;">
                <ul id='listUL' style="overflow-y: scroll;height:219px;"></ul>
            </div>
        </form>
    </div>

    <div class="row media-wrapper">

        <%
            List<VillageOperators> villageOperatorsList= (List<VillageOperators>) request.getAttribute("operatorList");
            if(villageOperatorsList==null) villageOperatorsList=new ArrayList<VillageOperators>();
            Map<Long,Operators> map= (Map<Long, Operators>) request.getAttribute("operatorMap");
            for(VillageOperators villageOperators : villageOperatorsList){
                int star=0;
                if(StringUtils.isNotBlank(villageOperators.getStar())){
                   double d =Double.valueOf(villageOperators.getStar());
                    star=(int)d;
                }
                Operators operators=map.get(villageOperators.getOperatorId());
                StringBuilder stars=new StringBuilder();
                for(int i=0;i<star;++i) stars.append('★');
                while (stars.length()<5) stars.append('☆');
        %>
        <div class="media" style="display: block;">
            <div class="media-left">
                <a href="#">
                    <img class="media-object" src="<%=operators.getLogo()%>" style="width: 64px;height: 64px;">
                </a>
            </div>
            <a class="media-body" href="5?id=<%=villageOperators.getId()%>">
                <h4 class="media-heading"><%=operators.getName()%></h4>
                <%--<p class="star">★★★☆☆</p>--%>
                <p class="star"><%=stars.toString()%></p>
                <p class="price">最低 <span style="color:#f5953d;">￥<%=operators.getFee()%></span><%=operators.getPeriod()%></p>
            </a>
            <img class="detail-tip" src="images/right-grey.png" alt=""/>
        </div>

        <%
            }
        %>

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
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/common.js"></script>
<script type="text/javascript">
    <%--var queryParams = parseUrl();--%>
    <%--var q = decodeURIComponent(queryParams.q);--%>
    <%--$('#keyword').val(q);--%>
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
        //$('.sousuo').submit();
    }
    $('#submit').click(function(e){
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
                        //发送ajax 获取小区支持的运营商信息
                        $.ajax({
                            type : 'get',
                            url : '/4',
                            dataType : 'json',
                            success : function(r){
                                // 渲染页面
                            }
                        });
                    }
                },
                error :function(){
                    $('.sousuo').submit();
                }
            });
        }
    });

</script>
</body>
</html>