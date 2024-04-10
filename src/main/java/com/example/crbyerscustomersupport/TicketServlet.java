// NAME: <Chelsie Byers>
// CLASS: Info 1541/Spring
// ASSIGNMENT: <Crbyers-Assignment 4>
// DATE: <3/27/2024/4/3/2024>
// RESOURCES: <I used the resource videos and lectures from this class and Professional Java for Web Applications>

//This program will allow a user to input a ticket to report any support issues with their accounts>


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
    private volatile int TICKET_ID = 1;
    private Map<Integer, Ticket> ticketDB = new LinkedHashMap<>();

    // set up my doGet post to get the form for responses
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "createTicket" -> showPostForm(request, response);
            case "view" -> viewPost(request, response);
            case "download" -> downloadAttachment(request, response);
            default -> listPosts(request, response); // this the list and any other
        }

    }
    //set up my doPost method to create the ticket from the form submitted by the user
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "create" -> createTicket(request, response);
            default -> response.sendRedirect("ticket"); // this the list and any other
        }
    }


    //method to list out the different tickets submitted by the user
    private void listPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("ticketDatabase", ticketDB);
        request.getRequestDispatcher("WEB-INF/jsp/view/listTickets.jsp").forward(request, response);

        /*PrintWriter out = response.getWriter();

        //heading and link to create a tickets
        out.println("<html><body><h2>Tickets</h2>");
        out.println("<a href=\"ticket?action=createTicket\">Create Ticket</a><br><br>");

        // list out the tickets
        if (ticketDB.size() == 0) {
            out.println("There are no tickets yet...");
        } else {
            for (int id : ticketDB.keySet()) {
                Ticket ticket = ticketDB.get(id);
                out.println("Ticket # " + id);
                out.println(": <a href=\"ticket?action=view&ticketId=" + id + "\">");
                out.println(ticket.getSubject() + "</a><br>");
            }
        }
        out.println("</body></html>");*/

    }

    //method to create the tickets
    private void createTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // create the ticket and set all values up
        Ticket ticket = new Ticket();

        ticket.setSubject(request.getParameter("subject"));
        ticket.setBody(request.getParameter("body"));

        Part file = request.getPart("file1");
        if (file != null && file.getSize() > -1) {
            Attachment attachment = processAttachment(file);
            if (attachment != null) {
                ticket.setAttachment(attachment);
            }
        }

        // add and synchronize
        int id;
        synchronized (this) {
            id = this.TICKET_ID++;
            ticketDB.put(id, ticket);
        }


        response.sendRedirect("ticket?action=view&ticketId=" + id);
    }

    private Attachment attachment(Part file) {
        return null;
    }

    //method to add int he attachment to the ticket
    private Attachment processAttachment(Part file) throws IOException {
        InputStream in = file.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // processing the binary data to bytes
        int read;
        final byte[] bytes = new byte[1024];
        while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        Attachment attachment = new Attachment();
        attachment.setName(file.getSubmittedFileName());
        attachment.setContents(out.toByteArray());

        return attachment;
    }

    //method to allow the attachment to be downloaded
    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("ticketId");

        Ticket ticket = getTicket(idString, response);

        if(ticket == null) {
            return;
        }

        String name = request.getParameter("attachment");
        if (name == null) {
            response.sendRedirect("ticket?action=view&ticketId=" + idString);
        }

        Attachment attachment = ticket.getAttachment();
        if (attachment == null) {
            response.sendRedirect("ticket?action=view&ticketId=" + idString);
            return;
        }

        response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
        response.setContentType("application/octet-stream");

        ServletOutputStream out = response.getOutputStream();
        out.write(attachment.getContents());
    }

    //method to view the ticket to be posted
    private void viewPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("ticketId");

        Ticket ticket = getTicket(idString, response);
        request.setAttribute("ticket", ticket);
        request.setAttribute("ticketId", idString);
        request.getRequestDispatcher("WEB-INF/jsp/view/viewTicket.jsp").forward(request, response);

        /*PrintWriter out = response.getWriter();
        out.println("<html><body><h2>Tickets</h2>");
        out.println("<h3>" + ticket.getSubject() + "</h3>");
        out.println("<p>" + ticket.getBody() + "</p>");
        if (ticket.hasAttachment()) {
          out.println("<a href=\"ticket?action=download&ticketId=" +
                  idString + "&attachment=" + ticket.getAttachment().getName() + "\">" +
                  ticket.getAttachment().getName() + "</a><br><br>");

        }
        out.println("<a href=\"ticket\">Return to ticket list</a>");
        out.println("</body></html>");*/

    }

    //method to show the form to submit from the user
    private void showPostForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/view/ticketForm.jsp").forward(request, response);

        /*out.println("<html><body><h2>Create a Ticket</h2>");
        out.println("<form method=\"POST\" action=\"ticket\" enctype=\"multipart/form-data\">");
        out.println("<input type=\"hidden\" name=\"action\" value=\"create\">");
        out.println("Subject:<br>");
        out.println("<input type=\"text\" name=\"subject\"><br><br>");
        out.println("Body:<br>");
        out.println("<textarea name=\"body\" rows=\"25\" cols=\"100\"></textarea><br><br>");
        out.println("<b>Attachment</b><br>");
        out.println("<input type=\"file\" name=\"file1\"><br><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form></body></html>");*/

    }

    //method to get information for the ticket
    private Ticket getTicket(String idString, HttpServletResponse response) throws ServletException, IOException {
        // empty string id
        if (idString == null || idString.length() == 0) {
            response.sendRedirect("ticket");
            return null;
        }

        // find in the 'database' otherwise return null
        try {
            int id = Integer.parseInt(idString);
            Ticket ticket = ticketDB.get(id);
            if (ticket == null) {
                response.sendRedirect("ticket");
                return null;
            }
            return ticket;
        } catch (Exception e) {
            response.sendRedirect("ticket");
            return null;
        }
    }
}