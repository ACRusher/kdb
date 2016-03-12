<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="com.kdb.manager.VillageOperatorsManager" %>
<%@ page import="com.kdb.model.VillageOperators" %>
<%@ page import="com.kdb.manager.OperatorManager" %>
<%@ page import="com.kdb.model.Operators" %>
<%@ page import="com.kdb.manager.VillageManager" %>
<%@ page import="com.kdb.model.Village" %>
<%@ page import="com.kdb.model.OperatorPackage" %>
<%@ page import="com.kdb.manager.OperatorPackageManager" %>
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
    <%@ include file="head.jsp"%>
</head>
<body>
<div class="container" id="page6">
    <div class="row">
        <p class="title"><a href="javascript:history.back();" style="color: #fff;"><img src="./images/back.png" class="back" />&nbsp;&nbsp;返回</a>&nbsp;</p>


        <div class="media pd15">
            <%
                ApplicationContext applicationContext= RequestContextUtils.getWebApplicationContext(request);
                VillageOperatorsManager villageOperatorsManager= (VillageOperatorsManager) applicationContext.getBean("villageOperatorsManager");
                OperatorManager operatorManager=(OperatorManager) applicationContext.getBean("operatorManager");
                VillageManager villageManager=(VillageManager) applicationContext.getBean("villageManager");

                Long villageOperatorId=Long.valueOf(request.getParameter("villageOperatorId"));
                VillageOperators villageOperators=villageOperatorsManager.getById(villageOperatorId);
                Operators operators=operatorManager.getById(villageOperators.getOperatorId());
                Village village=villageManager.getById(villageOperators.getVillageId());

                Integer star=Double.valueOf(villageOperators.getStar()).intValue();
                StringBuilder sb=new StringBuilder();
                for(int i=0;i<star;++i){
                    sb.append('★');
                }
                while (sb.length()<5) sb.append('☆');
            %>
            <div id="villageOperator" data-village-operator="<%=villageOperatorId%>" class="media-left media-middle">
                <a href="#">
                    <img class="media-object" src="<%=operators.getLogo()%>" style="width: 64px;height: 64px;">
                </a>
            </div>
            <div class="media-body">
                <h4 class="media-heading"><%=operators.getName()%><span class="fs_12">（<%=village.getArea()+village.getVillage()%>）</span></h4>
                <p class="star"><%=sb.toString()%></p>
            </div>
        </div>

        <form action="" style="border-top: 1px solid #ccc;">
            <p class="name">产品信息</p>
            <div class="form-group clearfix">
                <label class="col-xs-4 control-label">安装区域</label>
                <div class="col-xs-4 p0">
                    <input type="radio" id="infive" name="five" checked/>五环内
                </div>
                <div class="col-xs-4">
                    <input type="radio" id="outfive" name="five"/>五环外
                </div>
            </div>

            <div class="form-group clearfix pb15 bb">
                <label class="col-xs-4 control-label" style="padding-top: 5px;">宽带套餐</label>
                <div class="col-xs-8 p0">
                    <div class="dropdown" id="product">
                        <a id="topic" data-package-id="" style="margin-top: 0;width: 100%;" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                            请选择套餐
                            <span class="caret"></span>
                        </a>
                        <ul id="menu1" class="dropdown-menu" style="width: 100%;" aria-labelledby="topic">
                            <%
                                OperatorPackageManager operatorPackageManager= (OperatorPackageManager) applicationContext.getBean("operatorPackageManager");
                                List<OperatorPackage> operatorPackages=operatorPackageManager.getPackageByOperatorId(operators.getId());
                                if(operatorPackages!=null){
                                    for(OperatorPackage operatorPackage :operatorPackages){
                            %>
                            <li><a href="#" data-package-id="<%=operatorPackage.getId()%>">
                                <%="￥"+operatorPackage.getFee()/100+" "+operatorPackage.getTime()+"个月"+" "+
                                    operatorPackage.getDescription()
                                %>
                            </a></li>
                            <%
                                    }
                                }
                            %>
                        </ul>
                    </div>
                </div>

            </div>

            <p class="name">用户信息</p>
            <div class="form-group title-wrapper">
                <%--<label class="sr-only" for="">申请人</label>--%>
                <input type="text" maxlength="30" class="form-control" id="user-name" placeholder="申请人">
            </div>

            <div class="form-group title-wrapper">
                <%--<label class="sr-only" for="">申请人</label>--%>
                <input type="text" maxlength="30" class="form-control" id="install-place" placeholder="装机地址">
            </div>

            <div class="form-group title-wrapper">
                <%--<label class="sr-only" for="">申请人</label>--%>
                <input type="text" maxlength="30" class="form-control" id="user-phone" placeholder="联系电话">
            </div>
            <!--提交表单，打开下面-->
            <!--<button type="submit" class="btn btn-default col-xs-10 col-xs-offset-1">我要安装</button>-->
            <a href="#"  onclick="submit_form()" style="background-color: #4bc0e3;color: #fff;" class="btn btn-default col-xs-10 col-xs-offset-1">我要安装</a>

        </form>
    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="javascript/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<script type="text/javascript">

    $('#topic').click(function(e){
        $('#menu1').show();
    })
    $('#menu1 li').click(function(e){
        var val = $(this).text();
        $('#topic').html(val + ' <span class="caret"></span>');
        $('#topic').attr('data-package-id',$(this).children().eq(0).attr('data-package-id'));
        $(this).parent().hide();
    });


    function submit_form(){
        var villageOperatorId=$('#villageOperator').attr('data-village-operator');
        var inFiveCircle=document.getElementById("infive").checked?1:0;
        var packageId=$('#topic').attr('data-package-id');
        var userName=$('#user-name').val();
        var place=$('#install-place').val();
        var phone=$('#user-phone').val();
        $.ajax({
            url : '/ajax/createOrder',
            type : 'POST' ,
            data : {
                villageOperatorId : villageOperatorId,
                inFiveCircle : inFiveCircle,
                packageId : packageId,
                userName : userName,
                place : place,
                phone : phone

            } ,
            success : function(r){
                console.log(r);
                if(r.success){
                    window.location.href="/7";
                }else{
                    alert('很遗憾，提交订单失败.')
                }
            } ,
            error : function(){

            }
        })

        //提交表单
    }
//    $.ajax({
//        url : '/order',
//        data : {},
//        success : function(){
//
//        },
//        error : function(){}
//    });
</script>
</body>
</html>