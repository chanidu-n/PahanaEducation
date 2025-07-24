<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
</head>
<body>
<div align="center">
    <h1>User Login</h1>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" required/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" required/></td>
            </tr>
        </table>
        <br/>
        <input type="submit" value="Login"/>
    </form>

    <% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>
</div>
</body>
</html>
