<%--NAME: <Chelsie Byers>--%>
<%--CLASS: Info 1541/Spring--%>
<%--ASSIGNMENT: <Crbyers-Assignment 4>--%>
<%--DATE: <3/10/2024/4/6/2024>--%>
<%--RESOURCES: <I used the resource videos and lectures from this class and Professional Java for Web Applications>--%>

<%--This program will allow a user to input a ticket to report any support issues with their accounts>--%>


<%@ page import="java.util.Map" %>
<%
    @SuppressWarnings("unchecked")
    Map<Integer, Ticket> db = (Map<Integer, Ticket>)request.getAttribute("ticketDatabase");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Ticket List</h2>
    <a href="ticket?action=createTicket">Create Ticket</a><br><br>
    <%
        if(db.size() == 0) {%>
            <%="There are no tickets yet..."%>
        <%}
        else {
            for (int id : db.keySet()) {
                Ticket ticket = db.get(id);%>
                <%="Ticket # " + id%>
    <a href="ticket?action=view&ticketId=<%=id%>"><%=ticket.getSubject()%></a><br>
        <%}
    }%>


</body>
</html>
