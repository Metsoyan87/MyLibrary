<%@ page import="java.util.List" %>
<%@ page import="model.Author" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Authors</title>
</head>
<body>

<% List<Author> authors = (List<Author>) request.getAttribute("authors");

%>

<table border="1">

    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>age</th>
        <th>action</th>
        <th>action</th>
    </tr>

    <% for (Author author : authors) {
    %>

    <tr>
        <td><%=author.getId()%>
        </td>
        <td><%=author.getName()%>
        </td>
        <td><%=author.getSurname()%>
        </td>
        <td><%=author.getEmail()%>
        </td>
        <td><%=author.getAge()%>
        </td>
        <td><a href="/author/remove?authorId=<%=author.getId()%>">Remove</a> |
        <td><a href="/author/edit?authorId=<%=author.getId()%>">Edit</a>
        </td>
    </tr>
    <%}%>

</table>
</body>
</html>
