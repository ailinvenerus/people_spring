package com.example.demo.model;

import java.util.UUID;

public class Person {

    private String id;
    private String firstName;
    private String lastName;
    private Document document;

    public Person() {
    }

    public Person(String id, String firstName, String lastName, Document document) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
    }

    public Person(String firstName, String lastName, Document document) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
    }

    public Person(String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Document getDocument() {
        return document;
    }

    public String getId() {
        return id;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
