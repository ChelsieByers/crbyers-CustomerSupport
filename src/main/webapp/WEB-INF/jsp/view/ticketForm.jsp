<%--
  Created by IntelliJ IDEA.
  User: chels
  Date: 4/2/2024
  Time: 7:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a New Ticket Form</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/login?logout=true">Logout</a>

    <h2>Create a Ticket</h2>
    <form method="POST" action="ticket" enctype="multipart/form-data">
        <input type="hidden" name="action" value="create">
        Subject:<br>
        <input type="text" name="subject"><br><br>
        Body:<br>
        <textarea name="body" rows="25" cols="100"></textarea><br><br>
        <b>Attachment</b><br>
        <input type="file" name="file1"><br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
