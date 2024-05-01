<%--NAME: <Chelsie Byers>--%>
<%--CLASS: Info 1541/Spring--%>
<%--ASSIGNMENT: <Crbyers-Assignment 4>--%>
<%--DATE: <3/10/2024/4/6/2024>--%>
<%--RESOURCES: <I used the resource videos and lectures from this class and Professional Java for Web Applications>--%>

<%--This program will allow a user to input a ticket to report any support issues with their accounts>--%>

<html>
<head>
    <title>Tickets</title>
</head>
<body>
    <a href="<c:url value='/logout'/>">Logout</a>
    <h2>Tickets</h2>
    <a href="<c:url value='/ticket'/>">Create Ticket</a><br><br>
    <c:choose>
        <c:when test="${ticketDatabase.size() == 0}">
            <p>There are no tickets created yet...</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="ticket" items="${ticketDatabase}">
                Ticket#:&nbsp;<c:out value="${ticket.key}"/>
                <a href="<c:url value='/ticket/view/${ticket.key}'/>">
                    <c:out value="${ticket.value.subject}"/></a><br>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</body>
</html>