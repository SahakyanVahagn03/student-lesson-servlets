<%@ page import="com.example.studentlessonservlets.model.Lesson" %>
<%@ page import="com.example.studentlessonservlets.model.Student" %>
<%@ page import="java.util.List" %><%--
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
    Student student = (Student) request.getAttribute("student");
    List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
%>
<body>

<div class="main_for_add_and_edit">
    <form method="post" action="/updateStudent">
        <input type="hidden" name="id"  value="<%=student.getId()%>"><br>
        <input type="text" name="name" placeholder="name" value="<%=student.getName()%>"><br>
        <input type="text" name="surname" placeholder="surname" value="<%=student.getSurname()%>"><br>
        <input type="text" name="email" placeholder="email" value="<%=student.getEmail()%>"><br>
        <input type="number" name="age" placeholder="age" value="<%=student.getAge()%>"><br>
        <select name="lessonId">
            <%for (Lesson lesson : lessons) {%>
            <option value="<%=lesson.getId()%>" ><%=lesson.getName()%> </option>
            <% }%>
        </select>
        <br>
        <input type="submit" value="update">
    </form>
</div>

</body>
</html>
