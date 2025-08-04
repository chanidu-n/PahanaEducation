<%--
  Created by IntelliJ IDEA.
  User: Chanidu Neerada
  Date: 8/4/2025
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Item" %>
<%
    Item item = (Item) request.getAttribute("item");
    if (item == null) {
        response.sendRedirect("viewItems.jsp");
        return;
    }
%>
<html>
<head><title>Edit Item</title></head>
<body>
<h2>Edit Item</h2>
<form action="addItem" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%= item.getId() %>">
    Name: <input type="text" name="name" value="<%= item.getName() %>" required><br>
    Price: <input type="number" step="0.01" name="price" value="<%= item.getPrice() %>" required><br>
    <button type="submit">Update Item</button>
</form>
<br>
<a href="viewItems.jsp">Back to Item List</a>
</body>
</html>
