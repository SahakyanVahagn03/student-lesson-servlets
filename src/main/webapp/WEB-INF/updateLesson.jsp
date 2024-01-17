<%@ page import="com.example.studentlessonservlets.model.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: 37494
  Date: 16.01.2024
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<%
    Lesson lesson = (Lesson) request.getAttribute("lesson");
%>
<body>

<div class="main_for_add_and_edit">
    <form method="post" action="/updateLesson">
        <input type="hidden" name="id"  value="<%=lesson.getId()%>"><br>
        <input type="text" name="name" placeholder="name" value="<%=lesson.getName()%>"><br>
        <input type="text" name="duration" placeholder="duration" value="<%=lesson.getDuration()%>"><br>
        <input type="text" name="lecturerName" placeholder="lecturer name" value="<%=lesson.getLecturerName()%>"><br>
        <input type="number" name="price" placeholder="price" value="<%=lesson.getPrice()%>"><br>
        <input type="submit" value="update">
    </form>
</div>

</body>
</html>
