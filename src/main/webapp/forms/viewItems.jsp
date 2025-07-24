<%--
  Created by IntelliJ IDEA.
  User: Chanidu Neerada
  Date: 7/24/2025
  Time: 11:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, dao.ItemDAO, model.Item" %>
<%
    ItemDAO dao = new ItemDAO();
    List<Item> items = dao.getAllItems();
%>
<html>
<head><title>Item List</title></head>
<body>
<h2>Items</h2>
<table border="1">
    <tr><th>ID</th><th>Name</th><th>Price</th></tr>
    <% for (Item item : items) { %>
    <tr>
        <td><%= item.getId() %></td>
        <td><%= item.getName() %></td>
        <td>Rs. <%= item.getPrice() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>

