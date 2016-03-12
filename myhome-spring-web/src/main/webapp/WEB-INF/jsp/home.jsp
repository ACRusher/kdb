<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.kdb.manager.ArticleManager" %>
<%@ page import="com.kdb.model.Articles" %>
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
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <%@ include file="head.jsp"%>
    <style>
        .subtitle{
            line-height: 40px;
            font-size: 16px;
            color: #999;
            padding-top: 10px;
        }
        .hot-ques{
            background-color: #fff;
            padding-left: 30px;
            padding-right: 30px;
        }
        .hot-ques  li{
            line-height:1.5em;
            position:relative;
            border-bottom: 1px solid #ccc;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
        }

        .hot-ques a{
            color: #262626;
            font-weight: bold;
            padding: 5px 15px;
            display: inline-block;
            font-size:14px;
        }
        .hot-ques a:before{
            position: absolute;
            left: 0;
            top: 11px;
            content: " ";
            display: inline-block;
            background-color: #ffa201;
            height: 10px;
            width: 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <img class="big_title" src="images/big_title.jpg"  alt=""/>
    </div>
    <div class="row" style="background-color: #eeeff0;padding-top: 5px;padding-bottom: 5px;">
        <div class="col-xs-3 service">
            <a style="" href='/2'>
                <img src="images/wenhao.png" style="" alt=""/>
                <p>宽带求助</p>
            </a>
        </div>
        <div class="col-xs-3 service">
            <a href="/25">
                <img src="images/wifi.png" alt="" style=""/>
                <p>宽带哪家好</p>
            </a>
        </div>
        <div class="col-xs-3 service">
            <a href='/8'>
                <img src="images/video.png" alt="" style=""/>
                <p>宽带曝光</p>
            </a>
        </div>

        <div class="col-xs-3 service">
            <a href='/1_2'>
                <img src="images/cesu.png" alt="" style=""/>
                <p>宽带测速</p>
            </a>
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
                <a class="item active" href="1_2">
                    <img class="big_title" src="images/huodong_1.png" style="height: 130px;" alt=""/>

                    <div class="carousel-caption">
                        <!--<h3>this is title</h3>
                        <p>this is content</p>-->
                    </div>
                </a>
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

    <div class="row" style="margin-bottom: 60px;background-color:#eeeff0;padding-top: 5px;">
        <div class="hot-ques">
            <p class="subtitle">热门话题</p>
            <ul>
                <%
                    ApplicationContext applicationContext=RequestContextUtils.getWebApplicationContext(request);
                    ArticleManager articleManager= (ArticleManager) applicationContext.getBean("articleManager");
                    List<Articles> list=articleManager.getRecentArticles(6);
                    for(Articles a : list){
                %>
                <li><a href="9?id=<%=a.getId()%>"><%=a.getTitle()%></a></li>
                <%
                    }
                %>

            </ul>
        </div>
    </div>

    <div class="row navbar-fixed-bottom" id="nav_list">
        <ul class="nav nav-pills nav-justified">
            <li role="presentation" class="active"><a href="/home" class="col-xs-3">
                <img src="images/home_cur.png" alt=""/>
                <div>首页</div></a></li>
            <li role="presentation"><a href="2" class="col-xs-3">
                <img src="images/help.png" alt=""/>
                <div>宽带求助</div></a></li>
            <li role="presentation"><a href="./25" class="col-xs-3">
                <img src="images/hao.png" alt=""/>
                <div>哪家好</div></a></li>
            <li role="presentation"><a href="./24" class="col-xs-3">
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



    $('#nav_list a').click(function(e){
        var $self = $(this);

        $(this).parent();
    });


</script>


</body>
</html>