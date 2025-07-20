<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Form</title>
</head>
<body>
<div align="center">
    <h1>User Login Form</h1>
    <!-- <a href="list">User List</a><br/><br/> -->
    <form action="<%=request.getContextPath()%>/login" method="post">
        <table style="with: 100%">
            <tr>
                <td>UserName</td>
                <td><input type="text" name="username" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>

