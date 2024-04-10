<%
    String ticketId = (String)request.getAttribute("ticketId");
    Ticket ticket = (Ticket)request.getAttribute("ticket");
%>
<html>
<head>
    <title>Ticket # <%=ticketId%></title>
</head>
<body>
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
