package kr.megaptera.makaobank.exceptions;

public class LoginFailed extends RuntimeException {
    public LoginFailed() {
        super("Login failed");
    }
}
