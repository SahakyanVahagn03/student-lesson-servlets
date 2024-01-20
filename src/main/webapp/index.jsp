<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%
    String msg = (String) request.getSession().getAttribute("msg");
%>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css"/>
</head>
<body>


<div class="main_for_add_and_edit">
    <div class="register_and_massage_items">
        <a href="/register">register</a>
        <%if (msg != null) {%>
        <p style="color:red"><%=msg%></p>
        <%
                session.removeAttribute("msg");
            }
        %>
    </div>

    <form method="post" action="/login">
        <input type="email" name="email" placeholder="email"><br>
        <input type="password" name="password" placeholder="password"><br>
        <input type="submit" placeholder="add">
    </form>

</div>
</body>
</html>