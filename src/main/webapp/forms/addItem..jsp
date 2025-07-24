<%--
  Created by IntelliJ IDEA.
  User: Chanidu Neerada
  Date: 7/24/2025
  Time: 11:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Add Item</title></head>
<body>
<h2>Add New Item</h2>
<form action="addItem" method="post">
  Name: <input type="text" name="name" required><br>
  Price: <input type="number" step="0.01" name="price" required><br>
  <button type="submit">Add Item</button>
</form>
</body>
</html>
