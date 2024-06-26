package com.example.crbyerscustomersupport.site;

import com.example.crbyerscustomersupport.entities.Attachment;
import jakarta.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("ticket")
public class TicketController {
    //private volatile int TICKET_ID = 1;
    //private Map<Integer, Ticket> ticketDB = new LinkedHashMap<>();

    TicketService ticketService;
    @RequestMapping(value={"list", ""})
    public String listTickets(Model model) {
        model.addAttribute("ticketDatabase", ticketService.getAllTickets());
        return "listTickets";
    }

    @GetMapping("create")
    public ModelAndView createTicket() {
        return new ModelAndView("ticketForm", "ticket", new TicketForm());
    }

    @PostMapping("create")
    public View createTicket(@ModelAttribute("ticket")TicketForm form) throws IOException {
        Ticket ticket = new Ticket();
        ticket.setSubject(form.getSubject());
        ticket.setBody(form.getBody());

        MultipartFile file = form.getAttachment();
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setContents(file.getBytes());
        if ((attachment.getName() != null && !attachment.getName().isEmpty()) ||
                (attachment.getContents() != null && attachment.getContents().length > 0)) {
            ticket.setAttachment(attachment);
        }

        /*// add and synchronize
        int id;
        synchronized(this) {
            id = this.TICKET_ID++;
            ticketDB.put(id, ticket);
        }*/

        ticketService.save(ticket);

        // show the view with the ticket id
        return new RedirectView("view/"+ticket.getId(), true, false);

    }

    @GetMapping("view/{ticketId}")
    public ModelAndView viewPost(Model model, @PathVariable("ticketId")int ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId); // get the ticket
        // if ticket doesn't exist?
        if (ticket == null) {
            return new ModelAndView(new RedirectView("ticket/list", true, false));
        }

        // found the ticket, so send it to the view
        model.addAttribute("ticketId", ticketId);
        model.addAttribute("ticket", ticket);

        return new ModelAndView("viewTicket");

    }

    @GetMapping("/{ticketId}/attachment/{attachment:.+}")
    public View downloadAttachment(@PathVariable("ticketId")int ticketId, @PathVariable("attachment")String name) {
        Ticket ticket = ticketService.getTicket(ticketId);
        // no ticket
        if (ticket == null) {
            return new RedirectView("/ticket/list", true, false);
        }

        // make sure there is an attachment
        Attachment attachment = ticket.getAttachment(); // Corrected line
        if (attachment == null) { // Corrected line
            return new RedirectView("/ticket/list", true, false);
        }

        // otherwise we have an attachment, lets download
        return new DownloadView(attachment.getName(), attachment.getContents());
    }


    public static class TicketForm {
        private String subject;
        private String body;
        private MultipartFile attachment;

        public String getSubject() {
            return subject;
        }
        public void setSubject(String subject) {
            this.subject = subject;
        }
        public String getBody() {
            return body;
        }
        public void setBody(String body) {
            this.body = body;
        }
        public MultipartFile getAttachment() {
            return attachment;
        }
        public void setAttachment(MultipartFile attachment) {
            this.attachment = attachment;
        }
    }

}