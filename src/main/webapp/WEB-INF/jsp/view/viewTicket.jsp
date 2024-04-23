<%--NAME: <Chelsie Byers>--%>
<%--CLASS: Info 1541/Spring--%>
<%--ASSIGNMENT: <Crbyers-Assignment 4>--%>
<%--DATE: <3/10/2024/4/6/2024>--%>
<%--RESOURCES: <I used the resource videos and lectures from this class and Professional Java for Web Applications>--%>

<%--This program will allow a user to input a ticket to report any support issues with their accounts>--%>

<html>
<head>
    <title>Ticket #<c:out value="${ticketId}"/></title>
</head>
<body>
<a href="<c:url value='/logout'/>">Logout</a>
<h2>Ticket</h2>
<h3>Ticket #<c:out value="${ticketId}"/>: <c:out value="${ticket.subject}"/></h3>
<p><c:out value="${ticket.body}"/></p>
<c:if test="${ticket.hasAttachment()}">
    <a href="<c:url value='/ticket/${ticketId}/attachment/${ticket.attachment.name}' />">
        <c:out value="${ticket.attachment.name}"/></a>
</c:if>
<br><a href="<c:url value='/ticket/list'/>">Return to ticket list</a>

</body>
</html>