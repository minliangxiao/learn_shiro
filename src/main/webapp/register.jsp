<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/8/3
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>用户注册</h1>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    用户名： <input type="text" name="username"> <br>
    密码：<input type="password" name="password"> <br>
    <input type="submit" value="注册">

</form>
</body>
</html>
