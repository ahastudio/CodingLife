// Example #4 (부속)

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String number;

    private long amount;

    private final List<String> transactions = new ArrayList<>();

    // 핵심 도메인 객체를 검증!
    public static void main(String[] args) {
        Account account = new Account("1234-5678", 1000);

        System.out.println("Amount: " + account.getAmount());
        // -> 1000 나와야 함.
        assert account.getAmount() == 1000;

        account.transfer(100);

        System.out.println("Amount: " + account.getAmount());
        // -> 900 나와야 함.
        assert account.getAmount() == 900;

        account.transfer(50);

        System.out.println("Amount: " + account.getAmount());
        // -> 850 나와야 함.
        assert account.getAmount() == 850;

        for (String transaction : account.transactions()) {
            System.out.println(transaction);
        }
        // -> 송금 100, 50 이렇게 나와야 함.
    }

    public Account(String number, long amount) {
        this.number = number;
        this.amount = amount;
    }

    // getter!
    public String getNumber() {
        return number;
    }

    // getter!
    public long getAmount() {
        return amount;
    }

    // 변형 getter! -> 이런 형태를 (약간) 권장합니다. => 더 권장하는 건 따로 있습니다.
    public List<String> transactions() {
        return new ArrayList<>(transactions);
    }

    // 변형 getter! -> 이런 형태를 권장합니다.
    public int transactionsSize() {
        return transactions.size();
    }

    public void transfer(long transferAmount) {
        amount -= transferAmount;

        transactions.add("송금: " + transferAmount + "원");
    }
}
