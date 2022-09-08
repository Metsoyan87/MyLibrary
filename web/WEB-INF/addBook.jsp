<%@ page import="java.util.List" %>
<%@ page import="model.Author" %>
<%@ page import="model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
</head>
<body>

<%
    List<Author> authors = (List<Author>) request.getAttribute("authors");
%>

PLease input book
<form action="/books/add" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="please input title"><br>
    <input type="text" name="description" placeholder="please input description"><br>
    <input type="text" name="price" placeholder="Please input price"><br>
    <h5>Profile Picture</h5>
    <input type="file" name="profilePic" multiple><br>
    <select name="authorId">
        <% for (Author author : authors) {%>
        <option value="<%=author.getId()%>">
            <%=author.getName()%>
            <%=author.getSurname()%>
            <%=author.getEmail()%>
            (<%=author.getAge()%>)
            <%=author.getProfilePic()%>
        </option>
        <%}%>
    </select>
    <input type="submit" value="Add">
</form>

</body>
</html>
