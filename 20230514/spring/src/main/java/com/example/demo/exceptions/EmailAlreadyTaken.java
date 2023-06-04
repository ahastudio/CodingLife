package com.example.demo.exceptions;

public class EmailAlreadyTaken extends RuntimeException {
    public EmailAlreadyTaken(String email) {
        super("Email has already been taken: " + email);
    }
}
