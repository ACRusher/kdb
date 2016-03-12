<%@ page import="com.kdb.manager.ArticleCommentsManager" %>
<%@ page import="com.kdb.manager.ArticleManager" %>
<%@ page import="com.kdb.model.ArticleComments" %>
<%@ page import="com.kdb.model.Articles" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
        li {
            background-color: #e5e5e5;
        }

        li.cur {
            background-color: #fff;
        }

        li {
            padding: 10px 15px;
            border-bottom: 1px solid #ccc;
        }

        .cur .bref {
            padding: 5px 0;
        }

        li .bref {
            border-bottom: 1px solid #ccc;
            font-weight: bold;
            padding: 5px 15px;
        }

        li .que {
            padding: 10px 15px;
            padding-bottom: 0;
        }

        #send_msg {
            padding: 5px;

            margin: 0;
        }

        #send_msg .form-group {
            margin-bottom: 0;
            width: 80%;
        }

        #msg_submit {
            width: 17%;
        }

        .media-body {
            position: relative;
        }

        .reply {
            display: inline-block;
            position: absolute;
            right: 10px;
            top: 1em;
            background-color: #4bc0e3;
            color: #fff;
            padding: 5px 10px;
        }
    </style>
</head>
<body>
<div class="container" id="page9">
    <div class="row" style="margin-bottom:55px;">
        <p class="title"><a href="javascript:history.back();"><img src="./images/back.png" class="back"/></a>故障解决
            <!--<a href="1" class="right_tip">关闭</a>--></p>
        <ul id="replyList" class="" style="">
            <li class="cur">
                <%
                    ApplicationContext applicationContext = RequestContextUtils.getWebApplicationContext(request);
                    ArticleManager articleManager = (ArticleManager) applicationContext.getBean("articleManager");
                    Articles a = articleManager.getById(Long.valueOf(request.getParameter("id")==null?"1":request.getParameter("id")));
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                %>
                <div class="media" id="article" data-article-id="<%=a.getId()%>" data-author-id="<%=a.getAuthorId()%>">
                    <div class="media-left">
                        <img class="img-circle" style="width: 48px;height:48px;" src="images/person.jpg"
                             alt=""/>                                </a>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading"><%=a.getAuthorName()%>
                        </h4>

                        <p class=""><%=sdf.format(a.getGmtCreate())%></p>
                    </div>
                    <p class="bref"><%=a.getTitle()%>
                    </p>

                    <p class="que fs12"><%=a.getContent()%>
                    </p>
                </div>
            </li>

            <li class="">
                <%
                    ArticleCommentsManager articleCommentsManager = (ArticleCommentsManager) applicationContext.getBean("articleCommentsManager");
                    List<ArticleComments> list = articleCommentsManager.getArticleComments(a.getId());
                    if (list != null) {
                        for (ArticleComments articleComments : list) {


                %>
                <div class="media">
                    <div class="media-left">
                        <img class="img-circle" style="width: 48px;height:48px;" src="images/person.jpg"
                             alt=""/>                                </a>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading"><%=articleComments.getAuthorName()%></h4>

                        <p class=""><%=sdf.format(articleComments.getGmtCreate())%></p>
                        <a href='javascript:;' class="reply" data-author-id="<%=articleComments.getAuthorId()%>">回复</a>
                    </div>
                    <p class="bref">回复给：<%=articleComments.getToAuthorName()%></p>

                    <p class="que fs12"><%=articleComments.getContent()%></p>
                </div>
                <%
                        }
                    }
                %>
            </li>

        </ul>
    </div>

    <div class="row navbar-fixed-bottom" id="" style="margin:0;">
        <form id="send_msg" class="form-inline sousuo nav nav-pills nav-justified">
            <div class="form-group">
                <label class="sr-only" for="keyword">Email address</label>
                <input type="text" class="form-control" id="keyword" placeholder="回复给楼主">
            </div>
            <button type="submit" id="msg_submit" class="btn btn-default">回复</button>
        </form>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/common.js"></script>
<script type="text/javascript">
    $('#menu1 li').click(function (e) {
        var val = $(this).text();
        $('#topic').html(val + ' <span class="caret"></span>');
    });

    var globalObj = {};

    globalObj.replyTo = $('#replyList .cur .media-heading').text();
    globalObj.replyToAuthorId = $('#article').attr('data-author-id');
    globalObj.replyToArticleId= $('#article').attr('data-article-id');
    $('.reply').click(function (e) {
        globalObj.replyTo = $(this).closest('.media-body').find('.media-heading').text();
        globalObj.replyToAuthorId = $(this).attr('data-author-id');
        $('#keyword').focus();
        $('#keyword').attr('placeholder', '回复给：' + globalObj.replyTo);
        return false;
    });
    $('#msg_submit').click(function (e) {

        /**
         回复帖子
         1. 获取用户和回复者信息
         2. 在页面上创建帖子内容
         3. 发送ajax到服务器
         */
        var obj = parseCookie();
        var replyFrom = '';
        if (obj) replyFrom = obj.phone;
        replyFrom=replyFrom.substr(0,3)+'****'+replyFrom.substr(7);
        var t = getFormatDate();
        var cont = $('#keyword').val();
        var $item = $('<li class="">' +
                '<div class="media">' +
                '<div class="media-left">' +
                '<img class="img-circle"style="width: 48px;height:48px;" src="images/person.jpg" alt=""/>' +
                '</div>' +
                '<div class="media-body">' +
                '<h4 class="media-heading">' + replyFrom + '</h4>' +
                '<p class="">' + t + '</p>' +
                '</div>' +
                '<p class="bref">回复给：' + globalObj.replyTo + '</p>' +
                '<p class="que fs12">' + cont + '</p>' +
                '</div>' +
                '</li>');
        /**
         // 自己不能回复自己
         $item.find('.reply').click(function(e){
                    globalObj.replyTo = $(this).closest('.media-body').find('.media-heading').text();
                    $('#keyword').focus();
                    $('#keyword').attr('placeholder', '回复给：'+globalObj.replyTo);
                    return false;
                });
         */
        $('#replyList').append($item);
        $('#keyword').val("");
        $.ajax({
            type : 'POST',
            url : '/ajax/postcomments',
            data : {
                articleId : globalObj.replyToArticleId,
                toAuthorId : globalObj.replyToAuthorId,
                content : cont
            },
            success : function(r){
                console.log(r);
            },
            error : function(e){

            }
        });
        return false;
    });
</script>
</body>
</html>