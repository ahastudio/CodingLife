package com.example.demo.exceptions;

public class EmailAlreadyTaken extends RuntimeException {
    public EmailAlreadyTaken(String email) {
        super("Email Has Already Been Taken: " + email);
    }
}
