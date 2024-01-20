<%@ page import="com.example.studentlessonservlets.model.Lesson" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
    String msg = (String) request.getSession().getAttribute("msg");
%>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="main_for_add_and_edit">
    <%if (msg != null){%>
    <span style="color:red"><%=msg%></span>
    <%request.getSession().removeAttribute("msg");}%>
    <form method="post" action="/addStudent" enctype="multipart/form-data">
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
        <input type="file" name="picture"><br>
        <input type="submit" placeholder="add">
    </form>


</div>

</body>
</html>
