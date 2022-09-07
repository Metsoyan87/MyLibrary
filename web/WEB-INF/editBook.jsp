<%@ page import="model.Author" %>
<%@ page import="model.Book" %>
<%@ page import="java.util.List" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>

<%
    List<Author> authors = (List<Author>) request.getAttribute("authors");
    Book book = (Book) request.getAttribute("book");
%>

PLease update user's data
<form action="/books/edit" method="post">

    <input type="hidden" name="bookId" value="<%=book.getId()%>">
    <input type="text" name="title" value="<%=book.getTitle()%>"> <br>
    <input type="text" name="description" value="<%=book.getDescription()%>"> <br>
    <input type="text" name="price" value="<%=book.getPrice()%>"> <br>
    <input type="text" name="author" value="<%=book.getAuthor()%>"> <br>
    <select name="authorId">

        <% for (Author author : authors) {
            if (author.equals(book.getAuthor())) {
        %>
        <option selected
                value="<%=author.getId()%>"><%=author.getName()%> <%=author.getSurname()%><%=author.getEmail()%>
            (<%=author.getAge()%>)
        </option>
        <% } else { %>
        <option value="<%=author.getId()%>"><%=author.getName()%>
         <% }
        } %>
    </select>
    <input type="submit" value="Update">
</form>
</body>
</html>
