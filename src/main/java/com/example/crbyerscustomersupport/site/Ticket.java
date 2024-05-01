// NAME: <Chelsie Byers>
// CLASS: Info 1541/Spring
// ASSIGNMENT: <Crbyers-Assignment 4>
// DATE: <3/27/2024/4/3/2024>
// RESOURCES: <I used the resource videos and lectures from this class and Professional Java for Web Applications>

//This program will allow a user to input a ticket to report any support issues with their accounts>


package com.example.crbyerscustomersupport.site;

import com.example.crbyerscustomersupport.entities.Attachment;

public class Ticket {
    private String subject;
    private String body;
    private Attachment attachment;

    // Constructors, getters, and setters
    public Ticket() {
    }

    public Ticket(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

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

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}

