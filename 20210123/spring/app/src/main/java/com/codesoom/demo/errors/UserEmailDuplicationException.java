package com.codesoom.demo.errors;

public class UserEmailDuplicationException extends RuntimeException {
    public UserEmailDuplicationException(String email) {
        super("User email is alreay existed: " + email);
    }
}
