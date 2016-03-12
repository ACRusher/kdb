<%--
  Created by IntelliJ IDEA.
  User: zhouxiliang
  Date: 15/11/8
  Time: 下午12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myhome</title>
    <script type="text/javascript" src="/js/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
    <link href="/css/bootstrap-combined.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<h1>idea14 注册码生成器</h1>
<div class="span12">
    <label class="label">请输入注册用户名：</label>
    <input  id="name" type="text" class="input-medium">
    <button onclick="onRegister()">确定</button>
    <div id="result" class="alert-block">

    </div>
</div>
<script>
    function onRegister(){
        name=$("#name").text();
        var request=$.ajax({
            url : "/idea14/register.action",
            method : "post",
            data : {
                username : name
            }
        });
        request.done(function(data,textstatus,xhr){
            if(textstatus!="success") alert(textstatus);
            $("#result").text(data);
        });


    }
</script>
</body>
</html>
