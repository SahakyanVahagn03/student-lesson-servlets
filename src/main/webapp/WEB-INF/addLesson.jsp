
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="main_for_add">
    <form method="post" action="/addLesson">
        <input type="text" name="name" placeholder="name"><br>
        <input type="text" name="duration" placeholder="duration"><br>
        <input type="text" name="lecturer_name" placeholder="lecturer name"><br>
        <input type="number" name="price" placeholder="price"><br>
        <input type="submit" value="add">
    </form>
</div>
</body>
</html>