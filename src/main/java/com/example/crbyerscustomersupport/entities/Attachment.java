// NAME: <Chelsie Byers>
// CLASS: Info 1541/Spring
// ASSIGNMENT: <Crbyers-Assignment 4>
// DATE: <3/27/2024/4/3/2024>
// RESOURCES: <I used the resource videos and lectures from this class and Professional Java for Web Applications>

//This program will allow a user to input a ticket to report any support issues with their accounts>

package com.example.crbyerscustomersupport.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "attachments")

public class Attachment implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private long ticketId;
    private String name;
    private byte[] contents;

    // Constructors, getters, and setters

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public Attachment() {
    }

    public Attachment(String name, byte[] contents) {
        this.name = name;
        this.contents = contents;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}

