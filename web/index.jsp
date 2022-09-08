<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Library</title>
</head>
<%
    User user = (User) session.getAttribute("user");
%>
<body>

<div style="width: 1000px; margin: 0 auto">
    <div>
        <img src="/image/photo-1589998059171-988d887df646.jpg" width="1000" height="400"/>
    </div>
    <div>My Library
        <br>

        <%
            if (user != null) {
        %>
        <a href="/books">Show All books</a>
        <a href="/authors">Show all authors</a>
        <a href="/books/add">Add book</a>
        <a href="/authors/add">Add new author</a>
        <a style="color: blue" href="/logout">Logout</a>
        <%}%>
    </div>
</div>

<div style="width: 1000px; margin: 0 auto">
    <%
        if (user == null) {
    %>
    <a href="/users/add">Register</a>
    <a href="/login">Login</a>
    <%}%>
</div>
</body>
</html>
