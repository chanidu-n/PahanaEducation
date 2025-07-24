<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Form</title>
</head>
<body>
<h1><c:choose><c:when test="${user != null}">Edit</c:when><c:otherwise>Add</c:otherwise></c:choose> User</h1>
<form action="${pageContext.request.contextPath}/<c:choose><c:when test="${user != null}">update</c:when><c:otherwise>insert</c:otherwise></c:choose>" method="post">
    <c:if test="${user != null}">
        <input type="hidden" name="id" value="${user.id}" />
    </c:if>
    Account Number: <input type="text" name="accountnumber" value="<c:out value='${user.accountnumber}'/>" /><br/>
    User name: <input type="text" name="username" value="<c:out value='${user.username}'/>" /><br/>
    Pass code: <input type="text" name="passcode" value="<c:out value='${user.passcode}'/>" /><br/>
    Full Name: <input type="text" name="fullname" value="<c:out value='${user.fullname}'/>" /><br/>
    Address: <input type="text" name="address" value="<c:out value='${user.address}'/>" /><br/>
    Telephone no: <input type="text" name="telephonenumber" value="<c:out value='${user.telephonenumber}'/>" /><br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>