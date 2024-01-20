<%--
  Created by IntelliJ IDEA.
  User: 37494
  Date: 19.01.2024
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%String msg = (String) request.getAttribute("msg");%>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="main_for_add_and_edit">
    <%if (msg != null){%>
    <span style="color:red"><%=msg%></span>
    <%}%>
    <form method="post" action="/register" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="name"><br>
        <input type="text" name="surname" placeholder="surname"><br>
        <input type="text" name="email" placeholder="email"><br>
        <input type="password" name="password" placeholder="password"><br>
        <input type="file" name="picture"><br>
        <input type="submit" value="add">
    </form>
</div>
</body>
</html>
