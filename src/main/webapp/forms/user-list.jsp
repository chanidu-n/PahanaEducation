
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
