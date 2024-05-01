<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Ticket Login</title>
</head>
<body>
<h2>Login</h2>
You must log in to access the ticket website.<br><br>
<c:if test="${loginFailed == true}">
    <c:out value="The username or password you entered is not correct. Please try again." /><br> <!-- Provide the message in the value attribute -->
</c:if>
<form:form method="POST" action="/login" modelAttribute="loginForm">
    <form:label path="username">Username:&nbsp;</form:label>
    <form:input path="username"/><br><br>
    <form:label path="password">Password:&nbsp;</form:label>
    <form:password path="password"/><br><br>
    <input type="submit" value="Log In">
</form:form>
</body>
</html>


