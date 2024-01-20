
<%@ page import="java.util.List" %>
<%@ page import="com.example.studentlessonservlets.model.Student" %><%--
  Created by IntelliJ IDEA.
  User: 37494
  Date: 20.01.2024
  Time: 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<%
    List<Student> students = (List<Student>) request.getAttribute("myStudents");

%>
<body>
<div class="main">
    <%
        if (students != null && !students.isEmpty()) {%>
    <table border="1px">
        <tr>
            <th>image</th>
            <th>Id</th>
            <th>Name</th>
            <th>surname</th>
            <th>email</th>
            <th>age</th>
            <th>lesson</th>
            <th>adder</th>

        </tr>

        <%
            for (Student student : students) {%>

        <tr>
            <td>
                <%if (student.getPicName() != null) {%>
                <img src="/downloadImage?imageName=<%=student.getPicName()%>" width="100px">
                <%}else{%>
                no picture
                <%}%>
            </td>
            <td><%=student.getId()%>
            </td>
            <td><%=student.getName()%>
            </td>
            <td><%=student.getSurname()%>
            </td>
            <td><%=student.getEmail()%>
            </td>
            <td><%=student.getAge()%>
            </td>
            <td><%=student.getLesson().getName()%>
            </td>
            <td><%=student.getUser().getName()%>
            </td>

        </tr>
        <% }
            } else {
        %>
        <h2>there isn't added your students yet</h2>
        <%}%>
    </table>
</div>
</body>
</html>
