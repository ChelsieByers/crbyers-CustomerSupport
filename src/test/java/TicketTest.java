// NAME: <Chelsie Byers>
// CLASS: Info 1541/Spring
// ASSIGNMENT: <Crbyers-Assignment 4>
// DATE: <3/27/2024/4/3/2024>
// RESOURCES: <I used the resource videos and lectures from this class and

//This program will allow a user to test their ticketing system for any failures>

import com.example.crbyerscustomersupport.Attachment;
import com.example.crbyerscustomersupport.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    //Test to check if the information is pulling from the ticket properly
    @Test
    public void testTicketCreated() {
        Ticket ticket = new Ticket("John Doe", "Issue with software",
                "This software is not working.");
        assertEquals("John Doe", ticket.getCustomerName());
        assertEquals("Issue with software", ticket.getSubject());
        assertEquals("This software is not working.", ticket.getBody());
        assertEquals(0,ticket.getNumberAttachments());
    }

    //Test to check if the attachments are pulling and inputting appropriately
    @Test
    public void testAttachment(){
        Ticket ticket = new Ticket("John Doe", "Issue with software",
                "This software is not working.");
        Attachment attachment1 = new Attachment();
        attachment1.setName("Screenshot1.png");
        Attachment attachment2 = new Attachment();
        attachment2.setName("Screenshot2.png");

        ticket.addAttachment(1, attachment1);
        ticket.addAttachment(2, attachment2);

        assertEquals(2, ticket.getNumberAttachments());
        assertEquals(attachment1, ticket.getAttachment(1));
        assertEquals(attachment2, ticket.getAttachment(2));
    }
}