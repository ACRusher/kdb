<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="com.kdb.manager.ArticleCommentsManager" %>
<%@ page import="com.kdb.manager.UserManager" %>
<%@ page import="com.kdb.model.User" %>
<%@ page import="com.kdb.model.ArticleComments" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kdb.manager.ArticleManager" %>
<%@ page import="com.kdb.model.Articles" %>
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
    <%@ include file="head.jsp"%>
</head>
<body>
<div class="container" id="page17">
    <div class="row">
        <p class="title"><a href="javascript:history.back();"><img src="./images/back.png" class="back"/></a>我的消息</p>
        <ul class="res_list">
            <%
                ApplicationContext applicationContext= RequestContextUtils.getWebApplicationContext(request);
                ArticleCommentsManager articleCommentsManager= (ArticleCommentsManager) applicationContext.getBean("articleCommentsManager");
                UserManager userManager= (UserManager) applicationContext.getBean("userManager");
                ArticleManager articleManager= (ArticleManager) applicationContext.getBean("articleManager");
                Cookie[] cookies=request.getCookies();
                User curUser=null;
                if(cookies!=null){
                    for(Cookie cookie:cookies){
                        if(cookie.getName().equals("isonline")){
                            String value=cookie.getValue();
                            String phone=value.split(":")[1];
                            curUser=userManager.getUserByPhone(phone);
                        }
                    }
                }
                List<ArticleComments> list=articleCommentsManager.getArticleCommentsByToAuthorId(curUser.getId());
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                if(list!=null){
                    for(ArticleComments a : list){
                        Articles articles=articleManager.getById(a.getArticleId());
            %>
            <li>
                <div class="item">
                    <div class="info">
                        <img class="img-circle" src="images/person.jpg" alt=""/>
                        <p class="color1"><%=a.getAuthorName()%></p>
                        <p><%=sdf.format(a.getGmtCreate())%></p>
                        <a href="/9?id=<%=a.getArticleId()%>" class="tag color1">查看</a>
                    </div>
                    <p class="fs12"><%=a.getContent()%>/p>
                    <p class="bref">【帖子】<%=articles.getTitle()%></p>
                </div>
            </li>
            <%
                    }
                }
            %>

            <%--<li><div id="get-more" class = "item add_more" style="background-color: #e5e5e5;border:0;text-align:center;">点击加载更多</div></li>--%>
        </ul>
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
                <img src="images/hao.png" alt=""/>
                <div>哪家好</div></a></li>
            <li role="presentation"><a href="24" class="col-xs-3">
                <img src="images/me_cur.png" alt=""/>
                <div>我的</div></a></li>
        </ul>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/common.js"></script>
<script>
        var pageSize = 5, page = 0;
        $('#get-more').on('click', function(e){
        // ajax get data
        page++;
        var that = this;
        $.ajax({
            type : 'get',
            url : '/ajax/articles?type='+1+'&page='+page+'&pageSize='+pageSize,
            success : function(obj){
                // parse data
                //var obj = JSON.parse(data);
                var items = obj.data;
                for(var i = 0; i < items.length; i++){
                    var item = items[i];

                    var modelItem ='<li>' +
                                '<div class="item">'+
                                    '<div class="info">'+
                                        '<img class="img-circle" src="images/person.jpg" alt=""/>'+
                                        '<p class="color1">ace张旭</p>'+
                                        '<p>13:19</p>'+
                                        '<a class="tag color1">查看</a>'+
                                    '</div>'+
                                    '<p class="fs12">有问题回帖即可，楼主在第一时间就给你解答了！着急的别找我，我没收你钱，义务劳动你没资格催我。</p>'+
                                    '<p class="bref">【经验】免费给大家解决宽带问题</p>'+
                                '</div>'+
                            '</li>';
                    $(that).parent().before($(modelItem));
                }

            }
        });

        });
</script>
</body>
</html>