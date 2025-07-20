<%--
  Created by IntelliJ IDEA.
  User: Chanidu Neerada
  Date: 7/20/2025
  Time: 6:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User List</title>
</head>
<body>
<h1>User List</h1>
<a href="<%=request.getContextPath()%>/new">Add New</a><br/><br/>
<table border="1">
    <tr>
        <th>ID</th><th>Account Number</th><th>Full Name</th><th>Address</th><th>Telephone No</th><th>Actions</th>
    </tr>
    <c:forEach var="user" items="${listUser}">
        <tr>
            <td>${user.id}</td>
            <td>${user.accountnumber}</td>
            <td>${user.fullname}</td>
            <td>${user.address}</td>
            <td>${user.telephonenumber}</td>
            <td>
                <a href="edit?id=${user.id}">Edit</a>
                <a href="delete?id=${user.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br><br><a href="./index.jsp">Logout</a>
</body>
</html>
