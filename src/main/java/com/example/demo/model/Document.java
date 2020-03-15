package com.example.demo.model;


import java.time.LocalDate;
import java.util.UUID;

public class Document {

    private String id;
    private String type;
    private String number;
    private String countryCode;
    private LocalDate dateOfBirth;

    public Document(String type, String number, String countryCode, LocalDate dateOfBirth) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.number = number;
        this.countryCode = countryCode;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
