package com.example.demo.utils.exceptions;

public class PersonWithIdNotFound extends RuntimeException {
    public PersonWithIdNotFound(String message) {
        super(message);
    }
}
