<%--NAME: <Chelsie Byers>--%>
<%--CLASS: Info 1541/Spring--%>
<%--ASSIGNMENT: <Crbyers-Assignment 4>--%>
<%--DATE: <3/10/2024/4/6/2024>--%>
<%--RESOURCES: <I used the resource videos and lectures from this class and Professional Java for Web Applications>--%>

<%--This program will allow a user to input a ticket to report any support issues with their accounts>--%>


<%
    String ticketId = (String)request.getAttribute("ticketId");
    Ticket ticket = (Ticket)request.getAttribute("ticket");
%>
<%--create my form--%>
<html>
<head>
    <title>Ticket # <%=ticketId%></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/login?logout=true">Logout</a>
    <h2>Ticket</h2>
    <h3>Ticket # <%=ticketId%>: <%=ticket.getSubject()%></h3>
    <p><%=ticket.getBody()%></p>
    <%if (ticket.hasAttachment()) {%>
    <a href="ticket?action=download&ticketId=<%=ticketId%>&attachment=<%=ticket.getAttachment().getName()%>">
        <%=ticket.getAttachment().getName()%></>
    <%}%>
    <br><a href="ticket">Return to ticket list</a>

</body>
</html>
