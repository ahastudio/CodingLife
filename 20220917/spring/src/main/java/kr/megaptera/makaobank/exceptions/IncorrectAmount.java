package kr.megaptera.makaobank.exceptions;

public class IncorrectAmount extends RuntimeException {
    public IncorrectAmount(Long amount) {
        super("Incorrect amount (amount: " + amount + ")");
    }
}
