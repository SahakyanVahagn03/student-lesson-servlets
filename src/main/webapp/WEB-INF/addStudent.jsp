<%@ page import="com.example.studentlessonservlets.model.Lesson" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="main_for_add">

    <form method="post" action="/addStudent">
        <input type="text" name="name" placeholder="name"><br>
        <input type="text" name="surname" placeholder="surname"><br>
        <input type="text" name="email" placeholder="email"><br>
        <input type="number" name="age" placeholder="age"><br>
        <br>
        <select name="lessonId">
            <%for (Lesson lesson : lessons) {%>
               <option value="<%=lesson.getId()%>"><%=lesson.getName()%></option>
            <% }%>
        </select>
        <br>
        <input type="submit" placeholder="add">
    </form>


</div>

</body>
</html>
