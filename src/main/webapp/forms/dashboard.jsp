<%--
  Created by IntelliJ IDEA.
  User: Chanidu Neerada
  Date: 7/24/2025
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Dashboard</title></head>
<body>
<h1>Welcome <%= session.getAttribute("username") %></h1>
<ul>
  <li><a href="addCustomer.jsp">Add Customer</a></li>
  <li><a href="viewCustomer.jsp">View Customers</a></li>
  <li><a href="addItem.jsp">Add Item</a></li>
  <li><a href="viewItems.jsp">View Items</a></li>
  <li><a href="createBill.jsp">Create Bill</a></li>
  <li><a href="bill?action=list">View All Bills</a></li>
  <li><a href="help.jsp">Help</a></li>
  <li><a href="login.jsp">Logout</a></li>
</ul>
</body>
</html>

