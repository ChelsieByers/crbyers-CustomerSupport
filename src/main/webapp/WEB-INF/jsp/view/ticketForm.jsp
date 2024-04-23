<html>
<head>
    <title>Create a ticket</title>
</head>
<body>
<a href="<c:url value='/logout'/>">Logout</a>
<h2>Create a Ticket</h2>

<form:form method="POST" action="create" modelAttribute="ticket" enctype="multipart/form-data">
    <form:label path="subject">Subject:</form:label><br>
    <form:input path="subject"/><br><br>
    <form:label path="body">Body:</form:label><br>
    <form:textarea path="body" rows="25" cols="100"/><br><br>
    <b>Attachment</b><br>
    <form:input path="attachment" type="file"/><br><br>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>