package com.example.crbyerscustomersupport;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;

//Set up my servlet and file size limits
@WebServlet(name = "ticket", value = "/ticket")
@MultipartConfig(fileSizeThreshold = 5_242_880, maxFileSize = 20971520L, maxRequestSize = 41_94340L)
public class TicketServlet extends HttpServlet {
    //set up my variables and map
    private volatile int TICKET_ID = 1;
    private Map<Integer, Ticket> ticketDB = new LinkedHashMap<>();

    //
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }
        switch(action) {
            case "list":
                listTickets(request, response);
                break;
            case "view":
                viewTicket(request, response);
                break;
            case "showForm":
                showTicketForm(request, response);
                break;
            case "download":
                downloadAttachment(request, response);
                break;
            default:
                listTickets(request, response); // this the list and any other
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }
        switch(action) {
            case "create":
                createTicket(request, response);
                break;
            default:
                response.sendRedirect("ticket"); // this the list and any other
        }
    }



    //Method to show tickets filed listed or that there are no tickets
    private void listTickets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();

        //heading and link to create a Ticket
        out.println("<html><body><h2>Ticket List</h2>");
        out.println("<a href=\"ticket?action=showForm\">Create Ticket</a><br><br>");

        // list out the tickets
        if (ticketDB.isEmpty()) {
            out.println("There are no tickets created yet...");
        }
        else {
            for (Map.Entry<Integer, Ticket> entry : ticketDB.entrySet()){
              Ticket ticket = entry.getValue();
              out.println("Ticket #: " + entry.getKey() + ticket.getSubject() + "<br>");
            }
        }
        out.println("</body></html>");

    }

    //method to create the ticket
    private void createTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // create the ticket and set all values up
        Ticket ticket = new Ticket();
        ticket.setSubject(request.getParameter("Subject"));

        synchronized (this) {
            ticketDB.put(TICKET_ID++, ticket);
        }
        response.sendRedirect("ticket");
    }


    //method to download the attachments
    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ticketID = Integer.parseInt(request.getParameter("id"));

        Ticket ticket = getTicket(ticketID);

        if (ticket != null && ticket.getAllAttachments() != null) {
            int attachmentIndex = Integer.parseInt(request.getParameter("attachmentIndex"));
            Attachment attachment = ticket.getAttachment(attachmentIndex);

            if (attachment != null) {
                response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");
                response.setContentType("application/octet-stream");

                ServletOutputStream out = response.getOutputStream();
                out.write(attachment.getContents());
                out.flush();
            } else {
                response.sendRedirect("ticket");
            }
        } else {
            response.sendRedirect("ticket");
        }
    }

    //method to view the tickets created
    private void viewTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int ticketID = Integer.parseInt(request.getParameter("id"));

        Ticket ticket = getTicket(ticketID);

        if (ticket != null) {
            PrintWriter out = response.getWriter();
            out.println("<html><body><h2>Ticket #  " + ticketID + "</h2>");
            out.println("Description: " + ticket.getBody() + "<br>");
            out.println("<a href=\"ticket\">Return to ticket list</a>");
            out.println("</body></html>");
        }
        else {
            response.sendRedirect("ticket");
        }

    }
    //Form to create the ticket, attach a file, and submit it to the database

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();

        out.println("<html><body><h2>Create Ticket</h2>");
        out.println("<form method=\"POST\" action=\"ticket\">");
        out.println("<input type=\"hidden\" name=\"action\" value=\"create\">");
        out.println("Title:<br>");
        out.println("<input type=\"text\" name=\"Subject\"><br><br>");
        out.println("Body:<br>");
        out.println("<textarea name=\"body\" rows=\"25\" cols=\"100\"></textarea><br><br>");
        out.println("<b>Attachment</b><br>");
        out.println("<input type=\"file\" name=\"file1\"><br><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form></body></html>");

    }
    //method to add the ticket information to the database
    private Ticket getTicket(int id) {
        return ticketDB.get(id);
    }
}
