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
    <tr><th>ID</th><th>Name</th><th>Price</th><th>Actions</th></tr>
    <% for (Item item : items) { %>
    <tr>
        <td><%= item.getId() %></td>
        <td><%= item.getName() %></td>
        <td>Rs. <%= item.getPrice() %></td>
        <td>
            <a href="editItem?action=edit&id=<%= item.getId() %>">Edit</a>
            |
            <form style="display:inline;" method="post" action="addItem" onsubmit="return confirm('Are you sure you want to delete this item?');">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= item.getId() %>">
                <input type="submit" value="Delete" style="background:red;color:white;">
            </form>
        </td>
    </tr>
    <% } %>
</table>
<br>
<a href="addItem.jsp">Add New Item</a> | 
<a href="dashboard.jsp">Back to Dashboard</a>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>
</body>
</html>

