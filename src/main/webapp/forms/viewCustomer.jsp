<%--
  Created by IntelliJ IDEA.
  User: Chanidu Neerada
  Date: 7/24/2025
  Time: 11:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, dao.CustomerDAO, model.Customer" %>
<%
    CustomerDAO dao = new CustomerDAO();
    List<Customer> customers = dao.getAllCustomers();
%>
<html>
<head><title>Customers</title></head>
<body>
<h2>Customer List</h2>
<table border="1">
    <tr><th>Account No</th><th>Name</th><th>Address</th><th>Telephone</th><th>Units</th><th>Bill</th></tr>
    <% for (Customer c : customers) { %>
    <tr>
        <td><%= c.getAccountNumber() %></td>
        <td><%= c.getName() %></td>
        <td><%= c.getAddress() %></td>
        <td><%= c.getTelephone() %></td>
        <td><%= c.getUnitsConsumed() %></td>
        <td><a href="bill.jsp?accountNumber=<%= c.getAccountNumber() %>">View Bill</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>

