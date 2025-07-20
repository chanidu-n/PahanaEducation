<%--
  Created by IntelliJ IDEA.
  User: Chanidu Neerada
  Date: 7/20/2025
  Time: 6:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Form</title>
</head>
<body>
<h1>${user != null ? "Edit" : "Add"} User</h1>
<form action="${user != null ? 'update' : 'insert'}" method="post">
    <input type="hidden" name="id" value="${user.id}" />
    Account Number: <input type="text" name="accountnumber" value="${user.accountnumber}" /><br/>
    User name: <input type="text" name="username" value="${user.username}" /><br/>
    Pass code: <input type="text" name="passcode" value="${user.passcode}" /><br/>
    Full Name: <input type="text" name="fullname" value="${user.fullname}" /><br/>
    Address: <input type="text" name="address" value="${user.address}" /><br/>
    Telephone no: <input type="text" name="telephonenumber" value="${user.telephonenumber}" /><br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>

