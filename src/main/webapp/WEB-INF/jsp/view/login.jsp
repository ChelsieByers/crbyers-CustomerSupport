<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Ticket Login</title>
</head>
<body>
    <h2>Login</h2>
    You must log in to access the ticket system.<br><br>
    <c:if test="${loginFailed == true}">
        <b><c:out value="The username or password you entered is not correct, Please try again."></c:out></b><br>
    </c:if>
    <form method="POST" action="<c:url value='/login'/>">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Log In">
    </form>
</body>
</html>
