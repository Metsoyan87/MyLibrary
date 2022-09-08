<%--
  Created by IntelliJ IDEA.
  User: Mnac
  Date: 08.09.2022
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

<%
    String msg = (String)request.getAttribute("msg");
%>

<% if (msg != null) {%>
<p style="color: maroon"><%=msg%></p>

<%}%>

<form action="/login" method="post">
    <input type="email" name="email" placeholder="PLease input email"><br>
    <input type="password" name="password" placeholder="PLease input password"><br>
<input type="submit" value="Login">
</form>
</body>
</html>
