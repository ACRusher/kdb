<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kdb.manager.*" %>
<%@ page import="com.kdb.model.*" %>
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
<div class="container" id="page5">
    <div class="row">
        <p class="title">
        <a href="javascript:history.back();"><img src="./images/back.png" class="back"></a>
        宽带哪家好</p>

        <%
            ApplicationContext applicationContext= RequestContextUtils.getWebApplicationContext(request);
            VillageOperatorsManager villageOperatorsManager= (VillageOperatorsManager) applicationContext.getBean("villageOperatorsManager");
            VillageManager villageManager=(VillageManager) applicationContext.getBean("villageManager");
            OperatorManager operatorManager=(OperatorManager) applicationContext.getBean("operatorManager");
            Long id = Long.valueOf(request.getParameter("id"));
            VillageOperators villageOperators=villageOperatorsManager.getById(id);
            Village village=villageManager.getById(villageOperators.getVillageId());
            Operators operators=operatorManager.getById(villageOperators.getOperatorId());

            Integer start=Double.valueOf(villageOperators.getStar()).intValue();
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<start;++i){
                sb.append('★');
            }
            while (sb.length()<5) sb.append('☆');
        %>

        <div class="media pd15">
            <div class="media-left media-middle">
                <a href="#">
                    <img class="media-object" src="images/telecom.png" style="width: 64px;height: 64px;">
                </a>
            </div>
            <div class="media-body">
                <h4 class="media-heading"><%=operators.getName()%><span class="fs_12">（<%=village.getArea()+village.getVillage()%>）</span></h4>
                <p class="star"><%=sb.toString()%></p>
            </div>
        </div>
        <p class="desc">
            网速<span style="margin-left: 5px"><%=villageOperators.getSpeed()%></span>服务<span style="margin-left: 5px"><%=villageOperators.getService()%></span>稳定<span style="margin-left: 5px"><%=villageOperators.getStable()%></span>
            <a href="6?villageOperatorId=<%=villageOperators.getId()%>">购买</a>
        </p>

        <ul class="detail">
            <li><p style="margin-left:3px;margin-bottom: 5px;padding-bottom: 3px;border-bottom: 1px solid #ccc;">网友点赞</p>
                <%
                    String likes=villageOperators.getLikes();
                    if(StringUtils.isBlank(likes)){
                        likes="网络稳定:13,LOL不卡:5";
                    }
                    String[]arr=likes.split(",");
                    for(String s : arr){
                %>
                <span><%=s.split(":")[0]+"("+s.split(":")[1]+")"%></span>
                <%
                    }
                %>
            </li>
            <li>
                <p style="margin-left:3px;margin-bottom: 5px;padding-bottom: 3px;border-bottom: 1px solid #ccc;">网友吐槽</p>
                <%
                    String complaint=villageOperators.getComplaint();
                    if(StringUtils.isBlank(complaint)){
                        complaint="价格贵:31,下载BT卡:5";
                    }
                    String[]a=complaint.split(",");
                    for(String s : a){
                %>
                <span><%=s.split(":")[0]+"("+s.split(":")[1]+")"%></span>
                <%
                    }
                %>
            </li>
        </ul>

        <div class="panel">
            <div class="panel-heading">网友点评</div>
            <div class="panel-body">
                <ul class="">
                    <%
                        UserCommentManager userCommentManager= (UserCommentManager) applicationContext.getBean("userCommentManager");
                        UserManager userManager=(UserManager)applicationContext.getBean("userManager");
                        List<UserComment> userComments = userCommentManager.getUserCommentByVillageOperatorId(villageOperators.getId(),10);
                        if(userComments!=null){
                            for(UserComment userComment : userComments){
                                User user=userManager.getUserById(userComment.getAuthorId());
                                String name=user.getName();
                                if(StringUtils.isBlank(name)){
                                    name=user.getPhone().substring(0,3)+"****"+user.getPhone().substring(7);
                                }
                                Integer commentStart=Double.valueOf(userComment.getStar()).intValue();
                                StringBuilder stringBuilder=new StringBuilder();
                                for(int i=0;i<commentStart;++i){
                                    stringBuilder.append('★');
                                }
                                while (stringBuilder.length()<5) stringBuilder.append('☆');

                    %>
                    <li>
                        <div class="media">
                            <div class="media-left">
                                <a href="#">
                                    <img class="img-circle"style="width: 64px;height:64px;" src="images/person.jpg" alt=""/>                                </a>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading"><%=name%></h4>
                                <p class="star"><%=stringBuilder.toString()%></p>
                            </div>
                            <br/>
                            <p class="bref"><%=userComment.getContent()%></p>
                            <%--<p class="fs12"><%=userComment.getContent()%></p>--%>
                        </div>
                    </li>

                    <%
                            }
                        }
                    %>


                </ul>
            </div>
        </div>
    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
</body>
</html>