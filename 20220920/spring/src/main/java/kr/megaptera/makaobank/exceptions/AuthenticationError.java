package kr.megaptera.makaobank.exceptions;

public class AuthenticationError extends RuntimeException {
    public AuthenticationError(Throwable cause) {
        super("Authentication error", cause);
    }
}
