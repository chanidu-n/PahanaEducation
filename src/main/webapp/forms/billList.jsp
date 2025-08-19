<%--
  Created by IntelliJ IDEA.
  User: Chanidu Neerada
  Date: 8/4/2025
  Time: 10:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.Bill, java.text.SimpleDateFormat" %>
<%
    List<Bill> bills = (List<Bill>) request.getAttribute("bills");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>
<html>
<head>
    <title>Bill List</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .text-right { text-align: right; }
    </style>
</head>
<body>
    <h2>All Bills</h2>
    
    <table>
        <thead>
            <tr>
                <th>Bill ID</th>
                <th>Account No</th>
                <th>Customer Name</th>
                <th>Bill Date</th>
                <th class="text-right">Total Amount (Rs.)</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% if (bills != null && !bills.isEmpty()) { %>
                <% for (Bill bill : bills) { %>
                <tr>
                    <td><%= bill.getBillId() %></td>
                    <td><%= bill.getAccountNumber() %></td>
                    <td><%= bill.getCustomerName() %></td>
                    <td><%= dateFormat.format(bill.getBillDate()) %></td>
                    <td class="text-right"><%= String.format("%.2f", bill.getTotalAmount()) %></td>
                    <td>
                        <a href="bill?action=view&billId=<%= bill.getBillId() %>">View/Print</a>
                    </td>
                </tr>
                <% } %>
            <% } else { %>
                <tr>
                    <td colspan="6">No bills found.</td>
                </tr>
            <% } %>
        </tbody>
    </table>
    
    <br>
    <a href="createBill.jsp">Create New Bill</a> | 
    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>
