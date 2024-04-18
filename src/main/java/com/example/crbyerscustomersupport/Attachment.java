// NAME: <Chelsie Byers>
// CLASS: Info 1541/Spring
// ASSIGNMENT: <Crbyers-Assignment 4>
// DATE: <3/27/2024/4/3/2024>
// RESOURCES: <I used the resource videos and lectures from this class and Professional Java for Web Applications>

//This program will allow a user to input a ticket to report any support issues with their accounts>

package com.example.crbyerscustomersupport;

public class Attachment {
    //set up my variables

    private static String name;

    private static byte[] contents;

    //set up my getters and setters
    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static byte[] getContents() {
        return contents;
    }
    //set up my java bean contents

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}