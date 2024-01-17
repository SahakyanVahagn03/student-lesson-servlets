<%@ page import="java.util.List" %>
<%@ page import="com.example.studentlessonservlets.model.Lesson" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="container">
    <div class="header">
        <div class="nav-bar">
            <ul>
                <li><a href="/addLesson">addLesson</a></li>
            </ul>
        </div>
    </div>

    <div class="main">
        <%
            if (lessons != null && !lessons.isEmpty()) {%>
        <table border="1px">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>duration</th>
                <th>lecturer name</th>
                <th>price</th>
                <th>delete</th>
                <th>update</th>
            </tr>

            <%
                for (Lesson lesson : lessons) {%>

            <tr>
                <td><%=lesson.getId()%>
                </td>
                <td><%=lesson.getName()%>
                </td>
                <td><%=lesson.getDuration()%>
                </td>
                <td><%=lesson.getLecturerName()%>
                </td>
                <td><%=lesson.getPrice()
                %>
                <td><a href="/deleteLesson?lessonId=<%=lesson.getId()%>">delete</a>
                </td>
                <td><a href="/updateLesson?lessonId=<%=lesson.getId()%>">update</a>
                </td>
            </tr>
            <% }
            } else {
            %>
            <h2>there isn't added lessons yet</h2>
            <%}%>
        </table>
    </div>
</div>

</body>
</html>
