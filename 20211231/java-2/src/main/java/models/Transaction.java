package models;

public class Transaction {
    private final Account sender;
    private final Account receiver;
    private final long amount;

    public Transaction(Account sender, Account receiver, long amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Account sender() {
        return sender;
    }

    public Account receiver() {
        return receiver;
    }

    public long amount() {
        return amount;
    }

    public String command(Account account) {
        if (account.equals(sender)) {
            return "송금";
        }
        return "입금";
    }

    public Account other(Account account) {
        if (account.equals(sender)) {
            return receiver;
        }
        return sender;
    }
}
