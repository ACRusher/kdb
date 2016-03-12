<%--
  Created by IntelliJ IDEA.
  User: zhouxiliang
  Date: 15/12/4
  Time: 上午12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <%@ include file="head.jsp"%>
</head>
<body>
hello ,world

<form id="image-form" method="post" class="form-inline" action="/upload/image" enctype="multipart/form-data">
    <input type="file" class="form-control" name="file" value="图片"/>
    <button type="submit" class="button" name="submit" >提交图片</button>
</form>
</body>
</html>
