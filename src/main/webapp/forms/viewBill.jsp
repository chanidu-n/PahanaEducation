<%--
  Created by IntelliJ IDEA.
  User: Chanidu Neerada
  Date: 8/4/2025
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Bill, model.BillItem, java.text.SimpleDateFormat" %>
<%
    Bill bill = (Bill) request.getAttribute("bill");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>
<html>
<head>
    
    <title>Bill Details</title>
    <style>
        .bill-container { max-width: 800px; margin: 0 auto; padding: 20px; }
        .bill-header { text-align: center; border-bottom: 2px solid #000; padding-bottom: 10px; }
        .customer-info, .bill-info { margin: 20px 0; }
        table { width: 100%; border-collapse: collapse; margin: 20px 0; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .text-right { text-align: right; }
        .total-row { font-weight: bold; background-color: #f9f9f9; }
        .print-btn { margin: 20px 0; }
        @media print {
            .print-btn, .back-link { display: none; }
        }
    </style>
</head>
<body>
    <div class="bill-container">
        <div class="bill-header">
            <h1>Pahana Education Bookshop</h1>
            <h2>BILL RECEIPT</h2>
        </div>
        
        <% if (bill != null) { %>
        <div class="bill-info">
            <p><strong>Bill No:</strong> <%= bill.getBillId() %></p>
            <p><strong>Date:</strong> <%= dateFormat.format(bill.getBillDate()) %></p>
        </div>
        
        <div class="customer-info">
            <h3>Customer Details</h3>
            <p><strong>Account No:</strong> <%= bill.getAccountNumber() %></p>
            <p><strong>Name:</strong> <%= bill.getCustomerName() %></p>
            <p><strong>Address:</strong> <%= bill.getCustomerAddress() %></p>
            <p><strong>Telephone:</strong> <%= bill.getCustomerTelephone() %></p>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>Item Name</th>
                    <th>Price (Rs.)</th>
                    <th>Quantity</th>
                    <th class="text-right">Subtotal (Rs.)</th>
                </tr>
            </thead>
            <tbody>
                <% for (BillItem item : bill.getBillItems()) { %>
                <tr>
                    <td><%= item.getItemName() %></td>
                    <td class="text-right"><%= String.format("%.2f", item.getItemPrice()) %></td>
                    <td class="text-right"><%= item.getQuantity() %></td>
                    <td class="text-right"><%= String.format("%.2f", item.getSubtotal()) %></td>
                </tr>
                <% } %>
            </tbody>
            <tfoot>
                <tr class="total-row">
                    <td colspan="3"><strong>Total Amount:</strong></td>
                    <td class="text-right"><strong>Rs. <%= String.format("%.2f", bill.getTotalAmount()) %></strong></td>
                </tr>
            </tfoot>
        </table>
        
        <div class="print-btn">
            <button onclick="window.print()">Print Bill</button>
            <a href="bill?action=list" class="back-link">Back to Bill List</a>
            <a href="dashboard.jsp" class="back-link">Dashboard</a>
        </div>
        
        <% } else { %>
        <p>Bill not found.</p>
        <a href="dashboard.jsp">Back to Dashboard</a>
        <% } %>
    </div>
</body>
</html>
