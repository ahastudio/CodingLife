package com.ahastudio.user.exceptions;

public class LoginFailed extends RuntimeException {
    private final RuntimeException exception;

    public LoginFailed() {
        this.exception = null;
    }

    public LoginFailed(RuntimeException exception) {
        this.exception = exception;
    }
}
