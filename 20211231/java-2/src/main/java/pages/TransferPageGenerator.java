package pages;

import models.Account;

public class TransferPageGenerator extends PageGenerator {
    private Account account;

    public TransferPageGenerator(Account account) {
        super();

        this.account = account;
    }

    @Override
    public String content() {
        return "<p>잔액: " + account.amount() + "원</p>\n" +
                "<form method=\"POST\">\n" +
                "<p>보낼 계좌: <input type=\"text\" name=\"to\" /></p>\n" +
                "<p>보낼 금액: <input type=\"number\" name=\"amount\" /></p>\n" +
                "<p><button type=\"submit\">확인</button></p>" +
                "</form>";
    }
}
