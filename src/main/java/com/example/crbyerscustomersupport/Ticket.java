// NAME: <Chelsie Byers>
// CLASS: Info 1541/Spring
// ASSIGNMENT: <Crbyers-Assignment 4>
// DATE: <3/27/2024/4/3/2024>
// RESOURCES: <I used the resource videos and lectures from this class and Professional Java for Web Applications>

//This program will allow a user to input a ticket to report any support issues with their accounts>


package com.example.crbyerscustomersupport;


import java.util.HashMap;
import java.util.Map;

public class Ticket {
    //Setting up my variables
    private String customerName;
    private String subject;
    private String body;
    private Attachment attachment;
    private Map<Integer, Attachment> attachmentMap = new HashMap<>();

    //constructor
    public Ticket() {
        super();
    }

    //constructor
    public Ticket(String customerName, String subject, String body) {
        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
        this.attachment = attachment;
    }

    //creating my getters and setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName() {
        this.customerName = customerName;
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

    public void addAttachment (int index, Attachment attachment) {
        attachmentMap.put(index, attachment);
    }
    public int getNumberAttachments() {
        return attachmentMap.size();
    }
    public Attachment getAttachment(int index) {
        return attachmentMap.get(index);
    }

    public Map<Integer, Attachment> getAllAttachments() {
        return attachmentMap;
    }
    public boolean hasAttachment() {
        return Attachment.getName().length() > 0 && Attachment.getContents().length > 0;
    }

    //write the inputs to the ticket string
    @Override
    public String toString() {
        return "com.example.crbyerscustomersupport.Ticket{" + "customerName='" + customerName + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", attachmentsMap=" + attachmentMap +
                '}';
    }
}