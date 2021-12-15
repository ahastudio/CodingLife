// Example #4 (부속)

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AmountPanel extends JPanel {
    private final Account account;

    AmountPanel(Account account) {
        this.account = account;

        this.setLayout(new GridLayout(2, 1));
        this.add(new JLabel(accountNumber()));
        this.add(new JLabel(amount()));
    }

    private String accountNumber() {
        return "계좌번호: " + account.getNumber();
    }

    private String amount() {
        return "잔액: " + account.getAmount() + "원";
    }
}
