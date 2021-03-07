package com.codesoom.demo.errors;

public class LoginFailException extends RuntimeException{
    public LoginFailException(String email) {
        super("Login fail - email: " + email);
    }
}
