<%@ page import="com.example.studentlessonservlets.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%

    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="container">
    <div class="header">
        <div class="nav-bar">
            <ul>
                <li><a href="../addStudent">add student</a></li>
            </ul>
        </div>
    </div>

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
                <th>delete</th>
                <th>update</th>
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
                <td><a href="/deleteStudent?id=<%=student.getId()%>">delete</a>
                </td>
                <td><a href="/updateStudent?studentId=<%=student.getId()%>">update</a>
                </td>
            </tr>
            <% }
            } else {
            %>
            <h2>there isn't added students yet</h2>
            <%}%>
        </table>
    </div>
</div>

</body>
</html>
