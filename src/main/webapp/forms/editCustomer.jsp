<%--
  Created by IntelliJ IDEA.
  User: Chanidu Neerada
  Date: 8/4/2025
  Time: 9:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Customer" %>
<%
    Customer customer = (Customer) request.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("viewCustomer.jsp");
        return;
    }
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <title>Edit Customer</title></head>
<body>
<h2>Edit Customer</h2>
<form action="addCustomer" method="post">
    <input type="hidden" name="action" value="update">
    Account Number: <input type="number" name="accountNumber" value="<%= customer.getAccountNumber() %>" readonly><br>
    Name: <input type="text" name="name" value="<%= customer.getName() %>" required><br>
    Address: <input type="text" name="address" value="<%= customer.getAddress() %>" required><br>
    Telephone: <input type="text" name="telephone" value="<%= customer.getTelephone() %>" required><br>
    Units Consumed: <input type="number" name="unitsConsumed" value="<%= customer.getUnitsConsumed() %>" required><br>
    <button type="submit">Update Customer</button>
</form>
<br>
<a href="viewCustomer.jsp">Back to Customer List</a>
</body>
</html>
