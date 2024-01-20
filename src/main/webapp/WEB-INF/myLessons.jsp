<%@ page import="java.util.List" %>
<%@ page import="com.example.studentlessonservlets.model.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: 37494
  Date: 20.01.2024
  Time: 2:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<% List<Lesson> myLessons = (List<Lesson>) request.getAttribute("myLessons");%>
<body>

<div class="main">
    <%
        if (myLessons != null && !myLessons.isEmpty()) {%>
    <table border="1px">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>duration</th>
            <th>lecturer name</th>
            <th>price</th>
            <th>adder</th>
        </tr>

        <%
            for (Lesson lesson : myLessons) {%>

        <tr>
            <td><%=lesson.getId()%>
            </td>
            <td><%=lesson.getName()%>
            </td>
            <td><%=lesson.getDuration()%>
            </td>
            <td><%=lesson.getLecturerName()%>
            </td>
            <td><%=lesson.getPrice()%>
            </td>
            <td><%=lesson.getUser().getName()%>
            </td>
        </tr>
        <% }
        } else {
        %>
        <h2>there isn't added your Lessons yet</h2>
        <%}%>
    </table>
</div>
</body>
</html>
