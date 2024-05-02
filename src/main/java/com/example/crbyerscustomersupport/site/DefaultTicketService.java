package com.example.crbyerscustomersupport.site;

import com.example.crbyerscustomersupport.entities.Attachment;
import com.example.crbyerscustomersupport.entities.TicketEntity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

public class DefaultTicketService implements TicketService {

    @Inject TicketRepository ticketRepo;
    @Inject AttachmentRepository attachRepo;


    @Override
    @Transactional
    public List<Ticket> getAllTickets() {
        List<Ticket> list = new ArrayList<>();
        ticketRepo.getAll().forEach(e -> list.add(this.convert(e)));
        return list;
    }

    @Override
    public Ticket getTicket(long id) {
        TicketEntity entity = ticketRepo.get(id);
        return entity ==null ? null : this.convert(entity);
    }
    private Ticket convert(TicketEntity entity) {
        Ticket ticket = new Ticket();
        ticket.setId(entity.getId());
        ticket.setSubject(entity.getSubject());
        ticket.setBody(entity.getBody());
        //look up the attachment
        ticket.setAttachment(attachRepo.getByTicketId(entity.getId()));
        //check if attachment is null

        return ticket;

    }

    @Override
    @Transactional
    public void save(Ticket ticket) {
        //convert to ticket entity
        TicketEntity entity = new TicketEntity();
        entity.setId(ticket.getId());
        entity.setSubject(ticket.getSubject());
        entity.setBody(ticket.getBody());
        if (ticket.getId()< 1) {
            //no id, not updating
            ticketRepo.add(entity);
            ticket.setId(entity.getId());
            //if needing to add attachment
            if (ticket.hasAttachment()) {
                ticket.getAttachment().setTicketId(entity.getId());
                attachRepo.add(ticket.getAttachment());
            }
        }
        else {
            ticketRepo.update(entity);
        }
    }

    @Override
    @Transactional
    public void deleteTicket(long id) {
        ticketRepo.deleteById(id);

    }
}
