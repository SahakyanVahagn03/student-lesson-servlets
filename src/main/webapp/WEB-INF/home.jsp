<%@ page import="com.example.studentlessonservlets.model.User" %><%--
  Created by IntelliJ IDEA.
  User: 37494
  Date: 19.01.2024
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<%User user = (User) request.getSession().getAttribute("user");%>
<body>
<div class="container">
    <div class="header_for_profile">
        <div class="profile">
            <%if (user.getPicture() != null) {%>
            <img src="/downloadImage?imageName=<%=user.getPicture()%>" width="100px">
            <%} else {%>
            no picture
            <%}%>
            <p><%=user.getName() + " " + user.getSurname()%>
            </p>
        </div>
        <div class="logout">
            <a href="/logout">logout</a>
        </div>
    </div>
    <div class="home_main">
        <div class="items">
            <a href="/lessons">
                <img src="/img/lesson.jpeg" style="width:300px">
                <h4>lessons</h4>
            </a>
            <a href="/students">
                <img src="/img/shutterstock_1860471418.jpg" style="width:300px">
                <h4>students</h4>
            </a>

        </div>

        <div class="items">
            <a href="/myStudents">
                <img src="/img/lesson.jpeg" style="width:300px">
                <h4>my students</h4>
            </a>

            <a href="/myLessons">
                <img src="/img/shutterstock_1860471418.jpg" style="width:300px">
                <h4>my lessons</h4>
            </a>
        </div>
    </div>
</div>
</body>
</html>
