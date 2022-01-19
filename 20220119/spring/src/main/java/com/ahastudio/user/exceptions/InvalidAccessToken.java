package com.ahastudio.user.exceptions;

import com.auth0.jwt.exceptions.SignatureVerificationException;

public class InvalidAccessToken extends RuntimeException {
    private final RuntimeException exception;

    public InvalidAccessToken() {
        this.exception = null;
    }

    public InvalidAccessToken(SignatureVerificationException exception) {
        this.exception = exception;
    }
}
