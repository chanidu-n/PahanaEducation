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
    <tr><th>Account No</th><th>Name</th><th>Address</th><th>Telephone</th><th>Units</th><th>Actions</th></tr>
    <% for (Customer c : customers) { %>
    <tr>
        <td><%= c.getAccountNumber() %></td>
        <td><%= c.getName() %></td>
        <td><%= c.getAddress() %></td>
        <td><%= c.getTelephone() %></td>
        <td><%= c.getUnitsConsumed() %></td>
        <td>
            <a href="editCustomer?action=edit&accountNumber=<%= c.getAccountNumber() %>">Edit</a>
            |
            <form style="display:inline;" method="post" action="addCustomer" onsubmit="return confirm('Are you sure you want to delete this customer?');">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="accountNumber" value="<%= c.getAccountNumber() %>">
                <input type="submit" value="Delete" style="background:red;color:white;">
            </form>
        </td>
    </tr>
    <% } %>
</table>
<br>
<a href="addCustomer.jsp">Add New Customer</a> | 
<a href="dashboard.jsp">Back to Dashboard</a>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>
</body>
</html>

