package com.example.crbyerscustomersupport;// NAME: <Chelsie Byers>
// CLASS: Info 1541/Spring
// ASSIGNMENT: <Crbyers-Assignment 4>
// DATE: <3/27/2024/4/3/2024>
// RESOURCES: <I used the resource videos and lectures from this class and

//This program will allow a user to input a ticket to report any support issues with their accounts>
// and add attachments to their support ticket>

public class Attachment {
    //set up my variables

    private String name;

    private byte[] contents;

    //set up my getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContents() {
        return contents;
    }
    //set up my java bean contents

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}
