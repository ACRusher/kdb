<%@ page import="com.kdb.vo.Article" %>
<%@ page import="java.util.List" %>
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
    <%@ include file="head.jsp" %>
    <style type="text/css">
        a {
            display: inline-block;
            color: #000;
        }
    </style>
</head>
<body>
<div class="container" id="">
    <div class="row">
        <p class="title">宽带求助 <a href="/18?redirect=/2" id="online" class="tip">提问</a></p>

        <form class="form-inline sousuo">
            <div class="form-group">
                <label class="sr-only" for="keyword">Email address</label>
                <input type="text" class="form-control" id="keyword" name="keyword" value="${keyword}"
                       placeholder="请输入关键词">
                <input type="hidden" name="curIndex" value="0">
            </div>
            <button id="search" type="submit" class="btn btn-default">搜索</button>
        </form>
        <div class="selection clearfix">
            <%
                String[]arr={"故障解答","安装技术","宽带业务"};
                int curIndex=-0;
                String s=request.getParameter("curIndex");
                if(s!=null) curIndex=Integer.valueOf(s);
                for(int i=0;i<arr.length;++i){
                    String curClass="";
                    if(i==curIndex) curClass="cur";
            %>
            <div class="col-xs-4 <%=curClass%>"><a class="btn btn-default" href="#" role="button"><%=arr[i]%></a></div>
            <%
                }
            %>

        </div>

        <ul class="res_list">

            <%
                List<Article> articles = (List<Article>) request.getAttribute("articles");
                for (Article a : articles) {

            %>
            <li style="">
                <a class="item" style="width:100%" href="/9?id=<%=a.getId()%>">
                    <div class="info">
                        <img class="img-circle" src="images/person.jpg" alt=""/>

                        <p class="color1" style="width: 100%"><%=a.getAuthorName()%></p>

                        <p><%=a.getTime()%> </p>

                        <p class="tag color1"><%=a.getTag()==null?"":a.getTag()%> </p>
                    </div>
                    <p class="bref">【经验】<%=a.getTitle()%></p>

                    <p class="fs12"><%=a.getContent().length()>100?a.getContent().substring(0,100):a.getContent()%> </p>
                </a>
            </li>
            <% }%>


            <li>
                <div id='get-more' class='item text_c'>点击加载更多</div>
            </li>
        </ul>
    </div>

    <div class="row navbar-fixed-bottom" id="nav_list">
        <ul class="nav nav-pills nav-justified">
            <li role="presentation"><a href="/home" class="col-xs-3">
                <img src="images/home.png" alt=""/>

                <div>首页</div>
            </a></li>
            <li role="presentation" class="active"><a href="2" class="col-xs-3">
                <img src="images/help_cur.png" alt=""/>

                <div>宽带求助</div>
            </a></li>
            <li role="presentation"><a href="25" class="col-xs-3">
                <img src="images/hao.png" alt=""/>

                <div>哪家好</div>
            </a></li>
            <li role="presentation"><a href="24" class="col-xs-3">
                <img src="images/me.png" alt=""/>

                <div>我的</div>
            </a></li>
        </ul>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/common.js"></script>
<script type='text/javascript'>
    var pageSize = 5, page = 0;
    $('#get-more').on('click', function (e) {
        // ajax get data
        page++;
        var that = this;
        $.ajax({
            type : 'get',
            url : '/ajax/articles?type='+curIndex+'&keyword='+$('#keyword').text()+'&page='+page+'&pageSize='+pageSize,
            success : function(obj){
                // parse data
                //var obj = JSON.parse(data);
                var items = obj.data;
                for (var i = 0; i < items.length; i++) {
                    var item = items[i];

                    var modelItem = '<li><div class="item" style="width:100%;">' +
                            '<div class="info">' +
                            '<img class="img-circle" src="images/person.jpg" alt=""/>' +
                            '<p class="color1">' + item.authorName + '</p>' +
                            '<p>'+item.time+'</p>' +
                            '<p class="tag color1">'+item.tag+'</p>' +
                            ' </div>' +
                            '<p class="bref">【经验】' + item.title + '</p>' +
                            ' <p class="fs12">' + item.content + '</p>' +
                            ' </div></li>';
                    $(that).parent().before($(modelItem));
                }

            }
        });

    });
    var curIndex = 0; // 表示第一个
    // 通过cookie判断用户登录
    $(document).ready(function () {

        var obj = parseCookie();
        console.log('cookie = ' + JSON.stringify(obj));
        if (obj && obj.phone) {
            $('#online').attr('href', '/3');
        }
        $('.selection').children().click(function (e) {
            var flag = $(this).hasClass('cur');
            if (!flag) {
                curIndex = $(this).index();

                $(this).addClass('cur').siblings().removeClass('cur');
//                $(this).addClass('cur'). siblings().removeClass('cur');
                window.location.href="http://"+location.host+location.pathname+"?curIndex="+curIndex;
            }
        });

        $('#online').click(function(e){
            $(this).attr('href', "/3?redirect=2&&curIndex="+curIndex);
            console.log('aaa');
            return true;
        });
        
        var queries = parseUrl();
        if(queries && queries.curIndex){
            var index = parseInt(queries.curIndex);
            type = index;
            $('.selection').children().eq(index).addClass('cur').siblings().removeClass('cur');
        }
        var urlParam = parseUrl();
        console.log("urlParam:" + urlParam);

        if (!urlParam.curIndex) {
            urlParam.curIndex = '0';
        }
        console.log(urlParam.curIndex);
        if (urlParam.curIndex) {
            curIndex = urlParam.curIndex;
            $(".selection").children().eq(curIndex).addClass("cur");
            $(".selection").children().eq(curIndex).siblings().removeClass("cur");
        }

        $("#search").click(function (e) {
            $("form [name='curIndex']").attr("value", curIndex);
        });

    });

</script>
</body>
</html>