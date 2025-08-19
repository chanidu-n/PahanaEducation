<%--
  Created by IntelliJ IDEA.
  User: Chanidu Neerada
  Date: 7/24/2025
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <title>Add Customer</title></head>
<body>
<h2>Add New Customer</h2>
<form action="addCustomer" method="post">
    Account Number: <input type="number" name="accountNumber" required><br>
    Name: <input type="text" name="name" required><br>
    Address: <input type="text" name="address" required><br>
    Telephone: <input type="text" name="telephone" required><br>
    Units Consumed: <input type="number" name="unitsConsumed" required><br>
    <button type="submit">Add</button>
</form>
</body>
</html>
