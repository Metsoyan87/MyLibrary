<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<%@ page import="model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Author</title>
</head>
<body>
PLease input author
<form action="/authors/add" method="post">
    <input type="text" name="name" placeholder="please input name"><br>
    <input type="text" name="surname" placeholder="please input surName"><br>
    <input type="email" name="email" placeholder="please input email"><br>
    <input type="text" name="age" placeholder="please input age"><br>
    <input type="submit" value="Register">


</form>
</body>
</html>
