<%--
  Created by IntelliJ IDEA.
  User: Chanidu Neerada
  Date: 8/4/2025
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.Item, model.Customer" %>
<%
    Customer customer = (Customer) request.getAttribute("customer");
    List<Item> items = (List<Item>) request.getAttribute("items");
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <title>Create Bill</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .customer-info { background-color: #f9f9f9; padding: 10px; margin: 10px 0; }
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>
    <h2>Create New Bill</h2>
    
    <!-- Customer Selection Form -->
    <div class="customer-info">
        <h3>Customer Information</h3>
        <form method="post" action="bill">
            <input type="hidden" name="action" value="getCustomer">
            Account Number: <input type="number" name="accountNumber" value="<%= customer != null ? customer.getAccountNumber() : "" %>" required>
            <button type="submit">Get Customer</button>
        </form>
        
        <% if (request.getAttribute("customerError") != null) { %>
            <p class="error"><%= request.getAttribute("customerError") %></p>
        <% } %>
        
        <% if (customer != null) { %>
            <h4>Customer Details:</h4>
            <p><strong>Name:</strong> <%= customer.getName() %></p>
            <p><strong>Address:</strong> <%= customer.getAddress() %></p>
            <p><strong>Telephone:</strong> <%= customer.getTelephone() %></p>
        <% } %>
    </div>
    
    <!-- Bill Creation Form -->
    <% if (customer != null && items != null) { %>
    <form method="post" action="bill" onsubmit="return validateBill();">
        <input type="hidden" name="action" value="create">
        <input type="hidden" name="accountNumber" value="<%= customer.getAccountNumber() %>">
        
        <h3>Select Items</h3>
        <table>
            <thead>
                <tr>
                    <th>Item Name</th>
                    <th>Price (Rs.)</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                </tr>
            </thead>
            <tbody>
                <% for (Item item : items) { %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td class="price"><%= item.getPrice() %></td>
                    <td>
                        <input type="hidden" name="itemId" value="<%= item.getId() %>">
                        <input type="number" name="quantity" min="0" value="0" class="quantity" onchange="calculateSubtotal(this)">
                    </td>
                    <td class="subtotal">0.00</td>
                </tr>
                <% } %>
            </tbody>
            <tfoot>
                <tr>
                    <th colspan="3">Total Amount:</th>
                    <th id="totalAmount">0.00</th>
                </tr>
            </tfoot>
        </table>
        
        <br>
        <button type="submit">Create Bill</button>
        <a href="dashboard.jsp">Cancel</a>
    </form>
    <% } %>
    
    <% if (request.getAttribute("error") != null) { %>
        <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>

    <script>
        function calculateSubtotal(quantityInput) {
            var row = quantityInput.closest('tr');
            var price = parseFloat(row.querySelector('.price').textContent);
            var quantity = parseInt(quantityInput.value) || 0;
            var subtotal = price * quantity;
            
            row.querySelector('.subtotal').textContent = subtotal.toFixed(2);
            calculateTotal();
        }
        
        function calculateTotal() {
            var subtotals = document.querySelectorAll('.subtotal');
            var total = 0;
            
            subtotals.forEach(function(subtotalElement) {
                total += parseFloat(subtotalElement.textContent) || 0;
            });
            
            document.getElementById('totalAmount').textContent = total.toFixed(2);
        }
        
        function validateBill() {
            var quantities = document.querySelectorAll('.quantity');
            var hasItems = false;
            
            quantities.forEach(function(qty) {
                if (parseInt(qty.value) > 0) {
                    hasItems = true;
                }
            });
            
            if (!hasItems) {
                alert('Please select at least one item with quantity greater than 0');
                return false;
            }
            
            return true;
        }
    </script>
</body>
</html>
